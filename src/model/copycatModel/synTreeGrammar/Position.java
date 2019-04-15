package model.copycatModel.synTreeGrammar;

import java.util.ArrayList;
import java.util.List;

import model.generalModel.IElement;
import model.synTreeModel.ISynTreeElement;
import model.synTreeModel.impl.SynTreeElementImpl;
import settings.Settings;

public class Position extends SynTreeElementImpl implements ISynTreeElement, Cloneable {
	
	private static final String DESCRIPTOR_NAME = "position";
	protected String positionValue;
	protected String specialPositionValue = "";

	public Position(String positionValue) {
		super(false);
		if (positionValue.contains(Settings.POSITION_VALUES_SEPARATOR)) {
			String[] positionValuesArray = positionValue.split(Settings.POSITION_VALUES_SEPARATOR);
			this.positionValue = positionValuesArray[0];
			this.specialPositionValue = positionValuesArray[1];
		}
		else this.positionValue = positionValue;	
	}
	
	public Position(String positionValue, String specialPositionValue) {
		super(false);
		this.positionValue = positionValue;
		this.specialPositionValue = specialPositionValue;
	}	
	
	public String getPositionValue() {
		return positionValue;
	}
	
	@Override
	protected Position clone() throws CloneNotSupportedException {
		Position clonePosition = new Position(positionValue, specialPositionValue);
		return clonePosition;
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
	protected void doUpdatePosition(String newPositionValue) {
		if (Settings.AWAITING_POSITION_VALUE.equals(positionValue) && !Settings.NO_POSITION_INFORMATION.equals(newPositionValue)) {
			if (newPositionValue.contains(Settings.POSITION_VALUES_SEPARATOR)) {
				String[] positionValuesArray = newPositionValue.split(Settings.POSITION_VALUES_SEPARATOR);
				positionValue = positionValuesArray[0];
				specialPositionValue = positionValuesArray[1];
			}
			else positionValue = newPositionValue;
		}
	}
	
	@Override
	public List<String> getListOfPropertiesWithPath() {
		List<String> listOfPropertiesWithPath = new ArrayList<String>();
		StringBuilder sB = new StringBuilder();
		sB.append(DESCRIPTOR_NAME);
		sB.append(Settings.PATH_SEPARATOR);
		sB.append(positionValue);
		listOfPropertiesWithPath.add(sB.toString());
		if (!specialPositionValue.isEmpty()) {
			sB = new StringBuilder();
			sB.append(DESCRIPTOR_NAME);
			sB.append(Settings.PATH_SEPARATOR);
			sB.append(specialPositionValue);
			listOfPropertiesWithPath.add(sB.toString());
		}
		return listOfPropertiesWithPath;
	}	
	
	@Override
	public List<String> getListOfRelevantPropertiesWithPath() {
		List<String> listOfPropertiesWithPath = new ArrayList<String>();
		StringBuilder sB = new StringBuilder();
		sB.append(DESCRIPTOR_NAME);
		sB.append(Settings.PATH_SEPARATOR);
		sB.append(positionValue);
		listOfPropertiesWithPath.add(sB.toString());
		return listOfPropertiesWithPath;
	}		
	
}
