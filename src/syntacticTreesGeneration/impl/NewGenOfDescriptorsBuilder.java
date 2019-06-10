package syntacticTreesGeneration.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import exceptions.SynTreeGenerationException;
import model.copycatModel.signal.ICopycatSignal;
import model.copycatModel.synTreeGrammar.Frame;
import model.synTreeModel.IGrammaticalST;
import settings.Settings;
import syntacticTreesGeneration.IComponentGrouper;
import syntacticTreesGeneration.IDescriptorsBuildingManager;
import syntacticTreesGeneration.INewGenOfDescriptorsBuilder;

public class NewGenOfDescriptorsBuilder implements INewGenOfDescriptorsBuilder {

	private final int componentsMaxGenerationNumber;
	private final boolean thisWillBeTheLastGeneration;
	private final ICopycatSignal signal;
	private final List<Frame> previousGenOfDescriptors;
	private final IComponentGrouper componentGrouper;
	private static final int[] minSizeForIndexGeneration = new int[] {0,1,1,3,6,12};	
	
	public NewGenOfDescriptorsBuilder(int componentsMaxGenerationNumber, ICopycatSignal signal, 
			List<Frame> previousGenOfDescriptors) throws SynTreeGenerationException {
		this.componentsMaxGenerationNumber = componentsMaxGenerationNumber;
		this.signal = signal;
		thisWillBeTheLastGeneration = testIfThisWillBeTheLastGeneration();
		this.previousGenOfDescriptors = previousGenOfDescriptors;
		componentGrouper = 
				new ComponentGrouper(this.componentsMaxGenerationNumber, thisWillBeTheLastGeneration, 
						this.signal, this.previousGenOfDescriptors);
	}
	
	@Override
	public List<IGrammaticalST> getNewGenOfDescriptors() 
			throws SynTreeGenerationException, CloneNotSupportedException {
		List<IGrammaticalST> newGenOfDescriptors = new ArrayList<IGrammaticalST>();
		Set<List<Frame>> listOfFactorizableDescriptorSets = componentGrouper.getSetsOfFactorizableDescriptors();
		for (List<Frame> setOfFactorizableDescriptors : listOfFactorizableDescriptorSets) {
			boolean atLeastOneComponentIsFromTheLastGeneration = 
					testIfAtLeastOneComponentIsFromTheLastGeneration(setOfFactorizableDescriptors);
			if (atLeastOneComponentIsFromTheLastGeneration == true) {
				IDescriptorsBuildingManager descriptorsBuildingManager = 
						new DescriptorsBuildingManager(
								signal, componentsMaxGenerationNumber, setOfFactorizableDescriptors);
				newGenOfDescriptors.addAll(descriptorsBuildingManager.getListOfNewDescriptors());
			}	
		}
		return newGenOfDescriptors;
	}
	
	private boolean testIfAtLeastOneComponentIsFromTheLastGeneration(List<Frame> setOfFactorizableDescriptors) {
		boolean atLeastOneComponentIsFromTheLastGeneration = false;
		int frameIndex = 0;
		while (atLeastOneComponentIsFromTheLastGeneration == false && frameIndex < setOfFactorizableDescriptors.size()) {
			List<String> currentComponentListOfProperties = 
					setOfFactorizableDescriptors.get(frameIndex).getListOfPropertiesWithPath();
			int propertyIndex = 0;
			while (atLeastOneComponentIsFromTheLastGeneration == false && 
					propertyIndex < currentComponentListOfProperties.size()) {
				String currentProperty = currentComponentListOfProperties.get(propertyIndex);
				String[] currentPropertyArray = currentProperty.split(Settings.PATH_SEPARATOR);
				int currentComponentGenerationNumber = 0;
				for (String pathElement : currentPropertyArray) {
					if (pathElement.equals("frame"))
						currentComponentGenerationNumber++;
				}
				if (currentComponentGenerationNumber == componentsMaxGenerationNumber)
					atLeastOneComponentIsFromTheLastGeneration = true;
				propertyIndex++;
			}
		frameIndex++;
		}
		return atLeastOneComponentIsFromTheLastGeneration;
	}
	
	private boolean testIfThisWillBeTheLastGeneration() {
		boolean thisWillBeTheLastGeneration = false;
		int nextNextGen = componentsMaxGenerationNumber + 2;
		if (nextNextGen > Settings.MAX_NB_OF_DESCRIPTOR_GENERATIONS)
			thisWillBeTheLastGeneration = true;
		else if (signal.getSignalSize() < minSizeForIndexGeneration[nextNextGen])
			thisWillBeTheLastGeneration = true;
		return thisWillBeTheLastGeneration;
	}

}
