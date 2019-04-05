package syntacticTreesGeneration;

import copycatModel.ISynTreeIntegrableElement;
import exceptions.DescriptorsBuilderException;

public interface INewDescriptorBuilder {

	ISynTreeIntegrableElement getNewDescriptor()
			throws DescriptorsBuilderException, CloneNotSupportedException;

}