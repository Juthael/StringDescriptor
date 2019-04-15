package syntacticTreesGeneration;

import java.util.List;

import exceptions.SynTreeGenerationException;
import model.synTreeModel.ISynTreeElement;

public interface INewGenOfDescriptorsBuilder {

	List<ISynTreeElement> getNewGenOfDescriptors() throws SynTreeGenerationException,
			CloneNotSupportedException;

}