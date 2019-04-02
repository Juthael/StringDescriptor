package copycatModel.grammar;

import java.util.ArrayList;
import java.util.Arrays;

import copycatModel.implementations.AbstractDescriptorV1;

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
	protected ArrayList<AbstractDescriptorV1> buildListOfComponents(){
		ArrayList<AbstractDescriptorV1> componentDescriptors = new ArrayList<AbstractDescriptorV1>(
				Arrays.asList(position, platonicLetter));
		return componentDescriptors;
	}
	
	@Override
	protected ArrayList<AbstractDescriptorV1> buildListOfRelevantComponentsForRelationBuilding() {
		ArrayList<AbstractDescriptorV1> componentDescriptors = new ArrayList<AbstractDescriptorV1>(
				Arrays.asList(platonicLetter));
		return componentDescriptors;
	}	

	@Override
	public String getDescriptorName() {
		return descriptorName;
	}

}
