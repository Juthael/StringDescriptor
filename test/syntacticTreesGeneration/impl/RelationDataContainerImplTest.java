package descriptorsGeneration.implementations;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import settings.DescGenSettings;
import syntacticTreesGeneration.implementations.EnumerationRelationalDataV1;
import syntacticTreesGeneration.implementations.RelationDataContainerV1;
import syntacticTreesGeneration.implementations.SequenceRelationalDataV1;
import syntacticTreesGeneration.interfaces.EnumerationRelationalDataInterface;
import syntacticTreesGeneration.interfaces.RelationDataContainerInterface;
import syntacticTreesGeneration.interfaces.SequenceRelationalDataInterface;

public class RelationDataContainerV1Test {

	@Test
	public void redundanciesAreProperlyCleared() throws Exception {
		boolean redundanciesAreProperlyCleared = true;
		if (DescGenSettings.REDUNDANCIES_IN_RELATIONS_CAN_BE_CLEANED) {
			String dimension1 = "dimension1";
			String dimension2 = "dimension2";
			EnumerationRelationalDataInterface enum123A = new EnumerationRelationalDataV1(dimension1, "1,2,3");
			EnumerationRelationalDataInterface enum123B = new EnumerationRelationalDataV1(dimension2, "1,2,3");
			SequenceRelationalDataInterface sequence1A = new SequenceRelationalDataV1(dimension1, "1,2,3", "1");
			SequenceRelationalDataInterface sequence1B = new SequenceRelationalDataV1(dimension2, "1,2,3", "1");
			RelationDataContainerInterface rdContainer = new RelationDataContainerV1();
			rdContainer.addEnumeration(enum123A);
			rdContainer.addEnumeration(enum123B);
			rdContainer.addSequence(sequence1A);
			rdContainer.addSequence(sequence1B);
			ArrayList<EnumerationRelationalDataInterface> messyEnumList = rdContainer.getListOfEnumerations();
			ArrayList<SequenceRelationalDataInterface> messySequenceList = rdContainer.getListOfSequences();
			if (messyEnumList.size() != 2 || messySequenceList.size() != 2)
				throw new Exception();
			rdContainer.cleanValuesRedundancies();
			ArrayList<EnumerationRelationalDataInterface> cleanEnumList = rdContainer.getListOfEnumerations();
			ArrayList<SequenceRelationalDataInterface> cleanSequenceList = rdContainer.getListOfSequences();		
			if (cleanEnumList.size() != 1 || cleanSequenceList.size() != 1)
				redundanciesAreProperlyCleared = false;
		}
		assertTrue(redundanciesAreProperlyCleared);
	}

}
