package model.copycatModel.ordSetGrammar;

import java.util.List;

import model.generalModel.IElement;
import model.orderedSetModel.impl.MinimalLowerSetElement;
import model.orderedSetModel.impl.NonMinimalRelevantLowerSetElement;

public class AbsCommonDiffOS extends NonMinimalRelevantLowerSetElement implements IAbsCommonDiffOS {
	
	private static final String NAME = "absCommonDiff";
	private MinimalLowerSetElement absCommonDiffProperty;

	public AbsCommonDiffOS(String elementID, MinimalLowerSetElement absCommonDiffProperty) {
		super(elementID);
		this.absCommonDiffProperty = absCommonDiffProperty;
	}

	@Override
	public List<IElement> getListOfComponents() {
		List<IElement> listOfComponents = super.getListOfComponents();
		listOfComponents.add(absCommonDiffProperty);
		return listOfComponents;
	}

	@Override
	public String getDescriptorName() {
		return NAME;
	}

}
