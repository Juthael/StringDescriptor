package syntacticTreesGeneration.interfaces;

import java.util.ArrayList;

import copycatModel.interfaces.AbstractDescriptorInterface;
import exceptions.DescriptorsBuilderCriticalException;

public interface DescriptorsBuildingManagerInterface {

	ArrayList<AbstractDescriptorInterface> getListOfNewDescriptors()
			throws DescriptorsBuilderCriticalException, CloneNotSupportedException;

}