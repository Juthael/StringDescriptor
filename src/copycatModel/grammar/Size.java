package copycatModel.grammar;

import java.util.ArrayList;

import copycatModel.implementations.AbstractDescriptorV1;

public class Size extends AbstractDescriptorV1 implements Cloneable {
	
	private static final String descriptorName = "size";
	private String sizeValue;

	public Size(boolean codingDescriptor, String sizeValue) {
		super(codingDescriptor);
		this.sizeValue = sizeValue;
	}
	
	@Override
	protected Size clone() throws CloneNotSupportedException {
		Size cloneSize = new Size(isCodingDescriptor, sizeValue);
		return cloneSize;
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
		sB.append(sizeValue);
		listOfPropertiesWithPath.add(sB.toString());
		return listOfPropertiesWithPath;
	}
	
	@Override
	public ArrayList<String> getListOfRelevantPropertiesWithPath() {
		return getListOfPropertiesWithPath();
	}	
	
}
