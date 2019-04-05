package copycatModel.grammar;

import java.util.List;

import copycatModel.impl.SynTreeIntegrableElementImpl;

public abstract class HowManyDimensions extends SynTreeIntegrableElementImpl implements Cloneable {

	public HowManyDimensions(boolean codingDescriptor) {
		super(codingDescriptor);
	}

	@Override
	abstract protected List<SynTreeIntegrableElementImpl> buildListOfComponents();

	@Override
	abstract public String getDescriptorName();
	
	@Override
	abstract protected HowManyDimensions clone()  throws CloneNotSupportedException;

}
