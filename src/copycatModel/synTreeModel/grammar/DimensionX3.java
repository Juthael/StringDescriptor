package copycatModel.synTreeModel.grammar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import copycatModel.synTreeModel.impl.SynTreeIntegrableElementImpl;

public class DimensionX3 extends HowManyDimensions implements Cloneable {

	private static final String descriptorName = "dimensionX3";
	private Dimension dimension1;
	private Dimension dimension2;
	private Dimension dimension3;
	
	public DimensionX3(boolean codingDescriptor, Dimension dimension1, Dimension dimension2, Dimension dimension3) {
		super(codingDescriptor);
		this.dimension1 = dimension1;
		this.dimension2 = dimension2;
		this.dimension3 = dimension3;
	}
	
	@Override
	protected DimensionX3 clone() throws CloneNotSupportedException {
		Dimension cloneDimension1 = dimension1.clone();
		Dimension cloneDimension2 = dimension2.clone();
		Dimension cloneDimension3 = dimension3.clone();
		DimensionX3 cloneDimensionX3 = new DimensionX3(isCodingByDecomposition, cloneDimension1, cloneDimension2, cloneDimension3);
		return cloneDimensionX3;		
	}

	@Override
	protected List<SynTreeIntegrableElementImpl> buildListOfComponents(){
		List<SynTreeIntegrableElementImpl> componentDescriptors = new ArrayList<SynTreeIntegrableElementImpl>(
				Arrays.asList(dimension1, dimension2, dimension3));
		return componentDescriptors;
	}

	@Override
	public String getDescriptorName() {
		return descriptorName;
	}

}
