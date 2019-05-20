package syntacticTreesGeneration;

import java.util.List;
import java.util.Set;

import exceptions.SynTreeGenerationException;
import model.copycatModel.synTreeGrammar.Frame;

public interface IComponentGrouper {

	Set<List<Frame>> getSetsOfFactorizableDescriptors()
			throws SynTreeGenerationException;

}