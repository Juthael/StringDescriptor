package copycatModel;

public interface IProperty {

	//Getters
	String getValue();

	//Updater
	public void updatePosition(String newPosition);
	
	IProperty clone() throws CloneNotSupportedException;

}