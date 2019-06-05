package model.copycatModel.synTreeGrammar;

import model.synTreeModel.IGrammaticalST;
import model.synTreeModel.IPositionableST;

public interface IOneOrManyFrames extends IGrammaticalST, IPositionableST, Cloneable {
	
	IOneOrManyFrames clone() throws CloneNotSupportedException ;

}
