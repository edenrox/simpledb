package com.hopkins.simpledb.root;

import com.hopkins.simpledb.data.Column;
import com.hopkins.simpledb.data.Schema;

import java.util.Arrays;

public final class HockeyData {
  public static final class DivisionsTable {
    public static final String TABLE_NAME = "divisions";

    public interface Columns {
      String ID = Column.ROW_ID_NAME;
      String NAME = "name";
    }

    public static final Schema SCHEMA =
        new Schema(Arrays.asList(
            Column.newIntColumn(Columns.ID),
            Column.newStringColumn(Columns.NAME, 32)));
  }

  public static final Object[][] DIVISIONS_DATA =
      new Object[][] {
          new Object[] {0, "Atlantic"},
          new Object[] {0, "Metropolitan"},
          new Object[] {0, "Central"},
          new Object[] {0, "Pacific"},
      };

  public static final class TeamsTable {
    public static final String TABLE_NAME = "teams";

    public interface Columns {
      String ID = Column.ROW_ID_NAME;
      String NAME = "name";
      String CITY = "city";
      String DIVISION_ID = "division_id";
    }

    public static final Schema SCHEMA =
        new Schema(Arrays.asList(
            Column.newIntColumn(Columns.ID),
            Column.newStringColumn(Columns.NAME, 32),
            Column.newStringColumn(Columns.CITY, 32),
            Column.newIntColumn(Columns.DIVISION_ID)));
  }

  public static final Object[][] TEAMS_DATA =
      new Object[][] {
          new Object[] {0, "Bruins", "Boston", 1},
          new Object[] {0, "Sabres", "Buffalo", 1},
          new Object[] {0, "Red Wings", "Detroit", 1},
          new Object[] {0, "Panthers", "Florida", 1},
          new Object[] {0, "Canadiens", "Montreal", 1},
          new Object[] {0, "Senators", "Ottawa", 1},
          new Object[] {0, "Lightning", "Tampa Bay", 1},
          new Object[] {0, "Maple Leafs", "Toronto", 1},
          new Object[] {0, "Hurricanes", "Carolina", 2},
          new Object[] {0, "Blue Jackets", "Columbus", 2},
          new Object[] {0, "Devils", "New Jersey", 2},
          new Object[] {0, "Islanders", "New York", 2},
          new Object[] {0, "Rangers", "New York", 2},
          new Object[] {0, "Flyers", "Philadelphia", 2},
          new Object[] {0, "Penguins", "Pittsburgh", 2},
          new Object[] {0, "Capitals", "Washington", 2},
          new Object[] {0, "Blackhawks", "Chicago", 3},
          new Object[] {0, "Avalanche", "Colorado", 3},
          new Object[] {0, "Stars", "Dallas", 3},
          new Object[] {0, "Wild", "Minnesota", 3},
          new Object[] {0, "Predators", "Nashville", 3},
          new Object[] {0, "Blues", "St. Louis", 3},
          new Object[] {0, "Jets", "Winnipeg", 3},
          new Object[] {0, "Ducks", "Anaheim", 4},
          new Object[] {0, "Coyotes", "Arizona", 4},
          new Object[] {0, "Flames", "Calgary", 4},
          new Object[] {0, "Oilers", "Edmonton", 4},
          new Object[] {0, "Kings", "Los Angeles", 4},
          new Object[] {0, "Sharks", "San Jose", 4},
          new Object[] {0, "Canucks", "Vancouver", 4},
          new Object[] {0, "Golden Knights", "Vegas", 4},
      };

  public static final class GamesTable {
    public static final String TABLE_NAME = "games";

    public interface Columns {
      String ID = Column.ROW_ID_NAME;
      String DATE = "date";
      String HOME_TEAM_ID = "home_team_id";
      String HOME_TEAM_SCORE = "home_team_score";
      String AWAY_TEAM_ID = "away_team_id";
      String AWAY_TEAM_SCORE = "away_team_score";
    }

