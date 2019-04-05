package syntacticTreesGeneration;

import java.util.List;

import copycatModel.ISynTreeIntegrableElement;
import exceptions.DescriptorsBuilderException;

public interface INewGenOfDescriptorsBuilder {

	List<ISynTreeIntegrableElement> getNewGenOfDescriptors() throws DescriptorsBuilderException,
			CloneNotSupportedException;

}