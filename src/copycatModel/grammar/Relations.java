package copycatModel.grammar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import copycatModel.impl.SynTreeIntegrableElementImpl;

public class Relations extends RelationsOrLetter implements Cloneable {

	private static final String descriptorName = "relations";
	private Groups groups;
	private HowManyDimensions dimensionX;
	private HowManyRelations relationX;
	
	public Relations(boolean codingDescriptor, Groups groups, HowManyDimensions dimensionX, 
			HowManyRelations relationX) {
		super(codingDescriptor);
		this.groups = groups;
		this.dimensionX = dimensionX;
		this.relationX = relationX;
	}
	
	@Override
	protected Relations clone() throws CloneNotSupportedException {
		Relations cloneRelations;
		Groups cloneGroups = groups.clone();
		HowManyDimensions cloneDimensionX;
		switch (dimensionX.getDescriptorName()) {
			case "dimension" :
				Dimension dimensionCasted = (Dimension) dimensionX;
				cloneDimensionX = dimensionCasted.clone();
				break;
			case "dimensionX2" :
				DimensionX2 dimensionX2Casted = (DimensionX2) dimensionX;
				cloneDimensionX = dimensionX2Casted.clone();
				break;
			case "dimensionX3" :
				DimensionX3 dimensionX3Casted = (DimensionX3) dimensionX;
				cloneDimensionX = dimensionX3Casted.clone();
				break;
			case "dimensionX4" :
				DimensionX4 dimensionX4Casted = (DimensionX4) dimensionX;
				cloneDimensionX = dimensionX4Casted.clone();
				break;
			case "dimensionX5" :
				DimensionX5 dimensionX5Casted = (DimensionX5) dimensionX;
				cloneDimensionX = dimensionX5Casted.clone();
				break;
			case "dimensionX6" :
				DimensionX6 dimensionX6Casted = (DimensionX6) dimensionX;
				cloneDimensionX = dimensionX6Casted.clone();
				break;		
			case "dimensionX7" :
				DimensionX7 dimensionX7Casted = (DimensionX7) dimensionX;
				cloneDimensionX = dimensionX7Casted.clone();
				break;
			case "dimensionX8" :
				DimensionX8 dimensionX8Casted = (DimensionX8) dimensionX;
				cloneDimensionX = dimensionX8Casted.clone();
				break;
			case "dimensionX9" :
				DimensionX9 dimensionX9Casted = (DimensionX9) dimensionX;
				cloneDimensionX = dimensionX9Casted.clone();
				break;
			case "dimensionX10" :
				DimensionX10 dimensionX10Casted = (DimensionX10) dimensionX;
				cloneDimensionX = dimensionX10Casted.clone();
				break;				
			default : throw new CloneNotSupportedException("Relation : error in clone() method.");
		}		
		HowManyRelations cloneRelationX;
		switch(relationX.getDescriptorName()) {
			case "relation" :
				Relation castedRelation = (Relation) relationX;
				cloneRelationX = castedRelation.clone();
				break;
			case "relationX2" :
				RelationX2 castedRelationX2 = (RelationX2) relationX;
				cloneRelationX = castedRelationX2.clone();
				break;
			case "relationX3" :
				RelationX3 castedRelationX3 = (RelationX3) relationX;
				cloneRelationX = castedRelationX3.clone();
				break;
			case "relationX4" :
				RelationX4 castedRelationX4 = (RelationX4) relationX;
				cloneRelationX = castedRelationX4.clone();
				break;
			case "relationX5" :
				RelationX5 castedRelationX5 = (RelationX5) relationX;
				cloneRelationX = castedRelationX5.clone();
				break;
			case "relationX6" :
				RelationX6 castedRelationX6 = (RelationX6) relationX;
				cloneRelationX = castedRelationX6.clone();
				break;
			case "relationX7" :
				RelationX7 castedRelationX7 = (RelationX7) relationX;
				cloneRelationX = castedRelationX7.clone();
				break;				
			case "relationX8" :
				RelationX8 castedRelationX8 = (RelationX8) relationX;
				cloneRelationX = castedRelationX8.clone();
				break;	
			case "relationX9" :
				RelationX9 castedRelationX9 = (RelationX9) relationX;
				cloneRelationX = castedRelationX9.clone();
				break;	
			case "relationX10" :
				RelationX10 castedRelationX10 = (RelationX10) relationX;
				cloneRelationX = castedRelationX10.clone();
				break;					
			default : throw new CloneNotSupportedException("Relation : error in clone() method.");
		}
		cloneRelations = new Relations(isCodingDescriptor, cloneGroups, cloneDimensionX, 
				cloneRelationX);
		return cloneRelations;
	}

	@Override
	protected List<SynTreeIntegrableElementImpl> buildListOfComponents(){
		List<SynTreeIntegrableElementImpl> componentDescriptors = new ArrayList<SynTreeIntegrableElementImpl>(
				Arrays.asList(dimensionX, relationX, groups));
		return componentDescriptors;
	}

	@Override
	public String getDescriptorName() {
		return descriptorName;
	}
}
