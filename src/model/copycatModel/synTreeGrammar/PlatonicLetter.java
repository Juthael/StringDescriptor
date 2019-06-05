package model.copycatModel.synTreeGrammar;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import exceptions.OrderedSetsGenerationException;
import model.copycatModel.ordSetGrammar.PlatonicLetterOS;
import model.generalModel.IElement;
import model.orderedSetModel.IOrderedSet;
import model.orderedSetModel.impl.MinimalOS;
import model.synTreeModel.IGrammaticalST;
import model.synTreeModel.impl.MinimalST;
import model.synTreeModel.impl.GrammaticalST;

public class PlatonicLetter extends GrammaticalST implements IGrammaticalST, Cloneable {

	private static final String DESCRIPTOR_NAME = "platonicLetter";
	private MinimalST platonicLetterValue; 
	
	public PlatonicLetter(String platonicLetterValue) throws CloneNotSupportedException {
		this.platonicLetterValue = new MinimalST(platonicLetterValue);
		setHashCode();
	}
	
	protected PlatonicLetter clone() throws CloneNotSupportedException {
		PlatonicLetter clonePlatonicLetter = new PlatonicLetter(platonicLetterValue.getValue());
		return clonePlatonicLetter;
	}

	@Override
	public String getDescriptorName() {
		return DESCRIPTOR_NAME;
	}
	
	@Override
	public List<IElement> getListOfComponents(){
		List<IElement> listOfComponents = new ArrayList<IElement>();
		listOfComponents.add(platonicLetterValue);
		return listOfComponents;
	}	
	
	@Override
	public IOrderedSet upgradeAsTheElementOfAnOrderedSet(Map<List<String>, Integer> listOfPropertiesToIndex) 
			throws OrderedSetsGenerationException {
		IOrderedSet platonicLetterOS;
		List<String> listOfPropertiesWithPath = getListOfPropertiesWithPath();
		Integer platonicLetterIndex = listOfPropertiesToIndex.get(listOfPropertiesWithPath);
		String platonicLetterID = getDescriptorName().concat(platonicLetterIndex.toString());
		MinimalOS platonicLetterProperty = 
				(MinimalOS) platonicLetterValue.upgradeAsTheElementOfAnOrderedSet(listOfPropertiesToIndex);
		platonicLetterOS = new PlatonicLetterOS(platonicLetterID, platonicLetterProperty);
		return platonicLetterOS;		
	}	

}
