package syntacticTreesGeneration.interfaces;

import java.util.ArrayList;

public interface EnumerationRelationalDataInterface extends RelationalDataInterface {

	String getName();

	ArrayList<String> getDimensions();

	String getEnumerationValue();

	void addDimensions(ArrayList<String> dimensions);

}