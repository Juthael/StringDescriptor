package orderedSetGeneration.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import exceptions.OrderedSetsGenerationException;
import orderedSetGeneration.RelationClarifier;

public class RelationClarifierImpl implements RelationClarifier {

	private Map<String, Set<String>> clarifiedRelation;
	
	public RelationClarifierImpl(Map<String, Set<String>> rawRelation) throws OrderedSetsGenerationException {
		clarifiedRelation = setClarifiedRelation(rawRelation);
	}

	@Override
	public Map<String, Set<String>> getClarifiedRelation() {
		return clarifiedRelation;
	}
	
	private Map<String, Set<String>> setClarifiedRelation(Map<String, Set<String>> relation) 
			throws OrderedSetsGenerationException{
		Map<String, Set<String>> clarifiedRelation = null;
		List<String> listOfAttributes = new ArrayList<String>(relation.keySet());
		int attributeIndex = 0;
		boolean relationHasBeenUpdated = false;
		while (attributeIndex < listOfAttributes.size() && relationHasBeenUpdated == false) {
			String currentAttribute = listOfAttributes.get(attributeIndex);
			if (relation.get(currentAttribute).size() == 1) {
				List<String> listOfUpperBounds = new ArrayList<String>();
				for (String attribute : listOfAttributes) {
					if (!attribute.equals(currentAttribute) && relation.get(attribute).contains(currentAttribute))
						listOfUpperBounds.add(attribute);				
				}
				for (String upperBound : listOfUpperBounds) {
					boolean upperBoundsHaveAnInfimum = true;
					int upperBoundIndex = 0;
					while (upperBoundsHaveAnInfimum == true && upperBoundIndex < listOfUpperBounds.size()) {
						if (!relation.get(listOfUpperBounds.get(upperBoundIndex)).contains(upperBound))
							upperBoundsHaveAnInfimum = false;
						upperBoundIndex++;
					}
					if (upperBoundsHaveAnInfimum == true) {
						Map<String, Set<String>> updatedRelation = new HashMap<String, Set<String>>();
						for (String attribute : listOfAttributes) {
							if (!attribute.equals(currentAttribute)) {
								Set<String> attributeLowerSet = relation.get(attribute);
								attributeLowerSet.remove(currentAttribute);
								updatedRelation.put(attribute, attributeLowerSet);
							}
						}
						clarifiedRelation = setClarifiedRelation(updatedRelation);
						relationHasBeenUpdated = true;
					}
				}
			}
			attributeIndex++;
		}
		if (relationHasBeenUpdated == false)
			return relation;
		else return clarifiedRelation;
	}

}
