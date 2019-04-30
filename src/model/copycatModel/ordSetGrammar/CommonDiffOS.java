package model.copycatModel.ordSetGrammar;

import java.util.List;

import model.generalModel.IElement;
import model.orderedSetModel.IOrderedSet;
import model.orderedSetModel.impl.MinimalOS;
import model.orderedSetModel.impl.NonMinimalRelevantOS;

public class CommonDiffOS extends NonMinimalRelevantOS implements IOrderedSet {

	private static final String NAME = "commonDiff";
	private MinimalOS commonDiffProperty;
	
	public CommonDiffOS(String elementID, MinimalOS commonDiffProperty) {
		super(elementID);
		this.commonDiffProperty = commonDiffProperty;
	}

	@Override
	public List<IElement> getListOfComponents() {
		List<IElement> listOfComponents = super.getListOfComponents();
		listOfComponents.add(commonDiffProperty);
		return listOfComponents;
	}

	@Override
	public String getDescriptorName() {
		return NAME;
	}

}
