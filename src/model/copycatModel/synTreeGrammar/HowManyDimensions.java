package model.copycatModel.synTreeGrammar;

import model.synTreeModel.ISynTreeElement;
import model.synTreeModel.impl.SynTreeElementImpl;

public abstract class HowManyDimensions extends SynTreeElementImpl implements ISynTreeElement, Cloneable {

	public HowManyDimensions() {
	}

	@Override
	abstract protected HowManyDimensions clone()  throws CloneNotSupportedException;

}
