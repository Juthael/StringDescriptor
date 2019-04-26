package model.copycatModel.synTreeGrammar;

import model.synTreeModel.ISynTreeElement;
import model.synTreeModel.impl.SynTreeElementImpl;

public abstract class RelationsOrLetter extends SynTreeElementImpl implements ISynTreeElement, Cloneable {

	public RelationsOrLetter() {
	}
	
	public RelationsOrLetter(boolean codingDescriptor) {
		super(codingDescriptor);
	}

	@Override
	abstract protected RelationsOrLetter clone() throws CloneNotSupportedException;
}
