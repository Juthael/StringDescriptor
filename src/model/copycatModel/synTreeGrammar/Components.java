package model.copycatModel.synTreeGrammar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import exceptions.OrderedSetsGenerationException;
import exceptions.SynTreeGenerationException;
import model.copycatModel.ordSetGrammar.FrameOS;
import model.copycatModel.ordSetGrammar.ComponentsOS;
import model.copycatModel.ordSetGrammar.SizeOS;
import model.generalModel.IElement;
import model.orderedSetModel.IOrderedSet;
import model.synTreeModel.ISynTreeElementWithPosition;
import model.synTreeModel.impl.SynTreeElementWithPositionImpl;
import settings.Settings;

public class Components extends SynTreeElementWithPositionImpl implements ISynTreeElementWithPosition, Cloneable {

	private static final String DESCRIPTOR_NAME = "components";
	private Size size;
	private HowManyFrames frameHM;
	
	public Components(Size size, HowManyFrames frameHM) 
			throws SynTreeGenerationException, CloneNotSupportedException {
		this.size = size;
		if (frameHM.getDescriptorName().equals("frame")) {
			this.frameHM = (Frame) frameHM;
			List<IElement> listWithSingleFrame = new ArrayList<IElement>(); 
			listWithSingleFrame.add(this.frameHM);
			updateComponentsPosition("1", listWithSingleFrame);
		}
		else this.frameHM = frameHM;
	}
	
	public Components(Size size, HowManyFrames frameHM, boolean fullStringFrame) 
			throws SynTreeGenerationException {
		if (fullStringFrame == Settings.FULL_STRING_FRAME && frameHM.getDescriptorName().equals("frame")){
			this.size = size;
			Frame frame = (Frame) frameHM;
			this.frameHM = frame;
			List<IElement> listWithSingleFrame = new ArrayList<IElement>(); 
			listWithSingleFrame.add(this.frameHM);
			updateComponentsPosition(Settings.CONVENTIONAL_POSITION_FOR_FULL_STRING_FRAME, listWithSingleFrame);				
		} else throw new SynTreeGenerationException("Components : illegal parameter values in constructor");
	}
	
	@Override
	protected Components clone() throws CloneNotSupportedException {
		Components cloneComponents;
		Size cloneSize = size.clone();
		HowManyFrames cloneFrameHM = frameHM.clone();
		try {
			cloneComponents = new Components(cloneSize, cloneFrameHM);
		} catch (SynTreeGenerationException e) {
			throw new CloneNotSupportedException("Components : error in clone() method");
		}
		return cloneComponents;
	}

	@Override
	public String getDescriptorName() {
		return DESCRIPTOR_NAME;
	}	

	@Override
	public List<IElement> getListOfComponents(){
		List<IElement> componentDescriptors = new ArrayList<IElement>(
				Arrays.asList(size, frameHM));
		return componentDescriptors;
	}
	
	@Override
	public IOrderedSet upgradeAsTheElementOfAnOrderedSet(Map<List<String>, Integer> listOfPropertiesToIndex) 
			throws OrderedSetsGenerationException {
		IOrderedSet componentsOS;
		List<String> listOfPropertiesWithPath = getListOfPropertiesWithPath();
		Integer componentsIndex = listOfPropertiesToIndex.get(listOfPropertiesWithPath);
		String componentsID = getDescriptorName().concat(componentsIndex.toString());
		SizeOS sizeOS = (SizeOS) size.upgradeAsTheElementOfAnOrderedSet(listOfPropertiesToIndex);
		List<FrameOS> listOfFrameOS = new ArrayList<FrameOS>();
		if (frameHM.getDescriptorName().contains("frameX")) {
			for (IElement element : frameHM.getListOfComponents()) {
				Frame frame = (Frame) element;
				listOfFrameOS.add((FrameOS) frame.upgradeAsTheElementOfAnOrderedSet(listOfPropertiesToIndex));
			}
		}
		else if (frameHM.getDescriptorName().contentEquals("frame")){
			Frame frame = (Frame) frameHM;
			listOfFrameOS.add((FrameOS) frame.upgradeAsTheElementOfAnOrderedSet(listOfPropertiesToIndex));
		}
		else throw new OrderedSetsGenerationException("Components.upgradeAsTheElementOfAnOrderedSet : "
			+ "frameHM descriptor name was unexpected. (" + frameHM.getDescriptorName() + ")");
		componentsOS = new ComponentsOS(componentsID, isCodingElement, sizeOS, listOfFrameOS);
		return componentsOS;		
	}	

}
