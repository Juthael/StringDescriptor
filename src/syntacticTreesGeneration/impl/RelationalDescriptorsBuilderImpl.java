package syntacticTreesGeneration.impl;

import java.util.ArrayList;
import java.util.List;

import exceptions.SynTreeGenerationException;
import model.copycatModel.signal.ICopycatSignal;
import model.copycatModel.synTreeGrammar.CharString;
import model.copycatModel.synTreeGrammar.Frame;
import model.synTreeModel.IFrame;
import model.synTreeModel.IGrammaticalST;
import settings.Settings;
import syntacticTreesGeneration.INewGenOfDescriptorsBuilder;
import syntacticTreesGeneration.IRelationalDescriptorsBuilder;

public class RelationalDescriptorsBuilderImpl implements IRelationalDescriptorsBuilder {

	private final ICopycatSignal signal;
	private int previousGenerationNumber = 1;
	private List<CharString> listOfDescriptorsCoveringTheWholeString = new ArrayList<CharString>();
	private List<Frame> previousGenOfFactorizableDescriptors = new ArrayList<Frame>();
	private static final int[] minComponentSizeForIndexGeneration = new int[] {0,1,1,3,6,12};	
	boolean thisIsTheLastGeneration = false;
	
	public RelationalDescriptorsBuilderImpl(ICopycatSignal signal) 
			throws SynTreeGenerationException, CloneNotSupportedException {
		this.signal = signal;
		for (IFrame frame : this.signal.getFrames()) {
			previousGenOfFactorizableDescriptors.add((Frame) frame);
		}
		do {
			INewGenOfDescriptorsBuilder newGenOfDescriptorsBuilder = 
					new NewGenOfDescriptorsBuilderImpl(previousGenerationNumber, this.signal, 
							previousGenOfFactorizableDescriptors);
			previousGenerationNumber++;
			List<IGrammaticalST> newGenOfDescriptors = 
					newGenOfDescriptorsBuilder.getNewGenOfDescriptors();
			if (!newGenOfDescriptors.isEmpty()) {
				for (IGrammaticalST descriptor : newGenOfDescriptors) {
					if (descriptor.getDescriptorName().equals("charString"))
						listOfDescriptorsCoveringTheWholeString.add((CharString) descriptor);
					else if (descriptor.getDescriptorName().equals("frame"))
						previousGenOfFactorizableDescriptors.add((Frame) descriptor);
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
