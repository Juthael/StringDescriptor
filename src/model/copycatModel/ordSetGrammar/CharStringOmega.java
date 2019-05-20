package model.copycatModel.ordSetGrammar;

import java.util.List;
import java.util.Map;

import model.generalModel.IElement;
import model.orderedSetModel.IOrderedSet;
import model.orderedSetModel.impl.AbstractOmegaElement;
import settings.Settings;

public class CharStringOmega extends AbstractOmegaElement implements IOrderedSet {

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
	public void eliminateRedundancies(Map<String, IOrderedSet> idToIOrderedSet) {
		super.eliminateRedundancies(idToIOrderedSet);
		if (!direction.equals(idToIOrderedSet.get(direction.getElementID()))) {
			direction= (DirectionOS) idToIOrderedSet.get(direction.getElementID());
			direction.eliminateRedundancies(idToIOrderedSet);
		}
		if (!structure.equals(idToIOrderedSet.get(structure.getElementID()))) {
			structure = (StructureOS) idToIOrderedSet.get(structure.getElementID());
			structure.eliminateRedundancies(idToIOrderedSet);
		}
		if (!components.equals(idToIOrderedSet.get(components.getElementID()))) {
			components = (ComponentsOS) idToIOrderedSet.get(components.getElementID());
			components.eliminateRedundancies(idToIOrderedSet);
		}
	}	

}
