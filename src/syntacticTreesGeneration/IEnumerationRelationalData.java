package syntacticTreesGeneration;

import java.util.List;

public interface IEnumerationRelationalData extends IRelationalData {

	String getName();

	List<String> getDimensions();

	String getEnumerationValue();

	void addDimensions(List<String> dimensions);

}