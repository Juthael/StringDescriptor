package copycatModel.grammar;

import java.util.ArrayList;

import copycatModel.implementations.AbstractDescriptorV1;

public abstract class HowManyGroups extends AbstractDescriptorV1 implements Cloneable {

	public HowManyGroups(boolean codingDescriptor) {
		super(codingDescriptor);
	}

	@Override
	abstract protected ArrayList<AbstractDescriptorV1> buildListOfComponents();
	
	@Override
	abstract public String getDescriptorName();	
	
	@Override
	abstract protected HowManyGroups clone() throws CloneNotSupportedException;
}
