package model.copycatModel.synTreeGrammar;

import model.synTreeModel.ISynTreeElementWithPosition;
import model.synTreeModel.impl.SynTreeElementWithPositionImpl;

public abstract class HowManyGroups extends SynTreeElementWithPositionImpl implements ISynTreeElementWithPosition, Cloneable {

	public HowManyGroups() {
	}
	
	public HowManyGroups(boolean isCodingDescriptor) {
		super(isCodingDescriptor);
	}
	
	@Override
	abstract protected HowManyGroups clone() throws CloneNotSupportedException;
	
}
