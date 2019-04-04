package syntacticTreesGeneration.interfaces;

import copycatModel.grammar.Groups;
import exceptions.DescriptorsBuilderCriticalException;

public interface GroupsBuilderInterface {

	Groups getGroups() throws DescriptorsBuilderCriticalException, CloneNotSupportedException;

}