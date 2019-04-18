package model.copycatModel.ordSetGrammar.factories;

import java.util.List;

import model.copycatModel.ordSetGrammar.DimensionOS;
import model.copycatModel.ordSetGrammar.DimensionXOS;
import model.copycatModel.ordSetGrammar.DimensionXOS2Dead;
import model.copycatModel.ordSetGrammar.IDimensionXOS;
import settings.Settings;

public class DimensionXOSFactory {

	private DimensionXOSFactory() {
	}
	
	public static IDimensionXOS getDimensionX(String elementID, List<DimensionOS> listOfDimensions) {
		IDimensionXOS dimensionXOS;
		if (Settings.DIMENSIONX_LOWERSET_IS_DEAD) {
			dimensionXOS = new DimensionXOS2Dead(elementID, listOfDimensions);
		}
		else dimensionXOS = new DimensionXOS(elementID, listOfDimensions);
		return dimensionXOS;
	}

}
