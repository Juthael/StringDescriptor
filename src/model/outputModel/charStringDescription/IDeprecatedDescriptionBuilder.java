package model.outputModel.charStringDescription;

import java.util.List;
import java.util.function.Predicate;

import exceptions.OrderedSetsGenerationException;
import exceptions.StringFormatException;
import exceptions.SynTreeGenerationException;
import exceptions.VerbalizationException;

public interface IDeprecatedDescriptionBuilder {
	
	IDeprecatedDescriptionBuilder validatedBy(Predicate<String> predicate);
	
	List<IDeprecatedDescription> buildList(String input) 
			throws SynTreeGenerationException, CloneNotSupportedException, StringFormatException, VerbalizationException, 
			OrderedSetsGenerationException;

	IDeprecatedDescriptionBuilder stringCanBeReadInBothDirections(boolean stringCanBeReadInBothDirections);
}
