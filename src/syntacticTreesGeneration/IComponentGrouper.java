package syntacticTreesGeneration;

import java.util.List;
import java.util.Set;

import copycatModel.grammar.Group;
import exceptions.DescriptorsBuilderException;

public interface IComponentGrouper {

	Set<List<Group>> getSetsOfFactorizableDescriptors()
			throws DescriptorsBuilderException;

}