package syntacticTreesGeneration.interfaces;

import java.util.ArrayList;

public interface RelationalDataInterface {
	
	public String getName();
	
	public ArrayList<String> getDimensions();
	
	public String getEnumerationValue();
	
	public void addDimensions(ArrayList<String> dimension);

}
