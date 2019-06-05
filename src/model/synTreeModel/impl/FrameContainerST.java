package model.synTreeModel.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import exceptions.SynTreeGenerationException;
import model.generalModel.IElement;
import model.synTreeModel.IFrame;
import model.synTreeModel.IFrameContainerST;
import model.synTreeModel.ISyntacticTree;

public abstract class FrameContainerST extends GrammaticalST implements IFrameContainerST, Cloneable {

	public FrameContainerST() {
	}

	public FrameContainerST(boolean isCodingElement) {
		super(isCodingElement);
	}
	
	@Override
	public Set<ISyntacticTree> getFramesToAbstract() {
		Set<ISyntacticTree> framesToAbstract = new HashSet<ISyntacticTree>();
		boolean isWaitingForAbstraction = getIsWaitingForAbstraction();
		if (isWaitingForAbstraction) {
			for (IElement element : getListOfComponents()) {
				ISyntacticTree syntacticTree = (ISyntacticTree) element;
				framesToAbstract.addAll(syntacticTree.getFramesToAbstract());
			}
			if (framesToAbstract.isEmpty())
				framesToAbstract.addAll(getListOfChildrenFrames());
		}
		return framesToAbstract;
	}
	
	@Override
	public boolean replaceByAbstractFrame(IFrame abstractFrame) throws SynTreeGenerationException {
		boolean frameReplaced;
		if (getIsWaitingForAbstraction() == true) {
			List<IFrame> listOfFrames = getListOfChildrenFrames();
			frameReplaced = listOfFrames.get(0).replaceByAbstractFrame(abstractFrame);
			for (int i=1 ; i<listOfFrames.size() ; i++) {
				ISyntacticTree currentFrame = listOfFrames.get(i);
				if (currentFrame.replaceByAbstractFrame(abstractFrame) != frameReplaced)
					throw new SynTreeGenerationException("FrameContainerST.replaceByAbstractFrame() : inconsistent return.");
			}
			if (frameReplaced == false) {
				List<IFrame> newListOfFrames = new ArrayList<IFrame>();
				for (int i=0 ; i<listOfFrames.size() ; i++) {
					newListOfFrames.add(abstractFrame);
				}
				setNewListOfChildrenFrames(newListOfFrames);
				setIsWaitingForAbstraction(false);
				frameReplaced = true;
			}
		}
		else frameReplaced = false;
		return frameReplaced;
	}
	
	@Override
	abstract public List<IFrame> getListOfChildrenFrames();
	
	@Override
	abstract public void setNewListOfChildrenFrames(List<IFrame> newListOfFrames);
	
}
