package parser;

/**
 * Enumerates parsers for each category of team data.
 */
public enum ParserEnum {
	MLB_PARSER {
		/**
		 * Creates an MLB parser object.
		 * 
		 * @return 	The created MLB parser object
		 */
		@Override
		public StandingsParser create() { return new MLBParser(); }
	},
	NFL_PARSER {
		/**
		 * Creates an NFL parser object.
		 * 
		 * @return 	The created NFL parser object
		 */
		@Override
		public StandingsParser create() { return new NFLParser(); }
	},
	NBA_PARSER {
		/**
		 * Creates an NBA parser object.
		 * 
		 * @return 	The created NBA parser object
		 */
		@Override
		public StandingsParser create() { return new NBAParser(); }
	};
	
	/**
	 * Interface method for creating a parser object.
	 */
	public abstract StandingsParser create();
}