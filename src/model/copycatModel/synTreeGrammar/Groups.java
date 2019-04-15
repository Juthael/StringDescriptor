package model.copycatModel.synTreeGrammar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import exceptions.SynTreeGenerationException;
import model.generalModel.IElement;
import model.synTreeModel.ISynTreeElement;
import model.synTreeModel.impl.SynTreeElementImpl;
import settings.Settings;

public class Groups extends SynTreeElementImpl implements ISynTreeElement, Cloneable {

	private static final String DESCRIPTOR_NAME = "groups";
	private Size size;
	private HowManyGroups groupHM;
	
	public Groups(Size size, HowManyGroups groupX) 
			throws SynTreeGenerationException, CloneNotSupportedException {
		super(false);
		this.size = size;
		if (groupX.getDescriptorName().equals("group")) {
			Group groupXCasted = (Group) groupX;
			this.groupHM = groupXCasted;
			List<IElement> listWithSingleGroup = buildListOfRelevantComponentsForPositionUpdate();
			updateComponentsPosition("1", listWithSingleGroup);
		}
		else this.groupHM = groupX;
	}
	
	public Groups(Size size, HowManyGroups groupX, boolean fullStringGroup) 
			throws SynTreeGenerationException {
		super(false);
		if (fullStringGroup == Settings.FULL_STRING_GROUP && groupX.getDescriptorName().equals("group")){
			this.size = size;
			this.groupHM = groupX;
			List<IElement> componentDescriptors = buildListOfComponents();
			updateComponentsPosition(Settings.CONVENTIONAL_POSITION_FOR_FULL_STRING_GROUP, componentDescriptors);	
		} else throw new SynTreeGenerationException("Groups : illegal parameter values in constructor");
	}
	
	@Override
	protected Groups clone() throws CloneNotSupportedException {
		Groups cloneGroups;
		Size cloneSize = size.clone();
		HowManyGroups cloneGroupHM = groupHM.clone();
		try {
			cloneGroups = new Groups(cloneSize, cloneGroupHM);
		} catch (SynTreeGenerationException e) {
			throw new CloneNotSupportedException("Groups : error in clone() method");
		}
		return cloneGroups;
	}

	@Override
	protected List<IElement> buildListOfComponents(){
		List<IElement> componentDescriptors = new ArrayList<IElement>(
				Arrays.asList(size, groupHM));
		return componentDescriptors;
	}

	@Override
	public String getDescriptorName() {
		return DESCRIPTOR_NAME;
	}
	
	private List<IElement> buildListOfRelevantComponentsForPositionUpdate() {
		List<IElement> listOfRelevantComponentsForPositionUpdate = 
				new ArrayList<IElement>();
		Group singleGroup = (Group) groupHM;
		listOfRelevantComponentsForPositionUpdate.add(singleGroup);
		return listOfRelevantComponentsForPositionUpdate;
	}

}
