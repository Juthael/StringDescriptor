package syntacticTreesGeneration.impl;

import java.util.ArrayList;
import java.util.List;

import exceptions.OrderedSetsGenerationException;
import exceptions.SynTreeGenerationException;
import exceptions.VerbalizationException;
import model.copycatModel.signal.ICopycatSignal;
import model.copycatModel.synTreeGrammar.CharString;
import syntacticTreesGeneration.IListOfDescriptorsBuilder;
import syntacticTreesGeneration.IRelationalDescriptorsBuilder;
import syntacticTreesGeneration.ISignalBuilder;

public class ListOfDescriptorsBuilder implements IListOfDescriptorsBuilder {

	private final String charString;
	private final String directionValue;
	private final ICopycatSignal signal;
	private final List<CharString> listOfComprehensiveDescriptors;
	private final List<CharString> listOfDescWithAbstractFrames;
	
	
	
	public ListOfDescriptorsBuilder(String charString, String directionValue) 
			throws SynTreeGenerationException, CloneNotSupportedException, VerbalizationException, OrderedSetsGenerationException {
		this.charString = charString;
		this.directionValue = directionValue;
		ISignalBuilder signalBuilder = new SignalBuilder(this.charString, this.directionValue);
		signal = (ICopycatSignal) signalBuilder.getSignal();
		IRelationalDescriptorsBuilder relationalDescriptorsBuilder = new RelationalDescriptorsBuilder(signal);
		listOfComprehensiveDescriptors = relationalDescriptorsBuilder.getListOfDescriptorsCoveringTheWholeString();
		listOfDescWithAbstractFrames = new ArrayList<CharString>();
		for (CharString descriptor : listOfComprehensiveDescriptors) {
			CharString descriptorWithAbstractComp = (CharString) descriptor.getTreeWithAbstractFrames();
			listOfDescWithAbstractFrames.add(descriptorWithAbstractComp);
		}
			
			
	}
	
	public ListOfDescriptorsBuilder(ICopycatSignal signal) 
			throws SynTreeGenerationException, CloneNotSupportedException, VerbalizationException, OrderedSetsGenerationException {
		charString = "unknown";
		directionValue = signal.getDirectionValue();
		this.signal = signal;
		IRelationalDescriptorsBuilder relationalDescriptorsBuilder = new RelationalDescriptorsBuilder(signal);
		listOfComprehensiveDescriptors = relationalDescriptorsBuilder.getListOfDescriptorsCoveringTheWholeString();
		listOfDescWithAbstractFrames = new ArrayList<CharString>();
		for (CharString descriptor : listOfComprehensiveDescriptors) {
			CharString descriptorWithAbstractComp = (CharString) descriptor.getTreeWithAbstractFrames();
			listOfDescWithAbstractFrames.add(descriptorWithAbstractComp);
		}
	}
	
	@Override
	public List<CharString> getListOfComprehensiveDescriptors(){
		return listOfComprehensiveDescriptors;
	}

	@Override
	public List<CharString> getListOfDescriptorsWithAbstractComponents() {
		return listOfDescWithAbstractFrames;
	}

}
