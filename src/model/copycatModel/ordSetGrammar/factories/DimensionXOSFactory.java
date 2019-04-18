package model.copycatModel.ordSetGrammar.factories;

import java.util.List;

import model.copycatModel.ordSetGrammar.DimensionOS;
import model.copycatModel.ordSetGrammar.DimensionXOS;
import model.copycatModel.ordSetGrammar.DimensionXOS2Dead;
import model.copycatModel.ordSetGrammar.IDimensionX;
import settings.Settings;

public class DimensionXOSFactory {

	private DimensionXOSFactory() {
	}
	
	public static IDimensionX getDimensionX(String elementID, List<DimensionOS> listOfDimensions) {
		IDimensionX dimensionX;
		if (Settings.DIMENSIONX_LOWERSET_IS_DEAD) {
			dimensionX = new DimensionXOS2Dead(elementID, listOfDimensions);
		}
		else dimensionX = new DimensionXOS(elementID, listOfDimensions);
		return dimensionX;
	}

}
