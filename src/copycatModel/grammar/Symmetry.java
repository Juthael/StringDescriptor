package copycatModel.grammar;

import java.util.ArrayList;
import java.util.List;

import copycatModel.impl.SynTreeIntegrableElementImpl;
import settings.Settings;

public class Symmetry extends SynTreeIntegrableElementImpl implements Cloneable {

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
	protected ArrayList<SynTreeIntegrableElementImpl> buildListOfComponents(){
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
		sB.append(symmetryValue);
		listOfPropertiesWithPath.add(sB.toString());
		return listOfPropertiesWithPath;
	}	
	
	@Override
	public List<String> getListOfRelevantPropertiesWithPath() {
		return getListOfPropertiesWithPath();
	}	

}
