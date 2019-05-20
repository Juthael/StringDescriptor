package model.synTreeModel;

import java.util.List;

import model.copycatModel.synTreeGrammar.Frame;

public interface ISignal {

	List<Frame> getFrames();

	String getDirectionValue();

	int getSignalSize();

}