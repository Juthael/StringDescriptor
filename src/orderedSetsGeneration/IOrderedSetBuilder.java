package orderedSetsGeneration;

import java.util.Map;
import java.util.Set;

import model.orderedSetModel.ISetElement;

public interface IOrderedSetBuilder {
	
	ISetElement getOrderedSet();
	
	Map<String, Set<String>> getRelation();

}

