package syntacticTreesGeneration.impl;

import java.util.List;

import copycatModel.synTreeModel.ISignal;
import copycatModel.synTreeModel.grammar.CharString;
import exceptions.SynTreeGenerationException;
import syntacticTreesGeneration.IListOfDescriptorsBuilder;
import syntacticTreesGeneration.IRelationalDescriptorsBuilder;
import syntacticTreesGeneration.ISignalBuilder;

public class ListOfDescriptorsBuilderImpl implements IListOfDescriptorsBuilder {

	private final String charString;
	private final String directionValue;
	private final ISignal signal;
	private final List<CharString> listOfStringDescriptors;
	
	
	public ListOfDescriptorsBuilderImpl(String charString, String directionValue) 
			throws SynTreeGenerationException, CloneNotSupportedException {
		this.charString = charString;
		this.directionValue = directionValue;
		ISignalBuilder signalBuilder = new SignalBuilderImpl(this.charString, this.directionValue);
		signal = signalBuilder.getSignal();
		IRelationalDescriptorsBuilder relationalDescriptorsBuilder = new RelationalDescriptorsBuilderImpl(signal);
		listOfStringDescriptors = relationalDescriptorsBuilder.getListOfDescriptorsCoveringTheWholeString();
	}
	
	@Override
	public List<CharString> getListOfStringDescriptors(){
		return listOfStringDescriptors;
	}

}
