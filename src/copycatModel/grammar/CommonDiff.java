package copycatModel.grammar;

import java.util.ArrayList;
import java.util.List;

import copycatModel.impl.SynTreeIntegrableElementImpl;

public class CommonDiff extends SynTreeIntegrableElementImpl implements Cloneable {

	private static final String descriptorName = "commonDiff";
	private final String commonDiffValue; // "-3"< x <"3"
	
	public CommonDiff(boolean codingDescriptor, String commonDiffValue) {
		super(codingDescriptor);
		this.commonDiffValue = commonDiffValue;
	}
	
	@Override
	protected CommonDiff clone() throws CloneNotSupportedException {
		CommonDiff cloneCommonDiff = new CommonDiff(isCodingDescriptor, commonDiffValue);
		return cloneCommonDiff;
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
		sB.append(commonDiffValue);
		listOfPropertiesWithPath.add(sB.toString());
		return listOfPropertiesWithPath;
	}	
	
	@Override
	public List<String> getListOfRelevantPropertiesWithPath() {
		return getListOfPropertiesWithPath();
	}		

}
