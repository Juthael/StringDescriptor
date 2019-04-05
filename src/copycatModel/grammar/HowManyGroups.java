package copycatModel.grammar;

import java.util.List;

import copycatModel.impl.SynTreeIntegrableElementImpl;

public abstract class HowManyGroups extends SynTreeIntegrableElementImpl implements Cloneable {

	public HowManyGroups(boolean codingDescriptor) {
		super(codingDescriptor);
	}

	@Override
	abstract protected List<SynTreeIntegrableElementImpl> buildListOfComponents();
	
	@Override
	abstract public String getDescriptorName();	
	
	@Override
	abstract protected HowManyGroups clone() throws CloneNotSupportedException;
}
