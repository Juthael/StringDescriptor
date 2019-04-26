package model.copycatModel.synTreeGrammar;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import model.copycatModel.ordSetGrammar.factory.OSFactory;
import model.orderedSetModel.IOrderedSet;
import model.orderedSetModel.impl.MinimalOS;
import model.synTreeModel.ISynTreeElement;
import model.synTreeModel.impl.SynTreeElementImpl;
import settings.Settings;

public class AbsCommonDiff extends SynTreeElementImpl implements ISynTreeElement, Cloneable {

	private static final String DESCRIPTOR_NAME = "absCommonDiff";
	private final String absCommonDiffValue; // {"0", "1", "2"}
	
	public AbsCommonDiff(String absCommonDiffValue) {
		this.absCommonDiffValue = absCommonDiffValue;
	}
	
	@Override
	protected AbsCommonDiff clone() {
		AbsCommonDiff cloneAbsCommonDiff = new AbsCommonDiff(absCommonDiffValue);
		return cloneAbsCommonDiff;
	}
	
	@Override
	public String getDescriptorName() {
		return DESCRIPTOR_NAME;
	}
	
	@Override
	public List<String> getListOfPropertiesWithPath() {
		List<String> listOfPropertiesWithPath = new ArrayList<String>();
		StringBuilder sB = new StringBuilder();
		sB.append(DESCRIPTOR_NAME);
		sB.append(Settings.PATH_SEPARATOR);
		sB.append(absCommonDiffValue);
		listOfPropertiesWithPath.add(sB.toString());
		return listOfPropertiesWithPath;
	}
	
	@Override
	public List<String> getListOfRelevantPropertiesWithPath() {
		return getListOfPropertiesWithPath();
	}		
	
	@Override
	public IOrderedSet upgradeAsTheElementOfAnOrderedSet(Map<List<String>, Integer> listOfPropertiesToIndex) {
		IOrderedSet absCommonDiffOS;
		List<String> listOfPropertiesWithPath = getListOfPropertiesWithPath();
		Integer absCommonDiffIndex = listOfPropertiesToIndex.get(listOfPropertiesWithPath);
		String absCommonDiffID = getDescriptorName().concat(absCommonDiffIndex.toString());
		MinimalOS absCommonDiffProperty = new MinimalOS(absCommonDiffValue);
		absCommonDiffOS = OSFactory.getAbsCommonDiffOS(absCommonDiffID, absCommonDiffProperty);
		return absCommonDiffOS;		
	}	

}
