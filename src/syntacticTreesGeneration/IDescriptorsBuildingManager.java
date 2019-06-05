package syntacticTreesGeneration;

import java.util.List;

import exceptions.SynTreeGenerationException;
import model.synTreeModel.IGrammaticalST;

public interface IDescriptorsBuildingManager {

	List<IGrammaticalST> getListOfNewDescriptors()
			throws SynTreeGenerationException, CloneNotSupportedException;

}