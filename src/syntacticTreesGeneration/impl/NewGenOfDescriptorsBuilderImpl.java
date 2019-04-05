package syntacticTreesGeneration.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import copycatModel.ISignal;
import copycatModel.ISynTreeIntegrableElement;
import copycatModel.grammar.Group;
import exceptions.SynTreeGenerationException;
import settings.Settings;
import syntacticTreesGeneration.IComponentGrouper;
import syntacticTreesGeneration.IDescriptorsBuildingManager;
import syntacticTreesGeneration.INewGenOfDescriptorsBuilder;

public class NewGenOfDescriptorsBuilderImpl implements INewGenOfDescriptorsBuilder {

	private final int componentsMaxGenerationNumber;
	private final boolean thisWillBeTheLastGeneration;
	private final ISignal signal;
	private final List<Group> previousGenOfDescriptors;
	private final IComponentGrouper componentGrouper;
	private static final int[] minSizeForIndexGeneration = new int[] {0,1,1,3,6,12};	
	
	public NewGenOfDescriptorsBuilderImpl(int componentsMaxGenerationNumber, ISignal signal, 
			List<Group> previousGenOfDescriptors) throws SynTreeGenerationException {
		this.componentsMaxGenerationNumber = componentsMaxGenerationNumber;
		this.signal = signal;
		thisWillBeTheLastGeneration = testIfThisWillBeTheLastGeneration();
		this.previousGenOfDescriptors = previousGenOfDescriptors;
		componentGrouper = 
				new ComponentGrouperImpl(this.componentsMaxGenerationNumber, thisWillBeTheLastGeneration, 
						this.signal, this.previousGenOfDescriptors);
	}
	
	@Override
	public List<ISynTreeIntegrableElement> getNewGenOfDescriptors() 
			throws SynTreeGenerationException, CloneNotSupportedException {
		List<ISynTreeIntegrableElement> newGenOfDescriptors = new ArrayList<ISynTreeIntegrableElement>();
		Set<List<Group>> listOfFactorizableDescriptorSets = componentGrouper.getSetsOfFactorizableDescriptors();
		for (List<Group> setOfFactorizableDescriptors : listOfFactorizableDescriptorSets) {
			boolean atLeastOneComponentIsFromTheLastGeneration = 
					testIfAtLeastOneComponentIsFromTheLastGeneration(setOfFactorizableDescriptors);
			if (atLeastOneComponentIsFromTheLastGeneration == true) {
				IDescriptorsBuildingManager descriptorsBuildingManager = 
						new DescriptorsBuildingManagerImpl(
								signal, componentsMaxGenerationNumber, setOfFactorizableDescriptors);
				newGenOfDescriptors.addAll(descriptorsBuildingManager.getListOfNewDescriptors());
			}	
		}
		return newGenOfDescriptors;
	}
	
	private boolean testIfAtLeastOneComponentIsFromTheLastGeneration(List<Group> setOfFactorizableDescriptors) {
		boolean atLeastOneComponentIsFromTheLastGeneration = false;
		int groupIndex = 0;
		while (atLeastOneComponentIsFromTheLastGeneration == false && groupIndex < setOfFactorizableDescriptors.size()) {
			List<String> currentComponentListOfProperties = 
					setOfFactorizableDescriptors.get(groupIndex).getListOfPropertiesWithPath();
			int propertyIndex = 0;
			while (atLeastOneComponentIsFromTheLastGeneration == false && 
					propertyIndex < currentComponentListOfProperties.size()) {
				String currentProperty = currentComponentListOfProperties.get(propertyIndex);
				String[] currentPropertyArray = currentProperty.split("/");
				int currentComponentGenerationNumber = 0;
				for (String pathElement : currentPropertyArray) {
					if (pathElement.equals("group"))
						currentComponentGenerationNumber++;
				}
				if (currentComponentGenerationNumber == componentsMaxGenerationNumber)
					atLeastOneComponentIsFromTheLastGeneration = true;
				propertyIndex++;
			}
		groupIndex++;
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
