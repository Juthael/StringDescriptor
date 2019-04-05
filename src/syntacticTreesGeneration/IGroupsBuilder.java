package syntacticTreesGeneration;

import copycatModel.grammar.Groups;
import exceptions.SynTreeGenerationException;

public interface IGroupsBuilder {

	Groups getGroups() throws SynTreeGenerationException, CloneNotSupportedException;

}