package model.synTreeModel;

import java.util.List;

import exceptions.SynTreeGenerationException;
import model.generalModel.IElement;

public interface IPositionableST extends IGrammaticalST {
	
	void updatePosition(String newPosition, List<IElement>componentDescriptors) throws SynTreeGenerationException;

}
