package syntacticTreesGeneration.implementations;

import java.util.ArrayList;

import exceptions.DescriptorsBuilderCriticalException;
import syntacticTreesGeneration.interfaces.EnumerationRelationalDataInterface;
import syntacticTreesGeneration.interfaces.RelationalDataInterface;
import syntacticTreesGeneration.interfaces.SymmetryRelationalDataInterface;

public class SymmetryRelationalDataV1 extends EnumerationRelationalDataV1 implements RelationalDataInterface, SymmetryRelationalDataInterface {

	private final String typeOfSymmetry;
	
	public SymmetryRelationalDataV1(String dimensionValue, String enumerationValue, String typeOfSymmetry) 
			throws DescriptorsBuilderCriticalException {
		super(dimensionValue, enumerationValue);
		if (!typeOfSymmetry.isEmpty()) {
			changeRelationName();
			this.typeOfSymmetry = typeOfSymmetry;
		}
		else throw new DescriptorsBuilderCriticalException("SymmetryRelationnalData : empty parameter");
	}
	
	public SymmetryRelationalDataV1(SymmetryRelationalDataInterface symmetryRelationalData) {
		super((EnumerationRelationalDataInterface) symmetryRelationalData);
		changeRelationName();
		this.typeOfSymmetry = symmetryRelationalData.getTypeOfSymmetry();
	}
	
	@Override
	public String getTypeOfSymmetry() {
		return typeOfSymmetry;
	}
	
	@Override
	public void addDimensions(ArrayList<String> dimensions) {
		listOfDimensions.addAll(dimensions);
	}	

	private void changeRelationName() {
		super.relationName = "symmetry"; 
	}

}
