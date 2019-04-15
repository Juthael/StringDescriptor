package model.synTreeModel;

import java.util.List;

import model.copycatModel.synTreeGrammar.Group;

public interface ISignal {

	List<Group> getGroups();

	String getDirectionValue();

	int getSignalSize();

}