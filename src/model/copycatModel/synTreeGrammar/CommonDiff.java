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

public class CommonDiff extends SynTreeElementImpl implements ISynTreeElement, Cloneable {

	private static final String DESCRIPTOR_NAME = "commonDiff";
	private final String commonDiffValue; 
	
	public CommonDiff(String commonDiffValue) {
		this.commonDiffValue = commonDiffValue;
	}
	
	@Override
	protected CommonDiff clone() throws CloneNotSupportedException {
		CommonDiff cloneCommonDiff = new CommonDiff(commonDiffValue);
		return cloneCommonDiff;
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
		sB.append(commonDiffValue);
		listOfPropertiesWithPath.add(sB.toString());
		return listOfPropertiesWithPath;
	}	
	
	@Override
	public List<String> getListOfRelevantPropertiesWithPath() {
		return getListOfPropertiesWithPath();
	}

	@Override
	public IOrderedSet upgradeAsTheElementOfAnOrderedSet(Map<List<String>, Integer> listOfPropertiesToIndex) {
		IOrderedSet commonDiffOS;
		List<String> listOfPropertiesWithPath = getListOfPropertiesWithPath();
		Integer commonDiffIndex = listOfPropertiesToIndex.get(listOfPropertiesWithPath);
		String commonDiffID = getDescriptorName().concat(commonDiffIndex.toString());
		MinimalOS commonDiffProperty = new MinimalOS(commonDiffValue);
		commonDiffOS = OSFactory.getCommonDiffOS(commonDiffID, commonDiffProperty);
		return commonDiffOS;		
	}			

}
