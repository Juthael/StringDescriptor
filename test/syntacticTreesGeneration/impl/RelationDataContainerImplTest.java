package syntacticTreesGeneration.impl;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import settings.Settings;
import syntacticTreesGeneration.IEnumerationRelationalData;
import syntacticTreesGeneration.IRelationDataContainer;
import syntacticTreesGeneration.ISequenceRelationalData;
import syntacticTreesGeneration.impl.EnumerationRelationalDataImpl;
import syntacticTreesGeneration.impl.RelationDataContainerImpl;
import syntacticTreesGeneration.impl.SequenceRelationalDataImpl;

public class RelationDataContainerImplTest {

	@Test
	public void redundanciesAreProperlyCleared() throws Exception {
		boolean redundanciesAreProperlyCleared = true;
		if (Settings.REDUNDANCIES_IN_RELATIONS_CAN_BE_CLEANED) {
			String dimension1 = "dimension1";
			String dimension2 = "dimension2";
			IEnumerationRelationalData enum123A = new EnumerationRelationalDataImpl(dimension1, "1,2,3");
			IEnumerationRelationalData enum123B = new EnumerationRelationalDataImpl(dimension2, "1,2,3");
			ISequenceRelationalData sequence1A = new SequenceRelationalDataImpl(dimension1, "1,2,3", "1");
			ISequenceRelationalData sequence1B = new SequenceRelationalDataImpl(dimension2, "1,2,3", "1");
			IRelationDataContainer rdContainer = new RelationDataContainerImpl();
			rdContainer.addEnumeration(enum123A);
			rdContainer.addEnumeration(enum123B);
			rdContainer.addSequence(sequence1A);
			rdContainer.addSequence(sequence1B);
			List<IEnumerationRelationalData> messyEnumList = rdContainer.getListOfEnumerations();
			List<ISequenceRelationalData> messySequenceList = rdContainer.getListOfSequences();
			if (messyEnumList.size() != 2 || messySequenceList.size() != 2)
				throw new Exception();
			rdContainer.cleanValuesRedundancies();
			List<IEnumerationRelationalData> cleanEnumList = rdContainer.getListOfEnumerations();
			List<ISequenceRelationalData> cleanSequenceList = rdContainer.getListOfSequences();		
			if (cleanEnumList.size() != 1 || cleanSequenceList.size() != 1)
				redundanciesAreProperlyCleared = false;
		}
		assertTrue(redundanciesAreProperlyCleared);
	}

}
