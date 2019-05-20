package orderedSetGeneration.impl;

import java.util.Map;
import java.util.Set;
import java.util.Vector;

import exceptions.OrderedSetsGenerationException;
import fca.core.context.binary.BinaryContext;
import model.orderedSetModel.IOrderedSet;
import orderedSetGeneration.IBinaryContextBuilder;
import settings.Settings;

public class BinaryContextBuilderImpl implements IBinaryContextBuilder {

	BinaryContext context;
	
	public BinaryContextBuilderImpl(IOrderedSet orderedSet) throws OrderedSetsGenerationException {
		context = setContext(orderedSet);
	}

	@Override
	public BinaryContext getContext() {
		return context;
	}
	
	private BinaryContext setContext(IOrderedSet orderedSet) throws OrderedSetsGenerationException {
		BinaryContext context;
		Map<String, Set<String>> relation;
		if (Settings.CODING_ELEMENTS_ARE_ORDERED_SET_ATOMS) {
			if (Settings.NON_INFORMATIVE_ELEMENTS_MUST_BE_REMOVED)
				relation = orderedSet.getSetOfCodingComponentsReducedRelation();
			else relation = orderedSet.getSetOfCodingComponentsRelation();
		}
		else {
			if (Settings.NON_INFORMATIVE_ELEMENTS_MUST_BE_REMOVED)
				relation = orderedSet.getReducedRelation();
			else relation = orderedSet.getRelation();
		}
		Vector<String> attributes = new Vector<String>(relation.keySet());
		Vector<String> objects = new Vector<String>(attributes);
		Vector<Vector<String>> values = new Vector<Vector<String>>();
		for (String object : objects) {
			Vector<String> valuesForThisObject = new Vector<String>();
			for (String attribute : attributes) {
				if (relation.get(object).contains(attribute))
					valuesForThisObject.add(attribute);
				else valuesForThisObject.add("");
			}
			values.add(valuesForThisObject);
		}
		context = new BinaryContext("omega", objects, attributes, values);
		if (Settings.BINARY_CONTEXTS_MUST_BE_CLARIFIED)
			return context.clarifyContext();
		else return context;
	}

}
