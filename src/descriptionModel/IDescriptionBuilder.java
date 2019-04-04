package model;

import java.util.List;
import java.util.function.Predicate;

public interface IDescriptionBuilder {
	IDescriptionBuilder validatedBy(Predicate<String> predicate);
	
	List<IDescription> buildList(String input);

	IDescriptionBuilder stringCanBeReadInBothDirections(boolean stringCanBeReadInBothDirections);
}
