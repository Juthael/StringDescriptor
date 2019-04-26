package orderedSetGeneration.impl;

import java.util.ArrayList;
import java.util.List;

import model.generalModel.IElement;
import model.orderedSetModel.IOrderedSet;
import model.orderedSetModel.impl.NonMinimalRelevantOS;

public class OmegaElement extends NonMinimalRelevantOS implements IOrderedSet {

	private static final String NAME = "omega";
	private List<IOrderedSet> listOfSubMaxElements = new ArrayList<IOrderedSet>();
	
	public OmegaElement(List<IOrderedSet> listOfSetElements) {
		super(NAME);
		for (IOrderedSet element : listOfSetElements) {
			element.setMayBeTheCodedElement(true);
			this.listOfSubMaxElements.add(element);
		}
	}

	@Override
	public String getDescriptorName() {
		return NAME;
	}
	
	public List<IElement> getListOfComponents(){
		List<IElement> listOfComponents = super.getListOfComponents();
		listOfComponents.addAll(listOfSubMaxElements);
		return listOfComponents;
	}	

}
