package copycatModel.grammar;

import java.util.List;

import copycatModel.impl.SynTreeIntegrableElementImpl;

public abstract class HowManyRelations extends SynTreeIntegrableElementImpl implements Cloneable {

	public HowManyRelations(boolean codingDescriptor) {
		super(codingDescriptor);
	}

	@Override
	abstract protected List<SynTreeIntegrableElementImpl> buildListOfComponents();

	@Override
	abstract public String getDescriptorName();
	
	@Override
	abstract protected HowManyRelations clone() throws CloneNotSupportedException;

}
