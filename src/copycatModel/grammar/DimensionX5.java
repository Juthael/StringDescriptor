package copycatModel.grammar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import copycatModel.impl.SynTreeIntegrableElementImpl;

public class DimensionX5 extends HowManyDimensions implements Cloneable {

	private static final String descriptorName = "dimensionX5";
	private Dimension dimension1;
	private Dimension dimension2;
	private Dimension dimension3;
	private Dimension dimension4;
	private Dimension dimension5;
	
	public DimensionX5(boolean codingDescriptor, Dimension dimension1, Dimension dimension2, Dimension dimension3, 
			Dimension dimension4, Dimension dimension5) {
		super(codingDescriptor);
		this.dimension1 = dimension1;
		this.dimension2 = dimension2;
		this.dimension3 = dimension3;
		this.dimension4 = dimension4;
		this.dimension5 = dimension5;
	}
	
	@Override
	protected DimensionX5 clone() throws CloneNotSupportedException {
		Dimension cloneDimension1 = dimension1.clone();
		Dimension cloneDimension2 = dimension2.clone();
		Dimension cloneDimension3 = dimension3.clone();
		Dimension cloneDimension4 = dimension4.clone();
		Dimension cloneDimension5 = dimension5.clone();
		DimensionX5 cloneDimensionX5 = new DimensionX5(isCodingDescriptor, cloneDimension1, cloneDimension2, cloneDimension3, 
				cloneDimension4, cloneDimension5);
		return cloneDimensionX5;		
	}

	@Override
	protected List<SynTreeIntegrableElementImpl> buildListOfComponents(){
		List<SynTreeIntegrableElementImpl> componentDescriptors = new ArrayList<SynTreeIntegrableElementImpl>(
				Arrays.asList(dimension1, dimension2, dimension3, dimension4, dimension5));
		return componentDescriptors;
	}

	@Override
	public String getDescriptorName() {
		return descriptorName;
	}

}
