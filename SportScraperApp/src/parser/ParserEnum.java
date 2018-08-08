package parser;

public enum ParserEnum {
	MLB_PARSER {
		@Override
		public StandingsParser create() { return new MLBParser(); }
	},
	NFL_PARSER {
		@Override
		public StandingsParser create() { return new NFLParser(); }
	},
	NBA_PARSER {
		@Override
		public StandingsParser create() { return new NBAParser(); }
	};
	
	public abstract StandingsParser create();
}