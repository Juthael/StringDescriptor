package syntacticTreesGeneration;

import java.util.List;

import copycatModel.ISynTreeIntegrableElement;
import exceptions.SynTreeGenerationException;

public interface INewGenOfDescriptorsBuilder {

	List<ISynTreeIntegrableElement> getNewGenOfDescriptors() throws SynTreeGenerationException,
			CloneNotSupportedException;

}