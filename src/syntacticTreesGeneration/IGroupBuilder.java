package syntacticTreesGeneration;

import copycatModel.grammar.Group;
import exceptions.SynTreeGenerationException;

public interface IGroupBuilder {

	Group getGroup() throws SynTreeGenerationException, CloneNotSupportedException;

}