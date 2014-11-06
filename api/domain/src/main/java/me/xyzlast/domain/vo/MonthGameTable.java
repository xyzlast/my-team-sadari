package me.xyzlast.domain.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ykyoon on 14. 11. 6.
 */
public class MonthGameTable {
    private int year;
    private int month;
    private List<DayGameTable> dayGames = new ArrayList<>();

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public List<DayGameTable> getDayGames() {
        return dayGames;
    }

    public void setDayGames(List<DayGameTable> dayGames) {
        this.dayGames = dayGames;
    }
}
