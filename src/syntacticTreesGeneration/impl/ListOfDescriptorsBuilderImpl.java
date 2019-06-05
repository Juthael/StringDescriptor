package syntacticTreesGeneration.impl;

import java.util.List;

import exceptions.SynTreeGenerationException;
import model.copycatModel.signal.ICopycatSignal;
import model.copycatModel.synTreeGrammar.CharString;
import syntacticTreesGeneration.IListOfDescriptorsBuilder;
import syntacticTreesGeneration.IRelationalDescriptorsBuilder;
import syntacticTreesGeneration.ISignalBuilder;

public class ListOfDescriptorsBuilderImpl implements IListOfDescriptorsBuilder {

	private final String charString;
	private final String directionValue;
	private final ICopycatSignal signal;
	private final List<CharString> listOfStringDescriptors;
	
	
	public ListOfDescriptorsBuilderImpl(String charString, String directionValue) 
			throws SynTreeGenerationException, CloneNotSupportedException {
		this.charString = charString;
		this.directionValue = directionValue;
		ISignalBuilder signalBuilder = new SignalBuilderImpl(this.charString, this.directionValue);
		signal = (ICopycatSignal) signalBuilder.getSignal();
		IRelationalDescriptorsBuilder relationalDescriptorsBuilder = new RelationalDescriptorsBuilderImpl(signal);
		listOfStringDescriptors = relationalDescriptorsBuilder.getListOfDescriptorsCoveringTheWholeString();
	}
	
	public ListOfDescriptorsBuilderImpl(ICopycatSignal signal) throws SynTreeGenerationException, CloneNotSupportedException {
		charString = "unknown";
		directionValue = signal.getDirectionValue();
		this.signal = signal;
		IRelationalDescriptorsBuilder relationalDescriptorsBuilder = new RelationalDescriptorsBuilderImpl(signal);
		listOfStringDescriptors = relationalDescriptorsBuilder.getListOfDescriptorsCoveringTheWholeString();
	}
	
	@Override
	public List<CharString> getListOfStringDescriptors(){
		return listOfStringDescriptors;
	}

}
