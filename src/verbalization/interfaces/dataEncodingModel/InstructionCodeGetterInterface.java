package verbalization.interfaces.dataEncodingModel;

import java.util.List;

public interface InstructionCodeGetterInterface {
	
	String getNbOfRepetitionsCodeString();
	List<TransformationCodeGetterInterface> getListOfTransformationCodeGetters();

}
