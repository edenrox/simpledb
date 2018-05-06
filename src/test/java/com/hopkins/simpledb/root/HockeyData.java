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
      String STATE_CODE = "state_code";
      String DIVISION_ID = "division_id";
    }

    public static final Schema SCHEMA =
        new Schema(Arrays.asList(
            Column.newIntColumn(Columns.ID),
            Column.newStringColumn(Columns.NAME, 32),
            Column.newStringColumn(Columns.CITY, 32),
            Column.newStringColumn(Columns.STATE_CODE, 2),
            Column.newIntColumn(Columns.DIVISION_ID)));
  }

  public static final Object[][] TEAMS_DATA =
      new Object[][] {
          new Object[] {0, "Bruins", "Boston", "MA", 1},
          new Object[] {0, "Sabres", "Buffalo", "NY", 1},
          new Object[] {0, "Red Wings", "Detroit", "MI", 1},
          new Object[] {0, "Panthers", "Florida", "FL", 1},
          new Object[] {0, "Canadiens", "Montreal", "QC", 1},
          new Object[] {0, "Senators", "Ottawa", "ON", 1},
          new Object[] {0, "Lightning", "Tampa Bay", "FL", 1},
          new Object[] {0, "Maple Leafs", "Toronto", "ON", 1},
          new Object[] {0, "Hurricanes", "Carolina", "NC", 2},
          new Object[] {0, "Blue Jackets", "Columbus", "OH", 2},
          new Object[] {0, "Devils", "New Jersey", "NJ", 2},
          new Object[] {0, "Islanders", "New York", "NY", 2},
          new Object[] {0, "Rangers", "New York", "NY", 2},
          new Object[] {0, "Flyers", "Philadelphia", "PA", 2},
          new Object[] {0, "Penguins", "Pittsburgh", "PA", 2},
          new Object[] {0, "Capitals", "Washington", "DC", 2},
          new Object[] {0, "Blackhawks", "Chicago", "IL", 3},
          new Object[] {0, "Avalanche", "Colorado", "CO", 3},
          new Object[] {0, "Stars", "Dallas", "TX", 3},
          new Object[] {0, "Wild", "Minnesota", "MN", 3},
          new Object[] {0, "Predators", "Nashville", "TN", 3},
          new Object[] {0, "Blues", "St. Louis", "MO", 3},
          new Object[] {0, "Jets", "Winnipeg", "MB", 3},
          new Object[] {0, "Ducks", "Anaheim", "CA", 4},
          new Object[] {0, "Coyotes", "Arizona", "AZ", 4},
          new Object[] {0, "Flames", "Calgary", "AB", 4},
          new Object[] {0, "Oilers", "Edmonton", "AB", 4},
          new Object[] {0, "Kings", "Los Angeles", "CA", 4},
          new Object[] {0, "Sharks", "San Jose", "CA", 4},
          new Object[] {0, "Canucks", "Vancouver", "BC", 4},
          new Object[] {0, "Golden Knights", "Vegas", "NV", 4},
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

          ////////////////
          // Calgary games
          ////////////////

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


          ////////////////
          // Toronto Games
          ////////////////

          // October games
          new Object[] {0, "2017-10-04", "Winnipeg", 2, "Toronto", 7},
          new Object[] {0, "2017-10-07", "Toronto", 8, "New York Rangers", 5},
          new Object[] {0, "2017-10-09", "Toronto", 4, "Chicago", 3},
          new Object[] {0, "2017-10-11", "Toronto", 3, "New Jersey", 6},
          new Object[] {0, "2017-10-14", "Montreal", 3, "Toronto", 4},
          new Object[] {0, "2017-10-17", "Washington", 0, "Toronto", 2},
          new Object[] {0, "2017-10-18", "Toronto", 6, "Detroit", 3},
          new Object[] {0, "2017-10-21", "Ottawa", 6, "Toronto", 3},
          new Object[] {0, "2017-10-23", "Toronto", 3, "Los Angeles", 2},
          new Object[] {0, "2017-10-26", "Toronto", 3, "Carolina", 6},
          new Object[] {0, "2017-10-28", "Toronto", 2, "Philadelphia", 4},
          new Object[] {0, "2017-10-30", "San Jose", 3, "Toronto", 2},

          // November games
          new Object[] {0, "2017-11-01", "Anaheim", 1, "Toronto", 3},
          new Object[] {0, "2017-11-02", "Los Angeles", 5, "Toronto", 3},
          new Object[] {0, "2017-11-04", "St. Louis", 6, "Toronto", 4},
          new Object[] {0, "2017-11-06", "Toronto", 4, "Vegas", 3},
          new Object[] {0, "2017-11-08", "Toronto", 4, "Minnesota", 2},
          new Object[] {0, "2017-11-10", "Toronto", 3, "Boston", 2},
          new Object[] {0, "2017-11-11", "Boston", 1, "Toronto", 4},
          new Object[] {0, "2017-11-16", "Toronto", 1, "New Jersey", 0},
          new Object[] {0, "2017-11-18", "Montreal", 0, "Toronto", 6},
          new Object[] {0, "2017-11-20", "Toronto", 1, "Arizona", 4},
          new Object[] {0, "2017-11-22", "Florida", 2, "Toronto", 1},
          new Object[] {0, "2017-11-24", "Carolina", 4, "Toronto", 5},
          new Object[] {0, "2017-11-25", "Toronto", 2, "Washington", 4},
          //new Object[] {0, "2017-11-28", "Calgary Flames", 1, "Toronto", 4},
          new Object[] {0, "2017-11-30", "Edmonton", 4, "Toronto", 6},

          // December games
          new Object[] {0, "2017-12-02", "Vancouver", 2, "Toronto", 1},
          //new Object[] {0, "2017-12-06", "Toronto", 2, "Calgary Flames", 1},
          new Object[] {0, "2017-12-09", "Pittsburgh", 3, "Toronto", 4},
          new Object[] {0, "2017-12-10", "Toronto", 1, "Edmonton", 0},
          new Object[] {0, "2017-12-12", "Philadelphia", 4, "Toronto", 2},
          new Object[] {0, "2017-12-14", "Minnesota", 2, "Toronto", 0},
          new Object[] {0, "2017-12-15", "Detroit", 3, "Toronto", 1},
          new Object[] {0, "2017-12-19", "Toronto", 8, "Carolina", 1},
          new Object[] {0, "2017-12-20", "Columbus", 4, "Toronto", 2},
          new Object[] {0, "2017-12-23", "New York Rangers", 2, "Toronto", 3},
          new Object[] {0, "2017-12-28", "Arizona", 4, "Toronto", 7},
          new Object[] {0, "2017-12-29", "Colorado", 4, "Toronto", 3},
          new Object[] {0, "2017-12-31", "Vegas", 6, "Toronto", 3},

          // January games
          new Object[] {0, "2018-01-02", "Toronto", 0, "Tampa Bay", 2},
          new Object[] {0, "2018-01-04", "Toronto", 3, "San Jose", 2},
          new Object[] {0, "2018-01-06", "Toronto", 3, "Vancouver", 2},
          new Object[] {0, "2018-01-08", "Toronto", 2, "Columbus", 3},
          new Object[] {0, "2018-01-10", "Toronto", 3, "Ottawa", 4},
          new Object[] {0, "2018-01-16", "Toronto", 1, "St. Louis", 2},
          new Object[] {0, "2018-01-18", "Philadelphia", 3, "Toronto", 2},
          new Object[] {0, "2018-01-20", "Ottawa", 3, "Toronto", 4},
          new Object[] {0, "2018-01-22", "Toronto", 2, "Colorado", 4},
          new Object[] {0, "2018-01-24", "Chicago", 2, "Toronto", 3},
          new Object[] {0, "2018-01-25", "Dallas", 1, "Toronto", 4},
          new Object[] {0, "2018-01-31", "Toronto", 5, "New York Islanders", 0},

          // February games
          new Object[] {0, "2018-02-01", "New York Rangers", 0, "Toronto", 4},
          new Object[] {0, "2018-02-03", "Boston", 4, "Toronto", 1},
          new Object[] {0, "2018-02-05", "Toronto", 7, "Anaheim", 4},
          new Object[] {0, "2018-02-07", "Toronto", 3, "Nashville", 2},
          new Object[] {0, "2018-02-10", "Toronto", 6, "Ottawa", 3},
          new Object[] {0, "2018-02-12", "Toronto", 4, "Tampa Bay", 3},
          new Object[] {0, "2018-02-14", "Toronto", 6, "Columbus", 3},
          new Object[] {0, "2018-02-17", "Pittsburgh", 5, "Toronto", 3},
          new Object[] {0, "2018-02-18", "Detroit", 2, "Toronto", 3},
          new Object[] {0, "2018-02-20", "Toronto", 1, "Florida", 0},
          new Object[] {0, "2018-02-22", "Toronto", 4, "New York Islanders", 3},
          new Object[] {0, "2018-02-24", "Toronto", 4, "Boston", 3},
          new Object[] {0, "2018-02-26", "Tampa Bay", 4, "Toronto", 3},
          new Object[] {0, "2018-02-27", "Florida", 3, "Toronto", 2},

          // March games
          new Object[] {0, "2018-03-03", "Washington", 5, "Toronto", 2},
          new Object[] {0, "2018-03-05", "Buffalo", 5, "Toronto", 3},
          new Object[] {0, "2018-03-10", "Toronto", 5, "Pittsburgh", 2},
          new Object[] {0, "2018-03-14", "Toronto", 6, "Dallas", 5},
          new Object[] {0, "2018-03-15", "Buffalo", 2, "Toronto", 5},
          new Object[] {0, "2018-03-17", "Toronto", 4, "Montreal", 0},
          new Object[] {0, "2018-03-20", "Tampa Bay", 4, "Toronto", 3},
          new Object[] {0, "2018-03-22", "Nashville", 2, "Toronto", 5},
          new Object[] {0, "2018-03-24", "Toronto", 4, "Detroit", 3},
          new Object[] {0, "2018-03-26", "Toronto", 2, "Buffalo", 3},
          new Object[] {0, "2018-03-28", "Toronto", 4, "Florida", 3},
          new Object[] {0, "2018-03-30", "New York Islanders", 4, "Toronto", 5},
          new Object[] {0, "2018-03-31", "Toronto", 1, "Winnipeg", 3},

          // April games
          new Object[] {0, "2018-04-02", "Toronto", 5, "Buffalo", 2},
          new Object[] {0, "2018-04-05", "New Jersey", 2, "Toronto", 1},
          new Object[] {0, "2018-04-07", "Toronto", 4, "Montreal", 2},

          /////////////////
          // Montreal games
          /////////////////

          // October games
          new Object[] {0, "2017-10-05", "Buffalo", 3, "Montreal", 2},
          new Object[] {0, "2017-10-07", "Washington", 1, "Montreal", 6},
          new Object[] {0, "2017-10-08", "New York Rangers", 0, "Montreal", 2},
          new Object[] {0, "2017-10-10", "Montreal", 3, "Chicago", 1},
          //new Object[] {0, "2017-10-14", "Montreal", 4, "Toronto", 3},
          new Object[] {0, "2017-10-17", "San Jose", 2, "Montreal", 5},
          new Object[] {0, "2017-10-18", "Los Angeles", 1, "Montreal", 5},
          new Object[] {0, "2017-10-20", "Anaheim", 2, "Montreal", 6},
          new Object[] {0, "2017-10-24", "Montreal", 1, "Florida", 5},
          new Object[] {0, "2017-10-26", "Montreal", 4, "Los Angeles", 0},
          new Object[] {0, "2017-10-28", "Montreal", 4, "New York Rangers", 5},
          new Object[] {0, "2017-10-30", "Ottawa", 8, "Montreal", 3},

          // November games
          new Object[] {0, "2017-11-02", "Minnesota", 3, "Montreal", 6},
          new Object[] {0, "2017-11-04", "Winnipeg", 5, "Montreal", 4},
          new Object[] {0, "2017-11-05", "Chicago", 2, "Montreal", 0},
          new Object[] {0, "2017-11-07", "Montreal", 2, "Vegas", 3},
          new Object[] {0, "2017-11-09", "Montreal", 3, "Minnesota", 0},
          new Object[] {0, "2017-11-11", "Montreal", 1, "Buffalo", 2},
          new Object[] {0, "2017-11-14", "Montreal", 2, "Columbus", 1},
          new Object[] {0, "2017-11-16", "Montreal", 5, "Arizona", 4},
          //new Object[] {0, "2017-11-18", "Montreal", 6, "Toronto", 0},
          new Object[] {0, "2017-11-21", "Dallas", 1, "Montreal", 3},
          new Object[] {0, "2017-11-22", "Nashville", 2, "Montreal", 3},
          new Object[] {0, "2017-11-25", "Montreal", 0, "Buffalo", 3},
          new Object[] {0, "2017-11-27", "Montreal", 1, "Columbus", 3},
          new Object[] {0, "2017-11-29", "Montreal", 1, "Ottawa", 2},
          new Object[] {0, "2017-11-30", "Detroit", 6, "Montreal", 3},

          // December games
          new Object[] {0, "2017-12-02", "Montreal", 1, "Detroit", 0},
          new Object[] {0, "2017-12-05", "Montreal", 4, "St. Louis", 3},
          //new Object[] {0, "2017-12-07", "Montreal", 3, "Calgary", 2},
          new Object[] {0, "2017-12-09", "Montreal", 6, "Edmonton", 2},
          new Object[] {0, "2017-12-14", "Montreal", 1, "New Jersey", 2},
          new Object[] {0, "2017-12-16", "Ottawa", 0, "Montreal", 3},
          new Object[] {0, "2017-12-19", "Vancouver", 7, "Montreal", 5},
          //new Object[] {0, "2017-12-22", "Calgary", 3, "Montreal", 2},
          new Object[] {0, "2017-12-23", "Edmonton", 1, "Montreal", 4},
          new Object[] {0, "2017-12-27", "Carolina", 1, "Montreal", 3},
          new Object[] {0, "2017-12-28", "Tampa Bay", 1, "Montreal", 3},
          new Object[] {0, "2017-12-30", "Florida", 0, "Montreal", 2},

          // January games
          new Object[] {0, "2018-01-02", "Montreal", 4, "San Jose", 1},
          new Object[] {0, "2018-01-04", "Montreal", 1, "Tampa Bay", 2},
          new Object[] {0, "2018-01-07", "Montreal", 2, "Vancouver", 5},
          new Object[] {0, "2018-01-13", "Montreal", 4, "Boston", 3},
          new Object[] {0, "2018-01-15", "Montreal", 5, "New York Islanders", 4},
          new Object[] {0, "2018-01-17", "Boston", 1, "Montreal", 4},
          new Object[] {0, "2018-01-19", "Washington", 3, "Montreal", 2},
          new Object[] {0, "2018-01-20", "Montreal", 4, "Boston", 1},
          new Object[] {0, "2018-01-23", "Montreal", 2, "Colorado", 4},
          new Object[] {0, "2018-01-25", "Montreal", 6, "Carolina", 5},
          new Object[] {0, "2018-01-30", "St. Louis", 1, "Montreal", 3},

          // February games
          new Object[] {0, "2018-02-01", "Carolina", 0, "Montreal", 2},
          new Object[] {0, "2018-02-03", "Montreal", 2, "Anaheim", 5},
          new Object[] {0, "2018-02-04", "Montreal", 1, "Ottawa", 4},
          new Object[] {0, "2018-02-08", "Philadelphia", 3, "Montreal", 5},
          new Object[] {0, "2018-02-10", "Montreal", 3, "Nashville", 2},
          new Object[] {0, "2018-02-14", "Colorado", 0, "Montreal", 2},
          new Object[] {0, "2018-02-15", "Arizona", 2, "Montreal", 5},
          new Object[] {0, "2018-02-17", "Vegas", 3, "Montreal", 6},
          new Object[] {0, "2018-02-20", "Philadelphia", 2, "Montreal", 3},
          new Object[] {0, "2018-02-22", "Montreal", 1, "New York Rangers", 3},
          new Object[] {0, "2018-02-24", "Montreal", 4, "Tampa Bay", 3},
          new Object[] {0, "2018-02-26", "Montreal", 1, "Philadelphia", 0},
          new Object[] {0, "2018-02-28", "Montreal", 1, "New York Islanders", 3},

          // March games
          new Object[] {0, "2018-03-02", "New York Islanders", 6, "Montreal", 3},
          new Object[] {0, "2018-03-03", "Boston", 1, "Montreal", 2},
          new Object[] {0, "2018-03-06", "New Jersey", 4, "Montreal", 6},
          new Object[] {0, "2018-03-08", "Florida", 0, "Montreal", 5},
          new Object[] {0, "2018-03-10", "Tampa Bay", 2, "Montreal", 3},
          new Object[] {0, "2018-03-12", "Columbus", 2, "Montreal", 5},
          new Object[] {0, "2018-03-13", "Montreal", 2, "Dallas", 4},
          new Object[] {0, "2018-03-15", "Montreal", 5, "Pittsburgh", 3},
          //new Object[] {0, "2018-03-17", "Toronto", 0, "Montreal", 4},
          new Object[] {0, "2018-03-19", "Montreal", 2, "Florida", 0},
          new Object[] {0, "2018-03-21", "Pittsburgh", 3, "Montreal", 5},
          new Object[] {0, "2018-03-23", "Buffalo", 3, "Montreal", 0},
          new Object[] {0, "2018-03-24", "Montreal", 6, "Washington", 4},
          new Object[] {0, "2018-03-26", "Montreal", 2, "Detroit", 4},
          new Object[] {0, "2018-03-31", "Pittsburgh", 2, "Montreal", 5},

          // April games
          new Object[] {0, "2018-04-01", "Montreal", 2, "New Jersey", 1},
          new Object[] {0, "2018-04-03", "Montreal", 5, "Winnipeg", 4},
          new Object[] {0, "2018-04-05", "Detroit", 4, "Montreal", 3},
          //new Object[] {0, "2018-04-07", "Toronto", 2, "Montreal", 4},

          //////////////////
          // Vancouver games
          //////////////////

          // October games
          new Object[] {0, "2017-10-07", "Vancouver", 3, "Edmonton", 2},
          new Object[] {0, "2017-10-10", "Vancouver", 2, "Ottawa", 3},
          new Object[] {0, "2017-10-12", "Vancouver", 2, "Winnipeg", 4},
          //new Object[] {0, "2017-10-14", "Vancouver", 2, "Calgary", 5},
          new Object[] {0, "2017-10-17", "Ottawa", 0, "Vancouver", 3},
          new Object[] {0, "2017-10-19", "Boston", 6, "Vancouver", 3},
          new Object[] {0, "2017-10-20", "Buffalo", 2, "Vancouver", 4},
          new Object[] {0, "2017-10-22", "Detroit", 1, "Vancouver", 4},
          new Object[] {0, "2017-10-24", "Minnesota", 0, "Vancouver", 1},
          new Object[] {0, "2017-10-26", "Vancouver", 6, "Washington", 2},
          new Object[] {0, "2017-10-30", "Vancouver", 1, "Dallas", 2},

          // November games
          new Object[] {0, "2017-11-01", "Vancouver", 0, "New Jersey", 2},
          new Object[] {0, "2017-11-04", "Vancouver", 4, "Pittsburgh", 2},
          new Object[] {0, "2017-11-06", "Vancouver", 2, "Detroit", 3},
          //new Object[] {0, "2017-11-07", "Calgary", 3, "Vancouver", 5},
          new Object[] {0, "2017-11-09", "Anaheim", 4, "Vancouver", 1},
          new Object[] {0, "2017-11-11", "San Jose", 5, "Vancouver", 0},
          new Object[] {0, "2017-11-14", "Los Angeles", 2, "Vancouver", 3},
          new Object[] {0, "2017-11-16", "Vancouver", 2, "Vegas", 5},
          new Object[] {0, "2017-11-18", "Vancouver", 3, "St. Louis", 4},
          new Object[] {0, "2017-11-21", "Philadelphia", 2, "Vancouver", 5},
          new Object[] {0, "2017-11-22", "Pittsburgh", 2, "Vancouver", 5},
          new Object[] {0, "2017-11-24", "New Jersey", 3, "Vancouver", 2},
          new Object[] {0, "2017-11-26", "New York Rangers", 4, "Vancouver", 3},
          new Object[] {0, "2017-11-28", "New York Islanders", 5, "Vancouver", 2},
          new Object[] {0, "2017-11-30", "Nashville", 3, "Vancouver", 5},

          // December games
          //new Object[] {0, "2017-12-02", "Vancouver", 2, "Toronto", 1},
          new Object[] {0, "2017-12-05", "Vancouver", 3, "Carolina", 0},
          new Object[] {0, "2017-12-07", "Vancouver", 1, "Philadelphia", 4},
          //new Object[] {0, "2017-12-09", "Calgary", 4, "Vancouver", 2},
          new Object[] {0, "2017-12-11", "Winnipeg", 5, "Vancouver", 1},
          new Object[] {0, "2017-12-13", "Vancouver", 1, "Nashville", 7},
          new Object[] {0, "2017-12-15", "Vancouver", 4, "San Jose", 3},
          //new Object[] {0, "2017-12-17", "Vancouver", 1, "Calgary", 6},
          //new Object[] {0, "2017-12-19", "Vancouver", 5, "Montreal", 7},
          new Object[] {0, "2017-12-21", "San Jose", 5, "Vancouver", 4},
          new Object[] {0, "2017-12-23", "Vancouver", 1, "St. Louis", 3},
          new Object[] {0, "2017-12-28", "Vancouver", 5, "Chicago", 2},
          new Object[] {0, "2017-12-30", "Vancouver", 3, "Los Angeles", 4},

          // January games
          new Object[] {0, "2018-01-02", "Vancouver", 0, "Anaheim", 5},
          //new Object[] {0, "2018-01-06", "Toronto", 3, "Vancouver", 2},
          //new Object[] {0, "2018-01-07", "Montreal", 5, "Vancouver", 2},
          new Object[] {0, "2018-01-09", "Washington", 3, "Vancouver", 1},
          new Object[] {0, "2018-01-12", "Columbus", 2, "Vancouver", 5},
          new Object[] {0, "2018-01-14", "Minnesota", 2, "Vancouver", 3},
          new Object[] {0, "2018-01-20", "Edmonton", 5, "Vancouver", 2},
          new Object[] {0, "2018-01-21", "Winnipeg", 1, "Vancouver", 0},
          new Object[] {0, "2018-01-23", "Vancouver", 6, "Los Angeles", 2},
          new Object[] {0, "2018-01-25", "Vancouver", 0, "Buffalo", 4},
          new Object[] {0, "2018-01-30", "Vancouver", 4, "Colorado", 3},

          // February games
          new Object[] {0, "2018-02-01", "Vancouver", 4, "Chicago", 2},
          new Object[] {0, "2018-02-03", "Vancouver", 2, "Tampa Bay", 4},
          new Object[] {0, "2018-02-06", "Florida", 3, "Vancouver", 1},
          new Object[] {0, "2018-02-08", "Tampa Bay", 5, "Vancouver", 2},
          new Object[] {0, "2018-02-09", "Carolina", 4, "Vancouver", 1},
          new Object[] {0, "2018-02-11", "Dallas", 0, "Vancouver", 6},
          new Object[] {0, "2018-02-14", "Vancouver", 3, "Florida", 4},
          new Object[] {0, "2018-02-15", "San Jose", 4, "Vancouver", 1},
          new Object[] {0, "2018-02-17", "Vancouver", 6, "Boston", 1},
          new Object[] {0, "2018-02-20", "Vancouver", 4, "Colorado", 5},
          new Object[] {0, "2018-02-23", "Vegas", 6, "Vancouver", 3},
          new Object[] {0, "2018-02-25", "Arizona", 1, "Vancouver", 3},
          new Object[] {0, "2018-02-26", "Colorado", 3, "Vancouver", 1},
          new Object[] {0, "2018-02-28", "Vancouver", 5, "New York Rangers", 6},

          // March games
          new Object[] {0, "2018-03-02", "Vancouver", 3, "Nashville", 4},
          new Object[] {0, "2018-03-05", "Vancouver", 4, "New York Islanders", 3},
          new Object[] {0, "2018-03-07", "Vancouver", 1, "Arizona", 2},
          new Object[] {0, "2018-03-09", "Vancouver", 2, "Minnesota", 5},
          new Object[] {0, "2018-03-11", "Arizona", 1, "Vancouver", 0},
          new Object[] {0, "2018-03-12", "Los Angeles", 3, "Vancouver", 0},
          new Object[] {0, "2018-03-14", "Anaheim", 3, "Vancouver", 0},
          new Object[] {0, "2018-03-17", "Vancouver", 3, "San Jose", 5},
          new Object[] {0, "2018-03-20", "Vegas", 4, "Vancouver", 1},
          new Object[] {0, "2018-03-22", "Chicago", 2, "Vancouver", 5},
          new Object[] {0, "2018-03-23", "St. Louis", 4, "Vancouver", 1},
          new Object[] {0, "2018-03-25", "Dallas", 1, "Vancouver", 4},
          new Object[] {0, "2018-03-27", "Vancouver", 4, "Anaheim", 1},
          new Object[] {0, "2018-03-29", "Vancouver", 2, "Edmonton", 1},
          new Object[] {0, "2018-03-31", "Vancouver", 5, "Columbus", 4},

          // April games
          new Object[] {0, "2018-04-03", "Vancouver", 4, "Vegas", 5},
          new Object[] {0, "2018-04-05", "Vancouver", 4, "Arizona", 3},
          new Object[] {0, "2018-04-07", "Edmonton", 3, "Vancouver", 2},

          /////////////////
          // Winnipeg games
          /////////////////

          // October games
          //new Object[] {0, "2017-10-04", "Winnipeg", 2, "Toronto", 7},
          //new Object[] {0, "2017-10-07", "Calgary", 6, "Winnipeg", 3},
          new Object[] {0, "2017-10-09", "Edmonton", 2, "Winnipeg", 5},
          //new Object[] {0, "2017-10-12", "Vancouver", 2, "Winnipeg", 4},
          new Object[] {0, "2017-10-14", "Winnipeg", 2, "Carolina", 1},
          new Object[] {0, "2017-10-17", "Winnipeg", 2, "Columbus", 5},
          new Object[] {0, "2017-10-20", "Winnipeg", 4, "Minnesota", 3},
          new Object[] {0, "2017-10-26", "Pittsburgh", 2, "Winnipeg", 1},
          new Object[] {0, "2017-10-27", "Columbus", 2, "Winnipeg", 1},
          new Object[] {0, "2017-10-29", "Winnipeg", 7, "Pittsburgh", 1},
          new Object[] {0, "2017-10-31", "Minnesota", 1, "Winnipeg", 2},

          // November games
          new Object[] {0, "2017-11-02", "Winnipeg", 5, "Dallas", 2},
          //new Object[] {0, "2017-11-04", "Winnipeg", 4, "Montreal", 5},
          new Object[] {0, "2017-11-06", "Dallas", 1, "Winnipeg", 4},
          new Object[] {0, "2017-11-10", "Vegas", 5, "Winnipeg", 2},
          new Object[] {0, "2017-11-11", "Arizona", 1, "Winnipeg", 4},
          new Object[] {0, "2017-11-14", "Winnipeg", 4, "Arizona", 1},
          new Object[] {0, "2017-11-16", "Winnipeg", 3, "Philadelphia", 2},
          new Object[] {0, "2017-11-18", "Winnipeg", 5, "New Jersey", 2},
          new Object[] {0, "2017-11-20", "Nashville", 5, "Winnipeg", 3},
          new Object[] {0, "2017-11-22", "Los Angeles", 1, "Winnipeg", 2},
          new Object[] {0, "2017-11-24", "Anaheim", 1, "Winnipeg", 4},
          new Object[] {0, "2017-11-25", "San Jose", 4, "Winnipeg", 0},
          new Object[] {0, "2017-11-27", "Winnipeg", 7, "Minnesota", 2},
          new Object[] {0, "2017-11-29", "Colorado", 3, "Winnipeg", 2},

          // December games
          new Object[] {0, "2017-12-01", "Winnipeg", 7, "Vegas", 4},
          new Object[] {0, "2017-12-03", "Winnipeg", 5, "Ottawa", 0},
          new Object[] {0, "2017-12-05", "Detroit", 5, "Winnipeg", 1},
          new Object[] {0, "2017-12-07", "Florida", 6, "Winnipeg", 4},
          new Object[] {0, "2017-12-09", "Tampa Bay", 4, "Winnipeg", 3},
          //new Object[] {0, "2017-12-11", "Winnipeg", 5, "Vancouver", 1},
          new Object[] {0, "2017-12-14", "Winnipeg", 1, "Chicago", 5},
          new Object[] {0, "2017-12-16", "St. Louis", 2, "Winnipeg", 0},
          new Object[] {0, "2017-12-17", "Winnipeg", 4, "St. Louis", 0},
          new Object[] {0, "2017-12-19", "Nashville", 4, "Winnipeg", 6},
          new Object[] {0, "2017-12-21", "Boston", 2, "Winnipeg", 1},
          new Object[] {0, "2017-12-23", "New York Islanders", 5, "Winnipeg", 2},
          new Object[] {0, "2017-12-27", "Winnipeg", 4, "Edmonton", 3},
          new Object[] {0, "2017-12-29", "Winnipeg", 4, "New York Islanders", 2},
          new Object[] {0, "2017-12-31", "Edmonton", 0, "Winnipeg", 5},

          // January games
          new Object[] {0, "2018-01-02", "Colorado", 3, "Winnipeg", 2},
          new Object[] {0, "2018-01-05", "Winnipeg", 4, "Buffalo", 3},
          new Object[] {0, "2018-01-07", "Winnipeg", 4, "San Jose", 1},
          new Object[] {0, "2018-01-09", "Buffalo", 4, "Winnipeg", 7},
          new Object[] {0, "2018-01-12", "Chicago", 2, "Winnipeg", 1},
          new Object[] {0, "2018-01-13", "Minnesota", 4, "Winnipeg", 1},
          //new Object[] {0, "2018-01-20", "Calgary", 1, "Winnipeg", 2},
          //new Object[] {0, "2018-01-21", "Winnipeg", 1, "Vancouver", 0},
          new Object[] {0, "2018-01-23", "San Jose", 4, "Winnipeg", 5},
          new Object[] {0, "2018-01-25", "Anaheim", 4, "Winnipeg", 3},
          new Object[] {0, "2018-01-30", "Winnipeg", 3, "Tampa Bay", 1},

          // February games
          new Object[] {0, "2018-02-01", "Winnipeg", 2, "Vegas", 3},
          new Object[] {0, "2018-02-03", "Winnipeg", 3, "Colorado", 0},
          new Object[] {0, "2018-02-06", "Winnipeg", 4, "Arizona", 3},
          new Object[] {0, "2018-02-09", "Winnipeg", 2, "St. Louis", 5},
          new Object[] {0, "2018-02-11", "Winnipeg", 1, "New York Rangers", 3},
          new Object[] {0, "2018-02-13", "Winnipeg", 4, "Washington", 3},
          new Object[] {0, "2018-02-16", "Winnipeg", 6, "Colorado", 1},
          new Object[] {0, "2018-02-18", "Winnipeg", 7, "Florida", 2},
          new Object[] {0, "2018-02-20", "Winnipeg", 3, "Los Angeles", 4},
          new Object[] {0, "2018-02-23", "St. Louis", 0, "Winnipeg", 4},
          new Object[] {0, "2018-02-24", "Dallas", 3, "Winnipeg", 5},
          new Object[] {0, "2018-02-27", "Winnipeg", 5, "Nashville", 6},

          // March games
          new Object[] {0, "2018-03-02", "Winnipeg", 4, "Detroit", 3},
          new Object[] {0, "2018-03-04", "Carolina", 2, "Winnipeg", 3},
          new Object[] {0, "2018-03-06", "New York Rangers", 0, "Winnipeg", 3},
          new Object[] {0, "2018-03-08", "New Jersey", 2, "Winnipeg", 3},
          new Object[] {0, "2018-03-10", "Philadelphia", 2, "Winnipeg", 1},
          new Object[] {0, "2018-03-12", "Washington", 3, "Winnipeg", 2},
          new Object[] {0, "2018-03-13", "Nashville", 3, "Winnipeg", 1},
          new Object[] {0, "2018-03-15", "Winnipeg", 6, "Chicago", 2},
          new Object[] {0, "2018-03-18", "Winnipeg", 4, "Dallas", 2},
          new Object[] {0, "2018-03-20", "Winnipeg", 2, "Los Angeles", 1},
          new Object[] {0, "2018-03-23", "Winnipeg", 3, "Anaheim", 2},
          new Object[] {0, "2018-03-25", "Winnipeg", 5, "Nashville", 4},
          new Object[] {0, "2018-03-27", "Winnipeg", 5, "Boston", 4},
          new Object[] {0, "2018-03-29", "Chicago", 6, "Winnipeg", 2},
          //new Object[] {0, "2018-03-31", "Toronto", 1, "Winnipeg", 3},

          // April games
          new Object[] {0, "2018-04-02", "Ottawa", 5, "Winnipeg", 6},
          //new Object[] {0, "2018-04-03", "Montreal", 4, "Winnipeg", 5},
          //new Object[] {0, "2018-04-05", "Winnipeg", 2, "Calgary", 1},
          new Object[] {0, "2018-04-07", "Winnipeg", 4, "Chicago", 1},
      };
}
