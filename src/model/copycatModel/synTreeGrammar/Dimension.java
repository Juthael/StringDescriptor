package model.copycatModel.synTreeGrammar;

import java.util.ArrayList;
import java.util.List;

import model.generalModel.IElement;
import model.synTreeModel.ISynTreeElement;
import settings.Settings;
import syntacticTreesGeneration.impl.DimensionEncodingManager;

public class Dimension extends HowManyDimensions implements ISynTreeElement, Cloneable {

	private static final String DESCRIPTOR_NAME = "dimension";
	private String indexedPath;
	
	public Dimension(String indexedPath) {
		super(false);
		this.indexedPath = getDimensionCode(indexedPath);
	}
	
	@Override
	protected Dimension clone() throws CloneNotSupportedException {
		Dimension cloneDimension = new Dimension(indexedPath);
		return cloneDimension;
	}

	@Override
	protected List<IElement> buildListOfComponents() {
		List<IElement> componentDescriptors = new ArrayList<IElement>();
		return componentDescriptors;
	}
	
	@Override
	public String getDescriptorName() {
		return DESCRIPTOR_NAME;
	}
	
	@Override
	public List<String> getListOfPropertiesWithPath() {
		List<String> listOfPropertiesWithPath = new ArrayList<String>();
		StringBuilder sB = new StringBuilder();
		sB.append(DESCRIPTOR_NAME);
		sB.append(Settings.PATH_SEPARATOR);
		sB.append(indexedPath);
		listOfPropertiesWithPath.add(sB.toString());
		return listOfPropertiesWithPath;
	}
	
	@Override
	public List<String> getListOfRelevantPropertiesWithPath() {
		return getListOfPropertiesWithPath();
	}		
	
	private String getDimensionCode(String fullDimensionValue) {
		String codedDimension = DimensionEncodingManager.getDimensionCodeFromIndexedPath(fullDimensionValue);
		return codedDimension;
	}

}
