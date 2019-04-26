package model.copycatModel.synTreeGrammar;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import exceptions.SynTreeGenerationException;
import model.copycatModel.ordSetGrammar.factory.OSFactory;
import model.orderedSetModel.IOrderedSet;
import model.orderedSetModel.impl.MinimalOS;
import model.synTreeModel.ISynTreeElement;
import model.synTreeModel.impl.SynTreeElementImpl;
import settings.Settings;

public class Symmetry extends SynTreeElementImpl implements ISynTreeElement, Cloneable {

	private static final String DESCRIPTOR_NAME = "symmetry";
	private final String symmetryValue;
	
	public Symmetry(String symmetryValue) throws SynTreeGenerationException {
		if (symmetryValue.equals(Settings.SYMMETRY_WITH_CENTRAL_ELEMENT) 
				|| symmetryValue.equals(Settings.SYMMETRY_WITHOUT_CENTRAL_ELEMENT)) {
			this.symmetryValue = symmetryValue;
		}
		else throw new SynTreeGenerationException(
				"Symmetry() : unexpected symmetryValue parameter (" + symmetryValue + ").");
	}
	
	@Override
	protected Symmetry clone() throws CloneNotSupportedException {
		try {
			Symmetry cloneSymmetry = new Symmetry(symmetryValue);
			return cloneSymmetry;
		}
		catch (SynTreeGenerationException e) {
			throw new CloneNotSupportedException("Symmetry.clone() : " + e.getMessage());
		}
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
		sB.append(symmetryValue);
		listOfPropertiesWithPath.add(sB.toString());
		return listOfPropertiesWithPath;
	}	
	
	@Override
	public List<String> getListOfRelevantPropertiesWithPath() {
		return getListOfPropertiesWithPath();
	}	
	
	@Override
	public IOrderedSet upgradeAsTheElementOfAnOrderedSet(Map<List<String>, Integer> listOfPropertiesToIndex) {
		IOrderedSet symmetryOS;
		List<String> listOfPropertiesWithPath = getListOfPropertiesWithPath();
		Integer symmetryIndex = listOfPropertiesToIndex.get(listOfPropertiesWithPath);
		String symmetryID = getDescriptorName().concat(symmetryIndex.toString());
		MinimalOS symmetryProperty = new MinimalOS(symmetryValue);
		symmetryOS = OSFactory.getSymmetryOS(symmetryID, symmetryProperty);
		return symmetryOS;		
	}	

}
