package syntacticTreesGeneration;

import java.util.List;

public interface IRelationalData {
	
	public String getName();
	
	public List<String> getDimensions();
	
	public String getEnumerationValue();
	
	public void addDimensions(List<String> dimension);

}
