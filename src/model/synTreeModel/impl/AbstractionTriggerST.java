package model.synTreeModel.impl;

import java.util.Set;

import exceptions.OrderedSetsGenerationException;
import exceptions.SynTreeGenerationException;
import model.synTreeModel.IAbstractionTriggerST;
import model.synTreeModel.IFrame;
import model.synTreeModel.ISyntacticTree;
import model.synTreeModel.impl.utils.IAbstractionOperator;
import model.synTreeModel.impl.utils.impl.AbstractionOperator;

public abstract class AbstractionTriggerST extends GrammaticalST implements IAbstractionTriggerST, Cloneable {

	public AbstractionTriggerST() {
	}

	public AbstractionTriggerST(boolean isCodingElement) {
		super(isCodingElement);
	}

	public void proceedFrameAbstraction() throws OrderedSetsGenerationException, SynTreeGenerationException {
		triggerAbstractionProcess();
	}
	
	@Override
	public void triggerAbstractionProcess() throws OrderedSetsGenerationException, SynTreeGenerationException {
		setIsWaitingForAbstraction(true);
		Set<ISyntacticTree> setOfFrames = getFramesToAbstract();
		while (setOfFrames.size() > 1) {
			IAbstractionOperator abstractionOperator = new AbstractionOperator(setOfFrames);
			IFrame abstractFrame = abstractionOperator.getAbstractedTree();
			replaceByAbstractFrame(abstractFrame);
			setOfFrames = getFramesToAbstract();
		}
		setIsWaitingForAbstraction(false);
	}

}
