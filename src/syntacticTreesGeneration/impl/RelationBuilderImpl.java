package syntacticTreesGeneration.impl;

import java.util.List;

import exceptions.SynTreeGenerationException;
import model.copycatModel.synTreeGrammar.AbsCommonDiff;
import model.copycatModel.synTreeGrammar.CommonDiff;
import model.copycatModel.synTreeGrammar.Dimension;
import model.copycatModel.synTreeGrammar.Enumeration;
import model.copycatModel.synTreeGrammar.Relation;
import model.copycatModel.synTreeGrammar.Sequence;
import model.copycatModel.synTreeGrammar.SequenceAndSymmetryRel;
import model.copycatModel.synTreeGrammar.SequenceRel;
import model.copycatModel.synTreeGrammar.Symmetry;
import model.copycatModel.synTreeGrammar.SymmetryRel;
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
	
	private RelationBuilderImpl() {
	}
	
	public static Relation buildRelation(List<IRelationalData> relationData) 
			throws SynTreeGenerationException {
		Dimension dimension = buildDimension(relationData);
		Relation relation = buildRelation(dimension, relationData);
		return relation;
	}
	
	private static Dimension buildDimension(List<IRelationalData> relationData) 
			throws SynTreeGenerationException {
		Dimension dimension;
		String indexedPath;
		try {
			indexedPath = relationData.get(0).getIndexedPath();
			dimension = new Dimension(indexedPath);
		} 
		catch (Exception e) {
			throw new SynTreeGenerationException("RelationBuilder : parameter is empty");
		}
		return dimension;		
	}
	
	private static Relation buildRelation(Dimension dimension, List<IRelationalData> relationalDataList) 
			throws SynTreeGenerationException {
		Relation relation;
		switch (relationalDataList.size()) {
			case 1:
				if (relationalDataList.get(0).getName().equals("enumeration")) {
					Enumeration enumeration = new Enumeration(relationalDataList.get(0).getEnumerationValue());
					relation = new Relation(dimension, enumeration);
				}
				else throw new SynTreeGenerationException("Relation Builder : unique relation in RelationalDataList "
						+ "isn't an enumeration.");
				break;
			case 2:
				if (relationalDataList.get(0).getName().equals("enumeration") 
						&& relationalDataList.get(1).getName().equals("sequence")) {
					Enumeration enumeration = new Enumeration(relationalDataList.get(0).getEnumerationValue());
					ISequenceRelationalData sequenceRelationalData = (ISequenceRelationalData) relationalDataList.get(1);
					CommonDiff commonDiff = new CommonDiff(sequenceRelationalData.getCommonDifference());
					String absCommonDiffString = sequenceRelationalData.getCommonDifference().replace("-", "");
					AbsCommonDiff absCommonDiff = new AbsCommonDiff(absCommonDiffString);
					Sequence sequence = new Sequence(commonDiff, absCommonDiff);
					relation = new SequenceRel(dimension, enumeration, sequence);
				}
				else if (relationalDataList.get(0).getName().equals("enumeration") 
						&& relationalDataList.get(1).getName().equals("symmetry")) {
					Enumeration enumeration = new Enumeration(relationalDataList.get(0).getEnumerationValue());
					ISymmetryRelationalData symmetryRelationalData = (ISymmetryRelationalData) relationalDataList.get(1);
					Symmetry symmetry = new Symmetry(symmetryRelationalData.getTypeOfSymmetry());
					relation = new SymmetryRel(dimension, enumeration, symmetry);
				}
				else throw new SynTreeGenerationException("Relation Builder : list of 2 relations in the "
						+ "RelationalDataList isn't of type [enumeration,sequence] or [enumeration,symmetry].");
				break;
			case 3:
				if (relationalDataList.get(0).getName().equals("enumeration") 
						&& relationalDataList.get(1).getName().equals("sequence") 
						&& relationalDataList.get(2).getName().equals("symmetry")) {
					Enumeration enumeration = new Enumeration(relationalDataList.get(0).getEnumerationValue());
					ISequenceRelationalData sequenceRelationalData = (ISequenceRelationalData) relationalDataList.get(1);
					CommonDiff commonDiff = new CommonDiff(sequenceRelationalData.getCommonDifference());
					String absCommonDiffString = sequenceRelationalData.getCommonDifference().replace("-", "");
					AbsCommonDiff absCommonDiff = new AbsCommonDiff(absCommonDiffString);
					Sequence sequence = new Sequence(commonDiff, absCommonDiff);		
					ISymmetryRelationalData symmetryRelationalData = (ISymmetryRelationalData) relationalDataList.get(2);
					Symmetry symmetry = new Symmetry(symmetryRelationalData.getTypeOfSymmetry());			
					relation = new SequenceAndSymmetryRel(dimension, enumeration, sequence, symmetry);
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
