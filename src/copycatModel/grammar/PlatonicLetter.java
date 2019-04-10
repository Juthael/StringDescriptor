package copycatModel.grammar;

import java.util.ArrayList;
import java.util.List;

import copycatModel.impl.SynTreeIntegrableElementImpl;
import settings.Settings;

public class PlatonicLetter extends SynTreeIntegrableElementImpl implements Cloneable{

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
		sB.append(Settings.PATH_SEPARATOR);
		sB.append(platonicLetterValue);
		listOfPropertiesWithPath.add(sB.toString());
		return listOfPropertiesWithPath;
	}	
	
	@Override
	public List<String> getListOfRelevantPropertiesWithPath() {
		return getListOfPropertiesWithPath();
	}		

}
