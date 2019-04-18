package orderedSetsGeneration;

import java.util.Map;
import java.util.Set;

import model.orderedSetModel.ILowerSetElement;

public interface IOrderedSetBuilder {
	
	ILowerSetElement getOrderedSet();
	
	Map<String, Set<String>> getRelation();

}

