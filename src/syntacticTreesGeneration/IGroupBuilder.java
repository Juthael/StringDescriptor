package syntacticTreesGeneration;

import exceptions.SynTreeGenerationException;
import model.copycatModel.synTreeGrammar.Group;

public interface IGroupBuilder {

	Group getGroup() throws SynTreeGenerationException, CloneNotSupportedException;

}