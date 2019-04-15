package syntacticTreesGeneration;

import java.util.List;
import java.util.Set;

import exceptions.SynTreeGenerationException;
import model.copycatModel.synTreeGrammar.Group;

public interface IComponentGrouper {

	Set<List<Group>> getSetsOfFactorizableDescriptors()
			throws SynTreeGenerationException;

}