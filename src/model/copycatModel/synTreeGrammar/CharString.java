package model.copycatModel.synTreeGrammar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import exceptions.OrderedSetsGenerationException;
import exceptions.SynTreeGenerationException;
import exceptions.VerbalizationException;
import model.copycatModel.ordSetGrammar.CharStringOS;
import model.copycatModel.ordSetGrammar.CharStringOmega;
import model.copycatModel.ordSetGrammar.DirectionOS;
import model.copycatModel.ordSetGrammar.ComponentsOS;
import model.copycatModel.ordSetGrammar.StructureOS;
import model.generalModel.IElement;
import model.orderedSetModel.IOrderedSet;
import model.orderedSetModel.impl.OmegaOS;
import model.synTreeModel.ISyntacticTree;
import model.synTreeModel.IStartElementST;
import model.synTreeModel.impl.GrammaticalST;
import settings.Settings;
import verbalization.dataEncoding.encoders.IVerbalizer;
import verbalization.dataEncoding.encoders.impl.Verbalizer;

public class CharString extends GrammaticalST implements IStartElementST, Cloneable {
	
	private final static String DESCRIPTOR_NAME = "charString";
	protected Direction direction;
	protected Structure structure;
	protected Components components;

	public CharString(Direction direction, Structure structure, Components components) {
		this.direction = direction;
		this.structure = structure;
		this.components = components;
		setHashCode();
	}
	
	public CharString(CharString charString) {
		List<IElement> listOfComponents = charString.getListOfComponents();
		this.direction = (Direction) listOfComponents.get(0);
		this.structure = (Structure) listOfComponents.get(1);
		this.components = (Components) listOfComponents.get(2);
		hashCode = getListOfPropertiesWithPath().size();
	}
	
	@Override 
	protected CharString clone() throws CloneNotSupportedException {
		Direction cloneDirection = direction.clone();
		Structure cloneStructuration = structure.clone();
		Components cloneComponents = components.clone();
		CharString cloneCharString = new CharString(cloneDirection, cloneStructuration, cloneComponents);
		return cloneCharString;
	}
	
	@Override
	public String getDescriptorName() {
		return DESCRIPTOR_NAME;
	}	
	
	@Override
	public List<IElement> getListOfComponents(){
		List<IElement> componentDescriptors = new ArrayList<IElement>(
				Arrays.asList(direction, structure, components));
		return componentDescriptors;
	}

	
	@Override
	public IOrderedSet upgradeAsTheElementOfAnOrderedSet(Map<List<String>, Integer> listOfPropertiesToIndex) 
			throws OrderedSetsGenerationException {
		IOrderedSet charStringOS;
		List<String> listOfPropertiesWithPath = getListOfPropertiesWithPath();
		Integer charStringIndex = listOfPropertiesToIndex.get(listOfPropertiesWithPath);
		String charStringID = getDescriptorName().concat(charStringIndex.toString());
		DirectionOS directionOS = (DirectionOS) direction.upgradeAsTheElementOfAnOrderedSet(listOfPropertiesToIndex);
		StructureOS structureOS = (StructureOS) structure.upgradeAsTheElementOfAnOrderedSet(listOfPropertiesToIndex);
		ComponentsOS componentsOS = (ComponentsOS) components.upgradeAsTheElementOfAnOrderedSet(listOfPropertiesToIndex);
		charStringOS = new CharStringOS(charStringID, directionOS, structureOS, componentsOS);
		return charStringOS;
	}
	
	@Override
	public OmegaOS upgradeAsTheSupremumOfAnOrderedSet() 
			throws OrderedSetsGenerationException, VerbalizationException {
		OmegaOS charStringOmega;
		Integer charStringIndex = 1;
		Map<List<String>, Integer> listOfPropertiesToIndex = new HashMap<List<String>, Integer>();
		Settings.orderedSetIndex = 1;
		for (IElement element : getListOfComponents()) {
			Set<List<String>> setOfPropertyLists = element.getSetOfAllPropertyListsAccessibleFromThisDescriptor();
			for (List<String> listOfPropertiesWithPath : setOfPropertyLists) {
				if (!listOfPropertiesToIndex.containsKey(listOfPropertiesWithPath)) {
					listOfPropertiesToIndex.put(listOfPropertiesWithPath, Settings.orderedSetIndex);
					Settings.orderedSetIndex++;
				}
			}
		}
		String charStringID = getDescriptorName().concat(charStringIndex.toString());
		DirectionOS directionOS = (DirectionOS) direction.upgradeAsTheElementOfAnOrderedSet(listOfPropertiesToIndex);
		StructureOS structureOS = (StructureOS) structure.upgradeAsTheElementOfAnOrderedSet(listOfPropertiesToIndex);
		ComponentsOS componentsOS = (ComponentsOS) components.upgradeAsTheElementOfAnOrderedSet(listOfPropertiesToIndex);
		String verbalDescription = getVerbalDescription();
		charStringOmega = new CharStringOmega(charStringID, directionOS, structureOS, componentsOS, verbalDescription);
		return charStringOmega;
	}

	@Override
	public String getVerbalDescription() throws VerbalizationException {
		String verbalDescription;
		IVerbalizer verbalizer = new Verbalizer(this);
		verbalDescription = verbalizer.getTranslationInNaturalLanguage();
		return verbalDescription;
	}

	@Override
	public ISyntacticTree getTreeWithAbstractFrames() 
			throws VerbalizationException, CloneNotSupportedException, SynTreeGenerationException, 
			OrderedSetsGenerationException {
		ISyntacticTree treeWithAbstractFrames;
		String verbalDescription = this.getVerbalDescription();
		CharString cloneCharString = this.clone();
		cloneCharString.proceedFrameAbstraction();
		treeWithAbstractFrames = new CharStringWithAbstractFrames(cloneCharString, verbalDescription);
		return treeWithAbstractFrames;
	}
	
}
