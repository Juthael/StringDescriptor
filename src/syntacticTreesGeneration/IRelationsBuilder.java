package syntacticTreesGeneration;

import exceptions.SynTreeGenerationException;
import model.copycatModel.synTreeGrammar.Relations;

public interface IRelationsBuilder {

	Relations getRelations() throws SynTreeGenerationException, CloneNotSupportedException;

}