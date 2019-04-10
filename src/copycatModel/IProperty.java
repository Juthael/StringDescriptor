package copycatModel;

public interface IProperty {

	//Getters
	String getValue();

	//Updater
	void updatePosition(String newPosition);
	
	IProperty clone() throws CloneNotSupportedException;

}