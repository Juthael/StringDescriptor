package copycatModel.grammar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import copycatModel.impl.SynTreeIntegrableElementImpl;

public class Letter extends RelationsOrLetter implements Cloneable{

	private static final String descriptorName = "letter";
	private Position position;
	private PlatonicLetter platonicLetter;
	
	public Letter(boolean codingDescriptor, Position position, PlatonicLetter platonicLetter) {
		super(codingDescriptor);
		this.position = position;
		this.platonicLetter = platonicLetter;
	}
	
	@Override
	protected Letter clone() throws CloneNotSupportedException {
		Letter cloneLetter;
		Position clonePosition = position.clone();
		PlatonicLetter clonePlatonicLetter = platonicLetter.clone();
		cloneLetter = new Letter(isCodingDescriptor, clonePosition, clonePlatonicLetter);
		return cloneLetter;
	}

	@Override
	protected List<SynTreeIntegrableElementImpl> buildListOfComponents(){
		List<SynTreeIntegrableElementImpl> componentDescriptors = new ArrayList<SynTreeIntegrableElementImpl>(
				Arrays.asList(position, platonicLetter));
		return componentDescriptors;
	}
	
	@Override
	protected List<SynTreeIntegrableElementImpl> buildListOfRelevantComponentsForRelationBuilding() {
		List<SynTreeIntegrableElementImpl> componentDescriptors = new ArrayList<SynTreeIntegrableElementImpl>(
				Arrays.asList(platonicLetter));
		return componentDescriptors;
	}	

	@Override
	public String getDescriptorName() {
		return descriptorName;
	}

}
