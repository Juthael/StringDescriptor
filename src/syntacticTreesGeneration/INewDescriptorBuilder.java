package syntacticTreesGeneration;

import exceptions.SynTreeGenerationException;
import model.synTreeModel.IGrammaticalST;

public interface INewDescriptorBuilder {

	IGrammaticalST getNewDescriptor()
			throws SynTreeGenerationException, CloneNotSupportedException;

}