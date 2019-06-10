package model.synTreeModel.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import exceptions.OrderedSetsGenerationException;
import exceptions.SynTreeGenerationException;
import model.generalModel.IElement;
import model.generalModel.impl.Element;
import model.orderedSetModel.IOrderedSet;
import model.orderedSetModel.impl.GenericOS;
import model.orderedSetModel.impl.MinimalOS;
import model.synTreeModel.IFrame;
import model.synTreeModel.ISyntacticTree;

public abstract class SyntacticTree extends Element implements ISyntacticTree {

	protected int hashCode = -1;
	private boolean isWaitingForAbstraction = false;
	
	public SyntacticTree() {
	}

	public SyntacticTree(boolean isCodingElement) {
		super(isCodingElement);
	}	
	
	@Override
	public boolean getIsMinimal() {
		return false;
	}	

	@Override
	abstract public IOrderedSet upgradeAsTheElementOfAnOrderedSet(Map<List<String>, Integer> listOfPropertiesToIndex) 
			throws OrderedSetsGenerationException;
	
	@Override
	public IOrderedSet upgradeAsTheGenericElementOfAnOrderedSet(Map<List<String>, Integer> listOfPropertiesToIndex) 
			throws OrderedSetsGenerationException {
		IOrderedSet orderedSet;
		List<String> listOfPropertiesWithPath = this.getListOfPropertiesWithPath();
		Integer genericElementIndex = listOfPropertiesToIndex.get(listOfPropertiesWithPath);
		String genericElementID = this.getDescriptorName().concat(genericElementIndex.toString());		
		if (!getListOfComponents().isEmpty()) {
			List<IOrderedSet> listOfOSComponents = new ArrayList<IOrderedSet>();
			for (IElement element : getListOfComponents()) {
				ISyntacticTree synTreeComponent = (ISyntacticTree) element;
				listOfOSComponents.add(synTreeComponent.upgradeAsTheGenericElementOfAnOrderedSet(listOfPropertiesToIndex));
			}
			orderedSet = new GenericOS(genericElementID, getDescriptorName(), listOfOSComponents);
		}
		else {
			orderedSet = new MinimalOS(getDescriptorName());
		}
		return orderedSet;
	}
	
	@Override
	public void proceedFrameAbstraction() throws OrderedSetsGenerationException, SynTreeGenerationException, CloneNotSupportedException {
		for (IElement element : getListOfComponents()) {
			ISyntacticTree syntacticTree = (ISyntacticTree) element;
			syntacticTree.proceedFrameAbstraction();
		}
	}
	
	@Override
	public void setIsWaitingForAbstraction(boolean isWaitingForAbstraction) {
		this.isWaitingForAbstraction = isWaitingForAbstraction;
		for (IElement element : getListOfComponents()) {
			ISyntacticTree syntacticTree = (ISyntacticTree) element;
			syntacticTree.setIsWaitingForAbstraction(isWaitingForAbstraction);
		}
	}
	
	@Override
	public boolean getIsWaitingForAbstraction() {
		return isWaitingForAbstraction;
	}
	
	@Override
	public Set<ISyntacticTree> getFramesToAbstract() throws SynTreeGenerationException, CloneNotSupportedException {
		Set<ISyntacticTree> setOfFrames = new HashSet<ISyntacticTree>();
		if (this.isWaitingForAbstraction) {
			for (IElement element : getListOfComponents()) {
				ISyntacticTree currentSynTree = (ISyntacticTree) element;
				setOfFrames.addAll(currentSynTree.getFramesToAbstract());
			}
			if (setOfFrames.isEmpty())
				isWaitingForAbstraction = false;
		}
		return setOfFrames;
	}
	
	@Override
	public boolean replaceByAbstractFrame(IFrame abstractFrame) throws SynTreeGenerationException {
		boolean frameReplaced = false;
		if (this.isWaitingForAbstraction) {
			for (IElement element : getListOfComponents()) {
				ISyntacticTree currentSynTree = (ISyntacticTree) element;
				if (currentSynTree.replaceByAbstractFrame(abstractFrame))
					frameReplaced = true;
			}
		}
		return frameReplaced;
	}
	
	@Override
	public boolean equals(Object anObject) {
		boolean isEqual = false;
		boolean thisIsAISynTree = false;
		Class<?> objectClass = anObject.getClass();
		Class<?>[] objectInterfaces = objectClass.getInterfaces();
		for (Class<?> objInterface : objectInterfaces) {
			if (objInterface == ISyntacticTree.class)
				thisIsAISynTree = true;
		}
		if (thisIsAISynTree) {
			ISyntacticTree syntacticTree = (ISyntacticTree) anObject;
			if (this.getListOfPropertiesWithPath().equals(syntacticTree.getListOfPropertiesWithPath()))
				isEqual = true;
		}
		return isEqual;
	}
	
	protected void setHashCode() {
		try {
			hashCode = getListOfPropertiesWithPath().size();
		}
		catch (Exception expected) {};
	}

	@Override
	abstract public String getDescriptorName();

}
