package model.copycatModel.ordSetGrammar;

import java.util.List;

import model.generalModel.IElement;
import model.orderedSetModel.ISetElement;
import model.orderedSetModel.impl.PropertyOSImpl;
import model.orderedSetModel.impl.SetElementImpl;

public class CommonDiffOS extends SetElementImpl implements ISetElement {

	private PropertyOSImpl commonDiffProperty;
	
	public CommonDiffOS(String elementID, PropertyOSImpl commonDiffProperty) {
		super(elementID);
		this.commonDiffProperty = commonDiffProperty;
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
