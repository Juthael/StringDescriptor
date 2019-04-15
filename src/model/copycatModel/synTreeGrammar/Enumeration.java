package model.copycatModel.synTreeGrammar;

import java.util.ArrayList;
import java.util.List;

import model.generalModel.IElement;
import model.synTreeModel.ISynTreeElement;
import model.synTreeModel.impl.SynTreeElementImpl;
import settings.Settings;

public class Enumeration extends SynTreeElementImpl implements ISynTreeElement, Cloneable {

	private static final String DESCRIPTOR_NAME = "enumeration";
	private String enumerationValue; //"w,x,y,z" (simple enumeration) or "w,x-y,z" (2nd degree enumeration)
	
	public Enumeration(String enumerationValue) {
		super(false);
		this.enumerationValue = enumerationValue;
	}
	
	@Override
	protected Enumeration clone() throws CloneNotSupportedException {
		Enumeration cloneEnumeration = new Enumeration(enumerationValue);
		return cloneEnumeration;
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
		sB.append(enumerationValue);
		listOfPropertiesWithPath.add(sB.toString());
		return listOfPropertiesWithPath;
	}	
	
	@Override
	public List<String> getListOfRelevantPropertiesWithPath() {
		return getListOfPropertiesWithPath();
	}		

}
