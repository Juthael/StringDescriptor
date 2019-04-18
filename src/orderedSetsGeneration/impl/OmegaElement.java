package orderedSetsGeneration.impl;

import java.util.ArrayList;
import java.util.List;

import model.generalModel.IElement;
import model.orderedSetModel.ILowerSetElement;
import model.orderedSetModel.impl.NonMinimalRelevantLowerSetElement;

public class OmegaElement extends NonMinimalRelevantLowerSetElement implements ILowerSetElement {

	private static final String NAME = "omega";
	private List<ILowerSetElement> listOfSubMaxElements = new ArrayList<ILowerSetElement>();
	
	public OmegaElement(List<ILowerSetElement> listOfSetElements) {
		super(NAME);
		for (ILowerSetElement element : listOfSetElements) {
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
