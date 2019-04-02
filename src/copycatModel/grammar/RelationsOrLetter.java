package copycatModel.grammar;

import java.util.ArrayList;

import copycatModel.implementations.AbstractDescriptorV1;

public abstract class RelationsOrLetter extends AbstractDescriptorV1 implements Cloneable {

	public RelationsOrLetter(boolean codingDescriptor) {
		super(codingDescriptor);
	}

	@Override
	abstract protected ArrayList<AbstractDescriptorV1> buildListOfComponents();

	@Override
	abstract public String getDescriptorName();
	
	@Override
	abstract protected RelationsOrLetter clone() throws CloneNotSupportedException;
}
