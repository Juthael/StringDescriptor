package syntacticTreesGeneration.impl;

import java.util.List;

import copycatModel.synTreeModel.grammar.AbsCommonDiff;
import copycatModel.synTreeModel.grammar.CommonDiff;
import copycatModel.synTreeModel.grammar.Dimension;
import copycatModel.synTreeModel.grammar.Enumeration;
import copycatModel.synTreeModel.grammar.Relation;
import copycatModel.synTreeModel.grammar.Sequence;
import copycatModel.synTreeModel.grammar.SequenceAndSymmetryRel;
import copycatModel.synTreeModel.grammar.SequenceRel;
import copycatModel.synTreeModel.grammar.Symmetry;
import copycatModel.synTreeModel.grammar.SymmetryRel;
import exceptions.SynTreeGenerationException;
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
			dimension = new Dimension(false, indexedPath);
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
					Enumeration enumeration = new Enumeration(false, relationalDataList.get(0).getEnumerationValue());
					relation = new Relation(false, dimension, enumeration);
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
					relation = new SequenceRel(false, dimension, enumeration, sequence);
				}
				else if (relationalDataList.get(0).getName().equals("enumeration") 
						&& relationalDataList.get(1).getName().equals("symmetry")) {
					Enumeration enumeration = new Enumeration(false, relationalDataList.get(0).getEnumerationValue());
					ISymmetryRelationalData symmetryRelationalData = (ISymmetryRelationalData) relationalDataList.get(1);
					Symmetry symmetry = new Symmetry(false, symmetryRelationalData.getTypeOfSymmetry());
					relation = new SymmetryRel(false, dimension, enumeration, symmetry);
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
					relation = new SequenceAndSymmetryRel(false, dimension, enumeration, sequence, symmetry);
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
