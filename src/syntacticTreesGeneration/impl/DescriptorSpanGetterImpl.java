package syntacticTreesGeneration.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import copycatModel.ISynTreeIntegrableElement;
import copycatModel.ISignal;
import exceptions.DescriptorsBuilderException;
import syntacticTreesGeneration.IDescriptorSpanGetter;

public class DescriptorSpanGetterImpl implements IDescriptorSpanGetter {

	public static List<Integer> getDescriptorSpan (ISynTreeIntegrableElement synTreeIntegrableElement) 
			throws DescriptorsBuilderException {
		List<Integer> listOfLetterPositionsOccupied = new ArrayList<Integer>();
		List<String> listOfPropertiesWithPath = synTreeIntegrableElement.getListOfPropertiesWithPath();
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
		else throw new DescriptorsBuilderException("DescriptorSpanGetter : list of positions occupied is empty");
	}
	
	public static boolean testIfWholeStringIsDescribed(ISignal signal, 
			List<ISynTreeIntegrableElement> listOfDescriptors) 
			throws DescriptorsBuilderException {
		boolean wholeStringIsDescribed;
		Set<Integer> setOfLetterPositionsOccupied = new HashSet<Integer>();
		for (ISynTreeIntegrableElement descriptor : listOfDescriptors) {
			setOfLetterPositionsOccupied.addAll(DescriptorSpanGetterImpl.getDescriptorSpan(descriptor));
		}
		wholeStringIsDescribed = (setOfLetterPositionsOccupied.size() == signal.getSignalSize());
		return wholeStringIsDescribed;
	}	

}
