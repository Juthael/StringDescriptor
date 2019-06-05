package model.copycatModel.synTreeGrammar;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import exceptions.OrderedSetsGenerationException;
import model.copycatModel.ordSetGrammar.EnumerationOS;
import model.generalModel.IElement;
import model.orderedSetModel.IOrderedSet;
import model.orderedSetModel.impl.MinimalOS;
import model.synTreeModel.IGrammaticalST;
import model.synTreeModel.impl.MinimalST;
import model.synTreeModel.impl.GrammaticalST;

public class Enumeration extends GrammaticalST implements IGrammaticalST, Cloneable {

	private static final String DESCRIPTOR_NAME = "enumeration";
	private MinimalST enumerationValue;
	
	public Enumeration(String enumerationValue) throws CloneNotSupportedException {
		this.enumerationValue = new MinimalST(enumerationValue);
		setHashCode();
	}
	
	@Override
	protected Enumeration clone() throws CloneNotSupportedException {
		Enumeration cloneEnumeration = new Enumeration(enumerationValue.getValue());
		return cloneEnumeration;
	}

	@Override
	public String getDescriptorName() {
		return DESCRIPTOR_NAME;
	}
	
	@Override
	public List<IElement> getListOfComponents(){
		List<IElement> listOfComponents = new ArrayList<IElement>();
		listOfComponents.add(enumerationValue);
		return listOfComponents;
	}	
	
	@Override
	public IOrderedSet upgradeAsTheElementOfAnOrderedSet(Map<List<String>, Integer> listOfPropertiesToIndex) 
			throws OrderedSetsGenerationException {
		IOrderedSet enumerationOS;
		List<String> listOfPropertiesWithPath = getListOfPropertiesWithPath();
		Integer enumerationIndex = listOfPropertiesToIndex.get(listOfPropertiesWithPath);
		String enumerationID = getDescriptorName().concat(enumerationIndex.toString());
		MinimalOS enumerationProperty = (MinimalOS) enumerationValue.upgradeAsTheElementOfAnOrderedSet(listOfPropertiesToIndex);
		enumerationOS = new EnumerationOS(enumerationID, enumerationProperty);
		return enumerationOS;		
	}

}
