package syntacticTreesGeneration;

import copycatModel.grammar.Groups;
import exceptions.DescriptorsBuilderException;

public interface IGroupsBuilder {

	Groups getGroups() throws DescriptorsBuilderException, CloneNotSupportedException;

}