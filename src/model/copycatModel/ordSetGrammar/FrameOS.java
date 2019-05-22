package model.copycatModel.ordSetGrammar;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import exceptions.OrderedSetsGenerationException;
import model.generalModel.IElement;
import model.orderedSetModel.IOrderedSet;
import model.orderedSetModel.impl.AbstractNonMinimalOS;
import settings.Settings;

public class FrameOS extends AbstractNonMinimalOS implements IOrderedSet {

	private static final String NAME = "frame";
	private SizeOS size;
	private WhichPositionTypeOS positionType;
	private RelationsOrLetterOS relationsOrLetter;
	
	public FrameOS(String elementID, SizeOS size, WhichPositionTypeOS positionType, 
			RelationsOrLetterOS relationsOrLetter) {
		super(elementID);
		this.size = size;
		this.positionType = positionType;
		this.relationsOrLetter = relationsOrLetter;
	}

	public FrameOS(String elementID, boolean isCodingElement, SizeOS size, WhichPositionTypeOS positionType, 
			RelationsOrLetterOS relationsOrLetter) {
		super(elementID, isCodingElement);
		this.size = size;
		this.positionType = positionType;
		this.relationsOrLetter = relationsOrLetter;		
	}

	@Override
	public List<IElement> getListOfComponents() {
		List<IElement> listOfComponents = super.getListOfComponents();
		listOfComponents.add(size);
		listOfComponents.add(positionType);
		listOfComponents.add(relationsOrLetter);
		return listOfComponents;
	}

	@Override
	public String getDescriptorName() {
		return NAME;
	}
	
	@Override
	public Map<String, Set<String>> getReducedRelation() throws OrderedSetsGenerationException {
		if (Settings.FRAMED_COMPONENTS_RETURNS_CONTEXTUAL_RELATION) {
			Set<String> emptyListOfRelevantElementsIDs = new HashSet<String>();
			return getContextualRelation(emptyListOfRelevantElementsIDs, Settings.THIS_IS_NOT_A_COMPONENT_ELEMENT);
		}
		else return super.getReducedRelation();
	}	
	
	@Override
	public void eliminateRedundancies(Map<String, IOrderedSet> idToIOrderedSet) {
		super.eliminateRedundancies(idToIOrderedSet);
		if (!size.equals(idToIOrderedSet.get(size.getElementID())))
			size = (SizeOS) idToIOrderedSet.get(size.getElementID());
		size.eliminateRedundancies(idToIOrderedSet);
		if (!positionType.equals(idToIOrderedSet.get(positionType.getElementID())))
			positionType = (WhichPositionTypeOS) idToIOrderedSet.get(positionType.getElementID());
		positionType.eliminateRedundancies(idToIOrderedSet);
		if (!relationsOrLetter.equals(idToIOrderedSet.get(relationsOrLetter.getElementID())))
			relationsOrLetter = (RelationsOrLetterOS) idToIOrderedSet.get(relationsOrLetter.getElementID());
		relationsOrLetter.eliminateRedundancies(idToIOrderedSet);
	}
}
