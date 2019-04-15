package syntacticTreesGeneration.impl;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import exceptions.SynTreeGenerationException;
import model.copycatModel.synTreeGrammar.Group;
import model.copycatModel.synTreeGrammar.GroupX;
import model.copycatModel.synTreeGrammar.Groups;
import model.copycatModel.synTreeGrammar.Size;
import model.synTreeModel.ISignal;
import settings.Settings;
import syntacticTreesGeneration.ISignalBuilder;

public class GroupsBuilderImplTest {

	@Test
	public void whenGroupsBuiltThenGroupPositionsUpdatedAsExpected() throws SynTreeGenerationException, CloneNotSupportedException {
		boolean groupPositionsUpdatedAsExpected = true;
		ISignalBuilder signalBuilder = new SignalBuilderImpl("abc", "fromLeftToRight");
		ISignal signal = signalBuilder.getSignal();
		List<Group> listOfGroups = signal.getGroups();
		for (Group group : listOfGroups) {
			List<String> listOfProperties = group.getListOfPropertiesWithPath();
			for (String property : listOfProperties) {
				if (property.contains("group/position/1") 
						|| property.contains("group/position/" + Settings.FIRST_POSITION))
					groupPositionsUpdatedAsExpected = false;
			}
		}
		if (groupPositionsUpdatedAsExpected == true) {
			Size size = new Size("3");
			GroupX groupX = new GroupX(listOfGroups);
			Groups groups = new Groups(size, groupX);
			boolean nowItContainsFirstPosition = false;
			boolean nowItContainsPosition1 = false;
			List<String> listOfGroupsProperties = groups.getListOfPropertiesWithPath();
			for (String property : listOfGroupsProperties) {
				if (property.contains("group/position/1")) 
					nowItContainsPosition1 = true;
				else if (property.contains("group/position/" + Settings.FIRST_POSITION))
					nowItContainsFirstPosition = true;
			}
			groupPositionsUpdatedAsExpected = (nowItContainsPosition1 == true && nowItContainsFirstPosition == true);
		}
		assertTrue(groupPositionsUpdatedAsExpected);
	}

}
