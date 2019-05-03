package model.synTreeModel;

import exceptions.OrderedSetsGenerationException;
import exceptions.VerbalizationException;
import model.orderedSetModel.impl.AbstractOmegaElement;

public interface ISynTreeStartElement extends ISynTreeElement {
	
	abstract AbstractOmegaElement upgradeAsTheSupremumOfAnOrderedSet() throws OrderedSetsGenerationException, VerbalizationException; 
	
	abstract String getVerbalDescription() throws VerbalizationException;

}
