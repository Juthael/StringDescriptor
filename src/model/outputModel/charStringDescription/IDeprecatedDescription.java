package model.outputModel.charStringDescription;

import java.util.List;

import exceptions.VerbalizationException;

public interface IDeprecatedDescription {

	List<String> getCompleteDescription();

	List<String> getListOfRelevantPropertiesWithPath();

	String getDescriptionInNaturalLanguage() throws VerbalizationException;

}