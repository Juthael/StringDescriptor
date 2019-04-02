package syntacticTreesGeneration.implementations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

import copycatModel.grammar.Group;
import copycatModel.interfaces.SignalInterface;
import exceptions.DescriptorsBuilderCriticalException;
import settings.DescGenSettings;
import syntacticTreesGeneration.interfaces.ComponentGrouperInterface;
import syntacticTreesGeneration.interfaces.ListOfDescriptorsWithPositionsInterface;

public class ComponentGrouperV2 implements ComponentGrouperInterface {

	private final int componentsGenerationNumber;
	private final SignalInterface signal;
	private final ArrayList<Group> previousGenOfDescriptors;
	boolean nextGenerationWillBeTheLast;	
	private Group[][][] combinatoryArrayOfDescriptors;
	
	public ComponentGrouperV2(int componentsGenerationNumber, boolean nextGenerationWillBeTheLast, SignalInterface signal, 
			ArrayList<Group> previousGenOfDescriptors) throws DescriptorsBuilderCriticalException {
		this.componentsGenerationNumber = componentsGenerationNumber;
		this.signal = signal;
		this.previousGenOfDescriptors = previousGenOfDescriptors;
		this.nextGenerationWillBeTheLast = nextGenerationWillBeTheLast;	
		setCombinatoryArrayOfDescriptors();
	}

	@Override
	public HashSet<ArrayList<Group>> getSetsOfFactorizableDescriptors(){
		HashSet<ArrayList<Group>> setsOfFactorizableDescriptors;
		if (nextGenerationWillBeTheLast == false)
			setsOfFactorizableDescriptors = getAllSetsOfFactorizableDescriptors();
		else {
			setsOfFactorizableDescriptors = getSetsOfFactorizableDescriptorsCoveringTheWholeString();
		}
		return setsOfFactorizableDescriptors;
	}
	
