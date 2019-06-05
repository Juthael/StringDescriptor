package model.synTreeModel.impl.utils;

public interface IProperty {

	//Getters
	String getValue();

	//Updater
	void updatePosition(String newPosition);
	
	IProperty clone() throws CloneNotSupportedException;

}