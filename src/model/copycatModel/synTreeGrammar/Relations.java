package model.copycatModel.synTreeGrammar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.generalModel.IElement;
import model.synTreeModel.ISynTreeElement;

public class Relations extends RelationsOrLetter implements ISynTreeElement, Cloneable {

	private static final String DESCRIPTOR_NAME = "relations";
	private Groups groups;
	private HowManyDimensions dimensionX;
	private HowManyRelations relationX;
	
	public Relations(Groups groups, HowManyDimensions dimensionX, 
			HowManyRelations relationX) {
		super(false);
		this.groups = groups;
		this.dimensionX = dimensionX;
		this.relationX = relationX;
	}
	
	@Override
	protected Relations clone() throws CloneNotSupportedException {
		Relations cloneRelations;
		Groups cloneGroups = groups.clone();
		HowManyDimensions cloneDimensionX = dimensionX.clone();
		HowManyRelations cloneRelationX = relationX.clone();
		cloneRelations = new Relations(cloneGroups, cloneDimensionX, cloneRelationX);
		return cloneRelations;
	}

	@Override
	protected List<IElement> buildListOfComponents(){
		List<IElement> componentDescriptors = new ArrayList<IElement>(
				Arrays.asList(dimensionX, relationX, groups));
		return componentDescriptors;
	}

	@Override
	public String getDescriptorName() {
		return DESCRIPTOR_NAME;
	}
}
