package model.copycatModel.synTreeGrammar;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import exceptions.OrderedSetsGenerationException;
import model.copycatModel.ordSetGrammar.AbsCommonDiffOS;
import model.generalModel.IElement;
import model.orderedSetModel.IOrderedSet;
import model.orderedSetModel.impl.MinimalOS;
import model.synTreeModel.IGrammaticalST;
import model.synTreeModel.impl.MinimalST;
import model.synTreeModel.impl.GrammaticalST;

public class AbsCommonDiff extends GrammaticalST implements IGrammaticalST, Cloneable {

	private static final String DESCRIPTOR_NAME = "absCommonDiff";
	private final MinimalST absCommonDiffValue; 
	
	public AbsCommonDiff(String absCommonDiffValue) throws CloneNotSupportedException {
		this.absCommonDiffValue = new MinimalST(absCommonDiffValue);
		setHashCode();
	}
	
	@Override
	protected AbsCommonDiff clone() throws CloneNotSupportedException {
		AbsCommonDiff cloneAbsCommonDiff = new AbsCommonDiff(absCommonDiffValue.getValue());
		return cloneAbsCommonDiff;
	}
	
	@Override
	public String getDescriptorName() {
		return DESCRIPTOR_NAME;
	}	
	
	@Override
	public List<IElement> getListOfComponents(){
		List<IElement> listOfComponents = new ArrayList<IElement>();
		listOfComponents.add(absCommonDiffValue);
		return listOfComponents;
	}	
	
	@Override
	public IOrderedSet upgradeAsTheElementOfAnOrderedSet(Map<List<String>, Integer> listOfPropertiesToIndex) 
			throws OrderedSetsGenerationException {
		IOrderedSet absCommonDiffOS;
		List<String> listOfPropertiesWithPath = getListOfPropertiesWithPath();
		Integer absCommonDiffIndex = listOfPropertiesToIndex.get(listOfPropertiesWithPath);
		String absCommonDiffID = getDescriptorName().concat(absCommonDiffIndex.toString());
		MinimalOS absCommonDiffProperty = 
				(MinimalOS) absCommonDiffValue.upgradeAsTheElementOfAnOrderedSet(listOfPropertiesToIndex);
		absCommonDiffOS = new AbsCommonDiffOS(absCommonDiffID, absCommonDiffProperty);
		return absCommonDiffOS;		
	}

}
