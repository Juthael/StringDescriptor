package copycatModel.synTreeModel.grammar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import copycatModel.synTreeModel.impl.SynTreeIntegrableElementImpl;

public class SymmetryRel extends Relation implements Cloneable {

	private Symmetry symmetry;
	
	public SymmetryRel(boolean codingDescriptor, Dimension dimension, Enumeration enumeration, Symmetry symmetry) {
		super(codingDescriptor, dimension, enumeration);
		this.symmetry = symmetry;			
	}
	
	@Override
	protected SymmetryRel clone() throws CloneNotSupportedException {
		SymmetryRel cloneSymmetryRel;
		Dimension cloneDimension = dimension.clone();
		Enumeration cloneEnumeration = this.enumeration.clone();
		Symmetry cloneSymmetry = symmetry.clone();
		cloneSymmetryRel = new SymmetryRel(isCodingByDecomposition, cloneDimension, cloneEnumeration, cloneSymmetry);
		return cloneSymmetryRel;
	}	
	
	@Override
	protected List<SynTreeIntegrableElementImpl> buildListOfComponents(){
		List<SynTreeIntegrableElementImpl> componentDescriptors = new ArrayList<SynTreeIntegrableElementImpl>(
				Arrays.asList(dimension, enumeration, symmetry));
		return componentDescriptors;
	}	
}
