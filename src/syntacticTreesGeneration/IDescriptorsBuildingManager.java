package syntacticTreesGeneration;

import java.util.List;

import exceptions.SynTreeGenerationException;
import model.synTreeModel.ISynTreeElement;

public interface IDescriptorsBuildingManager {

	List<ISynTreeElement> getListOfNewDescriptors()
			throws SynTreeGenerationException, CloneNotSupportedException;

}