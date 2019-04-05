package verbalization.verbalStructureModel.impl;

import exceptions.VerbalizationException;
import verbalization.verbalStructureModel.IRepeatOrder;

public class RepeatOrderImpl implements IRepeatOrder {
	
	private String verbalRepeatOrder;
	
	public RepeatOrderImpl(String numberOfRepetitions) throws VerbalizationException {
		verbalRepeatOrder = setVerbalRepeatOrder(numberOfRepetitions);
	}
	
	@Override
	public String getverbalRepeatOrder() {
		return verbalRepeatOrder;
	}
	
	private String setVerbalRepeatOrder(String numberOfRepetitions) 
			throws VerbalizationException {
		String repetitionString;
		switch (numberOfRepetitions) {
			case "0" : 
				repetitionString = "";
				break;
			case "1" : 
				repetitionString = "consider it as a group consisting of a single element";
				break;
			case "2" :
				repetitionString = "repeat";
				break;
			case "3" :
			case "4" :
			case "5" :
			case "6" :
			case "7" :
			case "8" :
			case "9" :
			case "10" :
			case "11" :
			case "12" :
				int repeatXTimes = Integer.parseInt(numberOfRepetitions) - 1;
				repetitionString = "repeat " + Integer.toString(repeatXTimes) + " times";
				break;
			default :
				throw new VerbalizationException("Repetition.setRepetitionString() : the parameter "
						+ numberOfRepetitions + " can't be handled.");
		}
		return repetitionString;
	}

}
