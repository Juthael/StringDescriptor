package model.copycatModel.synTreeGrammar;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import exceptions.OrderedSetsGenerationException;
import exceptions.SynTreeGenerationException;
import model.generalModel.IElement;
import model.orderedSetModel.IOrderedSet;
import model.synTreeModel.IGrammaticalST;
import model.synTreeModel.impl.GrammaticalST;
import settings.Settings;

public class RelationX extends GrammaticalST implements IOneOrManyRelations, Cloneable {

	private static final String DESCRIPTOR_PARTIAL_NAME = "relationX";
	private List<Relation> listOfRelations;
	
	public RelationX(List<Relation> listOfRelations) throws SynTreeGenerationException {
		if (listOfRelations.size() > 1 && listOfRelations.size() <= Settings.MAX_NB_OF_RELATIONS)
			this.listOfRelations = listOfRelations;
		else throw new SynTreeGenerationException("RelationX() : "
				+ "illegal number of relations (" + listOfRelations.size() + ").");
		setHashCode();
	}

	@Override
	public RelationX clone() throws CloneNotSupportedException {
		RelationX cloneRelationX;
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
		List<IGrammaticalST> listOfRelevantComponents = buildListOfRelevantComponentsForRelationBuilding();
		for (IGrammaticalST componentDescriptor : listOfRelevantComponents) {
			listOfRelevantPropertiesWithPath.addAll(componentDescriptor.getListOfRelevantPropertiesWithPath());
		}
		return listOfRelevantPropertiesWithPath;
	}	
	
	@Override
	public IOrderedSet upgradeAsTheElementOfAnOrderedSet(Map<List<String>, Integer> listOfPropertiesToIndex) 
			throws OrderedSetsGenerationException {
		throw new OrderedSetsGenerationException("RelationX can't be upgraded.");
	}		

}
