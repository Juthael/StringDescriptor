package copycatModel.grammar;

import java.util.ArrayList;

import copycatModel.implementations.AbstractDescriptorV1;

public class PlatonicLetter extends AbstractDescriptorV1 implements Cloneable{

	private static final String descriptorName = "platonicLetter";
	private String platonicLetterValue; // size=1
	
	public PlatonicLetter(boolean codingDescriptor, String platonicLetterValue) {
		super(codingDescriptor);
		this.platonicLetterValue = platonicLetterValue;
	}
	
	protected PlatonicLetter clone() throws CloneNotSupportedException {
		PlatonicLetter clonePlatonicLetter = new PlatonicLetter(isCodingDescriptor, platonicLetterValue);
		return clonePlatonicLetter;
	}

	@Override
	protected ArrayList<AbstractDescriptorV1> buildListOfComponents(){
		ArrayList<AbstractDescriptorV1> componentDescriptors = new ArrayList<AbstractDescriptorV1>();
		return componentDescriptors;
	}

	@Override
	public String getDescriptorName() {
		return descriptorName;
	}
	
	@Override
	public ArrayList<String> getListOfPropertiesWithPath() {
		ArrayList<String> listOfPropertiesWithPath = new ArrayList<String>();
		StringBuilder sB = new StringBuilder();
		sB.append(descriptorName);
		sB.append("/");
		sB.append(platonicLetterValue);
		listOfPropertiesWithPath.add(sB.toString());
		return listOfPropertiesWithPath;
	}	
	
	@Override
	public ArrayList<String> getListOfRelevantPropertiesWithPath() {
		return getListOfPropertiesWithPath();
	}		

}
