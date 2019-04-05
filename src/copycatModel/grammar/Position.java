package copycatModel.grammar;

import java.util.ArrayList;
import java.util.List;

import copycatModel.impl.SynTreeIntegrableElementImpl;
import settings.Settings;

public class Position extends SynTreeIntegrableElementImpl implements Cloneable {
	
	private static final String descriptorName = "position";
	private String positionValue;

	public Position(boolean codingDescriptor, String positionValue) {
		super(codingDescriptor);
		this.positionValue = positionValue;	
	}
	
	public String getPositionValue() {
		return positionValue;
	}
	
	@Override
	protected Position clone() throws CloneNotSupportedException {
		Position clonePosition = new Position(isCodingDescriptor, positionValue);
		return clonePosition;
	}

	@Override
	protected List<SynTreeIntegrableElementImpl> buildListOfComponents(){
		List<SynTreeIntegrableElementImpl> componentDescriptors = new ArrayList<SynTreeIntegrableElementImpl>();
		return componentDescriptors;
	}

	@Override
	public String getDescriptorName() {
		return descriptorName;
	}
	
	@Override
	protected void doUpdatePosition(String newPosition) {
		if (Settings.AWAITING_POSITION_VALUE.equals(positionValue) && !Settings.NO_POSITION_INFORMATION.equals(newPosition))
			positionValue = newPosition;
	}
	
	@Override
	public List<String> getListOfPropertiesWithPath() {
		List<String> listOfPropertiesWithPath = new ArrayList<String>();
		StringBuilder sB = new StringBuilder();
		sB.append(descriptorName);
		sB.append("/");
		sB.append(positionValue);
		listOfPropertiesWithPath.add(sB.toString());
		return listOfPropertiesWithPath;
	}	
	
	@Override
	public List<String> getListOfRelevantPropertiesWithPath() {
		return getListOfPropertiesWithPath();
	}		
	
}
