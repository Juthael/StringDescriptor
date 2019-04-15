package model.copycatModel.synTreeGrammar;

import java.util.ArrayList;
import java.util.List;

import model.generalModel.IElement;
import model.synTreeModel.ISynTreeElement;
import model.synTreeModel.impl.SynTreeElementImpl;
import settings.Settings;

public class Size extends SynTreeElementImpl implements ISynTreeElement, Cloneable {
	
	private static final String DESCRIPTOR_NAME = "size";
	private String sizeValue;

	public Size(String sizeValue) {
		super(false);
		this.sizeValue = sizeValue;
	}
	
	@Override
	protected Size clone() throws CloneNotSupportedException {
		Size cloneSize = new Size(sizeValue);
		return cloneSize;
	}
	
	@Override
	protected List<IElement> buildListOfComponents(){
		ArrayList<IElement> componentDescriptors = new ArrayList<IElement>();
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
		sB.append(sizeValue);
		listOfPropertiesWithPath.add(sB.toString());
		return listOfPropertiesWithPath;
	}
	
	@Override
	public List<String> getListOfRelevantPropertiesWithPath() {
		return getListOfPropertiesWithPath();
	}	
	
}
