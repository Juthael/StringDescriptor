package model.copycatModel.synTreeGrammar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.generalModel.IElement;
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
	protected List<IElement> buildListOfComponents(){
		List<IElement> componentDescriptors = new ArrayList<IElement>(
				Arrays.asList(dimension, enumeration, symmetry));
		return componentDescriptors;
	}	
}
