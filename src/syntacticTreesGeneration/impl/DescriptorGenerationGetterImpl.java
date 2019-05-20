package syntacticTreesGeneration.impl;

import java.util.List;

import model.copycatModel.synTreeGrammar.Frame;
import settings.Settings;
import syntacticTreesGeneration.IDescriptorGenerationGetter;

public class DescriptorGenerationGetterImpl implements IDescriptorGenerationGetter {

	public static int getDescriptorGenerationNumber(Frame descriptor) {
		int generationNumber = 0;
		List<String> listOfProperties = descriptor.getListOfPropertiesWithPath();
		for (String property : listOfProperties) {
			String[] propertyPathArray = property.split(Settings.PATH_SEPARATOR);
			int nbOfFramePathElements = 0;
			for (String pathElement : propertyPathArray) {
				if (pathElement.equals("frame"))
					nbOfFramePathElements++;
			}
			if (nbOfFramePathElements > generationNumber)
				generationNumber = nbOfFramePathElements;
		}
		return generationNumber;
	}

}
