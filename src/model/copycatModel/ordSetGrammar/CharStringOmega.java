package model.copycatModel.ordSetGrammar;

import java.util.List;

import model.generalModel.IElement;
import model.orderedSetModel.IOrderedSet;
import model.orderedSetModel.impl.OmegaOS;
import settings.Settings;

public class CharStringOmega extends OmegaOS implements IOrderedSet {

	private static final String NAME = "charString";
	private DirectionOS direction;
	private StructureOS structure;
	private ComponentsOS components;
	
	public CharStringOmega(String elementID, DirectionOS direction, StructureOS structure, ComponentsOS components, String verbalDescription) {
		super(elementID, verbalDescription);
		this.direction = direction;
		this.structure = structure;
		this.components = components;
		setMayBeTheCodedElement(true);
		eliminateRedundancies();
		if (Settings.NON_INFORMATIVE_ELEMENTS_MUST_BE_REMOVED)
			checkThatInformativeStatusIsUpToDate();
	}

	@Override
	public List<IElement> getListOfComponents() {
		List<IElement> listOfComponents = super.getListOfComponents();
		listOfComponents.add(direction);
		listOfComponents.add(structure);
		listOfComponents.add(components);
		return listOfComponents;
	}

	@Override
	public String getDescriptorName() {
		return NAME;
	}	
	
	@Override
	public void eliminateRedundancies(IOrderedSet orderedSet) {
		super.eliminateRedundancies(orderedSet);
		if (direction.getElementID().equals(orderedSet.getElementID()) && direction != orderedSet) {
			direction = (DirectionOS) orderedSet;
		}
		else direction.eliminateRedundancies(orderedSet);
		if (structure.getElementID().equals(orderedSet.getElementID()) && structure != orderedSet) {
			structure = (StructureOS) orderedSet;
		}
		else direction.eliminateRedundancies(orderedSet);
		if (components.getElementID().equals(orderedSet.getElementID()) && components != orderedSet) {
			components = (ComponentsOS) orderedSet;
		}
		else components.eliminateRedundancies(orderedSet);
	}	

}
