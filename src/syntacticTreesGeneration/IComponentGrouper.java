package syntacticTreesGeneration.interfaces;

import java.util.ArrayList;
import java.util.HashSet;

import copycatModel.grammar.Group;
import exceptions.DescriptorsBuilderCriticalException;

public interface ComponentGrouperInterface {

	/* (non-Javadoc)
	 * @see descriptorsGeneration.ComponentGrouper#getSetOfFactorizableDescriptors()
	 */
	HashSet<ArrayList<Group>> getSetsOfFactorizableDescriptors()
			throws DescriptorsBuilderCriticalException;

}