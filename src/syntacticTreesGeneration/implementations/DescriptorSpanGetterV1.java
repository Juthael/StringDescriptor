package syntacticTreesGeneration.implementations;

import java.util.ArrayList;
import java.util.HashSet;

import copycatModel.interfaces.AbstractDescriptorInterface;
import copycatModel.interfaces.SignalInterface;
import exceptions.DescriptorsBuilderCriticalException;
import syntacticTreesGeneration.interfaces.DescriptorSpanGetterInterface;

public class DescriptorSpanGetterV1 implements DescriptorSpanGetterInterface {

	public static ArrayList<Integer> getDescriptorSpan (AbstractDescriptorInterface abstractDescriptor) 
			throws DescriptorsBuilderCriticalException {
		ArrayList<Integer> listOfLetterPositionsOccupied = new ArrayList<Integer>();
		ArrayList<String> listOfPropertiesWithPath = abstractDescriptor.getListOfPropertiesWithPath();
		for (String propertyWithPath : listOfPropertiesWithPath) {
			if (propertyWithPath.contains("letter/position")) {
				int lastSlashIndex = propertyWithPath.lastIndexOf("/");
				String letterPositionString = propertyWithPath.substring(lastSlashIndex + 1);
				Integer letterPosition = Integer.parseInt(letterPositionString);
				listOfLetterPositionsOccupied.add(letterPosition);
			}
		}
		if (listOfLetterPositionsOccupied.size() != 0) {
			return listOfLetterPositionsOccupied;
		}
		else throw new DescriptorsBuilderCriticalException("DescriptorSpanGetter : list of positions occupied is empty");
	}
	
	public static boolean testIfWholeStringIsDescribed(SignalInterface signal, 
			ArrayList<AbstractDescriptorInterface> listOfDescriptors) 
			throws DescriptorsBuilderCriticalException {
		boolean wholeStringIsDescribed;
		HashSet<Integer> setOfLetterPositionsOccupied = new HashSet<Integer>();
		for (AbstractDescriptorInterface descriptor : listOfDescriptors) {
			setOfLetterPositionsOccupied.addAll(DescriptorSpanGetterV1.getDescriptorSpan(descriptor));
		}
		wholeStringIsDescribed = (setOfLetterPositionsOccupied.size() == signal.getSignalSize());
		return wholeStringIsDescribed;
	}	

}
