package model.generalModel.impl;

import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Set;

import org.junit.Test;

import model.copycatModel.synTreeGrammar.Letter;
import model.copycatModel.synTreeGrammar.PlatonicLetter;
import model.copycatModel.synTreeGrammar.Position;

public class ElementImplTest {

	@Test
	public void whenAllAccessiblePropertyListsAreRequestedThenExpectedNumberOfListsReturned() throws CloneNotSupportedException {
		Position position = new Position("1");
		PlatonicLetter platonicLetter = new PlatonicLetter("a");
		Letter letter = new Letter(position, platonicLetter);
		Set<List<String>> listOfAllAccessibleListsOfProperties = letter.getSetOfAllPropertyListsAccessibleFromThisDescriptor();
		assertTrue(listOfAllAccessibleListsOfProperties.size() == 5);
	}

}
