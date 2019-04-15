package syntacticTreesGeneration.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import exceptions.SynTreeGenerationException;
import model.copycatModel.synTreeGrammar.Group;
import model.synTreeModel.ISignal;
import settings.Settings;
import syntacticTreesGeneration.IComponentGrouper;
import syntacticTreesGeneration.IListOfDescriptorsWithPositions;

public class ComponentGrouperImpl implements IComponentGrouper {

	private final int componentsGenerationNumber;
	private final ISignal signal;
	private final List<Group> previousGenOfDescriptors;
	boolean nextGenerationWillBeTheLast;	
	private Group[][][] combinatoryArrayOfDescriptors;
	
	public ComponentGrouperImpl(int componentsGenerationNumber, boolean nextGenerationWillBeTheLast, ISignal signal, 
			List<Group> previousGenOfDescriptors) throws SynTreeGenerationException {
		this.componentsGenerationNumber = componentsGenerationNumber;
		this.signal = signal;
		this.previousGenOfDescriptors = previousGenOfDescriptors;
		this.nextGenerationWillBeTheLast = nextGenerationWillBeTheLast;	
		setCombinatoryArrayOfDescriptors();
	}

	@Override
	public Set<List<Group>> getSetsOfFactorizableDescriptors(){
		Set<List<Group>> setsOfFactorizableDescriptors;
		if (nextGenerationWillBeTheLast == false)
			setsOfFactorizableDescriptors = getAllSetsOfFactorizableDescriptors();
		else {
			setsOfFactorizableDescriptors = getSetsOfFactorizableDescriptorsCoveringTheWholeString();
		}
		return setsOfFactorizableDescriptors;
	}
	
	private Set<List<Group>> getSetsOfFactorizableDescriptorsCoveringTheWholeString(){
		Set<List<Group>> setsOfFactorizableDescriptors = new HashSet<List<Group>>();
		int x = 0, y = 0, bufferIndex = 0;
		IListOfDescriptorsWithPositions[] buffer = new ListOfDescriptorsWithPositionsImpl[signal.getSignalSize()]; 
		while (bufferIndex >= 0) {
			if (combinatoryArrayOfDescriptors[x][y].length == 0) {
				if (y < signal.getSignalSize() - 1) {
					y++;
				}
				else {
					if (bufferIndex > 0) {
						int previousFirstPos = buffer[bufferIndex - 1].getFirstPosition();
						int previousLastPos = buffer[bufferIndex - 1].getLastPosition();
						x = previousFirstPos;
						y = previousLastPos + 1;
					}
					bufferIndex--;
				}
			}
			else {
				List<Group> currentList = 
						new ArrayList<Group>(Arrays.asList(combinatoryArrayOfDescriptors[x][y]));
				IListOfDescriptorsWithPositions currentListWithCoordinates = 
						new ListOfDescriptorsWithPositionsImpl(currentList, x, y);
				buffer[bufferIndex] = currentListWithCoordinates;
				if (y < signal.getSignalSize() - 1) {
					x = y + 1; 
					y = x;
					bufferIndex++;
				}
				else {
					if ((bufferIndex > 0) || (componentsGenerationNumber <= 1)) {
						boolean bufferPositionsAreLegal = testIfBufferPositionsAreLegal(buffer, bufferIndex);
						if (bufferPositionsAreLegal == true) {
							Set<List<Group>> newSetsOfFactorizableDescriptors = 
									new HashSet<List<Group>>(); 
							newSetsOfFactorizableDescriptors = saveNewSubSets(buffer, 0, bufferIndex, newSetsOfFactorizableDescriptors);
							setsOfFactorizableDescriptors.addAll(newSetsOfFactorizableDescriptors);
						}
					}
					int previousFirstPos = buffer[bufferIndex - 1].getFirstPosition();
					int previousLastPos = buffer[bufferIndex - 1].getLastPosition();
					x = previousFirstPos;
					y = previousLastPos + 1;
					bufferIndex--;
				}
			}
		}
		return setsOfFactorizableDescriptors;
	}
	
