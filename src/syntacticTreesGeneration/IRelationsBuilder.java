package syntacticTreesGeneration;

import copycatModel.synTreeModel.grammar.Relations;
import exceptions.SynTreeGenerationException;

public interface IRelationsBuilder {

	Relations getRelations() throws SynTreeGenerationException, CloneNotSupportedException;

}