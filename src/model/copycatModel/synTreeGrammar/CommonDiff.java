package model.copycatModel.synTreeGrammar;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import model.copycatModel.ordSetGrammar.CommonDiffOS;
import model.generalModel.IElement;
import model.orderedSetModel.ISetElement;
import model.orderedSetModel.impl.MinimalSetElement;
import model.synTreeModel.ISynTreeElement;
import model.synTreeModel.impl.SynTreeElementImpl;
import settings.Settings;

public class CommonDiff extends SynTreeElementImpl implements ISynTreeElement, Cloneable {

	private static final String DESCRIPTOR_NAME = "commonDiff";
	private final String commonDiffValue; // "-3"< x <"3"
	
	public CommonDiff(String commonDiffValue) {
		super(false);
		this.commonDiffValue = commonDiffValue;
	}
	
	@Override
	protected CommonDiff clone() throws CloneNotSupportedException {
		CommonDiff cloneCommonDiff = new CommonDiff(commonDiffValue);
		return cloneCommonDiff;
	}

	@Override
	protected List<IElement> getListOfComponents(){
		List<IElement> componentDescriptors = new ArrayList<IElement>();
		return componentDescriptors;
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
	public ISetElement upgradeAsTheElementOfAnOrderedSet(Map<List<String>, Integer> listOfPropertiesToIndex) {
		ISetElement commonDiffOS;
		List<String> listOfPropertiesWithPath = getListOfPropertiesWithPath();
		Integer commonDiffIndex = listOfPropertiesToIndex.get(listOfPropertiesWithPath);
		String commonDiffID = getDescriptorName().concat(commonDiffIndex.toString());
		MinimalSetElement commonDiffProperty = new MinimalSetElement(commonDiffValue);
		commonDiffOS = new CommonDiffOS(commonDiffID, commonDiffProperty);
		return commonDiffOS;		
	}			

}
