package syntacticTreesGeneration;

import exceptions.SynTreeGenerationException;
import model.copycatModel.synTreeGrammar.Frame;

public interface IFrameBuilder {

	Frame getFrame() throws SynTreeGenerationException, CloneNotSupportedException;

}