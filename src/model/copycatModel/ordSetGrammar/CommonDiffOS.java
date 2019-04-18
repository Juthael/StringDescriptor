package model.copycatModel.ordSetGrammar;

import java.util.List;

import model.generalModel.IElement;
import model.orderedSetModel.ILowerSetElement;
import model.orderedSetModel.impl.MinimalLowerSetElement;
import model.orderedSetModel.impl.NonMinimalRelevantLowerSetElement;

public class CommonDiffOS extends NonMinimalRelevantLowerSetElement implements ILowerSetElement {

	private static final String NAME = "commonDiff";
	private MinimalLowerSetElement commonDiffProperty;
	
	public CommonDiffOS(String elementID, MinimalLowerSetElement commonDiffProperty) {
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
