package model.copycatModel.synTreeGrammar;

import model.synTreeModel.IGrammaticalST;

public interface IOneOrManyDimensions extends IGrammaticalST, Cloneable {
	
	IOneOrManyDimensions clone() throws CloneNotSupportedException;

}
