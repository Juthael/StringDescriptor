package model.orderedSetModel.impl.utils;

import java.util.Comparator;
import java.util.Set;

import model.orderedSetModel.IOrderedSet;

public class OrderedSetsComparator implements Comparator<IOrderedSet> {

	public OrderedSetsComparator() {
	}

	@Override
	public int compare(IOrderedSet o1, IOrderedSet o2) {
		int value;
		Set<String> o1LowerSetIDs = o1.getLowerSetIDs();
		Set<String> o2LowerSetIDs = o2.getLowerSetIDs();
		if (o2LowerSetIDs.contains(o1.getElementID()))
			value = -1;
		else if (o1LowerSetIDs.contains(o2.getElementID()))
			value = 1;
		else {
			if (o1LowerSetIDs.size() < o2LowerSetIDs.size()) {
				value = -1;
			}
			else if (o1LowerSetIDs.size() > o2LowerSetIDs.size())
				value = 1;
			else value = 0;
		}
		return value;
	}

}