	private Set<List<Group>> getAllSetsOfFactorizableDescriptors() {
		Set<List<Group>> setsOfFactorizableDescriptors = new HashSet<List<Group>>();
		int x = 0, y = 0, bufferIndex = 0;
		IListOfDescriptorsWithPositions[] buffer = new ListOfDescriptorsWithPositionsImpl[signal.getSignalSize()]; 
		while (x < signal.getSignalSize()) {
			if (combinatoryArrayOfDescriptors[x][y].length == 0) {
				if (y < signal.getSignalSize() - 1) {
					y++;
				}
				else {
					if (bufferIndex > 0) {
						int previousLastPos = buffer[bufferIndex-1].getLastPosition();
						if (previousLastPos < signal.getSignalSize() - 1) {
							x = buffer[bufferIndex-1].getFirstPosition();;
							y = previousLastPos + 1;
							bufferIndex--;
						}
						else {
							x++;
							y = x;
							bufferIndex = 0;
						}
					}
					else {
						x++;
						y = x;
					}
				}
			}
			else {
				List<Group> currentList = 
						new ArrayList<Group>(Arrays.asList(combinatoryArrayOfDescriptors[x][y]));
				IListOfDescriptorsWithPositions currentListWithCoordinates = 
						new ListOfDescriptorsWithPositionsImpl(currentList, x, y);
				buffer[bufferIndex] = currentListWithCoordinates;
				if ((bufferIndex > 0) || (componentsGenerationNumber <= 1)) {
					boolean bufferPositionsAreLegal = testIfBufferPositionsAreLegal(buffer, bufferIndex);
					if (bufferPositionsAreLegal == true) {
						Set<List<Group>> newSetsOfFactorizableDescriptors = 
								new HashSet<List<Group>>(); 
						newSetsOfFactorizableDescriptors = saveNewSubSets(buffer, 0, bufferIndex, newSetsOfFactorizableDescriptors);
						setsOfFactorizableDescriptors.addAll(newSetsOfFactorizableDescriptors);
					}
				}
				if (y < signal.getSignalSize()-1) {
					x = y+1;
					y = x;
					bufferIndex++;
				}
				else {
					if (bufferIndex > 0) {
						int previousFirstPos = buffer[bufferIndex - 1].getFirstPosition();
						int previousLastPos = buffer[bufferIndex - 1].getLastPosition();
						x = previousFirstPos;
						y = previousLastPos + 1;
						bufferIndex--;
					}
					else {
						x++;
						y = x;
					}
				}
			}
		}
		return setsOfFactorizableDescriptors;
	}
	
	private boolean testIfBufferPositionsAreLegal(IListOfDescriptorsWithPositions[] buffer, int maxBufferIndex) {
		boolean bufferPositionsAreLegal = true;
		if (maxBufferIndex + 1 <= Settings.MAX_NB_OF_GROUPS_IN_RELATIONS) {
			if (componentsGenerationNumber > 1) {
				int nbOfSize1SetsOfDescriptors = 0;
				int bufferIndex = 0;
				int nbOfAdjacentSize1Components = 0;
				while (bufferIndex <= maxBufferIndex && 
						nbOfSize1SetsOfDescriptors < (Settings.MAX_NB_OF_SIZE1_COMPONENTS + 1) &&
						nbOfAdjacentSize1Components < (Settings.MAX_NB_OF_ADJACENT_SIZE1_COMPONENTS + 1)) {
					if ((buffer[bufferIndex].getLastPosition() - buffer[bufferIndex].getFirstPosition() == 0)) {
						nbOfSize1SetsOfDescriptors++;
						nbOfAdjacentSize1Components++;
					}
					else nbOfAdjacentSize1Components = 0;
					bufferIndex++;
				}
				if (nbOfSize1SetsOfDescriptors > Settings.MAX_NB_OF_SIZE1_COMPONENTS ||
						nbOfAdjacentSize1Components > Settings.MAX_NB_OF_ADJACENT_SIZE1_COMPONENTS)
					bufferPositionsAreLegal = false;
			}			
		}
		else bufferPositionsAreLegal = false;
		return bufferPositionsAreLegal;
	}
	
