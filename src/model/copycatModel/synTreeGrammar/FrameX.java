package model.copycatModel.synTreeGrammar;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import exceptions.OrderedSetsGenerationException;
import exceptions.SynTreeGenerationException;
import model.generalModel.IElement;
import model.orderedSetModel.IOrderedSet;
import model.synTreeModel.ISynTreeElementWithPosition;
import model.synTreeModel.impl.SynTreeElementImpl;
import model.synTreeModel.impl.SynTreeElementWithPositionImpl;
import settings.Settings;

public class FrameX extends HowManyFrames implements ISynTreeElementWithPosition, Cloneable {

	private static final String DESCRIPTOR_PARTIAL_NAME = "frameX";
	private List<Frame> listOfFrames;
	
	public FrameX(List<Frame> listOfFrames) throws SynTreeGenerationException {
		if (listOfFrames.size() < 2 || listOfFrames.size() > Settings.MAX_NB_OF_FRAMES_IN_RELATIONS)
			throw new SynTreeGenerationException("FrameX() : illegal number of frames (" + listOfFrames.size() + ")");
		else {
			this.listOfFrames = listOfFrames;
			List<SynTreeElementWithPositionImpl> synTreeComponents = new ArrayList<SynTreeElementWithPositionImpl>();
			for (IElement component : getListOfComponents())
				synTreeComponents.add((SynTreeElementWithPositionImpl) component);
			updateComponentsPosition(Settings.COMPONENT_AUTO_POSITIONING, synTreeComponents);	
		}
	}
	
	@Override
	protected HowManyFrames clone() throws CloneNotSupportedException {
		FrameX cloneFrameX;
		List<Frame> cloneListOfFrames = new ArrayList<Frame>();
		for (Frame frame : listOfFrames)
			cloneListOfFrames.add(frame.clone());
		try {
			cloneFrameX = new FrameX(cloneListOfFrames);
		} catch (SynTreeGenerationException e) {
			throw new CloneNotSupportedException("Frame.clone() : " + e.getMessage());
		}
		return cloneFrameX;
	}	
	
	@Override
	public String getDescriptorName() {
		String name = DESCRIPTOR_PARTIAL_NAME.concat(Integer.toString(listOfFrames.size()));
		return name;
	}	

	@Override
	public List<IElement> getListOfComponents() {
		List<IElement> listOfComponents = new ArrayList<IElement>();
		for (Frame frame : listOfFrames)
			listOfComponents.add(frame);
		return listOfComponents;
	}
	
	@Override
	public List<String> getListOfRelevantPropertiesWithPath(){
		List<String> listOfRelevantPropertiesWithPath = new ArrayList<String>();
		List<SynTreeElementImpl> listOfRelevantComponents = buildListOfRelevantComponentsForRelationBuilding();
		for (SynTreeElementImpl componentDescriptor : listOfRelevantComponents) {
			listOfRelevantPropertiesWithPath.addAll(componentDescriptor.getListOfRelevantPropertiesWithPath());
		}
		return listOfRelevantPropertiesWithPath;
	}	
	
	@Override
	protected List<SynTreeElementImpl> buildListOfRelevantComponentsForRelationBuilding() {
		List<SynTreeElementImpl> listOfRelevantComponents = new ArrayList<SynTreeElementImpl>();
		listOfRelevantComponents.add(listOfFrames.get(0));
		return listOfRelevantComponents;
	}
	
	@Override
	public IOrderedSet upgradeAsTheElementOfAnOrderedSet(Map<List<String>, Integer> listOfPropertiesToIndex) 
			throws OrderedSetsGenerationException {
		throw new OrderedSetsGenerationException("FrameX can't be upgraded.");	
	}	

}
