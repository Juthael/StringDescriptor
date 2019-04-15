package syntacticTreesGeneration;

import exceptions.SynTreeGenerationException;
import model.copycatModel.synTreeGrammar.Groups;

public interface IGroupsBuilder {

	Groups getGroups() throws SynTreeGenerationException, CloneNotSupportedException;

}