	private Set<List<Group>> saveNewSubSets(IListOfDescriptorsWithPositions[] buffer, int currentBufferIndex, 
			int maxBufferIndex,	Set<List<Group>> previousSubSets){
		Set<List<Group>> currentSubSets = new HashSet<List<Group>>();
		if (currentBufferIndex <= maxBufferIndex) {
			for (Group currentDescriptor : buffer[currentBufferIndex].getListOfDescriptors()) {
				if (!previousSubSets.isEmpty()) {
					for (List<Group> currentSubSet : previousSubSets) {
						List<Group> currentSubSetForked = new ArrayList<Group>();
						currentSubSetForked.addAll(currentSubSet);
						currentSubSetForked.add(currentDescriptor);
						currentSubSets.add(currentSubSetForked);	
					}
				}
				else {
					List<Group> newSubSet = new ArrayList<Group>();
					newSubSet.add(currentDescriptor);
					currentSubSets.add(newSubSet);
				}
			}
			currentBufferIndex++;
			return saveNewSubSets(buffer, currentBufferIndex, maxBufferIndex, currentSubSets);
		}
		else return currentSubSets = previousSubSets;
	}
	
	private void setCombinatoryArrayOfDescriptors() throws SynTreeGenerationException {
		initializeCombinatoryArrayOfDescriptors();
		Map<String, List<Group>> positionToListOfDescriptors = mapPositionToListOfDescriptors();
		List<String> listOfPositions = new ArrayList<String>(positionToListOfDescriptors.keySet());
		for (String positionString : listOfPositions) {
			String[] subPositionsArray = positionString.split(",");
			int firstLetterPosition = Integer.parseInt(subPositionsArray[0]);
			int lastLetterPosition = Integer.parseInt(subPositionsArray[subPositionsArray.length -1]);
			List<Group> listOfDescriptors = positionToListOfDescriptors.get(positionString);
			Group[] arrayOfDescriptors = listOfDescriptors.toArray(new Group[listOfDescriptors.size()]);
			combinatoryArrayOfDescriptors[firstLetterPosition-1][lastLetterPosition-1] = arrayOfDescriptors;
		}		
	}
	
	private void initializeCombinatoryArrayOfDescriptors(){
		combinatoryArrayOfDescriptors = new Group[signal.getSignalSize()][signal.getSignalSize()][];
		for (int i=0 ; i<signal.getSignalSize() ; i++) {
			for (int j=0 ; j<signal.getSignalSize() ; j++) {
				combinatoryArrayOfDescriptors[i][j] = new Group[0];
			}
		}
	}
	
	private Map<String, List<Group>> mapPositionToListOfDescriptors() 
			throws SynTreeGenerationException {
		Map<String, List<Group>> positionToListOfDescriptors = new HashMap<String, List<Group>>();
		for (Group currentDescriptor : previousGenOfDescriptors) {
			String positionsString = getStringOfLetterPositions(currentDescriptor);
			if (positionToListOfDescriptors.containsKey(positionsString))
				positionToListOfDescriptors.get(positionsString).add(currentDescriptor);
			else {
				List<Group> newListOfDescriptors = new ArrayList<Group>();
				newListOfDescriptors.add(currentDescriptor);
				positionToListOfDescriptors.put(positionsString, newListOfDescriptors);
			}
		}
		return positionToListOfDescriptors;
	}
	
	private String getStringOfLetterPositions(Group group) throws SynTreeGenerationException{
		String positions;
		List<String> listOfPositions = new ArrayList<String>();
		List<String> listOfPropertiesWithPath = group.getListOfPropertiesWithPath();
		for (String propertyWithPath : listOfPropertiesWithPath) {
			if (propertyWithPath.contains("letter/position")){
				int lastSlashIndex = propertyWithPath.lastIndexOf(Settings.PATH_SEPARATOR);
				String positionValue = propertyWithPath.substring(lastSlashIndex + 1);
				listOfPositions.add(positionValue);
			}
		}
		if (!listOfPositions.isEmpty()) {
			StringBuilder sB = new StringBuilder();
			for (int i=0 ; i<listOfPositions.size() ; i++) {
				sB.append(listOfPositions.get(i));
				if (i<listOfPositions.size()-1)
					sB.append(",");
			}
			positions = sB.toString();
			return positions;			
		}
		else throw new SynTreeGenerationException("ComponentGrouper : no letter position was found in current descriptor");
	}

}
