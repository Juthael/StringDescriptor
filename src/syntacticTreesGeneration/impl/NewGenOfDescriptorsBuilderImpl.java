package syntacticTreesGeneration.implementations;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import copycatModel.grammar.Group;
import copycatModel.interfaces.AbstractDescriptorInterface;
import copycatModel.interfaces.SignalInterface;
import exceptions.DescriptorsBuilderCriticalException;
import settings.DescGenSettings;
import syntacticTreesGeneration.interfaces.ComponentGrouperInterface;
import syntacticTreesGeneration.interfaces.DescriptorsBuildingManagerInterface;
import syntacticTreesGeneration.interfaces.NewGenOfDescriptorsBuilderInterface;

public class NewGenOfDescriptorsBuilderV1 implements NewGenOfDescriptorsBuilderInterface {

	private final int componentsMaxGenerationNumber;
	private final boolean thisWillBeTheLastGeneration;
	private final SignalInterface signal;
	private final ArrayList<Group> previousGenOfDescriptors;
	private final ComponentGrouperInterface componentGrouper;
	private static final int[] minSizeForIndexGeneration = new int[] {0,1,1,3,6,12};	
	
	public NewGenOfDescriptorsBuilderV1(int componentsMaxGenerationNumber, SignalInterface signal, 
			ArrayList<Group> previousGenOfDescriptors) throws DescriptorsBuilderCriticalException {
		this.componentsMaxGenerationNumber = componentsMaxGenerationNumber;
		this.signal = signal;
		thisWillBeTheLastGeneration = testIfThisWillBeTheLastGeneration();
		this.previousGenOfDescriptors = previousGenOfDescriptors;
		componentGrouper = 
				new ComponentGrouperV2(this.componentsMaxGenerationNumber, thisWillBeTheLastGeneration, 
						this.signal, this.previousGenOfDescriptors);
	}
	
	@Override
	public ArrayList<AbstractDescriptorInterface> getNewGenOfDescriptors() 
			throws DescriptorsBuilderCriticalException, CloneNotSupportedException {
		ArrayList<AbstractDescriptorInterface> newGenOfDescriptors = new ArrayList<AbstractDescriptorInterface>();
		HashSet<ArrayList<Group>> listOfFactorizableDescriptorSets = componentGrouper.getSetsOfFactorizableDescriptors();
		for (ArrayList<Group> setOfFactorizableDescriptors : listOfFactorizableDescriptorSets) {
			boolean atLeastOneComponentIsFromTheLastGeneration = 
					testIfAtLeastOneComponentIsFromTheLastGeneration(setOfFactorizableDescriptors);
			if (atLeastOneComponentIsFromTheLastGeneration == true) {
				DescriptorsBuildingManagerInterface descriptorsBuildingManager = 
						new DescriptorsBuildingManagerV1(
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
		if (nextNextGen > DescGenSettings.MAX_NB_OF_DESCRIPTOR_GENERATIONS)
			thisWillBeTheLastGeneration = true;
		else if (signal.getSignalSize() < minSizeForIndexGeneration[nextNextGen])
			thisWillBeTheLastGeneration = true;
		return thisWillBeTheLastGeneration;
	}

}
