package copycatModel.synTreeModel.impl;

import java.util.List;

import copycatModel.synTreeModel.ISignal;
import copycatModel.synTreeModel.grammar.Group;

public class SignalImpl implements Cloneable, ISignal {

	private List<Group> listOfGroups;
	private String directionValue; // "fromLeftToRight" or "fromRightToLeft";
	
	public SignalImpl(List<Group> listOfGroups, String directionValue) {
		this.listOfGroups = listOfGroups;
		this.directionValue = directionValue;
	}

	//Getters
	@Override
	public List<Group> getGroups() {
		return listOfGroups;
	}

	@Override
	public String getDirectionValue() {
		return directionValue;
	}
	
	@Override
	public int getSignalSize() {
		return listOfGroups.size();
	}

}
