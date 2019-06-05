package model.synTreeModel.impl.utils;

import exceptions.OrderedSetsGenerationException;
import exceptions.SynTreeGenerationException;
import model.synTreeModel.IFrame;

public interface IAbstractionOperator {

	IFrame getAbstractedTree() throws SynTreeGenerationException, OrderedSetsGenerationException;
	
}
