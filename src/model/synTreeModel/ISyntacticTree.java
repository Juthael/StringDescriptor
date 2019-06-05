package model.synTreeModel;

import java.util.List;
import java.util.Map;
import java.util.Set;

import exceptions.OrderedSetsGenerationException;
import exceptions.SynTreeGenerationException;
import model.generalModel.IElement;
import model.orderedSetModel.IOrderedSet;

public interface ISyntacticTree extends IElement {
	
	IOrderedSet upgradeAsTheElementOfAnOrderedSet(Map<List<String>, Integer> listOfPropertiesToIndex) 
			throws OrderedSetsGenerationException;
	
	IOrderedSet upgradeAsTheGenericElementOfAnOrderedSet(Map<List<String>, Integer> listOfPropertiesToIndex) 
			throws OrderedSetsGenerationException;
	
	void proceedFrameAbstraction() throws OrderedSetsGenerationException, SynTreeGenerationException;
	
	void setIsWaitingForAbstraction(boolean isWaitingForAbstraction);
	
	boolean getIsWaitingForAbstraction();
	
	Set<ISyntacticTree> getFramesToAbstract();
	
	boolean replaceByAbstractFrame(IFrame abstractFrame) throws SynTreeGenerationException;

}
