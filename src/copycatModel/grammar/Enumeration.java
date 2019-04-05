package copycatModel.grammar;

import java.util.ArrayList;
import java.util.List;

import copycatModel.impl.SynTreeIntegrableElementImpl;

public class Enumeration extends SynTreeIntegrableElementImpl implements Cloneable {

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
	protected List<SynTreeIntegrableElementImpl> buildListOfComponents(){
		List<SynTreeIntegrableElementImpl> componentDescriptors = new ArrayList<SynTreeIntegrableElementImpl>();
		return componentDescriptors;
	}
	
	@Override
	public String getDescriptorName() {
		return descriptorName;
	}
	
	@Override
	public List<String> getListOfPropertiesWithPath() {
		List<String> listOfPropertiesWithPath = new ArrayList<String>();
		StringBuilder sB = new StringBuilder();
		sB.append(descriptorName);
		sB.append("/");
		sB.append(enumerationValue);
		listOfPropertiesWithPath.add(sB.toString());
		return listOfPropertiesWithPath;
	}	
	
	@Override
	public List<String> getListOfRelevantPropertiesWithPath() {
		return getListOfPropertiesWithPath();
	}		

}
