package syntacticTreesGeneration.impl;

import java.util.ArrayList;
import java.util.List;

import exceptions.SynTreeGenerationException;
import model.copycatModel.synTreeGrammar.Frame;
import model.copycatModel.synTreeGrammar.FrameX;
import model.copycatModel.synTreeGrammar.Components;
import model.copycatModel.synTreeGrammar.IOneOrManyFrames;
import model.copycatModel.synTreeGrammar.Size;
import model.generalModel.IElement;
import model.synTreeModel.IFrame;
import settings.Settings;
import syntacticTreesGeneration.IComponentsBuilder;

public class ComponentsBuilder implements IComponentsBuilder {

	protected List<Frame> listOfFrames;
	protected boolean listOfFramesCoverTheFullString = false;
	
	public ComponentsBuilder(List<Frame> listOfFrames) {
		this.listOfFrames = listOfFrames;
	}
	
	public ComponentsBuilder(List<Frame> listOfFrames, boolean listOfFramesCoverTheFullString) 
			throws SynTreeGenerationException {
		if (listOfFramesCoverTheFullString == Settings.LIST_OF_FRAMES_COVER_THE_FULL_STRING) {
			this.listOfFrames = listOfFrames;
			this.listOfFramesCoverTheFullString = Settings.LIST_OF_FRAMES_COVER_THE_FULL_STRING;
		} else throw new SynTreeGenerationException("ComponentsBuilder : constructor illegal parameter value");
	}
	
	@SuppressWarnings("unused")
	@Override
	public Components getComponents() throws SynTreeGenerationException, CloneNotSupportedException {
		Components components;
		IOneOrManyFrames howManyFrames;
		Size size;
		int listOfFramesSize = listOfFrames.size();
		if (listOfFramesSize <= Settings.MAX_NB_OF_FRAMES_IN_RELATIONS && listOfFramesSize > 0) {
			if (listOfFramesSize == 1) {
				size = new Size("1");
				howManyFrames = listOfFrames.get(0).clone();
				if (listOfFramesCoverTheFullString == false) {
					components = new Components(size, howManyFrames);
				}
				else { 
					List<IElement> listOfCodingComponents = howManyFrames.getListOfCodingElements();
					if (listOfCodingComponents.isEmpty() && Settings.CODING_ELEM_ARE_SMALLEST_FULLSTRING_COMPONENTS_ELEM) {
						howManyFrames.setIsCodingElement(true);
					}
					components = new Components(size, howManyFrames, Settings.FULL_STRING_FRAME);
				}
			}
			else {
				size = new Size (Integer.toString(listOfFramesSize));
				List<IFrame> cloneListOfFrames = new ArrayList<IFrame>();
				for (Frame frame : listOfFrames) {
					Frame frameClone = frame.clone();
					if (listOfFramesCoverTheFullString == true 
							&& Settings.CODING_ELEM_ARE_SMALLEST_FULLSTRING_COMPONENTS_ELEM) {
						frameClone.setIsCodingElement(true);
					}
					cloneListOfFrames.add(frameClone);
				}					
				howManyFrames = new FrameX(cloneListOfFrames);
				components = new Components(size, howManyFrames);
				if (listOfFramesCoverTheFullString == true && Settings.CODING_ELEM_IS_GREATEST_COMPONENTS == true) {
					components.setIsCodingElement(Settings.THIS_IS_A_CODING_ELEMENT);
				}
			}
		} else throw new SynTreeGenerationException("ComponentsBuilder : listOfFrames size is illegal.");
		return components;	
	}	

}
