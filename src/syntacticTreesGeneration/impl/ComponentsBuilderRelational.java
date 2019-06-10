package syntacticTreesGeneration.impl;

import java.util.ArrayList;
import java.util.List;

import exceptions.SynTreeGenerationException;
import model.copycatModel.synTreeGrammar.Frame;
import syntacticTreesGeneration.IFrameBuilder;
import syntacticTreesGeneration.IComponentsBuilderRelational;
import syntacticTreesGeneration.IRelationDataContainer;

public class ComponentsBuilderRelational extends ComponentsBuilder implements IComponentsBuilderRelational {

	private IRelationDataContainer relationDataContainer;
	private IFrameBuilder frameBuilder;
	
	public ComponentsBuilderRelational(List<Frame> listOfFrames, IRelationDataContainer relationDataContainer) 
			throws SynTreeGenerationException, CloneNotSupportedException {
		super(listOfFrames);
		this.relationDataContainer = relationDataContainer;
		frameBuilder = new FrameBuilder(super.listOfFrames, this.relationDataContainer);
		Frame frame = frameBuilder.getFrame();
		List<Frame> newListOfFrames = new ArrayList<Frame>();
		newListOfFrames.add(frame);
		super.listOfFrames = newListOfFrames;
	}
	
	public ComponentsBuilderRelational(List<Frame> listOfFrames, IRelationDataContainer relationDataContainer, 
			boolean listOfFramesCoverTheFullString) throws SynTreeGenerationException, CloneNotSupportedException {
		super(listOfFrames, listOfFramesCoverTheFullString);
		this.relationDataContainer = relationDataContainer;
		List<Frame> listOfCodingFrames = new ArrayList<Frame>();
		for (Frame frame : super.listOfFrames) {
			Frame frameClone = frame.clone();
			frameClone.setIsCodingElement(true);
			listOfCodingFrames.add(frameClone);
		}
		frameBuilder = new FrameBuilder(listOfCodingFrames, this.relationDataContainer);
		Frame frame = frameBuilder.getFrame();
		List<Frame> newListOfFrames = new ArrayList<Frame>();
		newListOfFrames.add(frame);
		super.listOfFrames = newListOfFrames;
	}	

}
