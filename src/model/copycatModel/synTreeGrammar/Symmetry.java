package model.copycatModel.synTreeGrammar;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import exceptions.OrderedSetsGenerationException;
import exceptions.SynTreeGenerationException;
import model.copycatModel.ordSetGrammar.SymmetryOS;
import model.generalModel.IElement;
import model.orderedSetModel.IOrderedSet;
import model.orderedSetModel.impl.MinimalOS;
import model.synTreeModel.IGrammaticalST;
import model.synTreeModel.impl.MinimalST;
import model.synTreeModel.impl.GrammaticalST;
import settings.Settings;

public class Symmetry extends GrammaticalST implements IGrammaticalST, Cloneable {

	private static final String DESCRIPTOR_NAME = "symmetry";
	private final MinimalST symmetryValue;
	
	public Symmetry(String symmetryValue) throws SynTreeGenerationException, CloneNotSupportedException {
		if (symmetryValue.equals(Settings.SYMMETRY_WITH_CENTRAL_ELEMENT) 
				|| symmetryValue.equals(Settings.SYMMETRY_WITHOUT_CENTRAL_ELEMENT)) {
			this.symmetryValue = new MinimalST(symmetryValue);
		}
		else throw new SynTreeGenerationException(
				"Symmetry() : unexpected symmetryValue parameter (" + symmetryValue + ").");
		setHashCode();
	}
	
	@Override
	protected Symmetry clone() throws CloneNotSupportedException {
		try {
			Symmetry cloneSymmetry = new Symmetry(symmetryValue.getValue());
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
	public List<IElement> getListOfComponents(){
		List<IElement> listOfComponents = new ArrayList<IElement>();
		listOfComponents.add(symmetryValue);
		return listOfComponents;
	}	
	
	@Override
	public IOrderedSet upgradeAsTheElementOfAnOrderedSet(Map<List<String>, Integer> listOfPropertiesToIndex) 
			throws OrderedSetsGenerationException {
		IOrderedSet symmetryOS;
		List<String> listOfPropertiesWithPath = getListOfPropertiesWithPath();
		Integer symmetryIndex = listOfPropertiesToIndex.get(listOfPropertiesWithPath);
		String symmetryID = getDescriptorName().concat(symmetryIndex.toString());
		MinimalOS symmetryProperty = (MinimalOS) symmetryValue.upgradeAsTheElementOfAnOrderedSet(listOfPropertiesToIndex);
		symmetryOS = new SymmetryOS(symmetryID, symmetryProperty);
		return symmetryOS;		
	}	

}
