package syntacticTreesGeneration.interfaces;

import java.util.ArrayList;

import copycatModel.interfaces.AbstractDescriptorInterface;
import exceptions.DescriptorsBuilderCriticalException;

public interface NewGenOfDescriptorsBuilderInterface {

	ArrayList<AbstractDescriptorInterface> getNewGenOfDescriptors() throws DescriptorsBuilderCriticalException,
			CloneNotSupportedException;

}