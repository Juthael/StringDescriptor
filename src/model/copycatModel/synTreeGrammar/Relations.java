package model.copycatModel.synTreeGrammar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import model.copycatModel.ordSetGrammar.GroupsOS;
import model.copycatModel.ordSetGrammar.HowManyDimensionsOS;
import model.copycatModel.ordSetGrammar.HowManyRelationsOS;
import model.copycatModel.ordSetGrammar.RelationsOS;
import model.generalModel.IElement;
import model.orderedSetModel.ILowerSetElement;
import model.synTreeModel.ISynTreeElement;

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
	
	@Override
	public ILowerSetElement upgradeAsTheElementOfAnOrderedSet(Map<List<String>, Integer> listOfPropertiesToIndex) {
		ILowerSetElement relationsOS;
		List<String> listOfPropertiesWithPath = getListOfPropertiesWithPath();
		Integer relationsIndex = listOfPropertiesToIndex.get(listOfPropertiesWithPath);
		String relationsID = getDescriptorName().concat(relationsIndex.toString());
		GroupsOS groupsOS = (GroupsOS) groups.upgradeAsTheElementOfAnOrderedSet(listOfPropertiesToIndex);
		HowManyDimensionsOS dimensionHMOS = 
				(HowManyDimensionsOS) dimensionHM.upgradeAsTheElementOfAnOrderedSet(listOfPropertiesToIndex);
		HowManyRelationsOS relationHMOS = 
				(HowManyRelationsOS) relationHM.upgradeAsTheElementOfAnOrderedSet(listOfPropertiesToIndex);
		relationsOS = new RelationsOS(relationsID, dimensionHMOS, relationHMOS, groupsOS);
		return relationsOS;		
	}	
}
