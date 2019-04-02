package syntacticTreesGeneration.interfaces;

import copycatModel.interfaces.AbstractDescriptorInterface;
import exceptions.DescriptorsBuilderCriticalException;

public interface NewDescriptorBuilderInterface {

	AbstractDescriptorInterface getNewDescriptor()
			throws DescriptorsBuilderCriticalException, CloneNotSupportedException;

}