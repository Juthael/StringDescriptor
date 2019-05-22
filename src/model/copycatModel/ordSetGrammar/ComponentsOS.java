package model.copycatModel.ordSetGrammar;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import exceptions.OrderedSetsGenerationException;
import model.generalModel.IElement;
import model.orderedSetModel.IOrderedSet;
import model.orderedSetModel.impl.AbstractNonMinimalOS;
import settings.Settings;

public class ComponentsOS extends AbstractNonMinimalOS implements IOrderedSet {

	private static final String NAME = "components";
	private SizeOS size;
	private List<FrameOS> listOfFrames;
	
	public ComponentsOS(String elementID, SizeOS size, List<FrameOS> listOfFrames) {
		super(elementID);
		this.size = size;
		this.listOfFrames = listOfFrames;
	}
	
	public ComponentsOS(String elementID, boolean isCodingElement, SizeOS size, List<FrameOS> listOfFrames) {
		super(elementID, isCodingElement);
		this.size = size;
		this.listOfFrames = listOfFrames;
	}	

	@Override
	public List<IElement> getListOfComponents() {
		List<IElement> listOfComponents = super.getListOfComponents();
		listOfComponents.add(size);
		listOfComponents.addAll(listOfFrames);
		return listOfComponents;
	}

	@Override
	public String getDescriptorName() {
		return NAME;
	}
	
	@Override
	public Map<String, Set<String>> getContextualRelation(Set<String> setOfContextuallyRelevantElements, 
		boolean thisIsAComponentElement) throws OrderedSetsGenerationException{
		Map<String, Set<String>> contextualRelation = new HashMap<String, Set<String>>();
		contextualRelation.putAll(size.getContextualRelation(setOfContextuallyRelevantElements, thisIsAComponentElement));
		Set<String> componentsRelatedIds = new HashSet<String>();
		componentsRelatedIds.add(this.getElementID());
		componentsRelatedIds.addAll(
				size.getLowerSetContextuallyInformativeIDs(setOfContextuallyRelevantElements));
		for (IElement element : super.getListOfComponents()) {
			IOrderedSet orderedSet = (IOrderedSet) element;
			contextualRelation.putAll(
					orderedSet.getContextualRelation(setOfContextuallyRelevantElements, thisIsAComponentElement));
			componentsRelatedIds.addAll(
					orderedSet.getLowerSetContextuallyInformativeIDs(setOfContextuallyRelevantElements));
		}
		Set<String> relevantFrameElements;
		int frameIndex = 0;
		if (thisIsAComponentElement)
			relevantFrameElements = setOfContextuallyRelevantElements;
		else {
			relevantFrameElements = 
					listOfFrames.get(0).getLowerSetContextuallyInformativeIDs(setOfContextuallyRelevantElements);
			frameIndex++;
		}
		for (int i = frameIndex ; i<listOfFrames.size() ; i++) {
			relevantFrameElements.retainAll(
					listOfFrames.get(i).getLowerSetContextuallyInformativeIDs(setOfContextuallyRelevantElements));
		}
		componentsRelatedIds.addAll(relevantFrameElements);		
		contextualRelation.put(this.getElementID(), componentsRelatedIds);
		contextualRelation.putAll(
				listOfFrames.get(0).getContextualRelation(
						relevantFrameElements, Settings.THIS_IS_A_COMPONENT_ELEMENT));
		return contextualRelation;	
	}	
	
	@Override
	public Set<String> getLowerSetInformativeIDs(){
		Set<String> informativeLowerSet = new HashSet<String>();
		for (IElement element : super.getListOfComponents()) {
			IOrderedSet orderedSet = (IOrderedSet) element;
			informativeLowerSet.addAll(orderedSet.getLowerSetInformativeIDs());			
		}
		informativeLowerSet.add(this.getElementID());
		informativeLowerSet.addAll(size.getLowerSetInformativeIDs());
		Set<String> relevantFrameElements = listOfFrames.get(0).getLowerSetInformativeIDs();
		for (int i=1 ; i<listOfFrames.size() ; i++) {
			relevantFrameElements.retainAll(listOfFrames.get(i).getLowerSetInformativeIDs());
		}
		for (FrameOS frame : listOfFrames) {
			informativeLowerSet.addAll(frame.getLowerSetContextuallyInformativeIDs(relevantFrameElements));
		}
		return informativeLowerSet;
	}
	
	@Override
	public Set<String> getLowerSetContextuallyInformativeIDs(Set<String> setOfContextuallyRelevantElements){
		Set<String> contextuallyInformativeLowerSet = new HashSet<String>();
		if (getThisSetIsInformative()) {
			for (IElement element : super.getListOfComponents()) {
				IOrderedSet orderedSet = (IOrderedSet) element;
				contextuallyInformativeLowerSet.addAll(
						orderedSet.getLowerSetContextuallyInformativeIDs(setOfContextuallyRelevantElements));
			}
			contextuallyInformativeLowerSet.addAll(
					size.getLowerSetContextuallyInformativeIDs(setOfContextuallyRelevantElements));
			Set<String> relevantFrameElementIDs = 
					listOfFrames.get(0).getLowerSetContextuallyInformativeIDs(setOfContextuallyRelevantElements);
			for (int i=1 ; i<listOfFrames.size() ; i++) {
				relevantFrameElementIDs.retainAll(
						listOfFrames.get(i).getLowerSetContextuallyInformativeIDs(setOfContextuallyRelevantElements));
			}
			contextuallyInformativeLowerSet.addAll(relevantFrameElementIDs);	
		}
		return contextuallyInformativeLowerSet;
	}	
	
	@Override
	public void eliminateRedundancies(Map<String, IOrderedSet> idToIOrderedSet) {
		super.eliminateRedundancies(idToIOrderedSet);
		if (!size.equals(idToIOrderedSet.get(size.getElementID())))
			size = (SizeOS) idToIOrderedSet.get(size.getElementID());
		for (FrameOS frame : listOfFrames) {
			if (!frame.equals(idToIOrderedSet.get(frame.getElementID())))
				frame = (FrameOS) idToIOrderedSet.get(frame.getElementID());
			frame.eliminateRedundancies(idToIOrderedSet);
		}
	}		

}
