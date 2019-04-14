package syntacticTreesGeneration;

import copycatModel.synTreeModel.grammar.Groups;
import exceptions.SynTreeGenerationException;

public interface IGroupsBuilder {

	Groups getGroups() throws SynTreeGenerationException, CloneNotSupportedException;

}