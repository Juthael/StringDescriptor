package model.copycatModel.ordSetGrammar;

import java.util.List;

import model.generalModel.IElement;
import model.orderedSetModel.ISetElement;
import model.orderedSetModel.impl.MinimalSetElement;
import model.orderedSetModel.impl.NonMinimalRelevantSetElement;

public class CommonDiffOS extends NonMinimalRelevantSetElement implements ISetElement {

	private static final String NAME = "commonDiff";
	private MinimalSetElement commonDiffProperty;
	
	public CommonDiffOS(String elementID, MinimalSetElement commonDiffProperty) {
		super(elementID);
		this.commonDiffProperty = commonDiffProperty;
	}

	@Override
	protected List<IElement> getListOfComponents() {
		List<IElement> listOfComponents = super.getListOfComponents();
		listOfComponents.add(commonDiffProperty);
		return listOfComponents;
	}

	@Override
	public String getDescriptorName() {
		return NAME;
	}

}
