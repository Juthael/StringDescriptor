package model.copycatModel.ordSetGrammar.factory;

import java.util.List;

import model.copycatModel.ordSetGrammar.DimensionOS;
import model.copycatModel.ordSetGrammar.DimensionXOS;
import model.copycatModel.ordSetGrammar.DimensionXOSDead;
import model.copycatModel.ordSetGrammar.IDimensionXOS;
import settings.Settings;

public class OSFactory {

	private OSFactory() {
	}
	
	public static IDimensionXOS getDimensionXOS(String elementID, List<DimensionOS> listOfDimensions) {
		IDimensionXOS dimensionXOS;
		if (Settings.DIMENSIONX_LOWERSET_IS_DEAD) {
			dimensionXOS = new DimensionXOSDead(elementID, listOfDimensions);
		}
		else dimensionXOS = new DimensionXOS(elementID, listOfDimensions);
		return dimensionXOS;
	}

}
