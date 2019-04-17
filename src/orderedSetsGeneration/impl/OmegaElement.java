package orderedSetsGeneration.impl;

import java.util.ArrayList;
import java.util.List;

import model.generalModel.IElement;
import model.orderedSetModel.ISetElement;
import model.orderedSetModel.impl.NonMinimalRelevantSetElement;

public class OmegaElement extends NonMinimalRelevantSetElement implements ISetElement {

	private static final String NAME = "omega";
	private List<ISetElement> listOfSubMaxElements = new ArrayList<ISetElement>();
	
	public OmegaElement(List<ISetElement> listOfSetElements) {
		super(NAME);
		for (ISetElement element : listOfSetElements) {
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
