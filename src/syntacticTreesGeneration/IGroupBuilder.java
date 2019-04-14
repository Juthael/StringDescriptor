package syntacticTreesGeneration;

import copycatModel.synTreeModel.grammar.Group;
import exceptions.SynTreeGenerationException;

public interface IGroupBuilder {

	Group getGroup() throws SynTreeGenerationException, CloneNotSupportedException;

}