package model.copycatModel.synTreeGrammar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import exceptions.OrderedSetsGenerationException;
import model.copycatModel.ordSetGrammar.LetterOS;
import model.copycatModel.ordSetGrammar.PlatonicLetterOS;
import model.copycatModel.ordSetGrammar.PositionOS;
import model.generalModel.IElement;
import model.orderedSetModel.IOrderedSet;
import model.synTreeModel.IGrammaticalST;
import model.synTreeModel.impl.GrammaticalST;

public class Letter extends GrammaticalST implements IRelationsOrLetter, Cloneable {

	private static final String DESCRIPTOR_NAME = "letter";
	private Position position;
	private PlatonicLetter platonicLetter;
	
	public Letter(Position position, PlatonicLetter platonicLetter) {
		this.position = position;
		this.platonicLetter = platonicLetter;
		setHashCode();
	}
	
	public Letter(boolean codingDescriptor, Position position, PlatonicLetter platonicLetter) {
		super(codingDescriptor);
		this.position = position;
		this.platonicLetter = platonicLetter;
		setHashCode();
	}	
	
	@Override
	protected Letter clone() throws CloneNotSupportedException {
		Letter cloneLetter;
		Position clonePosition = position.clone();
		PlatonicLetter clonePlatonicLetter = platonicLetter.clone();
		cloneLetter = new Letter(isCodingElement, clonePosition, clonePlatonicLetter);
		return cloneLetter;
	}
	
	@Override
	public String getDescriptorName() {
		return DESCRIPTOR_NAME;
	}	

	@Override
	public List<IElement> getListOfComponents(){
		List<IElement> componentDescriptors = new ArrayList<IElement>(
				Arrays.asList(position, platonicLetter));
		return componentDescriptors;
	}
	
	@Override
	protected List<IGrammaticalST> buildListOfRelevantComponentsForRelationBuilding() {
		List<IGrammaticalST> componentDescriptors = new ArrayList<IGrammaticalST>(
				Arrays.asList(platonicLetter));
		return componentDescriptors;
	}	
	
	@Override
	public IOrderedSet upgradeAsTheElementOfAnOrderedSet(Map<List<String>, Integer> listOfPropertiesToIndex) 
			throws OrderedSetsGenerationException {
		IOrderedSet letterOS;
		List<String> listOfPropertiesWithPath = getListOfPropertiesWithPath();
		Integer letterIndex = listOfPropertiesToIndex.get(listOfPropertiesWithPath);
		String letterID = getDescriptorName().concat(letterIndex.toString());
		PositionOS positionOS = (PositionOS) position.upgradeAsTheElementOfAnOrderedSet(listOfPropertiesToIndex);
		PlatonicLetterOS platonicLetterOS = 
				(PlatonicLetterOS) platonicLetter.upgradeAsTheElementOfAnOrderedSet(listOfPropertiesToIndex);
		letterOS = new LetterOS(letterID, isCodingElement, positionOS, platonicLetterOS);
		return letterOS;		
	}		

}
