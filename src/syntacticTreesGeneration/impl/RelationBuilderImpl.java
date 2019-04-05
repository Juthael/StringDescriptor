package syntacticTreesGeneration.impl;

import java.util.List;

import copycatModel.grammar.AbsCommonDiff;
import copycatModel.grammar.CommonDiff;
import copycatModel.grammar.Dimension;
import copycatModel.grammar.DimensionX10;
import copycatModel.grammar.DimensionX2;
import copycatModel.grammar.DimensionX3;
import copycatModel.grammar.DimensionX4;
import copycatModel.grammar.DimensionX5;
import copycatModel.grammar.DimensionX6;
import copycatModel.grammar.DimensionX7;
import copycatModel.grammar.DimensionX8;
import copycatModel.grammar.DimensionX9;
import copycatModel.grammar.Enumeration;
import copycatModel.grammar.HowManyDimensions;
import copycatModel.grammar.Relation;
import copycatModel.grammar.Sequence;
import copycatModel.grammar.SequenceAndSymmetryRel;
import copycatModel.grammar.SequenceRel;
import copycatModel.grammar.Symmetry;
import copycatModel.grammar.SymmetryRel;
import exceptions.SynTreeGenerationException;
import settings.Settings;
import syntacticTreesGeneration.IRelationBuilder;
import syntacticTreesGeneration.IRelationalData;
import syntacticTreesGeneration.ISequenceRelationalData;
import syntacticTreesGeneration.ISymmetryRelationalData;

public class RelationBuilderImpl implements IRelationBuilder {

	/*Parameter must be a list of RelationData verifying the following constraints : 
	 * -the size of the list is : see Settings
	 * -for every element in the list, RelationData.getDimensions() must return the same value
	 * -the first element is an EnumerationRelationData
	 * -if the list contains 2 elements, the 2nd element is a SequenceRelationData or a SymmetryRelationData
	 * -if the list contains 3 elements, the 2nd is a SequenceRelationData and the 3rd is a SymmetryRelationData 
	 */
	public static Relation buildRelation(List<IRelationalData> relationData) 
			throws SynTreeGenerationException {
		HowManyDimensions howManyDimensions = buildDimensions(relationData);
		Relation relation = buildRelation(howManyDimensions, relationData);
		return relation;
	}
	
