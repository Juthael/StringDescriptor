package model.copycatModel.synTreeGrammar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.generalModel.IElement;
import model.synTreeModel.ISynTreeElement;
import model.synTreeModel.impl.SynTreeElementImpl;

public class Letter extends RelationsOrLetter implements ISynTreeElement, Cloneable {

	private static final String DESCRIPTOR_NAME = "letter";
	private Position position;
	private PlatonicLetter platonicLetter;
	
	public Letter(Position position, PlatonicLetter platonicLetter) {
		super(false);
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
	protected List<IElement> buildListOfComponents(){
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
	public String getDescriptorName() {
		return DESCRIPTOR_NAME;
	}

}
