package model.copycatModel.ordSetGrammar;

import java.util.ArrayList;
import java.util.List;

import model.generalModel.IElement;
import model.orderedSetModel.ISetElement;
import model.orderedSetModel.impl.PropertyOSImpl;
import model.orderedSetModel.impl.SetElementImpl;

public class AbsCommonDiffOS extends SetElementImpl implements ISetElement {
	
	private static final String name = "absCommonDiff";
	private PropertyOSImpl absCommonDiffProperty;

	public AbsCommonDiffOS(String elementID, PropertyOSImpl absCommonDiffProperty) {
		super(elementID);
		this.absCommonDiffProperty = absCommonDiffProperty;
	}

	@Override
	protected List<IElement> buildListOfComponents() {
		List<IElement> listOfComponents = new ArrayList<IElement>();
		listOfComponents.add(absCommonDiffProperty);
		return listOfComponents;
	}

	@Override
	public String getDescriptorName() {
		return name;
	}

}
