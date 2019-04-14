package syntacticTreesGeneration;

import java.util.List;
import java.util.Set;

import copycatModel.synTreeModel.grammar.Group;
import exceptions.SynTreeGenerationException;

public interface IComponentGrouper {

	Set<List<Group>> getSetsOfFactorizableDescriptors()
			throws SynTreeGenerationException;

}