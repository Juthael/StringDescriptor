package model.copycatModel.synTreeGrammar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import exceptions.OrderedSetsGenerationException;
import model.copycatModel.ordSetGrammar.GroupsOS;
import model.copycatModel.ordSetGrammar.RelationOS;
import model.copycatModel.ordSetGrammar.RelationsOS;
import model.generalModel.IElement;
import model.orderedSetModel.IOrderedSet;
import model.synTreeModel.ISynTreeElement;
import settings.Settings;

public class Relations extends RelationsOrLetter implements ISynTreeElement, Cloneable {

	private static final String DESCRIPTOR_NAME = "relations";
	private Groups groups;
	private HowManyDimensions dimensionHM;
	private HowManyRelations relationHM;
	
	public Relations(Groups groups, HowManyDimensions dimensionX, 
			HowManyRelations relationX) {
		this.groups = groups;
		this.dimensionHM = dimensionX;
		this.relationHM = relationX;
	}
	
	@Override
	protected Relations clone() throws CloneNotSupportedException {
		Relations cloneRelations;
		Groups cloneGroups = groups.clone();
		HowManyDimensions cloneDimensionX = dimensionHM.clone();
		HowManyRelations cloneRelationX = relationHM.clone();
		cloneRelations = new Relations(cloneGroups, cloneDimensionX, cloneRelationX);
		return cloneRelations;
	}
	
	@Override
	public String getDescriptorName() {
		return DESCRIPTOR_NAME;
	}	

	@Override
	public List<IElement> getListOfComponents(){
		List<IElement> componentDescriptors = new ArrayList<IElement>(
				Arrays.asList(dimensionHM, relationHM, groups));
		return componentDescriptors;
	}
	
	public void setGroupsAsCodingDescriptors() {
		groups.setIsCodingByDecomposition(Settings.THIS_IS_A_CODING_ELEMENT);
	}
	
	@Override
	public IOrderedSet upgradeAsTheElementOfAnOrderedSet(Map<List<String>, Integer> listOfPropertiesToIndex) 
			throws OrderedSetsGenerationException {
		IOrderedSet relationsOS;
		List<String> listOfPropertiesWithPath = getListOfPropertiesWithPath();
		Integer relationsIndex = listOfPropertiesToIndex.get(listOfPropertiesWithPath);
		String relationsID = getDescriptorName().concat(relationsIndex.toString());
		GroupsOS groupsOS = (GroupsOS) groups.upgradeAsTheElementOfAnOrderedSet(listOfPropertiesToIndex);
		List<RelationOS> listOfRelationOS = new ArrayList<RelationOS>();
		if (relationHM.getDescriptorName().contains("relationX")) {
			for (IElement element : relationHM.getListOfComponents()) {
				Relation relation = (Relation) element;
				listOfRelationOS.add((RelationOS) relation.upgradeAsTheElementOfAnOrderedSet(listOfPropertiesToIndex));
			}
		}
		else if (relationHM.getDescriptorName().equals("relation")) {
			Relation relation = (Relation) relationHM;
			listOfRelationOS.add((RelationOS) relation.upgradeAsTheElementOfAnOrderedSet(listOfPropertiesToIndex));
		}
		else throw new OrderedSetsGenerationException("Relations.upgradeAsTheElementOfAnOrderedSet : "
				+ "relationHM.descriptorName was unexpected (" + relationHM.getDescriptorName() + ").");
		relationsOS = new RelationsOS(relationsID, listOfRelationOS, groupsOS);
		return relationsOS;		
	}	
}