    public static final Schema SCHEMA =
        new Schema(Arrays.asList(
            Column.newIntColumn(Columns.ID),
            Column.newStringColumn(Columns.DATE, 32),
            Column.newIntColumn(Columns.HOME_TEAM_ID),
            Column.newIntColumn(Columns.HOME_TEAM_SCORE),
            Column.newIntColumn(Columns.AWAY_TEAM_ID),
            Column.newIntColumn(Columns.AWAY_TEAM_SCORE)));
  }

  public static final Object[][] GAMES_DATA =
      new Object[][] {
          // October games
          new Object[] {0, "2017-10-04", "Edmonton", 3, "Calgary", 0},
          new Object[] {0, "2017-10-07", "Calgary", 6, "Winnipeg", 3},
          new Object[] {0, "2017-10-09", "Anaheim", 0, "Calgary", 2},
          new Object[] {0, "2017-10-11", "Los Angeles", 3, "Calgary", 4},
          new Object[] {0, "2017-10-13", "Calgary", 0, "Ottawa", 6},
          new Object[] {0, "2017-10-14", "Vancouver", 2, "Calgary", 5},
          new Object[] {0, "2017-10-19", "Calgary", 1, "Carolina", 2},
          new Object[] {0, "2017-10-21", "Calgary", 2, "Minnesota", 4},
          new Object[] {0, "2017-10-24", "Nashville", 2, "Calgary", 3},
          new Object[] {0, "2017-10-25", "St. Louis", 5, "Calgary", 2},
          new Object[] {0, "2017-10-27", "Calgary", 1, "Dallas", 2},
          new Object[] {0, "2017-10-29", "Calgary", 2, "Washington", 1},

          // November games
          new Object[] {0, "2017-11-02", "Calgary", 2, "Pittsburgh", 1},
          new Object[] {0, "2017-11-05", "Calgary", 5, "New Jersey", 4},
          new Object[] {0, "2017-11-07", "Calgary", 3, "Vancouver", 5},
          new Object[] {0, "2017-11-09", "Calgary", 6, "Detroit", 3},
          new Object[] {0, "2017-11-13", "Calgary", 7, "St. Louis", 4},
          new Object[] {0, "2017-11-15", "Detroit", 8, "Calgary", 2},
          new Object[] {0, "2017-11-18", "Philadelphia", 4, "Calgary", 5},
          new Object[] {0, "2017-11-20", "Washington", 1, "Calgary", 4},
          new Object[] {0, "2017-11-22", "Columbus", 1, "Calgary", 0},
          new Object[] {0, "2017-11-24", "Dallas", 6, "Calgary", 4},
          new Object[] {0, "2017-11-25", "Colorado", 2, "Calgary", 3},
          new Object[] {0, "2017-11-28", "Calgary", 1, "Toronto", 4},
          new Object[] {0, "2017-11-30", "Calgary", 3, "Arizona", 0},

          // December games
          new Object[] {0, "2017-12-02", "Calgary", 5, "Edmonton", 7},
          new Object[] {0, "2017-12-04", "Calgary", 2, "Philadelphia", 5},
          new Object[] {0, "2017-12-06", "Toronto", 2, "Calgary", 1},
          new Object[] {0, "2017-12-07", "Montreal", 2, "Calgary", 3},
          new Object[] {0, "2017-12-09", "Calgary", 4, "Vancouver", 2},
          new Object[] {0, "2017-12-12", "Minnesota", 2, "Calgary", 1},
          new Object[] {0, "2017-12-14", "Calgary", 2, "San Jose", 3},
          new Object[] {0, "2017-12-16", "Calgary", 0, "Nashville", 2},
          new Object[] {0, "2017-12-17", "Vancouver", 1, "Calgary", 6},
          new Object[] {0, "2017-12-20", "Calgary", 2, "St. Louis", 1},
          new Object[] {0, "2017-12-22", "Calgary", 2, "Montreal", 3},
          new Object[] {0, "2017-12-28", "San Jose", 3, "Calgary", 2},
          new Object[] {0, "2017-12-29", "Anaheim", 2, "Calgary", 1},
          new Object[] {0, "2017-12-31", "Calgary", 4, "Chicago", 3},

          // January games
          new Object[] {0, "2018-01-04", "Calgary", 4, "Los Angeles", 3},
          new Object[] {0, "2018-01-06", "Calgary", 3, "Anaheim", 2},
          new Object[] {0, "2018-01-09", "Minnesota", 2, "Calgary", 3},
          new Object[] {0, "2018-01-11", "Tampa Bay", 1, "Calgary", 5},
          new Object[] {0, "2018-01-12", "Florida", 2, "Calgary", 4},
          new Object[] {0, "2018-01-14", "Carolina", 1, "Calgary", 4},
          new Object[] {0, "2018-01-20", "Calgary", 1, "Winnipeg", 2},
          new Object[] {0, "2018-01-22", "Calgary", 1, "Buffalo", 2},
          new Object[] {0, "2018-01-24", "Calgary", 1, "Los Angeles", 2},
          new Object[] {0, "2018-01-25", "Edmonton", 4, "Calgary", 3},
          new Object[] {0, "2018-01-30", "Calgary", 2, "Vegas", 4},

          // February games
          new Object[] {0, "2018-02-01", "Calgary", 4, "Tampa Bay", 7},
          new Object[] {0, "2018-02-03", "Calgary", 4, "Chicago", 3},
          new Object[] {0, "2018-02-06", "Chicago", 2, "Calgary", 3},
          new Object[] {0, "2018-02-08", "New Jersey", 2, "Calgary", 3},
          new Object[] {0, "2018-02-09", "New York Rangers", 4, "Calgary", 3},
          new Object[] {0, "2018-02-11", "New York Islanders", 2, "Calgary", 3},
          new Object[] {0, "2018-02-13", "Boston", 5, "Calgary", 2},
          new Object[] {0, "2018-02-15", "Nashville", 3, "Calgary", 4},
          new Object[] {0, "2018-02-17", "Calgary", 3, "Florida", 6},
          new Object[] {0, "2018-02-19", "Calgary", 1, "Boston", 2},
          new Object[] {0, "2018-02-21", "Vegas", 7, "Calgary", 3},
          new Object[] {0, "2018-02-22", "Arizona", 2, "Calgary", 5},
          new Object[] {0, "2018-02-24", "Calgary", 5, "Colorado", 1},
          new Object[] {0, "2018-02-27", "Dallas", 2, "Calgary", 0},
          new Object[] {0, "2018-02-28", "Colorado", 5, "Calgary", 2},

          // March games
          new Object[] {0, "2018-03-02", "Calgary", 1, "New York Rangers", 3},
          new Object[] {0, "2018-03-05", "Pittsburgh", 4, "Calgary", 3},
          new Object[] {0, "2018-03-07", "Buffalo", 1, "Calgary", 5},
          new Object[] {0, "2018-03-09", "Ottawa", 1, "Calgary", 2},
          new Object[] {0, "2018-03-11", "Calgary", 2, "New York Islanders", 5},
          new Object[] {0, "2018-03-13", "Calgary", 1, "Edmonton", 0},
          new Object[] {0, "2018-03-16", "Calgary", 4, "San Jose", 7},
          new Object[] {0, "2018-03-18", "Vegas", 4, "Calgary", 0},
          new Object[] {0, "2018-03-19", "Arizona", 5, "Calgary", 2},
          new Object[] {0, "2018-03-21", "Calgary", 0, "Anaheim", 4},
          new Object[] {0, "2018-03-24", "San Jose", 5, "Calgary", 1},
          new Object[] {0, "2018-03-26", "Los Angeles", 3, "Calgary", 0},
          new Object[] {0, "2018-03-29", "Calgary", 1, "Columbus", 5},
          new Object[] {0, "2018-03-31", "Calgary", 3, "Edmonton", 2},

          // April games
          new Object[] {0, "2018-04-03", "Calgary", 1, "Arizona", 4},
          new Object[] {0, "2018-04-05", "Winnipeg", 2, "Calgary", 1},
          new Object[] {0, "2018-04-07", "Calgary", 7, "Vegas", 1},
      };
}
