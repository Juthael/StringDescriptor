package syntacticTreesGeneration.impl;

import java.util.List;

import copycatModel.grammar.Group;
import syntacticTreesGeneration.IDescriptorGenerationGetter;

public class DescriptorGenerationGetterImpl implements IDescriptorGenerationGetter {

	public static int getDescriptorGenerationNumber(Group descriptor) {
		int generationNumber = 0;
		List<String> listOfProperties = descriptor.getListOfPropertiesWithPath();
		for (String property : listOfProperties) {
			String[] propertyPathArray = property.split("/");
			int nbOfGroupPathElements = 0;
			for (String pathElement : propertyPathArray) {
				if (pathElement.equals("group"))
					nbOfGroupPathElements++;
			}
			if (nbOfGroupPathElements > generationNumber)
				generationNumber = nbOfGroupPathElements;
		}
		return generationNumber;
	}

}
