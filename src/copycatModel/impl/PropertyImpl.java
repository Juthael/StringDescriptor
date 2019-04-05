package copycatModel.impl;

import copycatModel.IProperty;
import settings.Settings;

public class PropertyImpl implements Cloneable, IProperty {
	
	private String value;
	
	
	public PropertyImpl(String value) {
		this.value = value;
	}
	
	//Clone Method
	@Override
	public IProperty clone() throws CloneNotSupportedException {
		IProperty cloneProperty = new PropertyImpl(this.value);
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
		if (value.equals(Settings.AWAITING_POSITION_VALUE) && !Settings.NO_POSITION_INFORMATION.equals(newPosition))
			value = newPosition;		
	}
}
