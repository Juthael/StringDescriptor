package syntacticTreesGeneration.implementations;

import java.util.ArrayList;

import exceptions.DescriptorsBuilderCriticalException;
import syntacticTreesGeneration.interfaces.EnumerationRelationalDataInterface;
import syntacticTreesGeneration.interfaces.RelationalDataInterface;

public class EnumerationRelationalDataV1 implements RelationalDataInterface, EnumerationRelationalDataInterface {

	protected String relationName = "enumeration";
	protected ArrayList<String> listOfDimensions = new ArrayList<String>();
	protected String enumerationValue;
	
	public EnumerationRelationalDataV1(String dimensionValue, String enumerationValue) throws DescriptorsBuilderCriticalException {
		if (!dimensionValue.isEmpty() && !enumerationValue.isEmpty()) {
			listOfDimensions.add(dimensionValue);
			this.enumerationValue = enumerationValue;
		}
		else throw new DescriptorsBuilderCriticalException("EnumerationRelationalData : empty parameter.");
	}
	
	public EnumerationRelationalDataV1(EnumerationRelationalDataInterface enumerationRelationalData) {
		listOfDimensions.addAll(enumerationRelationalData.getDimensions());
		enumerationValue = enumerationRelationalData.getEnumerationValue();
	}	

	@Override
	public String getName() {
		return relationName;
	}

	@Override
	public ArrayList<String> getDimensions() {
		return listOfDimensions;
	}

	@Override
	public String getEnumerationValue() {
		return enumerationValue;
	}
	
	@Override
	public void addDimensions(ArrayList<String> dimensions) {
		listOfDimensions.addAll(0, dimensions);
	}

}
