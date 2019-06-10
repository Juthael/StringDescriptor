package syntacticTreesGeneration.impl;

import exceptions.SynTreeGenerationException;
import syntacticTreesGeneration.IEnumerationRelationalData;
import syntacticTreesGeneration.IRelationalData;

public class EnumerationRelationalData implements IRelationalData, IEnumerationRelationalData {

	protected String relationName = "enumeration";
	protected String dimension;
	protected String enumerationValue;
	
	public EnumerationRelationalData(String dimension, String enumerationValue) throws SynTreeGenerationException {
		if (!dimension.isEmpty() && !enumerationValue.isEmpty()) {
			this.dimension = dimension;
			this.enumerationValue = enumerationValue;
		}
		else throw new SynTreeGenerationException("EnumerationRelationalData : empty parameter.");
	}
	
	public EnumerationRelationalData(IEnumerationRelationalData enumerationRelationalData) {
		dimension = enumerationRelationalData.getIndexedPath();
		enumerationValue = enumerationRelationalData.getEnumerationValue();
	}	

	@Override
	public String getName() {
		return relationName;
	}

	@Override
	public String getIndexedPath() {
		return dimension;
	}

	@Override
	public String getEnumerationValue() {
		return enumerationValue;
	}

}
