package copycatModel.grammar;

import java.util.ArrayList;

import copycatModel.implementations.AbstractDescriptorV1;

public class Enumeration extends AbstractDescriptorV1 implements Cloneable {

	private static final String descriptorName = "enumeration";
	private String enumerationValue; //"w,x,y,z" (simple enumeration) or "w,x-y,z" (2nd degree enumeration)
	
	public Enumeration(boolean codingDescriptor, String enumerationValue) {
		super(codingDescriptor);
		this.enumerationValue = enumerationValue;
	}
	
	@Override
	protected Enumeration clone() throws CloneNotSupportedException {
		Enumeration cloneEnumeration = new Enumeration(isCodingDescriptor, enumerationValue);
		return cloneEnumeration;
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
		sB.append(enumerationValue);
		listOfPropertiesWithPath.add(sB.toString());
		return listOfPropertiesWithPath;
	}	
	
	@Override
	public ArrayList<String> getListOfRelevantPropertiesWithPath() {
		return getListOfPropertiesWithPath();
	}		

}
