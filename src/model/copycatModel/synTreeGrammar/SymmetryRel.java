package model.copycatModel.synTreeGrammar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import model.copycatModel.ordSetGrammar.DimensionOS;
import model.copycatModel.ordSetGrammar.EnumerationOS;
import model.copycatModel.ordSetGrammar.SymmetryOS;
import model.copycatModel.ordSetGrammar.SymmetryRelOS;
import model.generalModel.IElement;
import model.orderedSetModel.ISetElement;
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
	public ISetElement upgradeAsTheElementOfAnOrderedSet(Map<List<String>, Integer> listOfPropertiesToIndex) {
		ISetElement symmetryRel;
		List<String> listOfPropertiesWithPath = getListOfPropertiesWithPath();
		Integer symmetryRelIndex = listOfPropertiesToIndex.get(listOfPropertiesWithPath);
		String symmetryRelID = getDescriptorName().concat(symmetryRelIndex.toString());
		DimensionOS dimensionOS = (DimensionOS) dimension.upgradeAsTheElementOfAnOrderedSet(listOfPropertiesToIndex);
		EnumerationOS enumerationOS = (EnumerationOS) enumeration.upgradeAsTheElementOfAnOrderedSet(listOfPropertiesToIndex);
		SymmetryOS symmetryOS = (SymmetryOS) symmetry.upgradeAsTheElementOfAnOrderedSet(listOfPropertiesToIndex);
		symmetryRel = new SymmetryRelOS(symmetryRelID, dimensionOS, enumerationOS, symmetryOS);
		return symmetryRel;		
	}	
}
