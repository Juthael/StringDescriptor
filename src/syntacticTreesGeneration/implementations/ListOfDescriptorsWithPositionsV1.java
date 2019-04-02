package syntacticTreesGeneration.implementations;

import java.util.ArrayList;

import copycatModel.grammar.Group;
import syntacticTreesGeneration.interfaces.ListOfDescriptorsWithPositionsInterface;

public class ListOfDescriptorsWithPositionsV1 implements ListOfDescriptorsWithPositionsInterface {

	private ArrayList<Group> listOfDescriptors;
	private int firstPosition;
	private int lastPosition;
	
	public ListOfDescriptorsWithPositionsV1(ArrayList<Group> listOfDescriptors, int firstPosition, int lastPosition) {
		this.listOfDescriptors = listOfDescriptors;
		this.firstPosition = firstPosition;
		this.lastPosition = lastPosition;
	}

	@Override
	public ArrayList<Group> getListOfDescriptors() {
		return listOfDescriptors;
	}

	@Override
	public int getFirstPosition() {
		return firstPosition;
	}

	@Override
	public int getLastPosition() {
		return lastPosition;
	}

}
