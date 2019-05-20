package model.copycatModel.synTreeGrammar;

import model.synTreeModel.ISynTreeElementWithPosition;
import model.synTreeModel.impl.SynTreeElementWithPositionImpl;

public abstract class HowManyFrames extends SynTreeElementWithPositionImpl implements ISynTreeElementWithPosition, Cloneable {

	public HowManyFrames() {
	}
	
	public HowManyFrames(boolean isCodingDescriptor) {
		super(isCodingDescriptor);
	}
	
	@Override
	abstract protected HowManyFrames clone() throws CloneNotSupportedException;
	
}