	private HashSet<ArrayList<Group>> getSetsOfFactorizableDescriptorsCoveringTheWholeString(){
		HashSet<ArrayList<Group>> setsOfFactorizableDescriptors = new HashSet<ArrayList<Group>>();
		int x = 0, y = 0, bufferIndex = 0;
		ListOfDescriptorsWithPositionsInterface[] buffer = new ListOfDescriptorsWithPositionsV1[signal.getSignalSize()]; 
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
				ArrayList<Group> currentList = 
						new ArrayList<Group>(Arrays.asList(combinatoryArrayOfDescriptors[x][y]));
				ListOfDescriptorsWithPositionsInterface currentListWithCoordinates = 
						new ListOfDescriptorsWithPositionsV1(currentList, x, y);
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
							HashSet<ArrayList<Group>> newSetsOfFactorizableDescriptors = 
									new HashSet<ArrayList<Group>>(); 
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
	
	private HashSet<ArrayList<Group>> getAllSetsOfFactorizableDescriptors() {
		HashSet<ArrayList<Group>> setsOfFactorizableDescriptors = new HashSet<ArrayList<Group>>();
		int x = 0, y = 0, bufferIndex = 0;
		ListOfDescriptorsWithPositionsInterface[] buffer = new ListOfDescriptorsWithPositionsV1[signal.getSignalSize()]; 
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
				ArrayList<Group> currentList = 
						new ArrayList<Group>(Arrays.asList(combinatoryArrayOfDescriptors[x][y]));
				ListOfDescriptorsWithPositionsInterface currentListWithCoordinates = 
						new ListOfDescriptorsWithPositionsV1(currentList, x, y);
				buffer[bufferIndex] = currentListWithCoordinates;
				if ((bufferIndex > 0) || (componentsGenerationNumber <= 1)) {
					boolean bufferPositionsAreLegal = testIfBufferPositionsAreLegal(buffer, bufferIndex);
					if (bufferPositionsAreLegal == true) {
						HashSet<ArrayList<Group>> newSetsOfFactorizableDescriptors = 
								new HashSet<ArrayList<Group>>(); 
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
	
	private boolean testIfBufferPositionsAreLegal(ListOfDescriptorsWithPositionsInterface[] buffer, int maxBufferIndex) {
		boolean bufferPositionsAreLegal = true;
		if (maxBufferIndex + 1 <= DescGenSettings.MAX_NB_OF_GROUPS_IN_RELATIONS) {
			if (componentsGenerationNumber > 1) {
				int nbOfSize1SetsOfDescriptors = 0;
				int bufferIndex = 0;
				int nbOfAdjacentSize1Components = 0;
				while (bufferIndex <= maxBufferIndex && 
						nbOfSize1SetsOfDescriptors < (DescGenSettings.MAX_NB_OF_SIZE1_COMPONENTS + 1) &&
						nbOfAdjacentSize1Components < (DescGenSettings.MAX_NB_OF_ADJACENT_SIZE1_COMPONENTS + 1)) {
					if ((buffer[bufferIndex].getLastPosition() - buffer[bufferIndex].getFirstPosition() == 0)) {
						nbOfSize1SetsOfDescriptors++;
						nbOfAdjacentSize1Components++;
					}
					else nbOfAdjacentSize1Components = 0;
					bufferIndex++;
				}
				if (nbOfSize1SetsOfDescriptors > DescGenSettings.MAX_NB_OF_SIZE1_COMPONENTS ||
						nbOfAdjacentSize1Components > DescGenSettings.MAX_NB_OF_ADJACENT_SIZE1_COMPONENTS)
					bufferPositionsAreLegal = false;
			}			
		}
		else bufferPositionsAreLegal = false;
		return bufferPositionsAreLegal;
	}
	
	private HashSet<ArrayList<Group>> saveNewSubSets(ListOfDescriptorsWithPositionsInterface[] buffer, int currentBufferIndex, 
			int maxBufferIndex,	HashSet<ArrayList<Group>> previousSubSets){
		HashSet<ArrayList<Group>> currentSubSets = new HashSet<ArrayList<Group>>();
		if (currentBufferIndex <= maxBufferIndex) {
			for (Group currentDescriptor : buffer[currentBufferIndex].getListOfDescriptors()) {
				if (!previousSubSets.isEmpty()) {
					for (ArrayList<Group> currentSubSet : previousSubSets) {
						ArrayList<Group> currentSubSetForked = new ArrayList<Group>();
						currentSubSetForked.addAll(currentSubSet);
						currentSubSetForked.add(currentDescriptor);
						currentSubSets.add(currentSubSetForked);	
					}
				}
				else {
					ArrayList<Group> newSubSet = new ArrayList<Group>();
					newSubSet.add(currentDescriptor);
					currentSubSets.add(newSubSet);
				}
			}
			currentBufferIndex++;
			return saveNewSubSets(buffer, currentBufferIndex, maxBufferIndex, currentSubSets);
		}
		else return currentSubSets = previousSubSets;
	}
	
	private void setCombinatoryArrayOfDescriptors() throws DescriptorsBuilderCriticalException {
		initializeCombinatoryArrayOfDescriptors();
		HashMap<String, ArrayList<Group>> positionToListOfDescriptors = mapPositionToListOfDescriptors();
		ArrayList<String> listOfPositions = new ArrayList<String>(positionToListOfDescriptors.keySet());
		for (String positionString : listOfPositions) {
			String[] subPositionsArray = positionString.split(",");
			int firstLetterPosition = Integer.parseInt(subPositionsArray[0]);
			int lastLetterPosition = Integer.parseInt(subPositionsArray[subPositionsArray.length -1]);
			ArrayList<Group> listOfDescriptors = positionToListOfDescriptors.get(positionString);
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
	
	private HashMap<String, ArrayList<Group>> mapPositionToListOfDescriptors() 
			throws DescriptorsBuilderCriticalException {
		HashMap<String, ArrayList<Group>> positionToListOfDescriptors = new HashMap<String, ArrayList<Group>>();
		for (Group currentDescriptor : previousGenOfDescriptors) {
			String positionsString = getStringOfLetterPositions(currentDescriptor);
			if (positionToListOfDescriptors.containsKey(positionsString))
				positionToListOfDescriptors.get(positionsString).add(currentDescriptor);
			else {
				ArrayList<Group> newListOfDescriptors = new ArrayList<Group>();
				newListOfDescriptors.add(currentDescriptor);
				positionToListOfDescriptors.put(positionsString, newListOfDescriptors);
			}
		}
		return positionToListOfDescriptors;
	}
	
	private String getStringOfLetterPositions(Group group) throws DescriptorsBuilderCriticalException{
		String positions;
		ArrayList<String> listOfPositions = new ArrayList<String>();
		ArrayList<String> listOfPropertiesWithPath = group.getListOfPropertiesWithPath();
		for (String propertyWithPath : listOfPropertiesWithPath) {
			if (propertyWithPath.contains("letter/position")){
				int lastSlashIndex = propertyWithPath.lastIndexOf("/");
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
		else throw new DescriptorsBuilderCriticalException("ComponentGrouper : no letter position was found in current descriptor");
	}

}
