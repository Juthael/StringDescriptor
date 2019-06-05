package model.copycatModel.signal.impl;

import java.util.List;

import model.copycatModel.signal.ICopycatSignal;
import model.synTreeModel.IFrame;

public class CopycatSignal implements Cloneable, ICopycatSignal {

	private List<IFrame> listOfFrames;
	private String directionValue; // "fromLeftToRight" or "fromRightToLeft";
	
	public CopycatSignal(List<IFrame> listOfFrames, String directionValue) {
		this.listOfFrames = listOfFrames;
		this.directionValue = directionValue;
	}

	//Getters
	@Override
	public List<IFrame> getFrames() {
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
