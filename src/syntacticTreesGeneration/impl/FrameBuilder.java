package syntacticTreesGeneration.impl;

import java.util.List;

import exceptions.SynTreeGenerationException;
import model.copycatModel.synTreeGrammar.Frame;
import model.copycatModel.synTreeGrammar.Position;
import model.copycatModel.synTreeGrammar.Relations;
import model.copycatModel.synTreeGrammar.Size;
import settings.Settings;
import syntacticTreesGeneration.IFrameBuilder;
import syntacticTreesGeneration.IRelationDataContainer;
import syntacticTreesGeneration.IRelationsBuilder;

public class FrameBuilder implements IFrameBuilder {

	private final List<Frame> listOfFrames;
	private final IRelationDataContainer relationDataContainer;
	
	public FrameBuilder(List<Frame> listOfFrames, IRelationDataContainer relationDataContainer) {
		this.listOfFrames = listOfFrames;
		this.relationDataContainer = relationDataContainer;
	}
	
	public FrameBuilder(List<Frame> listOfFrames, IRelationDataContainer relationDataContainer, 
			boolean listOfFramesCoverTheWholeString) {
		this.listOfFrames = listOfFrames;
		this.relationDataContainer = relationDataContainer;
	}	
	
	@Override
	public Frame getFrame() throws SynTreeGenerationException, CloneNotSupportedException {
		Frame frame;
		Size size = new Size(Integer.toString(listOfFrames.size()));
		Position position = new Position(Settings.AWAITING_POSITION_VALUE);
		Relations relations;
		IRelationsBuilder relationsBuilder = new RelationsBuilder(relationDataContainer, listOfFrames);
		relations = relationsBuilder.getRelations();
		frame = new Frame(false, size, position, relations);
		return frame;		
	}

}
