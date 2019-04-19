package model.copycatModel.synTreeGrammar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import model.copycatModel.ordSetGrammar.IPlatonicLetterOS;
import model.copycatModel.ordSetGrammar.IPositionOS;
import model.copycatModel.ordSetGrammar.LetterOS;
import model.generalModel.IElement;
import model.orderedSetModel.ILowerSetElement;
import model.synTreeModel.ISynTreeElement;
import model.synTreeModel.impl.SynTreeElementImpl;

public class Letter extends RelationsOrLetter implements ISynTreeElement, Cloneable {

	private static final String DESCRIPTOR_NAME = "letter";
	private Position position;
	private PlatonicLetter platonicLetter;
	
	public Letter(Position position, PlatonicLetter platonicLetter) {
		this.position = position;
		this.platonicLetter = platonicLetter;
	}
	
	@Override
	protected Letter clone() throws CloneNotSupportedException {
		Letter cloneLetter;
		Position clonePosition = position.clone();
		PlatonicLetter clonePlatonicLetter = platonicLetter.clone();
		cloneLetter = new Letter(clonePosition, clonePlatonicLetter);
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
	protected List<SynTreeElementImpl> buildListOfRelevantComponentsForRelationBuilding() {
		List<SynTreeElementImpl> componentDescriptors = new ArrayList<SynTreeElementImpl>(
				Arrays.asList(platonicLetter));
		return componentDescriptors;
	}	
	
	@Override
	public ILowerSetElement upgradeAsTheElementOfAnOrderedSet(Map<List<String>, Integer> listOfPropertiesToIndex) {
		ILowerSetElement letterOS;
		List<String> listOfPropertiesWithPath = getListOfPropertiesWithPath();
		Integer letterIndex = listOfPropertiesToIndex.get(listOfPropertiesWithPath);
		String letterID = getDescriptorName().concat(letterIndex.toString());
		IPositionOS positionOS = (IPositionOS) position.upgradeAsTheElementOfAnOrderedSet(listOfPropertiesToIndex);
		IPlatonicLetterOS platonicLetterOS = 
				(IPlatonicLetterOS) platonicLetter.upgradeAsTheElementOfAnOrderedSet(listOfPropertiesToIndex);
		letterOS = new LetterOS(letterID, positionOS, platonicLetterOS);
		return letterOS;		
	}		

}
