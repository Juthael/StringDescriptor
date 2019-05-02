package model.copycatModel.ordSetGrammar;

import java.util.List;
import java.util.Map;

import model.generalModel.IElement;
import model.orderedSetModel.IOrderedSet;
import model.orderedSetModel.impl.MinimalOS;
import model.orderedSetModel.impl.AbstractNonMinimalOS;

public class CommonDiffOS extends AbstractNonMinimalOS implements IOrderedSet {

	private static final String NAME = "commonDiff";
	private MinimalOS commonDiffProperty;
	
	public CommonDiffOS(String elementID, MinimalOS commonDiffProperty) {
		super(elementID);
		this.commonDiffProperty = commonDiffProperty;
	}

	@Override
	public List<IElement> getListOfComponents() {
		List<IElement> listOfComponents = super.getListOfComponents();
		listOfComponents.add(commonDiffProperty);
		return listOfComponents;
	}

	@Override
	public String getDescriptorName() {
		return NAME;
	}
	
	@Override
	public void eliminateRedundancies(Map<String, IOrderedSet> idToIOrderedSet) {
		super.eliminateRedundancies(idToIOrderedSet);
		if (!commonDiffProperty.equals(idToIOrderedSet.get(commonDiffProperty.getElementID())))
			commonDiffProperty = (MinimalOS) idToIOrderedSet.get(commonDiffProperty.getElementID());
	}		

}
