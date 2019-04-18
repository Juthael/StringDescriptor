package model.copycatModel.synTreeGrammar;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import model.copycatModel.ordSetGrammar.PlatonicLetterOS;
import model.orderedSetModel.ILowerSetElement;
import model.orderedSetModel.impl.MinimalLowerSetElement;
import model.synTreeModel.ISynTreeElement;
import model.synTreeModel.impl.SynTreeElementImpl;
import settings.Settings;

public class PlatonicLetter extends SynTreeElementImpl implements ISynTreeElement, Cloneable {

	private static final String DESCRIPTOR_NAME = "platonicLetter";
	private String platonicLetterValue; 
	
	public PlatonicLetter(String platonicLetterValue) {
		this.platonicLetterValue = platonicLetterValue;
	}
	
	protected PlatonicLetter clone() throws CloneNotSupportedException {
		PlatonicLetter clonePlatonicLetter = new PlatonicLetter(platonicLetterValue);
		return clonePlatonicLetter;
	}

	@Override
	public String getDescriptorName() {
		return DESCRIPTOR_NAME;
	}
	
	@Override
	public List<String> getListOfPropertiesWithPath() {
		List<String> listOfPropertiesWithPath = new ArrayList<String>();
		StringBuilder sB = new StringBuilder();
		sB.append(DESCRIPTOR_NAME);
		sB.append(Settings.PATH_SEPARATOR);
		sB.append(platonicLetterValue);
		listOfPropertiesWithPath.add(sB.toString());
		return listOfPropertiesWithPath;
	}	
	
	@Override
	public List<String> getListOfRelevantPropertiesWithPath() {
		return getListOfPropertiesWithPath();
	}	
	
	@Override
	public ILowerSetElement upgradeAsTheElementOfAnOrderedSet(Map<List<String>, Integer> listOfPropertiesToIndex) {
		ILowerSetElement platonicLetterOS;
		List<String> listOfPropertiesWithPath = getListOfPropertiesWithPath();
		Integer platonicLetterIndex = listOfPropertiesToIndex.get(listOfPropertiesWithPath);
		String platonicLetterID = getDescriptorName().concat(platonicLetterIndex.toString());
		MinimalLowerSetElement platonicLetterProperty = new MinimalLowerSetElement(platonicLetterValue);
		platonicLetterOS = new PlatonicLetterOS(platonicLetterID, platonicLetterProperty);
		return platonicLetterOS;		
	}	

}
