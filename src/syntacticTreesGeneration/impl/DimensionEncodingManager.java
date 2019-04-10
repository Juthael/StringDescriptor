package syntacticTreesGeneration.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import exceptions.SynTreeGenerationException;
import settings.Settings;

public class DimensionEncodingManager {

	private Set<Integer> setOfCheckedIndexes;
	
	public DimensionEncodingManager(Set<Integer> listOfCheckedIndexes) {
		this.setOfCheckedIndexes = listOfCheckedIndexes;
	}	

	public String getDimension(List<List<String>> listOfPropertyLists, String possiblyIndexedPath, 
			List<String> listOfValues) throws SynTreeGenerationException {
		String dimension;
		String path = deIndexPath(possiblyIndexedPath);
		StringBuilder sB = new StringBuilder();
		String pathCode = getDimensionCodeFromIndexedPath(path);
		sB.append(pathCode);
		String pathType = getDimensionTypeFromPath(path);
		if (pathType.equals("enumeration") || pathType.equals("commonDiff")) {
			sB.append(Settings.SECOND_DEG_DIMENSION_SEPARATOR);
			int listSize = listOfPropertyLists.get(0).size();
			int propertyIndex = 0;
			boolean pathIndexWasFound = false;
			while (propertyIndex < listSize && pathIndexWasFound == false) {
				if (!setOfCheckedIndexes.contains(propertyIndex)) {
					if (listOfPropertyLists.get(0).get(propertyIndex).contains(path)) {
						List<String> checkedListOfValues = new ArrayList<String>();
						for (List<String> listOfProperties : listOfPropertyLists) {
							String value = getValue(listOfProperties.get(propertyIndex));
							checkedListOfValues.add(value);
						}
						if (listOfValues.equals(checkedListOfValues)) {
							setOfCheckedIndexes.add(propertyIndex);
							pathIndexWasFound = true;
							boolean codeContinuationWasFound = false;
							int propertyIndexBackwards = propertyIndex - 1;
							while (propertyIndexBackwards >= 0 && codeContinuationWasFound == false) {
								if (listOfPropertyLists.get(0).get(propertyIndexBackwards).contains("dimension")) {
									codeContinuationWasFound = true;
									String dimensionCode = 
											getValue(listOfPropertyLists.get(0).get(propertyIndexBackwards));
									sB.append(dimensionCode);
								}
								propertyIndexBackwards--;
							}
							if (codeContinuationWasFound == false)
								throw new SynTreeGenerationException("DimensionEncodingManager.getCompleteDimension() : "
										+ "code continuation couldn't be found.");
						}
					}
				}
				propertyIndex++;
			}
			if (pathIndexWasFound == false) {
				throw new SynTreeGenerationException("DimensionEncodingManager.getCompleteDimension() : "
						+ "path index couldn't be found.");
			}
		}
		dimension = sB.toString();
		return dimension;
	}
	
	public Set<Integer> getSetOfCheckedIndexes(){
		return setOfCheckedIndexes;
	}
	
	public static String getDimensionCodeFromIndexedPath(String indexedPath) {
		String codedDimension;
		String path = deIndexPath(indexedPath);
		StringBuilder codedDimensionBuilder = new StringBuilder();
			String[] pathArray = path.split(Settings.PATH_SEPARATOR);
			int lastGroupsIndex = -1;
			for (int i=0 ; i<pathArray.length ; i++) {
				if (pathArray[i].equals("groups"))
					lastGroupsIndex = i;
			}
			for (int i=0 ; i<pathArray.length ; i++) {
				if (pathArray[i].equals("groups")) {
					codedDimensionBuilder.append(":");
				}
				if (i > lastGroupsIndex) {
					if (!pathArray[i].contains("X")) {
						codedDimensionBuilder.append(pathArray[i]);
						if (i < pathArray.length - 1)
						codedDimensionBuilder.append(".");
					}
				}
			}
		codedDimension = codedDimensionBuilder.toString();		
		return codedDimension;
	}
	
	/*private String getDimensionFromDimensionCode(String dimensionCode) {
		String dimension;
		StringBuilder dimensionCodeBuilder = new StringBuilder();
		char[] dimensionCodeArray = dimensionCode.toCharArray();
		for (char thisChar : dimensionCodeArray) {
			if (thisChar == ':')
				dimensionCodeBuilder.append("relations/groups/");
			else if (thisChar == '.')
				dimensionCodeBuilder.append("/");
			else dimensionCodeBuilder.append(thisChar);
		}
		dimension = dimensionCodeBuilder.toString();
		return dimension;
	}*/
	
