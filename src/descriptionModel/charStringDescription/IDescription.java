package descriptionModel.charStringDescription;

import java.util.List;

import exceptions.VerbalizationException;

public interface IDescription {

	List<String> getCompleteDescription();

	List<String> getListOfRelevantPropertiesWithPath();

	String getDescriptionInNaturalLanguage() throws VerbalizationException;

}