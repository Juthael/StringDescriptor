package model.copycatModel.synTreeGrammar;

import java.util.ArrayList;
import java.util.List;

import exceptions.SynTreeGenerationException;
import model.generalModel.IElement;
import model.synTreeModel.ISynTreeElement;
import settings.Settings;

public class DimensionX extends HowManyDimensions implements ISynTreeElement, Cloneable {

	private static final String DESCRIPTOR_PARTIAL_NAME = "descriptorX";
	private List<Dimension> listOfDimensions;
	
	public DimensionX(List<Dimension> listOfDimensions) throws SynTreeGenerationException {
		super(false);
		if (listOfDimensions.size() > 1 && listOfDimensions.size() <= Settings.MAX_NB_OF_DIMENSIONS_IN_RELATIONS) {
			this.listOfDimensions = listOfDimensions;
		}
		else throw new SynTreeGenerationException("DimensionX() : illegal number of dimensions (" + listOfDimensions.size() + ")");
	}

	@Override
	protected HowManyDimensions clone() throws CloneNotSupportedException {
		DimensionX cloneDimensionX;
		List<Dimension> cloneListOfDimension = new ArrayList<Dimension>();
		for (Dimension dimension : listOfDimensions)
			cloneListOfDimension.add(dimension.clone());
		try {
			cloneDimensionX = new DimensionX(cloneListOfDimension);
		}
		catch (SynTreeGenerationException e) {
			throw new CloneNotSupportedException("DimensionX.clone() : " + e.getMessage());
		}
		return cloneDimensionX;
	}
	
	@Override
	protected List<IElement> buildListOfComponents() {
		List<IElement> listOfComponents = new ArrayList<IElement>();
		for (Dimension dimension : listOfDimensions) {
			listOfComponents.add(dimension);
		}
		return listOfComponents;
	}

	@Override
	public String getDescriptorName() {
		String name = DESCRIPTOR_PARTIAL_NAME.concat(Integer.toString(listOfDimensions.size()));
		return name;
	}



}
