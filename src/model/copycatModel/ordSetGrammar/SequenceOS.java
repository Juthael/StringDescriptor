package model.copycatModel.ordSetGrammar;

import java.util.List;
import java.util.Map;

import model.generalModel.IElement;
import model.orderedSetModel.IOrderedSet;
import model.orderedSetModel.impl.AbstractNonMinimalExplicitOS;
import settings.Settings;

public class SequenceOS extends AbstractNonMinimalExplicitOS implements IOrderedSet {

	private static final String NAME = "sequence";
	private CommonDiffOS commonDiff;
	private AbsCommonDiffOS absCommonDiff;
	
	public SequenceOS(String elementID, CommonDiffOS commonDiff, AbsCommonDiffOS absCommonDiff) {
		super(elementID);
		this.commonDiff = commonDiff;
		this.absCommonDiff = absCommonDiff;
		if (Settings.MAKE_ELEMENT_ID_MORE_EXPLICIT)
			setElementID(getExplicitID());
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
	
	@Override
	public void eliminateRedundancies(Map<String, IOrderedSet> idToIOrderedSet) {
		super.eliminateRedundancies(idToIOrderedSet);
		if (!commonDiff.equals(idToIOrderedSet.get(commonDiff.getElementID())))
			commonDiff = (CommonDiffOS) idToIOrderedSet.get(commonDiff.getElementID());
		commonDiff.eliminateRedundancies(idToIOrderedSet);
		if (!absCommonDiff.equals(idToIOrderedSet.get(absCommonDiff.getElementID())))
			absCommonDiff = (AbsCommonDiffOS) idToIOrderedSet.get(absCommonDiff.getElementID());
		absCommonDiff.eliminateRedundancies(idToIOrderedSet);
	}

	@Override
	public String getExplicitID() {
		String explicitID;
		StringBuilder sB = new StringBuilder();
		sB.append(NAME);
		sB.append("(");
		sB.append(commonDiff.getCommonDiffValue());
		sB.append(")");
		explicitID = sB.toString();
		return explicitID;
	}	

}
