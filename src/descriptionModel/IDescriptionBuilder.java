package descriptionModel;

import java.util.List;
import java.util.function.Predicate;

import exceptions.StringFormatException;
import exceptions.SynTreeGenerationException;

public interface IDescriptionBuilder {
	
	IDescriptionBuilder validatedBy(Predicate<String> predicate);
	
	List<IDescription> buildList(String input) throws SynTreeGenerationException, CloneNotSupportedException, StringFormatException;

	IDescriptionBuilder stringCanBeReadInBothDirections(boolean stringCanBeReadInBothDirections);
}
