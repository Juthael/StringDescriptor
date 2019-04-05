package syntacticTreesGeneration.impl;

import java.util.ArrayList;
import java.util.List;

import exceptions.DescriptorsBuilderException;
import syntacticTreesGeneration.IEnumerationRelationalData;
import syntacticTreesGeneration.IRelationalData;

public class EnumerationRelationalDataImpl implements IRelationalData, IEnumerationRelationalData {

	protected String relationName = "enumeration";
	protected List<String> listOfDimensions = new ArrayList<String>();
	protected String enumerationValue;
	
	public EnumerationRelationalDataImpl(String dimensionValue, String enumerationValue) throws DescriptorsBuilderException {
		if (!dimensionValue.isEmpty() && !enumerationValue.isEmpty()) {
			listOfDimensions.add(dimensionValue);
			this.enumerationValue = enumerationValue;
		}
		else throw new DescriptorsBuilderException("EnumerationRelationalData : empty parameter.");
	}
	
	public EnumerationRelationalDataImpl(IEnumerationRelationalData enumerationRelationalData) {
		listOfDimensions.addAll(enumerationRelationalData.getDimensions());
		enumerationValue = enumerationRelationalData.getEnumerationValue();
	}	

	@Override
	public String getName() {
		return relationName;
	}

	@Override
	public List<String> getDimensions() {
		return listOfDimensions;
	}

	@Override
	public String getEnumerationValue() {
		return enumerationValue;
	}
	
	@Override
	public void addDimensions(List<String> dimensions) {
		listOfDimensions.addAll(0, dimensions);
	}

}
