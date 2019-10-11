package description;

import java.util.function.Predicate;

import exceptions.OrderedSetsGenerationException;
import exceptions.StringFormatException;
import exceptions.SynTreeGenerationException;
import exceptions.VerbalizationException;

public interface IDescriptionValuatorBuilder {
	
	IDescriptionValuatorBuilder validatedBy(Predicate<String> predicate);
	
	IDescriptionValuator buildDescriptionValuator(String string) 
			throws SynTreeGenerationException, CloneNotSupportedException, VerbalizationException, OrderedSetsGenerationException, 
			StringFormatException;
	
	IDescriptionValuator buildDescriptionValuator(String string1, String string2) 
			throws SynTreeGenerationException, CloneNotSupportedException, VerbalizationException, OrderedSetsGenerationException, 
			StringFormatException;

}
