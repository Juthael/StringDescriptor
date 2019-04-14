package copycatModel.synTreeModel;

import java.util.List;

import copycatModel.synTreeModel.grammar.Group;

public interface ISignal {

	List<Group> getGroups();

	String getDirectionValue();

	int getSignalSize();

}