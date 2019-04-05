package syntacticTreesGeneration.impl;

import java.util.List;

import exceptions.DescriptorsBuilderException;
import syntacticTreesGeneration.IEnumerationRelationalData;
import syntacticTreesGeneration.IRelationalData;
import syntacticTreesGeneration.ISymmetryRelationalData;

public class SymmetryRelationalDataImpl extends EnumerationRelationalDataImpl implements IRelationalData, ISymmetryRelationalData {

	private final String typeOfSymmetry;
	
	public SymmetryRelationalDataImpl(String dimensionValue, String enumerationValue, String typeOfSymmetry) 
			throws DescriptorsBuilderException {
		super(dimensionValue, enumerationValue);
		if (!typeOfSymmetry.isEmpty()) {
			changeRelationName();
			this.typeOfSymmetry = typeOfSymmetry;
		}
		else throw new DescriptorsBuilderException("SymmetryRelationnalData : empty parameter");
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
	
	@Override
	public void addDimensions(List<String> dimensions) {
		listOfDimensions.addAll(dimensions);
	}	

	private void changeRelationName() {
		super.relationName = "symmetry"; 
	}

}
