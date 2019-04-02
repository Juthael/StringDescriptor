package copycatModel.grammar;

import java.util.ArrayList;
import java.util.Arrays;

import copycatModel.implementations.AbstractDescriptorV1;

public class DimensionX10 extends HowManyDimensions implements Cloneable {

	private static final String descriptorName = "dimensionX10";
	private Dimension dimension1;
	private Dimension dimension2;
	private Dimension dimension3;
	private Dimension dimension4;
	private Dimension dimension5;
	private Dimension dimension6;
	private Dimension dimension7;
	private Dimension dimension8;
	private Dimension dimension9;
	private Dimension dimension10;
	
	public DimensionX10(boolean codingDescriptor, Dimension dimension1, Dimension dimension2, Dimension dimension3, 
			Dimension dimension4, Dimension dimension5, Dimension dimension6, Dimension dimension7, 
			Dimension dimension8, Dimension dimension9, Dimension dimension10) {
		super(codingDescriptor);
		this.dimension1 = dimension1;
		this.dimension2 = dimension2;
		this.dimension3 = dimension3;
		this.dimension4 = dimension4;
		this.dimension5 = dimension5;
		this.dimension6 = dimension6;
		this.dimension7 = dimension7;
		this.dimension8 = dimension8;
		this.dimension9 = dimension9;
		this.dimension10 = dimension10;
	}
	
	@Override
	protected DimensionX10 clone() throws CloneNotSupportedException {
		Dimension cloneDimension1 = dimension1.clone();
		Dimension cloneDimension2 = dimension2.clone();
		Dimension cloneDimension3 = dimension3.clone();
		Dimension cloneDimension4 = dimension4.clone();
		Dimension cloneDimension5 = dimension5.clone();
		Dimension cloneDimension6 = dimension6.clone();
		Dimension cloneDimension7 = dimension7.clone();
		Dimension cloneDimension8 = dimension8.clone();
		Dimension cloneDimension9 = dimension9.clone();
		Dimension cloneDimension10 = dimension10.clone();
		DimensionX10 cloneDimensionX10 = new DimensionX10(isCodingDescriptor, cloneDimension1, cloneDimension2, cloneDimension3, 
				cloneDimension4, cloneDimension5, cloneDimension6, cloneDimension7, cloneDimension8, cloneDimension9, 
				cloneDimension10);
		return cloneDimensionX10;		
	}

	@Override
	protected ArrayList<AbstractDescriptorV1> buildListOfComponents(){
		ArrayList<AbstractDescriptorV1> componentDescriptors = new ArrayList<AbstractDescriptorV1>(
				Arrays.asList(dimension1, dimension2, dimension3, dimension4, dimension5, dimension6, dimension7, 
						dimension8, dimension9, dimension10));
		return componentDescriptors;
	}

	@Override
	public String getDescriptorName() {
		return descriptorName;
	}

}
