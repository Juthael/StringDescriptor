package syntacticTreesGeneration.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import exceptions.SynTreeGenerationException;
import model.synTreeModel.ISignal;
import model.synTreeModel.IGrammaticalST;
import settings.Settings;
import syntacticTreesGeneration.IDescriptorSpanGetter;

public class DescriptorSpanGetter implements IDescriptorSpanGetter {

	public static List<Integer> getDescriptorSpan (IGrammaticalST grammaticalST) 
			throws SynTreeGenerationException {
		List<Integer> listOfLetterPositionsOccupied = new ArrayList<Integer>();
		List<String> listOfPropertiesWithPath = grammaticalST.getListOfPropertiesWithPath();
		for (String propertyWithPath : listOfPropertiesWithPath) {
			if (propertyWithPath.contains("letter/position")) {
				int lastSlashIndex = propertyWithPath.lastIndexOf(Settings.PATH_SEPARATOR);
				String letterPositionString = propertyWithPath.substring(lastSlashIndex + 1);
				Integer letterPosition = Integer.parseInt(letterPositionString);
				listOfLetterPositionsOccupied.add(letterPosition);
			}
		}
		if (listOfLetterPositionsOccupied.size() != 0) {
			return listOfLetterPositionsOccupied;
		}
		else throw new SynTreeGenerationException("DescriptorSpanGetter : list of positions occupied is empty");
	}
	
	public static boolean testIfWholeStringIsDescribed(ISignal signal, 
			List<IGrammaticalST> listOfDescriptors) 
			throws SynTreeGenerationException {
		boolean wholeStringIsDescribed;
		Set<Integer> setOfLetterPositionsOccupied = new HashSet<Integer>();
		for (IGrammaticalST descriptor : listOfDescriptors) {
			setOfLetterPositionsOccupied.addAll(DescriptorSpanGetter.getDescriptorSpan(descriptor));
		}
		wholeStringIsDescribed = (setOfLetterPositionsOccupied.size() == signal.getSignalSize());
		return wholeStringIsDescribed;
	}	

}
