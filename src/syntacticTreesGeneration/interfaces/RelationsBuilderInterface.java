package syntacticTreesGeneration.interfaces;

import copycatModel.grammar.Relations;
import exceptions.DescriptorsBuilderCriticalException;

public interface RelationsBuilderInterface {

	Relations getRelations() throws DescriptorsBuilderCriticalException, CloneNotSupportedException;

}