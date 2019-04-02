package copycatModel.grammar;

import java.util.ArrayList;

import copycatModel.implementations.AbstractDescriptorV1;
import settings.DescGenSettings;

public class Position extends AbstractDescriptorV1 implements Cloneable {
	
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
	protected ArrayList<AbstractDescriptorV1> buildListOfComponents(){
		ArrayList<AbstractDescriptorV1> componentDescriptors = new ArrayList<AbstractDescriptorV1>();
		return componentDescriptors;
	}

	@Override
	public String getDescriptorName() {
		return descriptorName;
	}
	
	@Override
	protected void doUpdatePosition(String newPosition) {
		if (DescGenSettings.AWAITING_POSITION_VALUE.equals(positionValue) && !DescGenSettings.NO_POSITION_INFORMATION.equals(newPosition))
			positionValue = newPosition;
	}
	
	@Override
	public ArrayList<String> getListOfPropertiesWithPath() {
		ArrayList<String> listOfPropertiesWithPath = new ArrayList<String>();
		StringBuilder sB = new StringBuilder();
		sB.append(descriptorName);
		sB.append("/");
		sB.append(positionValue);
		listOfPropertiesWithPath.add(sB.toString());
		return listOfPropertiesWithPath;
	}	
	
	@Override
	public ArrayList<String> getListOfRelevantPropertiesWithPath() {
		return getListOfPropertiesWithPath();
	}		
	
}
