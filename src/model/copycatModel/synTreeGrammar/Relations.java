package model.copycatModel.synTreeGrammar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import exceptions.OrderedSetsGenerationException;
import exceptions.SynTreeGenerationException;
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
			IOneOrManyRelations relationX) throws SynTreeGenerationException, CloneNotSupportedException {
		this.components = components;
		this.dimensionHM = dimensionX;
		this.relationHM = relationX;
		setHashCode();
		if (Settings.PREVENT_FRAME_ABSTRACTION_FOR_SIMPLE_ENUM) {
			boolean componentsAreAbstractable = checkIfRelationsContainsSequence();
			if (componentsAreAbstractable == false)
				components.preventFrameAbstraction();
		}
	}
	
	@Override
	protected Relations clone() throws CloneNotSupportedException {
		Relations cloneRelations;
		Components cloneComponents = components.clone();
		IOneOrManyDimensions cloneDimensionX = dimensionHM.clone();
		IOneOrManyRelations cloneRelationX = relationHM.clone();
		try {
			cloneRelations = new Relations(cloneComponents, cloneDimensionX, cloneRelationX);
		}
		catch (SynTreeGenerationException unexpected) {
			throw new CloneNotSupportedException("Relations.clone() : SynTreeGenerationException catched.");
		}
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
	
	private boolean checkIfRelationsContainsSequence() throws SynTreeGenerationException {
		boolean relationsContainsSequence = false;
		if (relationHM.getDescriptorName().equals("relation")) {
			List<IElement> listOfRelationComponents = relationHM.getListOfComponents();
			for (IElement element : listOfRelationComponents) {
				if (element.getDescriptorName().contentEquals("sequence"))
					relationsContainsSequence = true;
			}
		}
		else if (relationHM.getDescriptorName().contains("relationX")) {
			RelationX manyRelations = (RelationX) relationHM;
			List<IElement> listOfRelations = manyRelations.getListOfComponents();
			boolean oneRelationAtLeastContainsNoSequence = false;
			int relationIndex = 0;
			while (oneRelationAtLeastContainsNoSequence == false && relationIndex < listOfRelations.size()) {
				IElement relation = listOfRelations.get(relationIndex);
				boolean thisRelationContainsNoSequence = true;
				for (IElement relationElement : relation.getListOfComponents()) {
					if (relationElement.getDescriptorName().equals("sequence"))
						thisRelationContainsNoSequence = false;
				}
				oneRelationAtLeastContainsNoSequence = thisRelationContainsNoSequence;
				relationIndex++;
			}
			relationsContainsSequence = !oneRelationAtLeastContainsNoSequence;
		}
		else throw new SynTreeGenerationException("Relations.checkIfRelationsContainsSequence : relationHM type unrecognized");
		return relationsContainsSequence;
	}
}
