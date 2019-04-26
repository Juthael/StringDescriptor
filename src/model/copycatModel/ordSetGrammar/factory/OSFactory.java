package model.copycatModel.ordSetGrammar.factory;

import java.util.List;

import model.copycatModel.ordSetGrammar.AbsCommonDiffOS;
import model.copycatModel.ordSetGrammar.AbsCommonDiffOSMinimal;
import model.copycatModel.ordSetGrammar.CommonDiffOS;
import model.copycatModel.ordSetGrammar.CommonDiffOSMinimal;
import model.copycatModel.ordSetGrammar.DimensionOS;
import model.copycatModel.ordSetGrammar.DimensionOSMinimal;
import model.copycatModel.ordSetGrammar.DimensionXOS;
import model.copycatModel.ordSetGrammar.DimensionXOSDead;
import model.copycatModel.ordSetGrammar.DirectionOS;
import model.copycatModel.ordSetGrammar.DirectionOSMinimal;
import model.copycatModel.ordSetGrammar.EndPositionOS;
import model.copycatModel.ordSetGrammar.EndPositionOSMinimal;
import model.copycatModel.ordSetGrammar.EnumerationOS;
import model.copycatModel.ordSetGrammar.EnumerationOSMinimal;
import model.copycatModel.ordSetGrammar.IAbsCommonDiffOS;
import model.copycatModel.ordSetGrammar.ICommonDiffOS;
import model.copycatModel.ordSetGrammar.IDimensionOS;
import model.copycatModel.ordSetGrammar.IDimensionXOS;
import model.copycatModel.ordSetGrammar.IDirectionOS;
import model.copycatModel.ordSetGrammar.IEndPositionOS;
import model.copycatModel.ordSetGrammar.IEnumerationOS;
import model.copycatModel.ordSetGrammar.IPlatonicLetterOS;
import model.copycatModel.ordSetGrammar.IPositionOS;
import model.copycatModel.ordSetGrammar.ISizeOS;
import model.copycatModel.ordSetGrammar.ISymmetryOS;
import model.copycatModel.ordSetGrammar.PlatonicLetterOS;
import model.copycatModel.ordSetGrammar.PlatonicLetterOSMinimal;
import model.copycatModel.ordSetGrammar.PositionOS;
import model.copycatModel.ordSetGrammar.PositionOSMinimal;
import model.copycatModel.ordSetGrammar.SizeOS;
import model.copycatModel.ordSetGrammar.SizeOSMinimal;
import model.copycatModel.ordSetGrammar.SymmetryOS;
import model.copycatModel.ordSetGrammar.SymmetryOSMinimal;
import model.orderedSetModel.impl.MinimalOS;
import settings.Settings;

public class OSFactory {

	private OSFactory() {
	}
	
	public static IAbsCommonDiffOS getAbsCommonDiffOS(String elementID, MinimalOS absCommonDiffProperty) {
		IAbsCommonDiffOS absCommonDiffOS;
		if (Settings.IF_SINGLE_LOWER_BOUND_THEN_MINIMAL)
			absCommonDiffOS = new AbsCommonDiffOSMinimal(elementID);
		else absCommonDiffOS = new AbsCommonDiffOS(elementID, absCommonDiffProperty);
		return absCommonDiffOS;
	}
	
	public static ICommonDiffOS getCommonDiffOS(String elementID, MinimalOS commonDiffProperty) {
		ICommonDiffOS commonDiffOS;
		if (Settings.IF_SINGLE_LOWER_BOUND_THEN_MINIMAL)
			commonDiffOS = new CommonDiffOSMinimal(elementID);
		else commonDiffOS = new CommonDiffOS(elementID, commonDiffProperty);
		return commonDiffOS;
	}
	
	public static IDimensionOS getDimensionOS(String elementID, MinimalOS dimensionProperty) {
		IDimensionOS dimensionOS;
		if (Settings.IF_SINGLE_LOWER_BOUND_THEN_MINIMAL)
			dimensionOS = new DimensionOSMinimal(elementID);
		else dimensionOS = new DimensionOS(elementID, dimensionProperty);
		return dimensionOS;	
	}
	
	public static IDimensionXOS getDimensionXOS(String elementID, List<IDimensionOS> listOfDimensions) {
		IDimensionXOS dimensionXOS;
		if (Settings.DIMENSIONX_LOWERSET_IS_DEAD) {
			dimensionXOS = new DimensionXOSDead(elementID, listOfDimensions);
		}
		else dimensionXOS = new DimensionXOS(elementID, listOfDimensions);
		return dimensionXOS;
	}
	
	public static IDirectionOS getDirectionOS(String elementID, MinimalOS directionProperty) {
		IDirectionOS directionOS;
		if (Settings.IF_SINGLE_LOWER_BOUND_THEN_MINIMAL)
			directionOS = new DirectionOSMinimal(elementID);
		else directionOS = new DirectionOS(elementID, directionProperty);
		return directionOS;
	}	
	
	public static IEndPositionOS getEndPositionOS(String elementID, MinimalOS endPositionProperty) {
		IEndPositionOS endPositionOS;
		if (Settings.IF_SINGLE_LOWER_BOUND_THEN_MINIMAL)
			endPositionOS = new EndPositionOSMinimal(elementID);
		else endPositionOS = new EndPositionOS(elementID, endPositionProperty);
		return endPositionOS;
	}
	
	public static IEnumerationOS getEnumerationOS(String elementID, MinimalOS enumerationProperty) {
		IEnumerationOS enumerationOS;
		if (Settings.IF_SINGLE_LOWER_BOUND_THEN_MINIMAL)
			enumerationOS = new EnumerationOSMinimal(elementID);
		else enumerationOS = new EnumerationOS(elementID, enumerationProperty);
		return enumerationOS;
	}
	
	public static IPlatonicLetterOS getPlatonicLetterOS(String elementID, MinimalOS platonicLetterProperty) {
		IPlatonicLetterOS platonicLetterOS;
		if (Settings.IF_SINGLE_LOWER_BOUND_THEN_MINIMAL)
			platonicLetterOS = new PlatonicLetterOSMinimal(elementID);
		else platonicLetterOS = new PlatonicLetterOS(elementID, platonicLetterProperty);
		return platonicLetterOS;
	}
	
	public static IPositionOS getPositionOS(String elementID, MinimalOS positionProperty) {
		IPositionOS positionOS;
		if (Settings.IF_SINGLE_LOWER_BOUND_THEN_MINIMAL)
			positionOS = new PositionOSMinimal(elementID);
		else positionOS = new PositionOS(elementID, positionProperty);
		return positionOS;
	}
	
	public static ISymmetryOS getSymmetryOS(String elementID, MinimalOS symmetryProperty) {
		ISymmetryOS symmetryOS;
		if (Settings.IF_SINGLE_LOWER_BOUND_THEN_MINIMAL)
			symmetryOS = new SymmetryOSMinimal(elementID);
		else symmetryOS = new SymmetryOS(elementID, symmetryProperty);
		return symmetryOS;
	}
	
	public static ISizeOS getSizeOS(String elementID, MinimalOS sizeProperty) {
		ISizeOS sizeOS;
		if (Settings.IF_SINGLE_LOWER_BOUND_THEN_MINIMAL)
			sizeOS = new SizeOSMinimal(elementID);
		else sizeOS = new SizeOS(elementID, sizeProperty);
		return sizeOS;
	}

}
