package copycatModel.implementations;

import java.util.ArrayList;

import copycatModel.grammar.Group;
import copycatModel.interfaces.SignalInterface;

public class SignalV1 implements Cloneable, SignalInterface {

	private ArrayList<Group> listOfGroups;
	private String directionValue; // "fromLeftToRight" or "fromRightToLeft";
	
	public SignalV1(ArrayList<Group> listOfGroups, String directionValue) {
		this.listOfGroups = listOfGroups;
		this.directionValue = directionValue;
	}

	//Getters
	@Override
	public ArrayList<Group> getGroups() {
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
