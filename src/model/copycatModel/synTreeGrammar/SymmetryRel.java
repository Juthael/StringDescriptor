package model.copycatModel.synTreeGrammar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import model.copycatModel.ordSetGrammar.IDimensionOS;
import model.copycatModel.ordSetGrammar.IEnumerationOS;
import model.copycatModel.ordSetGrammar.ISymmetryOS;
import model.copycatModel.ordSetGrammar.SymmetryRelOS;
import model.generalModel.IElement;
import model.orderedSetModel.IOrderedSet;
import model.synTreeModel.ISynTreeElement;

public class SymmetryRel extends Relation implements ISynTreeElement, Cloneable {

	private Symmetry symmetry;
	
	public SymmetryRel(Dimension dimension, Enumeration enumeration, Symmetry symmetry) {
		super(dimension, enumeration);
		this.symmetry = symmetry;			
	}
	
	@Override
	protected SymmetryRel clone() throws CloneNotSupportedException {
		SymmetryRel cloneSymmetryRel;
		Dimension cloneDimension = dimension.clone();
		Enumeration cloneEnumeration = this.enumeration.clone();
		Symmetry cloneSymmetry = symmetry.clone();
		cloneSymmetryRel = new SymmetryRel(cloneDimension, cloneEnumeration, cloneSymmetry);
		return cloneSymmetryRel;
	}	
	
	@Override
	public List<IElement> getListOfComponents(){
		List<IElement> componentDescriptors = new ArrayList<IElement>(
				Arrays.asList(dimension, enumeration, symmetry));
		return componentDescriptors;
	}	
	
	@Override
	public IOrderedSet upgradeAsTheElementOfAnOrderedSet(Map<List<String>, Integer> listOfPropertiesToIndex) {
		IOrderedSet symmetryRel;
		List<String> listOfPropertiesWithPath = getListOfPropertiesWithPath();
		Integer symmetryRelIndex = listOfPropertiesToIndex.get(listOfPropertiesWithPath);
		String symmetryRelID = getDescriptorName().concat(symmetryRelIndex.toString());
		IDimensionOS dimensionOS = (IDimensionOS) dimension.upgradeAsTheElementOfAnOrderedSet(listOfPropertiesToIndex);
		IEnumerationOS enumerationOS = (IEnumerationOS) enumeration.upgradeAsTheElementOfAnOrderedSet(listOfPropertiesToIndex);
		ISymmetryOS symmetryOS = (ISymmetryOS) symmetry.upgradeAsTheElementOfAnOrderedSet(listOfPropertiesToIndex);
		symmetryRel = new SymmetryRelOS(symmetryRelID, dimensionOS, enumerationOS, symmetryOS);
		return symmetryRel;		
	}	
}