	private static HowManyDimensions buildDimensions(List<IRelationalData> relationData) 
			throws SynTreeGenerationException {
		List<String> dimensions;
		try {
			 dimensions = relationData.get(0).getDimensions();
		} catch (Exception e) {throw new SynTreeGenerationException("RelationBuilder : parameter is empty");}		
		HowManyDimensions howManyDimensions ; 
		int numberOfDimensions= dimensions.size();
		if (numberOfDimensions <= Settings.MAX_NB_OF_DIMENSIONS_IN_RELATIONS) {
			switch (numberOfDimensions) {
				case 1 : 
					howManyDimensions = new Dimension(false, dimensions.get(0));
					break;
				case 2 : 
					Dimension dimension21 = new Dimension(false, dimensions.get(0));
					Dimension dimension22 = new Dimension(false, dimensions.get(1));
					howManyDimensions = new DimensionX2(false, dimension21, dimension22);
					break;
				case 3 : 
					Dimension dimension31 = new Dimension(false, dimensions.get(0));
					Dimension dimension32 = new Dimension(false, dimensions.get(1));
					Dimension dimension33 = new Dimension(false, dimensions.get(2));
					howManyDimensions = new DimensionX3(false, dimension31, dimension32, dimension33);
					break;		
				case 4 : 
					Dimension dimension41 = new Dimension(false, dimensions.get(0));
					Dimension dimension42 = new Dimension(false, dimensions.get(1));
					Dimension dimension43 = new Dimension(false, dimensions.get(2));
					Dimension dimension44 = new Dimension(false, dimensions.get(3));
					howManyDimensions = new DimensionX4(false, dimension41, dimension42, dimension43, dimension44);
					break;		
				case 5 : 
					Dimension dimension51 = new Dimension(false, dimensions.get(0));
					Dimension dimension52 = new Dimension(false, dimensions.get(1));
					Dimension dimension53 = new Dimension(false, dimensions.get(2));
					Dimension dimension54 = new Dimension(false, dimensions.get(3));
					Dimension dimension55 = new Dimension(false, dimensions.get(4));
					howManyDimensions = new DimensionX5(false, dimension51, dimension52, dimension53, dimension54, 
							dimension55);
					break;
				case 6 : 
					Dimension dimension61 = new Dimension(false, dimensions.get(0));
					Dimension dimension62 = new Dimension(false, dimensions.get(1));
					Dimension dimension63 = new Dimension(false, dimensions.get(2));
					Dimension dimension64 = new Dimension(false, dimensions.get(3));
					Dimension dimension65 = new Dimension(false, dimensions.get(4));
					Dimension dimension66 = new Dimension(false, dimensions.get(5));
					howManyDimensions = new DimensionX6(false, dimension61, dimension62, dimension63, dimension64, 
							dimension65, dimension66);
					break;		
				case 7 : 
					Dimension dimension71 = new Dimension(false, dimensions.get(0));
					Dimension dimension72 = new Dimension(false, dimensions.get(1));
					Dimension dimension73 = new Dimension(false, dimensions.get(2));
					Dimension dimension74 = new Dimension(false, dimensions.get(3));
					Dimension dimension75 = new Dimension(false, dimensions.get(4));
					Dimension dimension76 = new Dimension(false, dimensions.get(5));
					Dimension dimension77 = new Dimension(false, dimensions.get(6));
					howManyDimensions = new DimensionX7(false, dimension71, dimension72, dimension73, dimension74, 
							dimension75, dimension76, dimension77);
					break;
				case 8 : 
					Dimension dimension81 = new Dimension(false, dimensions.get(0));
					Dimension dimension82 = new Dimension(false, dimensions.get(1));
					Dimension dimension83 = new Dimension(false, dimensions.get(2));
					Dimension dimension84 = new Dimension(false, dimensions.get(3));
					Dimension dimension85 = new Dimension(false, dimensions.get(4));
					Dimension dimension86 = new Dimension(false, dimensions.get(5));
					Dimension dimension87 = new Dimension(false, dimensions.get(6));
					Dimension dimension88 = new Dimension(false, dimensions.get(7));
					howManyDimensions = new DimensionX8(false, dimension81, dimension82, dimension83, dimension84, 
							dimension85, dimension86, dimension87, dimension88);
					break;		
				case 9 : 
					Dimension dimension91 = new Dimension(false, dimensions.get(0));
					Dimension dimension92 = new Dimension(false, dimensions.get(1));
					Dimension dimension93 = new Dimension(false, dimensions.get(2));
					Dimension dimension94 = new Dimension(false, dimensions.get(3));
					Dimension dimension95 = new Dimension(false, dimensions.get(4));
					Dimension dimension96 = new Dimension(false, dimensions.get(5));
					Dimension dimension97 = new Dimension(false, dimensions.get(6));
					Dimension dimension98 = new Dimension(false, dimensions.get(7));
					Dimension dimension99 = new Dimension(false, dimensions.get(8));
					howManyDimensions = new DimensionX9(false, dimension91, dimension92, dimension93, dimension94, 
							dimension95, dimension96, dimension97, dimension98, dimension99);
					break;
				case 10 : 
					Dimension dimension101 = new Dimension(false, dimensions.get(0));
					Dimension dimension102 = new Dimension(false, dimensions.get(1));
					Dimension dimension103 = new Dimension(false, dimensions.get(2));
					Dimension dimension104 = new Dimension(false, dimensions.get(3));
					Dimension dimension105 = new Dimension(false, dimensions.get(4));
					Dimension dimension106 = new Dimension(false, dimensions.get(5));
					Dimension dimension107 = new Dimension(false, dimensions.get(6));
					Dimension dimension108 = new Dimension(false, dimensions.get(7));
					Dimension dimension109 = new Dimension(false, dimensions.get(8));
					Dimension dimension110 = new Dimension(false, dimensions.get(9));
					howManyDimensions = new DimensionX10(false, dimension101, dimension102, dimension103, 
							dimension104, dimension105, dimension106, dimension107, dimension108, dimension109, 
							dimension110);
					break;					
				default : throw new SynTreeGenerationException("Relation Builder : too many dimensions");
			}	
		} else throw new SynTreeGenerationException("Relation Builder : too many dimensions");
		return howManyDimensions;		
	}
	
