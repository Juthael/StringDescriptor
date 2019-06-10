package settings;

public class Settings {

	//1.Conventions
	public static final boolean THIS_IS_A_CODING_ELEMENT = true;
	public static final boolean USE_LOWERCASE_LETTER = true;
	public static final String AWAITING_POSITION_VALUE = "noValueYet";
	public static final int COMPONENT_AUTO_POSITIONING = 0;
	public static final String NO_POSITION_INFORMATION = "";
	public static final boolean FULL_STRING_FRAME = true;
	public static final String CONVENTIONAL_POSITION_FOR_FULL_STRING_FRAME = "1";
	public static final String IRRELEVANT_VALUE = "irrelevant";
	public static final String PATH_SEPARATOR = "/";
	public static final String SECOND_DEG_VALUE_SEPARATOR = ":";
	public static final String SECOND_DEG_DIMENSION_SEPARATOR = ">";
	public static final String PROPERTY_INDEX_SEPARATOR = "_";
	public static final boolean LIST_OF_FRAMES_COVER_THE_FULL_STRING = true;	
	public static final boolean TAKE_SUBCOMP_INTO_ACCOUNT_IF_CHARSTRING_HAS_MONOSTRUCTURE = true;
	public static final String LEFT_TO_RIGHT = "fromLeftToRight";
	public static final String RIGHT_TO_LEFT = "fromRightToLeft";
	public static final String FIRST_POSITION = "firstPosition";
	public static final String CENTRAL_POSITION = "centralPosition";
	public static final String LAST_POSITION = "lastPosition";
	public static final String SYMMETRY_WITH_CENTRAL_ELEMENT = "withCentralElement";
	public static final String SYMMETRY_WITHOUT_CENTRAL_ELEMENT = "withoutCentralElement";
	public static final String POSITION_VALUES_SEPARATOR = ":";
	public static final String STRUCTURE_RELATION_CONVENTIONAL_DIMENSION = "frame.size";
	public static final String NO_VERBAL_DESCRIPTION = "No verbal description available";
	public static final boolean THIS_IS_A_COMPONENT_ELEMENT = true;
	public static final boolean THIS_IS_NOT_A_COMPONENT_ELEMENT = false;
	public static final String ABSTRACT_TREE_NAME = "abstractFrame";
	public static final String ABSTRACTABLE_TREE_NAME = "frame";
	public static final String TREE_TOP_ELEMENT_GENERIC_NAME = "omega";
	public static final boolean DO_ABSTRACT_COMPONENTS = true;
	public static final boolean WITH_IDIOSYNCRATIC_ELEMENTS = true;
	public static final boolean THIS_SET_IS_GENERIC = true;
	public static final boolean CALLED_BY_GENERIC = true;
	public static final boolean THIS_ELEMENT_IS_IDIOSYNCRATIC = true;
	
	//2.Parameters concerning the generation of syntactic trees
	public static final int MAX_NB_OF_CHARS_IN_STRING = 10;
	public static final int MAX_NB_OF_UNRELATED_FRAMES = 4;
	public static final int MAX_NB_OF_FRAMES_IN_RELATIONS = 10; //error if >12
	public static final int MAX_NB_OF_DIMENSIONS_IN_RELATIONS = 10; //error if >10
	public static final int MAX_NB_OF_RELATIONS = 10; //error if >10
	public static final int MAX_NB_OF_TRANSFORMATION_RELATIONS = 2; //error if >5
	public static final int MAX_NB_OF_NON_CONSTANT_SEQUENCES = 2;
	public static final int MAX_NB_OF_SIMPLE_ENUMERATIONS_IN_RELATION = 1;
	public static final int MAX_NB_OF_SIZE1_COMPONENTS = 2;
	public static final int MAX_NB_OF_ADJACENT_SIZE1_COMPONENTS = 1;
	public static final boolean GEN2SIZE1_ENUMERATION_ALLOWED_FOR_THE_WORD_ENDS = true; 
	public static final boolean GEN2SIZE1_ENUMERATION_ALLOWED_FOR_ALL_LETTERS = true;
	public static final boolean GEN2SIZE1_SEQUENCE_ALLOWED_FOR_FIRST_LETTER = true; 
	public static final boolean GEN2SIZE1_RESTRICTED_SEQUENCE_ALLOWED_FOR_CENTRAL_LETTER = true; 
	public static final boolean GEN2SIZE1_SEQUENCE_ALLOWED_FOR_LAST_LETTER = true; 
	public static final boolean GEN2SIZE1_SEQUENCE_ALLOWED_FOR_ALL_LETTERS = false;
	public static final int GEN2SIZE1_SEQUENCE_MIN_INCREMENT = -1;
	public static final int GEN2SIZE1_SEQUENCE_MAX_INCREMENT = 1;
	public static final int GEN2SIZE1_RESTRICTED_SEQUENCE_MIN_INCREMENT = 0;
	public static final int GEN2SIZE1_RESTRICTED_SEQUENCE_MAX_INCREMENT = 1;
	public static final int MAX_NB_OF_DESCRIPTOR_GENERATIONS = 3; //error if >5
	public static final int MAX_NB_OF_COMPONENTS_FOR_2ND_DEGREE_ENUMERATION = 4;
	public static final int MAX_INCREMENT_ABS_VALUE = 3;
	public static final boolean CODING_ELEM_ARE_SMALLEST_FULLSTRING_COMPONENTS_ELEM = true;
	public static final boolean CODING_ELEM_IS_GREATEST_COMPONENTS = false;
	public static final boolean PREVENT_FRAME_ABSTRACTION_FOR_SIMPLE_ENUM = true;
	
	//3. Parameters concerning the generation of ordered sets and binary contexts
	public static int orderedSetIndex = 1;
	public static final boolean CONSTANT_SEQUENCES_CAN_BE_UPGRADED_TO_SETS = false;
	public static final boolean ENUM_OF_IDENTICAL_ELEMENTS_CAN_NEVER_BE_UPGRADED = true; //if true, then previous boolean = false
	public static final boolean NON_INFORMATIVE_ELEMENTS_MUST_BE_REMOVED = false;
	public static final boolean BINARY_CONTEXTS_MUST_BE_CLARIFIED = false;
	public static final boolean MAKE_ELEMENT_ID_MORE_EXPLICIT = false;
	public static final boolean CODING_ELEMENTS_ARE_ORDERED_SET_ATOMS = false;
	public static final boolean FRAMED_COMPONENTS_RETURNS_CONTEXTUAL_RELATION = true ;

}