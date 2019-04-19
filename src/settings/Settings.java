package settings;

public class Settings {

	//1.Conventions
	public static final boolean USE_LOWERCASE_LETTER = true;
	public static final String AWAITING_POSITION_VALUE = "noValueYet";
	public static final int COMPONENT_AUTO_POSITIONING = 0;
	public static final String NO_POSITION_INFORMATION = "";
	public static final boolean FULL_STRING_GROUP = true;
	public static final String CONVENTIONAL_POSITION_FOR_FULL_STRING_GROUP = "1";
	public static final String IRRELEVANT_VALUE = "irrelevant";
	public static final String PATH_SEPARATOR = "/";
	public static final String SECOND_DEG_VALUE_SEPARATOR = ":";
	public static final String SECOND_DEG_DIMENSION_SEPARATOR = ">";
	public static final String PROPERTY_INDEX_SEPARATOR = "_";
	public static final boolean LIST_OF_GROUPS_COVER_THE_FULL_STRING = true;	
	public static final boolean TAKE_SUBCOMP_INTO_ACCOUNT_IF_CHARSTRING_HAS_MONOSTRUCTURE = true;
	public static final String FIRST_POSITION = "firstPosition";
	public static final String CENTRAL_POSITION = "centralPosition";
	public static final String LAST_POSITION = "lastPosition";
	public static final String SYMMETRY_WITH_CENTRAL_ELEMENT = "withCentralElement";
	public static final String SYMMETRY_WITHOUT_CENTRAL_ELEMENT = "withoutCentralElement";
	public static final String POSITION_VALUES_SEPARATOR = ":";
	
	//2.Parameters preventing combinatorial explosion during the syntactic trees generation 
	public static final int MAX_NB_OF_CHARS_IN_STRING = 10;
	public static final int MAX_NB_OF_UNRELATED_GROUPS = 4;
	public static final int MAX_NB_OF_GROUPS_IN_RELATIONS = 10; //error if >12
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
	
	//3. Parameters reducing the size of ordered sets
	//3.1. allows to declare "dead" (and not subsequently taken into account) some subsets of the ordered set.
	public static final boolean DIMENSIONX_LOWERSET_IS_DEAD = true;
	//3.2. allows to declare a lower set as minimal if it has a single lower bound (besides idiosyncratic element)
	public static boolean IF_SINGLE_LOWER_BOUND_THEN_MINIMAL = true;
	
	//4. Settings modifiers : for test purpose only
	
	public void setIfSngleLowerBoundThenMinimalToFalse() {
		IF_SINGLE_LOWER_BOUND_THEN_MINIMAL = false;
	}
	

}
