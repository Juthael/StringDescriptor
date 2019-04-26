package orderedSetGeneration;

import java.util.Map;
import java.util.Set;

import exceptions.OrderedSetsGenerationException;
import model.orderedSetModel.IOrderedSet;

public interface IOrderedSetBuilder {
	
	IOrderedSet getOrderedSet();
	
	Map<String, Set<String>> getRelation() throws OrderedSetsGenerationException;

}

