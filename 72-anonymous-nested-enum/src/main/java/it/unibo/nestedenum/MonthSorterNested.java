package it.unibo.nestedenum;

import java.util.Comparator;
import java.util.List;

/**
 * Implementation of {@link MonthSorter}.
 */
public final class MonthSorterNested implements MonthSorter {

  public enum Month {
    JANUARY(31),
    FEBRUARY(28),
    MARCH(31),
    APRIL(30),
    MAY(31),
    JUNE(30),
    JULY(31),
    AUGUST(31),
    SEPTEMBER(30),
    OCTOBER(31),
    NOVEMBER(30),
    DECEMBER(31);

    public final int days;

    private Month(final int days) {
      this.days = days;
    }

    public static Month fromString(String str) {
      str = str.toUpperCase();
      Month match = null;
      for (Month month : Month.values()) {
        for (int i = 0; i < str.length(); i++) {
          if (month.toString().charAt(i) != str.charAt(i)) {
            break;
          }
          // found a match
          if (i == (str.length() - 1)) {
            if (match != null) {
              throw new IllegalArgumentException("string " + str + " is ambiguous");
            }
            match = month;
          }
        }
      }
      if (match == null) {
        throw new IllegalArgumentException("string " + str + " does not match with any month");
      }
      return match;
    }
  }

  @Override
  public Comparator<String> sortByDays() {
    return new Comparator<String>() {
      @Override
      public int compare(String m1, String m2) {
        return Month.fromString(m1).days - Month.fromString(m2).days;
      }
    };
  }

  @Override
  public Comparator<String> sortByOrder() {
    return new Comparator<String>() {
      @Override
      public int compare(String m1, String m2) {
        //var months = Arrays.asList(Month.values());
        var months = List.of(Month.values());
        return months.indexOf(Month.fromString(m1)) - months.indexOf(Month.fromString(m2));
      }
    };
  }
}
