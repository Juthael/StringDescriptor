package copycatModel.implementations;

import copycatModel.interfaces.PropertyInterface;
import settings.DescGenSettings;

public class PropertyV1 implements Cloneable, PropertyInterface {
	
	private String value;
	
	
	public PropertyV1(String value) {
		this.value = value;
	}
	
	//Clone Method
	@Override
	public PropertyInterface clone() throws CloneNotSupportedException {
		PropertyInterface cloneProperty = new PropertyV1(this.value);
		return cloneProperty;
	}
	
	//Getters
	@Override
	public String getValue() {
		return value;
	}
	
	//Updater
	@Override
	public void updatePosition(String newPosition) {
		if (value.equals(DescGenSettings.AWAITING_POSITION_VALUE) && !DescGenSettings.NO_POSITION_INFORMATION.equals(newPosition))
			value = newPosition;		
	}
}
