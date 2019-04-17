package model.copycatModel.synTreeGrammar;

import model.synTreeModel.ISynTreeElementWithPosition;
import model.synTreeModel.impl.SynTreeElementWithPositionImpl;

public abstract class WhichPositionType extends SynTreeElementWithPositionImpl implements ISynTreeElementWithPosition, Cloneable {
	
	abstract protected WhichPositionType clone() throws CloneNotSupportedException;

}
