package model.copycatModel.synTreeGrammar;

import java.util.List;

import model.generalModel.IElement;
import model.synTreeModel.ISynTreeElement;
import model.synTreeModel.impl.SynTreeElementImpl;

public abstract class RelationsOrLetter extends SynTreeElementImpl implements ISynTreeElement, Cloneable {

	public RelationsOrLetter(boolean codingDescriptor) {
		super(codingDescriptor);
	}

	@Override
	abstract protected List<IElement> getListOfComponents();

	@Override
	abstract public String getDescriptorName();
	
	@Override
	abstract protected RelationsOrLetter clone() throws CloneNotSupportedException;
}
