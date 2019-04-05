package copycatModel.grammar;

import java.util.List;

import copycatModel.impl.SynTreeIntegrableElementImpl;

public abstract class RelationsOrLetter extends SynTreeIntegrableElementImpl implements Cloneable {

	public RelationsOrLetter(boolean codingDescriptor) {
		super(codingDescriptor);
	}

	@Override
	abstract protected List<SynTreeIntegrableElementImpl> buildListOfComponents();

	@Override
	abstract public String getDescriptorName();
	
	@Override
	abstract protected RelationsOrLetter clone() throws CloneNotSupportedException;
}
