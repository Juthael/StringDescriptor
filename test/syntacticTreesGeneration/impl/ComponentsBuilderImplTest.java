package syntacticTreesGeneration.impl;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import exceptions.SynTreeGenerationException;
import model.copycatModel.synTreeGrammar.Frame;
import model.copycatModel.synTreeGrammar.FrameX;
import model.copycatModel.synTreeGrammar.Components;
import model.copycatModel.synTreeGrammar.Size;
import model.synTreeModel.IFrame;
import model.synTreeModel.ISignal;
import settings.Settings;
import syntacticTreesGeneration.ISignalBuilder;

public class ComponentsBuilderImplTest {

	@Test
	public void whenComponentsBuiltThenFramePositionsUpdatedAsExpected() throws SynTreeGenerationException, CloneNotSupportedException {
		boolean framePositionsUpdatedAsExpected = true;
		ISignalBuilder signalBuilder = new SignalBuilderImpl("abc", "fromLeftToRight");
		ISignal signal = signalBuilder.getSignal();
		List<IFrame> listOfFrames = signal.getFrames();
		for (IFrame frame : listOfFrames) {
			List<String> listOfProperties = frame.getListOfPropertiesWithPath();
			for (String property : listOfProperties) {
				if (property.contains("frame/prominentPosition/position/1") 
						|| property.contains("frame/prominentPosition/endPosition/" + Settings.FIRST_POSITION))
					framePositionsUpdatedAsExpected = false;
			}
		}
		if (framePositionsUpdatedAsExpected == true) {
			Size size = new Size("3");
			FrameX frameX = new FrameX(listOfFrames);
			Components components = new Components(size, frameX);
			boolean nowItContainsFirstPosition = false;
			boolean nowItContainsPosition1 = false;
			List<String> listOfFramesProperties = components.getListOfPropertiesWithPath();
			for (String property : listOfFramesProperties) {
				if (property.contains("frame/prominentPosition/position/1")) 
					nowItContainsPosition1 = true;
				else if (property.contains("frame/prominentPosition/endPosition/" + Settings.FIRST_POSITION))
					nowItContainsFirstPosition = true;
			}
			framePositionsUpdatedAsExpected = (nowItContainsPosition1 == true && nowItContainsFirstPosition == true);
		}
		assertTrue(framePositionsUpdatedAsExpected);
	}

}
