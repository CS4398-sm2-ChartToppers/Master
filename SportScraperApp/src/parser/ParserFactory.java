package parser;

public class ParserFactory {
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
