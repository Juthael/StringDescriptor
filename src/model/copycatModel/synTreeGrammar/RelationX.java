package model.copycatModel.synTreeGrammar;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import exceptions.SynTreeGenerationException;
import model.copycatModel.ordSetGrammar.RelationOS;
import model.copycatModel.ordSetGrammar.RelationXOS;
import model.generalModel.IElement;
import model.orderedSetModel.ISetElement;
import model.synTreeModel.ISynTreeElement;
import model.synTreeModel.impl.SynTreeElementImpl;
import settings.Settings;

public class RelationX extends HowManyRelations implements ISynTreeElement, Cloneable {

	private static final String DESCRIPTOR_PARTIAL_NAME = "relationX";
	private List<Relation> listOfRelations;
	
	public RelationX(List<Relation> listOfRelations) throws SynTreeGenerationException {
		if (listOfRelations.size() > 1 && listOfRelations.size() <= Settings.MAX_NB_OF_RELATIONS)
			this.listOfRelations = listOfRelations;
		else throw new SynTreeGenerationException("RelationX() : illegal number of relations (" + listOfRelations.size() + ").");
	}

	@Override
	protected HowManyRelations clone() throws CloneNotSupportedException {
		HowManyRelations cloneRelationX;
		List<Relation> cloneListOfRelations = new ArrayList<Relation>();
		for (Relation relation : listOfRelations)
			cloneListOfRelations.add(relation.clone());
		try {
			cloneRelationX = new RelationX(cloneListOfRelations);
		}
		catch (SynTreeGenerationException e) {
			throw new CloneNotSupportedException("RelationX.clone() : " + e.getMessage());
		}
		return cloneRelationX;
	}	
	
	@Override
	public String getDescriptorName() {
		String name = DESCRIPTOR_PARTIAL_NAME.concat(Integer.toString(listOfRelations.size()));
		return name;
	}	
	
	@Override
	public List<IElement> getListOfComponents() {
		List<IElement> listOfComponents = new ArrayList<IElement>();
		for (Relation relation : listOfRelations)
			listOfComponents.add(relation);
		return listOfComponents;
	}
	
	@Override
	public List<String> getListOfRelevantPropertiesWithPath(){
		List<String> listOfRelevantPropertiesWithPath = new ArrayList<String>();
		List<SynTreeElementImpl> listOfRelevantComponents = buildListOfRelevantComponentsForRelationBuilding();
		for (SynTreeElementImpl componentDescriptor : listOfRelevantComponents) {
			listOfRelevantPropertiesWithPath.addAll(componentDescriptor.getListOfRelevantPropertiesWithPath());
		}
		return listOfRelevantPropertiesWithPath;
	}	
	
	@Override
	public ISetElement upgradeAsTheElementOfAnOrderedSet(Map<List<String>, Integer> listOfPropertiesToIndex) {
		ISetElement relationXOS;
		List<String> listOfPropertiesWithPath = getListOfPropertiesWithPath();
		Integer relationXIndex = listOfPropertiesToIndex.get(listOfPropertiesWithPath);
		String relationXID = getDescriptorName().concat(relationXIndex.toString());
		List<RelationOS> listOfRelationsOS = new ArrayList<RelationOS>();
		for (Relation relation : listOfRelations) {
			RelationOS relationOS = (RelationOS) relation.upgradeAsTheElementOfAnOrderedSet(listOfPropertiesToIndex);
			listOfRelationsOS.add(relationOS);
		}
		relationXOS = new RelationXOS(relationXID, listOfRelationsOS);
		return relationXOS;		
	}		

}
