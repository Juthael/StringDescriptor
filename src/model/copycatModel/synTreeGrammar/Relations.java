package model.copycatModel.synTreeGrammar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import exceptions.OrderedSetsGenerationException;
import model.copycatModel.ordSetGrammar.RelationOS;
import model.copycatModel.ordSetGrammar.RelationsOS;
import model.generalModel.IElement;
import model.orderedSetModel.IOrderedSet;
import model.synTreeModel.IAbstractionTriggerST;
import model.synTreeModel.impl.AbstractionTriggerST;
import settings.Settings;

public class Relations extends AbstractionTriggerST implements IRelationsOrLetter, IAbstractionTriggerST, Cloneable {

	private static final String DESCRIPTOR_NAME = "relations";
	private Components components;
	private IOneOrManyDimensions dimensionHM;
	private IOneOrManyRelations relationHM;
	
	public Relations(Components components, IOneOrManyDimensions dimensionX, 
			IOneOrManyRelations relationX) {
		this.components = components;
		this.dimensionHM = dimensionX;
		this.relationHM = relationX;
		setHashCode();
	}
	
	@Override
	protected Relations clone() throws CloneNotSupportedException {
		Relations cloneRelations;
		Components cloneComponents = components.clone();
		IOneOrManyDimensions cloneDimensionX = dimensionHM.clone();
		IOneOrManyRelations cloneRelationX = relationHM.clone();
		cloneRelations = new Relations(cloneComponents, cloneDimensionX, cloneRelationX);
		return cloneRelations;
	}
	
	@Override
	public String getDescriptorName() {
		return DESCRIPTOR_NAME;
	}	

	@Override
	public List<IElement> getListOfComponents(){
		List<IElement> componentDescriptors = new ArrayList<IElement>(
				Arrays.asList(dimensionHM, relationHM, components));
		return componentDescriptors;
	}
	
	public void setComponentsAsCodingDescriptors() {
		components.setIsCodingElement(Settings.THIS_IS_A_CODING_ELEMENT);
	}	
	
	@Override
	public IOrderedSet upgradeAsTheElementOfAnOrderedSet(Map<List<String>, Integer> listOfPropertiesToIndex) 
			throws OrderedSetsGenerationException {
		IOrderedSet relationsOS;
		List<String> listOfPropertiesWithPath = getListOfPropertiesWithPath();
		Integer relationsIndex = listOfPropertiesToIndex.get(listOfPropertiesWithPath);
		String relationsID = getDescriptorName().concat(relationsIndex.toString());
		IOrderedSet componentsOS = components.upgradeAsTheElementOfAnOrderedSet(listOfPropertiesToIndex);
		List<RelationOS> listOfRelationOS = new ArrayList<RelationOS>();
		if (relationHM.getDescriptorName().contains("relationX")) {
			for (IElement element : relationHM.getListOfComponents()) {
				Relation relation = (Relation) element;
				if (relation.getThisRelationIsUpgradable() == true)
					listOfRelationOS.add((RelationOS) relation.upgradeAsTheElementOfAnOrderedSet(listOfPropertiesToIndex));
			}
		}
		else if (relationHM.getDescriptorName().equals("relation")) {
			Relation relation = (Relation) relationHM;
			if (relation.getThisRelationIsUpgradable() == true)
				listOfRelationOS.add((RelationOS) relation.upgradeAsTheElementOfAnOrderedSet(listOfPropertiesToIndex));
		}
		else throw new OrderedSetsGenerationException("Relations.upgradeAsTheElementOfAnOrderedSet : "
				+ "relationHM.descriptorName was unexpected (" + relationHM.getDescriptorName() + ").");
		relationsOS = new RelationsOS(relationsID, listOfRelationOS, componentsOS);
		return relationsOS;		
	}	
}
