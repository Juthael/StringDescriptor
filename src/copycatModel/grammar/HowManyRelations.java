package copycatModel.grammar;

import java.util.ArrayList;

import copycatModel.implementations.AbstractDescriptorV1;

public abstract class HowManyRelations extends AbstractDescriptorV1 implements Cloneable {

	public HowManyRelations(boolean codingDescriptor) {
		super(codingDescriptor);
	}

	@Override
	abstract protected ArrayList<AbstractDescriptorV1> buildListOfComponents();

	@Override
	abstract public String getDescriptorName();
	
	@Override
	abstract protected HowManyRelations clone() throws CloneNotSupportedException;

}
