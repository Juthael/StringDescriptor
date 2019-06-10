package model.outputModel.charStringDescription;

import java.util.List;
import java.util.function.Predicate;

import exceptions.OrderedSetsGenerationException;
import exceptions.StringFormatException;
import exceptions.SynTreeGenerationException;
import exceptions.VerbalizationException;

public interface IDescriptionBuilder {
	
	IDescriptionBuilder validatedBy(Predicate<String> predicate);
	
	List<IDescription> buildList(String input) 
			throws SynTreeGenerationException, CloneNotSupportedException, StringFormatException, VerbalizationException, 
			OrderedSetsGenerationException;

	IDescriptionBuilder stringCanBeReadInBothDirections(boolean stringCanBeReadInBothDirections);
}
