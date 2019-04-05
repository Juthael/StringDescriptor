package syntacticTreesGeneration;

import copycatModel.grammar.Group;
import exceptions.DescriptorsBuilderException;

public interface IGroupBuilder {

	Group getGroup() throws DescriptorsBuilderException, CloneNotSupportedException;

}