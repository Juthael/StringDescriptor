package model.synTreeModel.impl;

import java.util.List;

import model.copycatModel.synTreeGrammar.Frame;
import model.synTreeModel.ISignal;

public class SignalImpl implements Cloneable, ISignal {

	private List<Frame> listOfFrames;
	private String directionValue; // "fromLeftToRight" or "fromRightToLeft";
	
	public SignalImpl(List<Frame> listOfFrames, String directionValue) {
		this.listOfFrames = listOfFrames;
		this.directionValue = directionValue;
	}

	//Getters
	@Override
	public List<Frame> getFrames() {
		return listOfFrames;
	}

	@Override
	public String getDirectionValue() {
		return directionValue;
	}
	
	@Override
	public int getSignalSize() {
		return listOfFrames.size();
	}

}
