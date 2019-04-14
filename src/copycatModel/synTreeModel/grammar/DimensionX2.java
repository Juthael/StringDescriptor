package copycatModel.synTreeModel.grammar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import copycatModel.synTreeModel.impl.SynTreeIntegrableElementImpl;

public class DimensionX2 extends HowManyDimensions implements Cloneable {

	private static final String descriptorName="dimensionX2";
	private Dimension dimension1;
	private Dimension dimension2;
	
	public DimensionX2(boolean codingDescriptor, Dimension dimension1, Dimension dimension2) {
		super(codingDescriptor);
		this.dimension1 = dimension1;
		this.dimension2 = dimension2;
	}
	
	@Override
	protected DimensionX2 clone() throws CloneNotSupportedException {
		Dimension cloneDimension1 = dimension1.clone();
		Dimension cloneDimension2 = dimension2.clone();
		DimensionX2 cloneDimensionX2 = new DimensionX2(isCodingByDecomposition, cloneDimension1, cloneDimension2);
		return cloneDimensionX2;
	}

	@Override
	protected List<SynTreeIntegrableElementImpl> buildListOfComponents(){
		List<SynTreeIntegrableElementImpl> componentDescriptors = new ArrayList<SynTreeIntegrableElementImpl>(
				Arrays.asList(dimension1, dimension2));
		return componentDescriptors;
	}
	
	@Override
	public String getDescriptorName() {
		return descriptorName;
	}

}
