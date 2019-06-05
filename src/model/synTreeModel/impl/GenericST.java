package model.synTreeModel.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import exceptions.OrderedSetsGenerationException;
import exceptions.SynTreeGenerationException;
import model.generalModel.IElement;
import model.orderedSetModel.IOrderedSet;
import model.synTreeModel.IFrame;
import model.synTreeModel.ISyntacticTree;
import settings.Settings;

public class GenericST extends SyntacticTree implements ISyntacticTree, IFrame {

	private final String descriptorName;
	private List<ISyntacticTree> listOfSynTreeComponents= new ArrayList<ISyntacticTree>();
	
	public GenericST(List<String> treeListOfMaxChains, Map<String, String> orderedSetIDToDescriptorName) 
			throws SynTreeGenerationException {
		Map<String, List<String>> componentToComponentChains = new HashMap<String, List<String>>();
		if (!treeListOfMaxChains.isEmpty()) {
			descriptorName = setDescriptorName(treeListOfMaxChains, orderedSetIDToDescriptorName);
			int firstSlashIndex = treeListOfMaxChains.get(0).indexOf(Settings.PATH_SEPARATOR);
			if (firstSlashIndex != -1) {
				if (firstSlashIndex < (treeListOfMaxChains.get(0).length() - 1)) {
					for (String maxChain : treeListOfMaxChains) {
						String componentChain = maxChain.substring(firstSlashIndex + 1);
						int secondSlashIndex = componentChain.indexOf(Settings.PATH_SEPARATOR);
						if (secondSlashIndex != -1) {
							String component = componentChain.substring(0,secondSlashIndex);
							if (componentToComponentChains.containsKey(component)) {
								componentToComponentChains.get(component).add(componentChain);
							}
							else {
								List<String> componentListOfChains = new ArrayList<String>();
								componentListOfChains.add(componentChain);
								componentToComponentChains.put(component, componentListOfChains);
							}							
						}
						else throw new SynTreeGenerationException("SynTreeGenericElement : path separator missing");
					}
				}
			}
			else throw new SynTreeGenerationException("SynTreeGenericElement : inconsistant parameter.");	
			List<String> orderedListOfComponentsIDs = new ArrayList<String>(componentToComponentChains.keySet());
			Collections.sort(orderedListOfComponentsIDs);
			for (String componentID : orderedListOfComponentsIDs) {
				List<String> listOfComponentMaxChains = componentToComponentChains.get(componentID);
				GenericST component;
				if ((listOfComponentMaxChains.size() == 1) 
						&& (listOfComponentMaxChains.get(0).equals(componentID.concat(Settings.PATH_SEPARATOR)))) {
					component = 
							new MinimalST(
									componentToComponentChains.get(componentID), orderedSetIDToDescriptorName);
				}
				else component = 
						new GenericST(componentToComponentChains.get(componentID), orderedSetIDToDescriptorName);
				listOfSynTreeComponents.add(component);
			}
		}
		else {
			throw new SynTreeGenerationException("SynTreeGenericElement : empty parameter.");
		}
		setHashCode();
	}
	
	public GenericST(String value) {
		this.descriptorName = value;
		setHashCode();
	}		
	
	public GenericST(GenericST genericElement) throws CloneNotSupportedException {
		this.descriptorName = genericElement.getDescriptorName();
		for (IElement element : genericElement.getListOfComponents()) {
			GenericST synTreeElement = (GenericST) element;
			listOfSynTreeComponents.add(synTreeElement.clone());
		}
		setHashCode();
	}	
	
	@Override
	public GenericST clone() throws CloneNotSupportedException {
		return new GenericST(this);
	}
	
	@Override
	public List<IElement> getListOfComponents(){
		List<IElement> listOfComponents = new ArrayList<IElement>();
		for (ISyntacticTree syntacticTree : listOfSynTreeComponents) {
			listOfComponents.add(syntacticTree);
		}
		return listOfComponents;
	}	
	
	@Override
	public List<String> getListOfPropertiesWithPath(){
		List<String> listOfPropertiesWithPath = new ArrayList<String>();
		if (!listOfSynTreeComponents.isEmpty()) {
			List<IElement> listOfComponents = getListOfComponents();
			for (IElement componentDescriptor : listOfComponents) {
				boolean componentIsIdiosyncratic;
				if (componentDescriptor.getIsMinimal()) {
					if (this.getDescriptorName().equals(Settings.ABSTRACT_TREE_NAME)) {
						componentIsIdiosyncratic = 
								componentDescriptor.getDescriptorName().equals(Settings.ABSTRACTABLE_TREE_NAME);
					}
					else componentIsIdiosyncratic = this.getDescriptorName().equals(componentDescriptor.getDescriptorName());
				}
				else componentIsIdiosyncratic = false;
				if (componentIsIdiosyncratic == false) {
					List<String> listOfComponentPropertiesWithPath = componentDescriptor.getListOfPropertiesWithPath();
					for (String propertyWithPath : listOfComponentPropertiesWithPath){
						String propertyWithUpdatedPath = 
								descriptorName.concat(Settings.PATH_SEPARATOR + propertyWithPath);
						listOfPropertiesWithPath.add(propertyWithUpdatedPath);					
					}	
				}
			}
		}
		else {
			listOfPropertiesWithPath.add(descriptorName);
		}
		return listOfPropertiesWithPath;
	}	

	@Override
	public String getDescriptorName() {
		return descriptorName;
	}
	
	@Override
	public void proceedFrameAbstraction() {
	}	
	
	@Override
	public IOrderedSet upgradeAsTheElementOfAnOrderedSet(Map<List<String>, Integer> listOfPropertiesToIndex)
			throws OrderedSetsGenerationException {
		return upgradeAsTheGenericElementOfAnOrderedSet(listOfPropertiesToIndex);
	}	
	
	private String setDescriptorName(List<String> treeListOfMaxChains, Map<String, String> orderedSetIDToDescriptorName) 
			throws SynTreeGenerationException {
		String descriptorName;
		int firstSlashIndex = treeListOfMaxChains.get(0).indexOf(Settings.PATH_SEPARATOR);
		String treeStartElement = treeListOfMaxChains.get(0).substring(0, firstSlashIndex);
		if (orderedSetIDToDescriptorName.containsKey(treeStartElement))
			descriptorName = orderedSetIDToDescriptorName.get(treeStartElement);
		else if (treeStartElement.equals(Settings.ABSTRACT_TREE_NAME))
			descriptorName = Settings.ABSTRACT_TREE_NAME;
		else throw new SynTreeGenerationException("SynTreeGenericElement() : couldn't get a name from ID '" 
			+ treeStartElement + "'." );
		return descriptorName;
	}

}
