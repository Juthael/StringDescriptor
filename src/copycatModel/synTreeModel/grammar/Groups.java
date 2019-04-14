package copycatModel.synTreeModel.grammar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import copycatModel.synTreeModel.impl.SynTreeIntegrableElementImpl;
import exceptions.SynTreeGenerationException;
import settings.Settings;

public class Groups extends SynTreeIntegrableElementImpl implements Cloneable {

	private static final String descriptorName = "groups";
	private Size size;
	private HowManyGroups groupX;
	
	public Groups(boolean codingDescriptor, Size size, HowManyGroups groupX) 
			throws SynTreeGenerationException, CloneNotSupportedException {
		super(codingDescriptor);
		this.size = size;
		if (groupX.getDescriptorName().equals("group")) {
			Group groupXCasted = (Group) groupX;
			this.groupX = groupXCasted;
			List<SynTreeIntegrableElementImpl> listWithSingleGroup = buildListOfRelevantComponentsForPositionUpdate();
			updateComponentsPosition("1", listWithSingleGroup);
		}
		else this.groupX = groupX;
	}
	
	public Groups(boolean codingDescriptor, Size size, HowManyGroups groupX, boolean fullStringGroup) 
			throws SynTreeGenerationException {
		super(codingDescriptor);
		if (fullStringGroup == Settings.FULL_STRING_GROUP && groupX.getDescriptorName().equals("group")){
			this.size = size;
			this.groupX = groupX;
			List<SynTreeIntegrableElementImpl> componentDescriptors = buildListOfComponents();
			updateComponentsPosition(Settings.CONVENTIONAL_POSITION_FOR_FULL_STRING_GROUP, componentDescriptors);	
		} else throw new SynTreeGenerationException("Groups : illegal parameter values in constructor");
	}
	
	@Override
	protected Groups clone() throws CloneNotSupportedException {
		Size cloneSize = size.clone();
		HowManyGroups cloneGroupX;
		switch (groupX.getDescriptorName()) {
			case "group" :
				Group groupCasted = (Group) groupX;
				cloneGroupX = groupCasted.clone();
				break;
			case "groupX2" :
				GroupX2 groupX2Casted = (GroupX2) groupX;
				cloneGroupX = groupX2Casted.clone();
				break;
			case "groupX3" :
				GroupX3 groupX3Casted = (GroupX3) groupX;
				cloneGroupX = groupX3Casted.clone();
				break;
			case "groupX4" :
				GroupX4 groupX4Casted = (GroupX4) groupX;
				cloneGroupX = groupX4Casted.clone();
				break;
			case "groupX5" :
				GroupX5 groupX5Casted = (GroupX5) groupX;
				cloneGroupX = groupX5Casted.clone();
				break;
			case "groupX6" :
				GroupX6 groupX6Casted = (GroupX6) groupX;
				cloneGroupX = groupX6Casted.clone();
				break;
			case "groupX7" :
				GroupX7 groupX7Casted = (GroupX7) groupX;
				cloneGroupX = groupX7Casted.clone();
				break;
			case "groupX8" :
				GroupX8 groupX8Casted = (GroupX8) groupX;
				cloneGroupX = groupX8Casted.clone();
				break;
			case "groupX9" :
				GroupX9 groupX9Casted = (GroupX9) groupX;
				cloneGroupX = groupX9Casted.clone();
				break;
			case "groupX10" :
				GroupX10 groupX10Casted = (GroupX10) groupX;
				cloneGroupX = groupX10Casted.clone();
				break;
			case "groupX11" :
				GroupX11 groupX11Casted = (GroupX11) groupX;
				cloneGroupX = groupX11Casted.clone();
				break;
			case "groupX12" :
				GroupX12 groupX12Casted = (GroupX12) groupX;
				cloneGroupX = groupX12Casted.clone();
				break;				
			default : throw new CloneNotSupportedException("Groups : error in clone() method");
		}
		Groups cloneGroups;
		try {
			cloneGroups = new Groups(isCodingByDecomposition, cloneSize, cloneGroupX);
		} catch (SynTreeGenerationException e) {
			throw new CloneNotSupportedException("Groups : error in clone() method");
		}
		return cloneGroups;
	}

	@Override
	protected List<SynTreeIntegrableElementImpl> buildListOfComponents(){
		List<SynTreeIntegrableElementImpl> componentDescriptors = new ArrayList<SynTreeIntegrableElementImpl>(
				Arrays.asList(size, groupX));
		return componentDescriptors;
	}

	@Override
	public String getDescriptorName() {
		return descriptorName;
	}
	
	private List<SynTreeIntegrableElementImpl> buildListOfRelevantComponentsForPositionUpdate() {
		List<SynTreeIntegrableElementImpl> listOfRelevantComponentsForPositionUpdate = 
				new ArrayList<SynTreeIntegrableElementImpl>();
		Group singleGroup = (Group) groupX;
		listOfRelevantComponentsForPositionUpdate.add(singleGroup);
		return listOfRelevantComponentsForPositionUpdate;
	}

}
