package syntacticTreesGeneration;

import exceptions.SynTreeGenerationException;
import model.copycatModel.synTreeGrammar.Components;

public interface IComponentsBuilder {

	Components getComponents() throws SynTreeGenerationException, CloneNotSupportedException;

}