package syntacticTreesGeneration.impl;

import exceptions.SynTreeGenerationException;
import syntacticTreesGeneration.IEnumerationRelationalData;
import syntacticTreesGeneration.IRelationalData;

public class EnumerationRelationalDataImpl implements IRelationalData, IEnumerationRelationalData {

	protected String relationName = "enumeration";
	protected String dimension;
	protected String enumerationValue;
	
	public EnumerationRelationalDataImpl(String dimension, String enumerationValue) throws SynTreeGenerationException {
		if (!dimension.isEmpty() && !enumerationValue.isEmpty()) {
			this.dimension = dimension;
			this.enumerationValue = enumerationValue;
		}
		else throw new SynTreeGenerationException("EnumerationRelationalData : empty parameter.");
	}
	
	public EnumerationRelationalDataImpl(IEnumerationRelationalData enumerationRelationalData) {
		dimension = enumerationRelationalData.getDimension();
		enumerationValue = enumerationRelationalData.getEnumerationValue();
	}	

	@Override
	public String getName() {
		return relationName;
	}

	@Override
	public String getDimension() {
		return dimension;
	}

	@Override
	public String getEnumerationValue() {
		return enumerationValue;
	}

}
