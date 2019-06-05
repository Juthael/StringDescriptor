package model.copycatModel.ordSetGrammar;

import java.util.List;

import model.generalModel.IElement;
import model.orderedSetModel.IOrderedSet;
import model.orderedSetModel.impl.NonMinimalExplicitOS;
import settings.Settings;

public class SequenceOS extends NonMinimalExplicitOS implements IOrderedSet {

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
	public void eliminateRedundancies(IOrderedSet orderedSet) {
		super.eliminateRedundancies(orderedSet);
		if (commonDiff.getElementID().equals(orderedSet.getElementID()) && commonDiff != orderedSet) {
			commonDiff = (CommonDiffOS) orderedSet;
		}
		else commonDiff.eliminateRedundancies(orderedSet);
		if (absCommonDiff.getElementID().equals(orderedSet.getElementID()) && absCommonDiff != orderedSet) {
			absCommonDiff = (AbsCommonDiffOS) orderedSet;
		}
		else absCommonDiff.eliminateRedundancies(orderedSet);
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