	public static String getDimensionTypeFromPath(String paths) {
		String pathType;
		String[] dimensionArray = paths.split(Settings.SECOND_DEG_DIMENSION_SEPARATOR);
		String path = dimensionArray[0];
		int lastSeparatorIndex = path.lastIndexOf(Settings.PATH_SEPARATOR);
		pathType = path.substring(lastSeparatorIndex + 1);
		return pathType;
	}
	
	public static String getDimensionTypeFromCode (String dimensionCode) {	
		String pathType;
		String[] dimensionCodeArray = dimensionCode.split(Settings.SECOND_DEG_DIMENSION_SEPARATOR);
		String lastGenDimensionCode = dimensionCodeArray[0];
		int lastDotIndex = lastGenDimensionCode.lastIndexOf(".");
		int lastColonIndex = lastGenDimensionCode.lastIndexOf(":");
		int firstDimensionTypeIndex;
		if (lastDotIndex != -1 || lastColonIndex != -1) {
			if (lastDotIndex < lastColonIndex)
				firstDimensionTypeIndex = lastColonIndex + 1;
			else firstDimensionTypeIndex = lastDotIndex + 1;
			pathType = lastGenDimensionCode.substring(firstDimensionTypeIndex);	
		}
		else pathType = lastGenDimensionCode;
		return pathType;
	}
	
	public static int getDimensionDegree (String dimensionCode) {
		int dimensionDegree;
		String[] dimensionCodeArray = dimensionCode.split(Settings.SECOND_DEG_DIMENSION_SEPARATOR);
		String lastGenDimensionValue = dimensionCodeArray[dimensionCodeArray.length - 1];
		if (lastGenDimensionValue.indexOf(":") == -1)
			dimensionDegree = 0;
		else dimensionDegree = (lastGenDimensionValue.lastIndexOf(":") - lastGenDimensionValue.indexOf(":")) + 1;
		return dimensionDegree;
	}
	
	private static String deIndexPath(String possiblyIndexedPath) {
		String deIndexedPath;
		int indexSeparatorIndex = possiblyIndexedPath.indexOf(Settings.PROPERTY_INDEX_SEPARATOR);
		deIndexedPath = possiblyIndexedPath.substring(indexSeparatorIndex + 1);
		return deIndexedPath;
	}	
	
	/* private List<List<String>> getNextListOfPropertyLists(List<String> listOfProperties){
		List<List<String>> listOfPropertyLists = new ArrayList<List<String>>();
		List<String> currentListOfProperties = new ArrayList<String>();
		int groupLabelShift = 6;
		int secondGroupIndex = 0;
		boolean secondGroupIndexFound = false;
		for (String propertyWithPath : listOfProperties) {
			String propertyWithRelevantPath = propertyWithPath.substring(groupLabelShift + secondGroupIndex);
			if (secondGroupIndexFound == false && propertyWithRelevantPath.contains("/group/")) {
				secondGroupIndexFound = true;
				secondGroupIndex = propertyWithRelevantPath.indexOf("/group/");
			}
			else if (secondGroupIndexFound == true) {
				if (propertyWithRelevantPath.startsWith("group/size")) {
					if (!currentListOfProperties.isEmpty()) {
						listOfPropertyLists.add(currentListOfProperties);
					}
					currentListOfProperties = new ArrayList<String>();
					currentListOfProperties.add(propertyWithRelevantPath);
				}
				else currentListOfProperties.add(propertyWithRelevantPath);
			}
		}
		listOfPropertyLists.add(currentListOfProperties);
		return listOfPropertyLists;
	} */
	
	private String getValue(String propertyWithPath) {
		int lastSeparatorIndex = propertyWithPath.lastIndexOf(Settings.PATH_SEPARATOR);
		String value = propertyWithPath.substring(lastSeparatorIndex + 1);
		return value;
	}
	
	/* private List<String> getListOfValues(String propertyWithPath) {
		List<String> listOfValues;
		String valueString = getValue(propertyWithPath);
		String[] valueArray;
		if (valueString.contains(Settings.SECOND_DEG_VALUES_SEPARATOR))
			valueArray = valueString.split(Settings.SECOND_DEG_VALUES_SEPARATOR);
		else valueArray = valueString.split(",");
		listOfValues = new ArrayList<String>(Arrays.asList(valueArray));
		return listOfValues;
	}	*/

}
