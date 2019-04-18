package model.copycatModel.ordSetGrammar;

import java.util.List;

import model.generalModel.IElement;
import model.orderedSetModel.impl.NonMinimalDeadLowerSetElement;

public class DimensionXOS2Dead extends NonMinimalDeadLowerSetElement implements IDimensionXOS {

	private static final String PARTIAL_NAME = "dimensionX";
	private List<DimensionOS> listOfDimensions;
	
	public DimensionXOS2Dead(String elementID, List<DimensionOS> listOfDimensions) {
		super(elementID);
		this.listOfDimensions = listOfDimensions;
	}

	@Override
	public List<IElement> getListOfComponents() {
		List<IElement> listOfComponents = super.getListOfComponents();
		listOfComponents.addAll(listOfDimensions);
		return listOfComponents;
	}

	@Override
	public String getDescriptorName() {
		String name = PARTIAL_NAME.concat(Integer.toString(listOfDimensions.size()));
		return name;
	}

}
