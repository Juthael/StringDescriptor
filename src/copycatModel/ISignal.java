package copycatModel;

import java.util.List;

import copycatModel.grammar.Group;

public interface ISignal {

	List<Group> getGroups();

	String getDirectionValue();

	int getSignalSize();

}