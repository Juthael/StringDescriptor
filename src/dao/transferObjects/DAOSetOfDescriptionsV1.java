package dao.transferObjects;

import java.util.ArrayList;
import java.util.List;

import copycatModel.grammar.CharString;
import dao.tools.DAOStringValidityCheckerV1;
import exceptions.DAOException;
import exceptions.DescriptorsBuilderCriticalException;
import syntacticTreesGeneration.implementations.ListOfDescriptorsBuilderV1;
import syntacticTreesGeneration.interfaces.ListOfDescriptorsBuilderInterface;

public class DAOSetOfDescriptionsV1 {

	String stringToBeDescribed="";
	boolean stringCanBeReadInBothDirections = false;
	private List<DAODescriptionV1> listOfDescriptions = new ArrayList<DAODescriptionV1>();
	
	public DAOSetOfDescriptionsV1() {
	}
	
	public void setStringToBeDescribed(String stringToBeDescribed) throws DAOException {
		List<CharString> listOfWholeStringDescriptors = new ArrayList<CharString>();
		try {
			this.stringToBeDescribed = stringToBeDescribed;
			DAOStringValidityCheckerV1.checkStringValidity(this.stringToBeDescribed);
			ListOfDescriptorsBuilderInterface descriptorsBuilderLeftToRight = 
					new ListOfDescriptorsBuilderV1(this.stringToBeDescribed, "fromLeftToRight");
			listOfWholeStringDescriptors.addAll(descriptorsBuilderLeftToRight.getListOfStringDescriptors());
			if (stringCanBeReadInBothDirections == true) {
				StringBuilder sB = new StringBuilder(this.stringToBeDescribed);
				ListOfDescriptorsBuilderInterface descriptorsBuilderRightToLeft = 
						new ListOfDescriptorsBuilderV1(sB.reverse().toString(), "fromRightToLeft");
				listOfWholeStringDescriptors.addAll(descriptorsBuilderRightToLeft.getListOfStringDescriptors());
			}
			if (listOfWholeStringDescriptors.isEmpty())
				throw new DAOException("Unexpected error. List of Descriptors is empty");			
		}
		catch (DescriptorsBuilderCriticalException descException) {
			throw new DAOException("Unexpected error. " + descException.getMessage());
		}
		catch (CloneNotSupportedException cloneException) {
			throw new DAOException("Unexpected error. Clone not supported exception");
		}
		for (CharString descriptor : listOfWholeStringDescriptors) {
			DAODescriptionV1 description = new DAODescriptionV1(descriptor);
			listOfDescriptions.add(description);
		}
	}
	
	public List<DAODescriptionV1> getDescriptionsOfThisString(){
		return listOfDescriptions;
	}

}
