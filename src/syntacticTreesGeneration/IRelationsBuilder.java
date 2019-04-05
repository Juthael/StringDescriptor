package syntacticTreesGeneration;

import copycatModel.grammar.Relations;
import exceptions.SynTreeGenerationException;

public interface IRelationsBuilder {

	Relations getRelations() throws SynTreeGenerationException, CloneNotSupportedException;

}