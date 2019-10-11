package description.impl;

import java.util.function.Predicate;

import description.IDescriptionValuator;
import description.IDescriptionValuatorBuilder;
import description.IStringSyntaxer;
import exceptions.OrderedSetsGenerationException;
import exceptions.StringFormatException;
import exceptions.SynTreeGenerationException;
import exceptions.VerbalizationException;
import model.synTreeModel.ISignal;
import settings.Settings;
import syntacticTreesGeneration.ISignalBuilder;
import syntacticTreesGeneration.impl.SignalBuilder;

public class DescriptionValuatorBuilder implements IDescriptionValuatorBuilder {

	Predicate<String> validator;
	
	public DescriptionValuatorBuilder() {
	}

	@Override
	public IDescriptionValuatorBuilder validatedBy(Predicate<String> predicate) {
		this.validator = predicate;
		return this;
	}

	@Override
	public IDescriptionValuator buildDescriptionValuator(String string) 
			throws SynTreeGenerationException, CloneNotSupportedException, VerbalizationException, OrderedSetsGenerationException, 
			StringFormatException {
		IDescriptionValuator valuator;
		if (validator == null || validator.test(string) == true) {
			ISignalBuilder signalBuilder = new SignalBuilder(string, Settings.LEFT_TO_RIGHT);
			ISignal signal = signalBuilder.getSignal();
			valuator = new DescriptionValuator(signal, Settings.getScoreCalculator());
			return valuator;
		}
		else throw new StringFormatException(string);
	}

	@Override
	public IDescriptionValuator buildDescriptionValuator(String string1, String string2) throws SynTreeGenerationException, CloneNotSupportedException, VerbalizationException, OrderedSetsGenerationException, StringFormatException {
		if (validator == null || (validator.test(string1) == true && validator.test(string2) == true)) {
			ISignalBuilder signalBuilder1 = new SignalBuilder(string1, Settings.LEFT_TO_RIGHT);
			ISignalBuilder signalBuilder2 = new SignalBuilder(string2, Settings.LEFT_TO_RIGHT);
			ISignal signal1 = signalBuilder1.getSignal();
			ISignal signal2 = signalBuilder2.getSignal();
			IStringSyntaxer syntaxer1 = new StringSyntaxer(signal1);
			IStringSyntaxer syntaxer2 = new StringSyntaxer(signal2);
			IDescriptionValuator descriptionValuator = new DescriptionValuator(syntaxer1, syntaxer2, Settings.getScoreCalculator());
			return descriptionValuator;
		}
		else throw new StringFormatException(string1 + "," + string2);
	}
}
