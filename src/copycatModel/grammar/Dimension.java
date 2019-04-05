package copycatModel.grammar;

import java.util.ArrayList;
import java.util.List;

import copycatModel.impl.SynTreeIntegrableElementImpl;

public class Dimension extends HowManyDimensions implements Cloneable {

	private static final String descriptorName = "dimension";
	private String dimensionValue;
	
	public Dimension(boolean codingDescriptor, String dimensionValue) {
		super(codingDescriptor);
		this.dimensionValue = codeDimensionValue(dimensionValue);
	}
	
	@Override
	protected Dimension clone() throws CloneNotSupportedException {
		Dimension cloneDimension = new Dimension(isCodingDescriptor, dimensionValue);
		return cloneDimension;
	}

	@Override
	protected List<SynTreeIntegrableElementImpl> buildListOfComponents() {
		List<SynTreeIntegrableElementImpl> componentDescriptors = new ArrayList<SynTreeIntegrableElementImpl>();
		return componentDescriptors;
	}
	
	@Override
	public String getDescriptorName() {
		return descriptorName;
	}
	
	@Override
	public List<String> getListOfPropertiesWithPath() {
		List<String> listOfPropertiesWithPath = new ArrayList<String>();
		StringBuilder sB = new StringBuilder();
		sB.append(descriptorName);
		sB.append("/");
		sB.append(dimensionValue);
		listOfPropertiesWithPath.add(sB.toString());
		return listOfPropertiesWithPath;
	}
	
	@Override
	public List<String> getListOfRelevantPropertiesWithPath() {
		return getListOfPropertiesWithPath();
	}		
	
	private String codeDimensionValue(String fullDimensionValue) {
		String codedDimension;
		String[] dimensionArray = fullDimensionValue.split("/");
		StringBuilder codedDimensionValueBuilder = new StringBuilder();
		int lastGroupsIndex = 0;
		for (int i=0 ; i<(dimensionArray.length-1) ; i++) {
			if (dimensionArray[i].equals("groups"))
				lastGroupsIndex = i;
		}
		for (int i=0 ; i<(dimensionArray.length-1) ; i++) {
			if (dimensionArray[i].equals("groups")) {
				codedDimensionValueBuilder.append(":");
			}
			if (i > lastGroupsIndex) {
				if (!dimensionArray[i].contains("X")) {
					codedDimensionValueBuilder.append(dimensionArray[i]);
					codedDimensionValueBuilder.append(".");
				}
			}
		}		
		codedDimensionValueBuilder.append(dimensionArray[dimensionArray.length-1]);
		codedDimension = codedDimensionValueBuilder.toString();		
		return codedDimension;
	}

}
