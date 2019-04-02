package copycatModel.interfaces;

public interface PropertyInterface {

	//Getters
	String getValue();

	//Updater
	public void updatePosition(String newPosition);
	
	PropertyInterface clone() throws CloneNotSupportedException;

}