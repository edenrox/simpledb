package com.hopkins.simpledb.root;

import com.hopkins.simpledb.data.Column;
import com.hopkins.simpledb.data.Schema;

import java.util.Arrays;

public final class RegionData {

  public static final class CountriesTable {
    public static final String TABLE_NAME = "countries";

    public interface Columns {
      String ID = Column.ROW_ID_NAME;
      String CODE = "code";
      String NAME = "name";
    }

    public static final Schema SCHEMA = new Schema(Arrays.asList(
        Column.ROW_ID,
        Column.newStringColumn(Columns.CODE, 2),
        Column.newStringColumn(Columns.NAME, 32)
    ));
  }

  public static final Object[][] COUNTRIES_DATA = new Object[][] {
      new Object[] {0, "CA", "Canada"},
      new Object[] {0, "US", "United States"},
  };

  public static final class StatesTable {
    public static final String TABLE_NAME = "states";

    public interface Columns {
      String ID = Column.ROW_ID_NAME;
      String COUNTRY_CODE = "country_code";
      String CODE = "code";
      String NAME = "name";
    }

    public static final Schema SCHEMA = new Schema(Arrays.asList(
        Column.ROW_ID,
        Column.newStringColumn(Columns.COUNTRY_CODE, 2),
        Column.newStringColumn(Columns.CODE, 2),
        Column.newStringColumn(Columns.NAME, 32)
    ));
  }

  public static final Object[][] STATES_DATA = new Object[][] {

      // Canadian Provinces
      new Object[] {0, "CA", "BC", "British Columbia"},
      new Object[] {0, "CA", "AB", "Alberta"},
      new Object[] {0, "CA", "SK", "Saskatchewan"},
      new Object[] {0, "CA", "MB", "Manitoba"},
      new Object[] {0, "CA", "ON", "Ontario"},
      new Object[] {0, "CA", "QC", "Quebec"},
      new Object[] {0, "CA", "NB", "New Brunswick"},
      new Object[] {0, "CA", "NS", "Nova Scotia"},
      new Object[] {0, "CA", "PE", "Prince Edward Island"},
      new Object[] {0, "CA", "NL", "Newfoundland"},
      new Object[] {0, "CA", "NU", "Nunavut"},
      new Object[] {0, "CA", "NT", "Northwest Territories"},
      new Object[] {0, "CA", "YT", "Yukon Territory"},

      // US States

      new Object[] {0, "US", "AL", "Alabama"},
      new Object[] {0, "US", "AK", "Alaska"},
      new Object[] {0, "US", "AZ", "Arizona"},
      new Object[] {0, "US", "AR", "Arkansas"},
      new Object[] {0, "US", "CA", "California"},
      new Object[] {0, "US", "CO", "Colorado"},
      new Object[] {0, "US", "CT", "Connecticut"},
      new Object[] {0, "US", "DE", "Delaware"},
      new Object[] {0, "US", "DC", "District of Columbia"},
      new Object[] {0, "US", "FL", "Florida"},
      new Object[] {0, "US", "GA", "Georgia"},
      new Object[] {0, "US", "HI", "Hawaii"},
      new Object[] {0, "US", "ID", "Idaho"},
      new Object[] {0, "US", "IL", "Illinois"},
      new Object[] {0, "US", "IN", "Indiana"},
      new Object[] {0, "US", "IA", "Iowa"},
      new Object[] {0, "US", "KS", "Kansas"},
      new Object[] {0, "US", "KY", "Kentucky"},
      new Object[] {0, "US", "LA", "Louisiana"},
      new Object[] {0, "US", "ME", "Maine"},
      new Object[] {0, "US", "MD", "Maryland"},
      new Object[] {0, "US", "MA", "Massachusetts"},
      new Object[] {0, "US", "MI", "Michigan"},
      new Object[] {0, "US", "MN", "Minnesota"},
      new Object[] {0, "US", "MS", "Mississippi"},
      new Object[] {0, "US", "MO", "Missouri"},
      new Object[] {0, "US", "MT", "Montana"},
      new Object[] {0, "US", "NE", "Nebraska"},
      new Object[] {0, "US", "NV", "Nevada"},
      new Object[] {0, "US", "NH", "New Hampshire"},
      new Object[] {0, "US", "NJ", "New Jersey"},
      new Object[] {0, "US", "NM", "New Mexico"},
      new Object[] {0, "US", "NY", "New York"},
      new Object[] {0, "US", "NC", "North Carolina"},
      new Object[] {0, "US", "ND", "North Dakota"},
      new Object[] {0, "US", "OH", "Ohio"},
      new Object[] {0, "US", "OK", "Oklahoma"},
      new Object[] {0, "US", "OR", "Oregon"},
      new Object[] {0, "US", "PA", "Pennsylvania"},
      new Object[] {0, "US", "RI", "Rhode Island"},
      new Object[] {0, "US", "SC", "South Carolina"},
      new Object[] {0, "US", "SD", "South Dakota"},
      new Object[] {0, "US", "TN", "Tennessee"},
      new Object[] {0, "US", "TX", "Texas"},
      new Object[] {0, "US", "UT", "Utah"},
      new Object[] {0, "US", "VT", "Vermont"},
      new Object[] {0, "US", "VA", "Virginia"},
      new Object[] {0, "US", "WA", "Washington"},
      new Object[] {0, "US", "WV", "West Virginia"},
      new Object[] {0, "US", "WI", "Wisconsin"},
      new Object[] {0, "US", "WY", "Wyoming"},
  };
}
