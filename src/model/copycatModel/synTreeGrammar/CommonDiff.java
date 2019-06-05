package model.copycatModel.synTreeGrammar;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import exceptions.OrderedSetsGenerationException;
import model.copycatModel.ordSetGrammar.CommonDiffOS;
import model.generalModel.IElement;
import model.orderedSetModel.IOrderedSet;
import model.orderedSetModel.impl.MinimalOS;
import model.synTreeModel.IGrammaticalST;
import model.synTreeModel.impl.MinimalST;
import model.synTreeModel.impl.GrammaticalST;

public class CommonDiff extends GrammaticalST implements IGrammaticalST, Cloneable {

	private static final String DESCRIPTOR_NAME = "commonDiff";
	private final MinimalST commonDiffValue; 
	
	public CommonDiff(String commonDiffValue) throws CloneNotSupportedException {
		this.commonDiffValue = new MinimalST(commonDiffValue);
		setHashCode();
	}
	
	@Override
	protected CommonDiff clone() throws CloneNotSupportedException {
		CommonDiff cloneCommonDiff = new CommonDiff(commonDiffValue.getValue());
		return cloneCommonDiff;
	}
	
	@Override
	public String getDescriptorName() {
		return DESCRIPTOR_NAME;
	}
	
	public String getValue() {
		return commonDiffValue.getValue();
	}
	
	@Override
	public List<IElement> getListOfComponents(){
		List<IElement> listOfComponents = new ArrayList<IElement>();
		listOfComponents.add(commonDiffValue);
		return listOfComponents;
	}	

	@Override
	public IOrderedSet upgradeAsTheElementOfAnOrderedSet(Map<List<String>, Integer> listOfPropertiesToIndex) 
			throws OrderedSetsGenerationException {
		IOrderedSet commonDiffOS;
		List<String> listOfPropertiesWithPath = getListOfPropertiesWithPath();
		Integer commonDiffIndex = listOfPropertiesToIndex.get(listOfPropertiesWithPath);
		String commonDiffID = getDescriptorName().concat(commonDiffIndex.toString());
		MinimalOS commonDiffProperty = (MinimalOS) commonDiffValue.upgradeAsTheElementOfAnOrderedSet(listOfPropertiesToIndex);
		commonDiffOS = new CommonDiffOS(commonDiffID, commonDiffProperty);
		return commonDiffOS;		
	}			

}
