package syntacticTreesGeneration.implementations;

import java.util.ArrayList;

import copycatModel.grammar.CharString;
import copycatModel.interfaces.SignalInterface;
import exceptions.DescriptorsBuilderCriticalException;
import syntacticTreesGeneration.interfaces.ListOfDescriptorsBuilderInterface;
import syntacticTreesGeneration.interfaces.RelationalDescriptorsBuilderInterface;
import syntacticTreesGeneration.interfaces.SignalBuilderInterface;

public class ListOfDescriptorsBuilderV1 implements ListOfDescriptorsBuilderInterface {

	private final String charString;
	private final String directionValue;
	private final SignalInterface signal;
	private final ArrayList<CharString> listOfStringDescriptors;
	
	
	public ListOfDescriptorsBuilderV1(String charString, String directionValue) 
			throws DescriptorsBuilderCriticalException, CloneNotSupportedException {
		this.charString = charString;
		this.directionValue = directionValue;
		SignalBuilderInterface signalBuilder = new SignalBuilderV1(this.charString, this.directionValue);
		signal = signalBuilder.getSignal();
		RelationalDescriptorsBuilderInterface relationalDescriptorsBuilder = new RelationalDescriptorsBuilderV1(signal);
		listOfStringDescriptors = relationalDescriptorsBuilder.getListOfDescriptorsCoveringTheWholeString();
	}
	
	@Override
	public ArrayList<CharString> getListOfStringDescriptors(){
		return listOfStringDescriptors;
	}

}
