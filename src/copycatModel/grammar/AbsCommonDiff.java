package copycatModel.grammar;

import java.util.ArrayList;
import java.util.List;

import copycatModel.impl.SynTreeIntegrableElementImpl;

public class AbsCommonDiff extends SynTreeIntegrableElementImpl implements Cloneable {

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
		sB.append(absCommonDiffValue);
		listOfPropertiesWithPath.add(sB.toString());
		return listOfPropertiesWithPath;
	}
	
	@Override
	public List<String> getListOfRelevantPropertiesWithPath() {
		return getListOfPropertiesWithPath();
	}		

}