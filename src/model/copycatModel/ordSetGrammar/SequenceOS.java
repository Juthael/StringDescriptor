package model.copycatModel.ordSetGrammar;

import java.util.List;

import model.generalModel.IElement;
import model.orderedSetModel.ILowerSetElement;
import model.orderedSetModel.impl.NonMinimalRelevantLowerSetElement;

public class SequenceOS extends NonMinimalRelevantLowerSetElement implements ILowerSetElement {

	private static final String NAME = "sequence";
	private ICommonDiffOS commonDiff;
	private IAbsCommonDiffOS absCommonDiff;
	
	public SequenceOS(String elementID, ICommonDiffOS commonDiff, IAbsCommonDiffOS absCommonDiff) {
		super(elementID);
		this.commonDiff = commonDiff;
		this.absCommonDiff = absCommonDiff;
	}

	@Override
	public List<IElement> getListOfComponents() {
		List<IElement> listOfComponents = super.getListOfComponents();
		listOfComponents.add(commonDiff);
		listOfComponents.add(absCommonDiff);
		return listOfComponents;	
	}

	@Override
	public String getDescriptorName() {
		return NAME;
	}

}
