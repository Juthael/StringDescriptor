package copycatModel.grammar;

import java.util.ArrayList;
import java.util.Arrays;

import copycatModel.implementations.AbstractDescriptorV1;

public class DimensionX4 extends HowManyDimensions implements Cloneable {

	private static final String descriptorName = "dimensionX4";
	private Dimension dimension1;
	private Dimension dimension2;
	private Dimension dimension3;
	private Dimension dimension4;
	
	public DimensionX4(boolean codingDescriptor, Dimension dimension1, Dimension dimension2, Dimension dimension3, 
			Dimension dimension4) {
		super(codingDescriptor);
		this.dimension1 = dimension1;
		this.dimension2 = dimension2;
		this.dimension3 = dimension3;
		this.dimension4 = dimension4;
	}
	
	@Override
	protected DimensionX4 clone() throws CloneNotSupportedException {
		Dimension cloneDimension1 = dimension1.clone();
		Dimension cloneDimension2 = dimension2.clone();
		Dimension cloneDimension3 = dimension3.clone();
		Dimension cloneDimension4 = dimension4.clone();
		DimensionX4 cloneDimensionX4 = new DimensionX4(isCodingDescriptor, cloneDimension1, cloneDimension2, cloneDimension3, 
				cloneDimension4);
		return cloneDimensionX4;		
	}

	@Override
	protected ArrayList<AbstractDescriptorV1> buildListOfComponents(){
		ArrayList<AbstractDescriptorV1> componentDescriptors = new ArrayList<AbstractDescriptorV1>(
				Arrays.asList(dimension1, dimension2, dimension3, dimension4));
		return componentDescriptors;
	}

	@Override
	public String getDescriptorName() {
		return descriptorName;
	}

}
