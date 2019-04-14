package copycatModel.synTreeModel.grammar;

import java.util.ArrayList;
import java.util.List;

import copycatModel.synTreeModel.impl.SynTreeIntegrableElementImpl;
import settings.Settings;

public class Size extends SynTreeIntegrableElementImpl implements Cloneable {
	
	private static final String descriptorName = "size";
	private String sizeValue;

	public Size(boolean codingDescriptor, String sizeValue) {
		super(codingDescriptor);
		this.sizeValue = sizeValue;
	}
	
	@Override
	protected Size clone() throws CloneNotSupportedException {
		Size cloneSize = new Size(isCodingByDecomposition, sizeValue);
		return cloneSize;
	}
	
	@Override
	protected List<SynTreeIntegrableElementImpl> buildListOfComponents(){
		ArrayList<SynTreeIntegrableElementImpl> componentDescriptors = new ArrayList<SynTreeIntegrableElementImpl>();
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
		sB.append(Settings.PATH_SEPARATOR);
		sB.append(sizeValue);
		listOfPropertiesWithPath.add(sB.toString());
		return listOfPropertiesWithPath;
	}
	
	@Override
	public List<String> getListOfRelevantPropertiesWithPath() {
		return getListOfPropertiesWithPath();
	}	
	
}
