package copycatModel.grammar;

import java.util.ArrayList;

import copycatModel.implementations.AbstractDescriptorV1;

public abstract class HowManyDimensions extends AbstractDescriptorV1 implements Cloneable {

	public HowManyDimensions(boolean codingDescriptor) {
		super(codingDescriptor);
	}

	@Override
	abstract protected ArrayList<AbstractDescriptorV1> buildListOfComponents();

	@Override
	abstract public String getDescriptorName();
	
	@Override
	abstract protected HowManyDimensions clone()  throws CloneNotSupportedException;

}
