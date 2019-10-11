package model.synTreeModel.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import exceptions.OrderedSetsGenerationException;
import exceptions.VerbalizationException;
import model.generalModel.IElement;
import model.orderedSetModel.IOrderedSet;
import model.orderedSetModel.impl.GenericOmegaOS;
import model.orderedSetModel.impl.OmegaOS;
import model.synTreeModel.IStartElementST;
import model.synTreeModel.IStartGrammElementST;
import model.synTreeModel.ISyntacticTree;
import settings.Settings;
import verbalization.dataEncoding.encoders.IVerbalizer;
import verbalization.dataEncoding.encoders.impl.Verbalizer;

public class GenericStartElementST extends SyntacticTree implements IStartElementST {

	private static final String NAME = Settings.TREE_TOP_ELEMENT_GENERIC_NAME;
	private final String verbalDescription;
	List<IStartGrammElementST> listOfComponents = new ArrayList<IStartGrammElementST>();
	
	public GenericStartElementST(IStartGrammElementST subOmega1, IStartGrammElementST subOmega2) throws VerbalizationException {
		listOfComponents.add(subOmega1);
		listOfComponents.add(subOmega2);
		verbalDescription = getVerbalDescription();
	}

	public GenericStartElementST(boolean isCodingElement, IStartGrammElementST subOmega1, IStartGrammElementST subOmega2) 
			throws VerbalizationException {
		super(isCodingElement);
		listOfComponents.add(subOmega1);
		listOfComponents.add(subOmega2);
		verbalDescription = getVerbalDescription();
	}

	@Override
	public OmegaOS upgradeAsTheSupremumOfAnOrderedSet() throws OrderedSetsGenerationException, VerbalizationException {
		OmegaOS omega;
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
		List<IOrderedSet> listOfComponentsOS = new ArrayList<IOrderedSet>();
		for (IStartGrammElementST component : listOfComponents) {
			listOfComponentsOS.add(component.upgradeAsTheElementOfAnOrderedSet(listOfPropertiesToIndex));
		}
		omega = new GenericOmegaOS(listOfComponentsOS, verbalDescription);
		return omega;		
	}

	@Override
	public String getVerbalDescription() throws VerbalizationException {
		String verbalDescription;
		StringBuilder stringBuilder = new StringBuilder();
		int componentIndex = 1;
		String newLine = System.getProperty("line.separator");
		IVerbalizer verbalizer;
		for (IStartGrammElementST startElement : listOfComponents) {
			stringBuilder.append("STRING N." + componentIndex + " :" + newLine);
			verbalizer = new Verbalizer(startElement);
			stringBuilder.append(verbalizer.getTranslationInNaturalLanguage());
			stringBuilder.append(newLine);
			componentIndex++;
		}
		verbalDescription = stringBuilder.toString();
		return verbalDescription;
	}
	
	@Override
	public List<IElement> getListOfComponents(){
		List<IElement> listOfComponents = new ArrayList<IElement>(this.listOfComponents);
		return listOfComponents;
	}

	@Override
	public IOrderedSet upgradeAsTheElementOfAnOrderedSet(Map<List<String>, Integer> listOfPropertiesToIndex)
			throws OrderedSetsGenerationException {
		IOrderedSet omegaOS;
		List<IOrderedSet> listOfComponentsOS = new ArrayList<IOrderedSet>();
		for (IStartGrammElementST component : listOfComponents) {
			listOfComponentsOS.add(component.upgradeAsTheElementOfAnOrderedSet(listOfPropertiesToIndex));
		}
		omegaOS = new GenericOmegaOS(listOfComponentsOS, verbalDescription);
		return omegaOS;			
	}

	@Override
	public String getDescriptorName() {
		return NAME;
	}

}
