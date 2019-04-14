package syntacticTreesGeneration;

import java.util.List;

import copycatModel.synTreeModel.ISynTreeIntegrableElement;
import exceptions.SynTreeGenerationException;

public interface IDescriptorsBuildingManager {

	List<ISynTreeIntegrableElement> getListOfNewDescriptors()
			throws SynTreeGenerationException, CloneNotSupportedException;

}