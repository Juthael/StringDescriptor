package copycatModel.grammar;

import java.util.ArrayList;

import copycatModel.implementations.AbstractDescriptorV1;

public class AbsCommonDiff extends AbstractDescriptorV1 implements Cloneable {

	private static final String descriptorName = "absCommonDiff";
	private final String absCommonDiffValue; // {"0", "1", "2"}
	
	public AbsCommonDiff(boolean codingDescriptor, String absCommonDiffValue) {
		super(codingDescriptor);
		this.absCommonDiffValue = absCommonDiffValue;
	}
	
	@Override
	protected AbsCommonDiff clone() {
		AbsCommonDiff cloneAbsCommonDiff = new AbsCommonDiff(isCodingDescriptor, absCommonDiffValue);
		return cloneAbsCommonDiff;
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
		sB.append(absCommonDiffValue);
		listOfPropertiesWithPath.add(sB.toString());
		return listOfPropertiesWithPath;
	}
	
	@Override
	public ArrayList<String> getListOfRelevantPropertiesWithPath() {
		return getListOfPropertiesWithPath();
	}		

}
