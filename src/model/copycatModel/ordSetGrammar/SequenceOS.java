package model.copycatModel.ordSetGrammar;

import java.util.List;

import model.generalModel.IElement;
import model.orderedSetModel.ISetElement;
import model.orderedSetModel.impl.SetElementImpl;

public class SequenceOS extends SetElementImpl implements ISetElement {

	private CommonDiffOS commonDiff;
	private AbsCommonDiffOS absCommonDiff;
	
	public SequenceOS(String elementID, CommonDiffOS commonDiff, AbsCommonDiffOS absCommonDiff) {
		super(elementID);
		this.commonDiff = commonDiff;
		this.absCommonDiff = absCommonDiff;
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
