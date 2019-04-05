package syntacticTreesGeneration.impl;

import java.util.ArrayList;
import java.util.List;

import copycatModel.ISynTreeIntegrableElement;
import copycatModel.ISignal;
import copycatModel.grammar.CharString;
import copycatModel.grammar.Group;
import exceptions.SynTreeGenerationException;
import settings.Settings;
import syntacticTreesGeneration.INewGenOfDescriptorsBuilder;
import syntacticTreesGeneration.IRelationalDescriptorsBuilder;

public class RelationalDescriptorsBuilderImpl implements IRelationalDescriptorsBuilder {

	private final ISignal signal;
	private int previousGenerationNumber = 1;
	private List<CharString> listOfDescriptorsCoveringTheWholeString = new ArrayList<CharString>();
	private List<Group> previousGenOfFactorizableDescriptors = new ArrayList<Group>();
	private static final int[] minComponentSizeForIndexGeneration = new int[] {0,1,1,3,6,12};	
	boolean thisIsTheLastGeneration = false;
	
	public RelationalDescriptorsBuilderImpl(ISignal signal) 
			throws SynTreeGenerationException, CloneNotSupportedException {
		this.signal = signal;
		previousGenOfFactorizableDescriptors.addAll(this.signal.getGroups());
		do {
			INewGenOfDescriptorsBuilder newGenOfDescriptorsBuilder = 
					new NewGenOfDescriptorsBuilderImpl(previousGenerationNumber, this.signal, 
							previousGenOfFactorizableDescriptors);
			previousGenerationNumber++;
			List<ISynTreeIntegrableElement> newGenOfDescriptors = 
					newGenOfDescriptorsBuilder.getNewGenOfDescriptors();
			if (!newGenOfDescriptors.isEmpty()) {
				for (ISynTreeIntegrableElement descriptor : newGenOfDescriptors) {
					if (descriptor.getDescriptorName().equals("charString"))
						listOfDescriptorsCoveringTheWholeString.add((CharString) descriptor);
					else if (descriptor.getDescriptorName().equals("group"))
						previousGenOfFactorizableDescriptors.add((Group) descriptor);
					else throw new SynTreeGenerationException("RelationalDescriptorsBuilder : "
							+ "unexpected type of descriptor was found.");
				}
			}
			thisIsTheLastGeneration = testIfThisIsTheLastGeneration();
		} while (thisIsTheLastGeneration == false);
	}
	
	@Override
	public List<CharString> getListOfDescriptorsCoveringTheWholeString(){
		return listOfDescriptorsCoveringTheWholeString;
	}
	
	private boolean testIfThisIsTheLastGeneration() {
		boolean thisIsTheLastGeneration = false;
		int nextGen = previousGenerationNumber + 1;
		if (nextGen > Settings.MAX_NB_OF_DESCRIPTOR_GENERATIONS	|| 
				signal.getSignalSize() < minComponentSizeForIndexGeneration[nextGen] ||
				previousGenOfFactorizableDescriptors.isEmpty())
			thisIsTheLastGeneration = true;
		return thisIsTheLastGeneration;
	}

}
