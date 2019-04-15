package model.copycatModel.synTreeGrammar;

import java.util.ArrayList;
import java.util.List;

import exceptions.SynTreeGenerationException;
import model.generalModel.IElement;
import model.synTreeModel.ISynTreeElement;
import model.synTreeModel.impl.SynTreeElementImpl;
import settings.Settings;

public class Symmetry extends SynTreeElementImpl implements ISynTreeElement, Cloneable {

	private static final String DESCRIPTOR_NAME = "symmetry";
	private final String symmetryValue;
	
	public Symmetry(String symmetryValue) throws SynTreeGenerationException {
		super(false);
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
	protected ArrayList<IElement> buildListOfComponents(){
		ArrayList<IElement> componentDescriptors = new ArrayList<IElement>();
		return componentDescriptors;
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

}
