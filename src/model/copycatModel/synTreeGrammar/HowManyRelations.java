package model.copycatModel.synTreeGrammar;

import model.synTreeModel.ISynTreeElement;
import model.synTreeModel.impl.SynTreeElementImpl;

public abstract class HowManyRelations extends SynTreeElementImpl implements ISynTreeElement, Cloneable {

	public HowManyRelations() {
	}

	@Override
	abstract protected HowManyRelations clone() throws CloneNotSupportedException;
}
