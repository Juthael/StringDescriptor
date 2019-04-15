package model.copycatModel.synTreeGrammar;

import java.util.ArrayList;
import java.util.List;

import model.generalModel.IElement;
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
	protected List<IElement> buildListOfComponents(){
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

}