	private static Relation buildRelation(HowManyDimensions howManyDimensions, List<IRelationalData> relationalDataList) 
			throws SynTreeGenerationException {
		Relation relation;
		switch (relationalDataList.size()) {
			case 1:
				if (relationalDataList.get(0).getName().equals("enumeration")) {
					Enumeration enumeration = new Enumeration(false, relationalDataList.get(0).getEnumerationValue());
					relation = new Relation(false, howManyDimensions, enumeration);
				}
				else throw new SynTreeGenerationException("Relation Builder : unique relation in RelationalDataList "
						+ "isn't an enumeration.");
				break;
			case 2:
				if (relationalDataList.get(0).getName().equals("enumeration") 
						&& relationalDataList.get(1).getName().equals("sequence")) {
					Enumeration enumeration = new Enumeration(false, relationalDataList.get(0).getEnumerationValue());
					ISequenceRelationalData sequenceRelationalData = (ISequenceRelationalData) relationalDataList.get(1);
					CommonDiff commonDiff = new CommonDiff(false, sequenceRelationalData.getCommonDifference());
					String absCommonDiffString = sequenceRelationalData.getCommonDifference().replace("-", "");
					AbsCommonDiff absCommonDiff = new AbsCommonDiff(false, absCommonDiffString);
					Sequence sequence = new Sequence(false, commonDiff, absCommonDiff);
					relation = new SequenceRel(false, howManyDimensions, enumeration, sequence);
				}
				else if (relationalDataList.get(0).getName().equals("enumeration") 
						&& relationalDataList.get(1).getName().equals("symmetry")) {
					Enumeration enumeration = new Enumeration(false, relationalDataList.get(0).getEnumerationValue());
					ISymmetryRelationalData symmetryRelationalData = (ISymmetryRelationalData) relationalDataList.get(1);
					Symmetry symmetry = new Symmetry(false, symmetryRelationalData.getTypeOfSymmetry());
					relation = new SymmetryRel(false, howManyDimensions, enumeration, symmetry);
				}
				else throw new SynTreeGenerationException("Relation Builder : list of 2 relations in the "
						+ "RelationalDataList isn't of type [enumeration,sequence] or [enumeration,symmetry].");
				break;
			case 3:
				if (relationalDataList.get(0).getName().equals("enumeration") 
						&& relationalDataList.get(1).getName().equals("sequence") 
						&& relationalDataList.get(2).getName().equals("symmetry")) {
					Enumeration enumeration = new Enumeration(false, relationalDataList.get(0).getEnumerationValue());
					ISequenceRelationalData sequenceRelationalData = (ISequenceRelationalData) relationalDataList.get(1);
					CommonDiff commonDiff = new CommonDiff(false, sequenceRelationalData.getCommonDifference());
					String absCommonDiffString = sequenceRelationalData.getCommonDifference().replace("-", "");
					AbsCommonDiff absCommonDiff = new AbsCommonDiff(false, absCommonDiffString);
					Sequence sequence = new Sequence(false, commonDiff, absCommonDiff);		
					ISymmetryRelationalData symmetryRelationalData = (ISymmetryRelationalData) relationalDataList.get(2);
					Symmetry symmetry = new Symmetry(false, symmetryRelationalData.getTypeOfSymmetry());			
					relation = new SequenceAndSymmetryRel(false, howManyDimensions, enumeration, sequence, symmetry);
				}
				else throw new SynTreeGenerationException("Relation Builder : list of 3 relations in the "
						+ "RelationalDataList isn't of type [enumeration,sequence,symmetry].");
				break;
			default : throw new SynTreeGenerationException("Relation Builder : the size of the RelationalDataList "
					+ "is illegal");
		}
		return relation;
	}

}
