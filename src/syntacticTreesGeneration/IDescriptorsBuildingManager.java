package syntacticTreesGeneration;

import java.util.List;

import copycatModel.ISynTreeIntegrableElement;
import exceptions.SynTreeGenerationException;

public interface IDescriptorsBuildingManager {

	List<ISynTreeIntegrableElement> getListOfNewDescriptors()
			throws SynTreeGenerationException, CloneNotSupportedException;

}