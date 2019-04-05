package syntacticTreesGeneration;

import java.util.List;

import copycatModel.ISynTreeIntegrableElement;
import exceptions.DescriptorsBuilderException;

public interface IDescriptorsBuildingManager {

	List<ISynTreeIntegrableElement> getListOfNewDescriptors()
			throws DescriptorsBuilderException, CloneNotSupportedException;

}