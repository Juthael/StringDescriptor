package model.copycatModel.ordSetGrammar;

import java.util.List;

import model.generalModel.IElement;
import model.orderedSetModel.impl.IrrelevantSetElementImpl;

public class DimensionXOS extends IrrelevantSetElementImpl implements HowManyDimensionsOS {

	private List<DimensionOS> listOfDimensions;
	
	public DimensionXOS(String elementID, List<DimensionOS> listOfDimensions) {
		super(elementID);
		this.listOfDimensions = listOfDimensions;
	}

	@Override
	protected List<IElement> buildListOfComponents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDescriptorName() {
		// TODO Auto-generated method stub
		return null;
	}

}
