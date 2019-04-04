package syntacticTreesGeneration.implementations;

import java.util.ArrayList;

import copycatModel.grammar.CharString;
import copycatModel.grammar.Group;
import copycatModel.interfaces.AbstractDescriptorInterface;
import copycatModel.interfaces.SignalInterface;
import exceptions.DescriptorsBuilderCriticalException;
import settings.DescGenSettings;
import syntacticTreesGeneration.interfaces.NewGenOfDescriptorsBuilderInterface;
import syntacticTreesGeneration.interfaces.RelationalDescriptorsBuilderInterface;

public class RelationalDescriptorsBuilderV1 implements RelationalDescriptorsBuilderInterface {

	private final SignalInterface signal;
	private int previousGenerationNumber = 1;
	private ArrayList<CharString> listOfDescriptorsCoveringTheWholeString = new ArrayList<CharString>();
	private ArrayList<Group> previousGenOfFactorizableDescriptors = new ArrayList<Group>();
	private static final int[] minComponentSizeForIndexGeneration = new int[] {0,1,1,3,6,12};	
	boolean thisIsTheLastGeneration = false;
	
	public RelationalDescriptorsBuilderV1(SignalInterface signal) 
			throws DescriptorsBuilderCriticalException, CloneNotSupportedException {
		this.signal = signal;
		previousGenOfFactorizableDescriptors.addAll(this.signal.getGroups());
		do {
			NewGenOfDescriptorsBuilderInterface newGenOfDescriptorsBuilder = 
					new NewGenOfDescriptorsBuilderV1(previousGenerationNumber, this.signal, 
							previousGenOfFactorizableDescriptors);
			previousGenerationNumber++;
			ArrayList<AbstractDescriptorInterface> newGenOfDescriptors = 
					newGenOfDescriptorsBuilder.getNewGenOfDescriptors();
			if (!newGenOfDescriptors.isEmpty()) {
				for (AbstractDescriptorInterface descriptor : newGenOfDescriptors) {
					if (descriptor.getDescriptorName().equals("charString"))
						listOfDescriptorsCoveringTheWholeString.add((CharString) descriptor);
					else if (descriptor.getDescriptorName().equals("group"))
						previousGenOfFactorizableDescriptors.add((Group) descriptor);
					else throw new DescriptorsBuilderCriticalException("RelationalDescriptorsBuilder : "
							+ "unexpected type of descriptor was found.");
				}
			}
			thisIsTheLastGeneration = testIfThisIsTheLastGeneration();
		} while (thisIsTheLastGeneration == false);
	}
	
	@Override
	public ArrayList<CharString> getListOfDescriptorsCoveringTheWholeString(){
		return listOfDescriptorsCoveringTheWholeString;
	}
	
	private boolean testIfThisIsTheLastGeneration() {
		boolean thisIsTheLastGeneration = false;
		int nextGen = previousGenerationNumber + 1;
		if (nextGen > DescGenSettings.MAX_NB_OF_DESCRIPTOR_GENERATIONS	|| 
				signal.getSignalSize() < minComponentSizeForIndexGeneration[nextGen] ||
				previousGenOfFactorizableDescriptors.isEmpty())
			thisIsTheLastGeneration = true;
		return thisIsTheLastGeneration;
	}

}
