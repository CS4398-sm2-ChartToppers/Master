package parser;

/**
 * Facilitates creation of parsers for each category of team data.
 */
public class ParserFactory {
	/**
	 * Creates parsers for any valid category of team data
	 * 
	 * @param parser			The category of team data to be parsed
	 * @return					The created parser object for the team data, appropriate 
	 * 							to its category (MLB/NFL/NBA)
	 * @throws RuntimeException	If parser is not able to be created
	 */
	public StandingsParser getParser(ParserEnum parser) {
		switch (parser) {
		case MLB_PARSER:
			return parser.create();
		case NFL_PARSER:
			return parser.create();
		case NBA_PARSER:
			return parser.create();
		default:
			throw new RuntimeException("ParserFactory could not create Object!");
		}
	}
}