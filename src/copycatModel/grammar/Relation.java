package copycatModel.grammar;

import java.util.ArrayList;
import java.util.Arrays;

import copycatModel.implementations.AbstractDescriptorV1;

public class Relation extends HowManyRelations implements Cloneable {
	
	protected static final String descriptorName = "relation";
	protected HowManyDimensions dimensionX;
	protected Enumeration enumeration;

	public Relation(boolean codingDescriptor, HowManyDimensions dimensionX, Enumeration enumeration) {
		super(codingDescriptor);
		this.dimensionX = dimensionX;
		this.enumeration = enumeration;
	}
	
	@Override
	protected Relation clone() throws CloneNotSupportedException {
		Relation cloneRelation;
		HowManyDimensions cloneDimensionX;
		switch (dimensionX.getDescriptorName()) {
			case "dimension" :
				Dimension dimensionCasted = (Dimension) dimensionX;
				cloneDimensionX = dimensionCasted.clone();
				break;
			case "dimensionX2" :
				DimensionX2 dimensionX2Casted = (DimensionX2) dimensionX;
				cloneDimensionX = dimensionX2Casted.clone();
				break;
			case "dimensionX3" :
				DimensionX3 dimensionX3Casted = (DimensionX3) dimensionX;
				cloneDimensionX = dimensionX3Casted.clone();
				break;
			default : throw new CloneNotSupportedException("Relation : error in clone() method.");
		}
		Enumeration cloneEnumeration = enumeration.clone();
		cloneRelation = new Relation(isCodingDescriptor, cloneDimensionX, cloneEnumeration);
		return cloneRelation;
	}
	
	@Override
	protected ArrayList<AbstractDescriptorV1> buildListOfComponents(){
		ArrayList<AbstractDescriptorV1> componentDescriptors = new ArrayList<AbstractDescriptorV1>(
				Arrays.asList(dimensionX, enumeration));
		return componentDescriptors;
	}
	
	@Override
	public String getDescriptorName() {
		return descriptorName;
	}
}
