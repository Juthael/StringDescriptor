package verbalization.dataEncoding.encodingModel;

import java.util.List;

public interface IInstructionCodeGetter {
	
	String getNbOfRepetitionsCodeString();
	List<ITransformationCodeGetter> getListOfTransformationCodeGetters();

}
