package syntacticTreesGeneration;

import copycatModel.grammar.Relations;
import exceptions.DescriptorsBuilderException;

public interface IRelationsBuilder {

	Relations getRelations() throws DescriptorsBuilderException, CloneNotSupportedException;

}