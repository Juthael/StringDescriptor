package syntacticTreesGeneration.interfaces;

import copycatModel.grammar.Group;
import exceptions.DescriptorsBuilderCriticalException;

public interface GroupBuilderInterface {

	Group getGroup() throws DescriptorsBuilderCriticalException, CloneNotSupportedException;

}