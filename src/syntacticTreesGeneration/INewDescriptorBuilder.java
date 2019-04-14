package syntacticTreesGeneration;

import copycatModel.synTreeModel.ISynTreeIntegrableElement;
import exceptions.SynTreeGenerationException;

public interface INewDescriptorBuilder {

	ISynTreeIntegrableElement getNewDescriptor()
			throws SynTreeGenerationException, CloneNotSupportedException;

}