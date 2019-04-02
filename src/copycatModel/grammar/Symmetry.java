package copycatModel.grammar;

import java.util.ArrayList;

import copycatModel.implementations.AbstractDescriptorV1;

public class Symmetry extends AbstractDescriptorV1 implements Cloneable {

	private static final String descriptorName="symmetry";
	private final String symmetryValue; // "withCentralElement" or "withoutCentralElement"
	
	public Symmetry(boolean codingDescriptor, String symmetryValue) {
		super(codingDescriptor);
		this.symmetryValue = symmetryValue;
	}
	
	@Override
	protected Symmetry clone() throws CloneNotSupportedException {
		Symmetry cloneSymmetry = new Symmetry(isCodingDescriptor, symmetryValue);
		return cloneSymmetry;
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
		sB.append(symmetryValue);
		listOfPropertiesWithPath.add(sB.toString());
		return listOfPropertiesWithPath;
	}	
	
	@Override
	public ArrayList<String> getListOfRelevantPropertiesWithPath() {
		return getListOfPropertiesWithPath();
	}	

}
