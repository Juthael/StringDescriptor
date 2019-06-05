package syntacticTreesGeneration;

import java.util.List;

import exceptions.SynTreeGenerationException;
import model.synTreeModel.IGrammaticalST;

public interface INewGenOfDescriptorsBuilder {

	List<IGrammaticalST> getNewGenOfDescriptors() throws SynTreeGenerationException,
			CloneNotSupportedException;

}