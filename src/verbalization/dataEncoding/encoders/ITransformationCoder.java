package verbalization.dataEncoding.encoders;

import verbalization.dataEncoding.encodingModel.ITransformationCodeGetter;

public interface ITransformationCoder {

	ITransformationCodeGetter getTransformationCodeGetter();

}