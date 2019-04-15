package syntacticTreesGeneration;

import exceptions.SynTreeGenerationException;
import model.synTreeModel.ISynTreeElement;

public interface INewDescriptorBuilder {

	ISynTreeElement getNewDescriptor()
			throws SynTreeGenerationException, CloneNotSupportedException;

}