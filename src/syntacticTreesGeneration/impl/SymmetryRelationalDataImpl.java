package syntacticTreesGeneration.impl;

import exceptions.SynTreeGenerationException;
import syntacticTreesGeneration.IEnumerationRelationalData;
import syntacticTreesGeneration.IRelationalData;
import syntacticTreesGeneration.ISymmetryRelationalData;

public class SymmetryRelationalDataImpl extends EnumerationRelationalDataImpl implements IRelationalData, ISymmetryRelationalData {

	private final String typeOfSymmetry;
	
	public SymmetryRelationalDataImpl(String dimensionValue, String enumerationValue, String typeOfSymmetry) 
			throws SynTreeGenerationException {
		super(dimensionValue, enumerationValue);
		if (!typeOfSymmetry.isEmpty()) {
			changeRelationName();
			this.typeOfSymmetry = typeOfSymmetry;
		}
		else throw new SynTreeGenerationException("SymmetryRelationnalData : empty parameter");
	}
	
	public SymmetryRelationalDataImpl(ISymmetryRelationalData symmetryRelationalData) {
		super((IEnumerationRelationalData) symmetryRelationalData);
		changeRelationName();
		this.typeOfSymmetry = symmetryRelationalData.getTypeOfSymmetry();
	}
	
	@Override
	public String getTypeOfSymmetry() {
		return typeOfSymmetry;
	}

	private void changeRelationName() {
		super.relationName = "symmetry"; 
	}

}
