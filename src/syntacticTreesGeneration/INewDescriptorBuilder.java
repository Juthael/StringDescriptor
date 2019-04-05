package syntacticTreesGeneration;

import copycatModel.ISynTreeIntegrableElement;
import exceptions.SynTreeGenerationException;

public interface INewDescriptorBuilder {

	ISynTreeIntegrableElement getNewDescriptor()
			throws SynTreeGenerationException, CloneNotSupportedException;

}