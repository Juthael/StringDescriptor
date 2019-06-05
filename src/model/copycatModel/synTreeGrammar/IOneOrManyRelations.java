package model.copycatModel.synTreeGrammar;

import model.synTreeModel.IGrammaticalST;

public interface IOneOrManyRelations extends IGrammaticalST, Cloneable {
	
	IOneOrManyRelations clone() throws CloneNotSupportedException;

}
