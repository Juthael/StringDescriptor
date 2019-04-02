package dao.interfaces;

import java.util.List;

import exceptions.VerbalizationException;

public interface DAODescription {

	List<String> getCompleteDescription();

	List<String> getListOfRelevantPropertiesWithPath();

	String getDescriptionInNaturalLanguage() throws VerbalizationException;

}