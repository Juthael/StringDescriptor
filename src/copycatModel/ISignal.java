package copycatModel.interfaces;

import java.util.ArrayList;

import copycatModel.grammar.Group;

public interface SignalInterface {

	ArrayList<Group> getGroups();

	String getDirectionValue();

	int getSignalSize();

}