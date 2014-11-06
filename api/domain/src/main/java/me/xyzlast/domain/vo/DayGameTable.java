package me.xyzlast.domain.vo;

import me.xyzlast.domain.entities.Game;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by ykyoon on 14. 11. 6.
 */
public class DayGameTable {
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private String date;
    private String playerNames;
    private String matchPlayerName;
    private String matchNumber;
    private final boolean isValid;

    public DayGameTable() {
        isValid = false;
    }

    public DayGameTable(Game game) {
        isValid = true;
        setDate(dateFormat.format(game.getDate()));
        List<String> playerNames = game.getResults().stream()
                .filter(r -> !r.isDeleted()).map(r -> String.format("%s(%s)", r.getPlayer().getName(), r.getOwnNumber()))
                .collect(Collectors.toList());
        setPlayerNames(String.join(",", playerNames));
        setMatchNumber(game.getMatchingNumber());
        setMatchPlayerName(game.getMatchingPlayer().getName());
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPlayerNames() {
        return playerNames;
    }

    public void setPlayerNames(String playerNames) {
        this.playerNames = playerNames;
    }

    public String getMatchPlayerName() {
        return matchPlayerName;
    }

    public void setMatchPlayerName(String matchPlayerName) {
        this.matchPlayerName = matchPlayerName;
    }

    public String getMatchNumber() {
        return matchNumber;
    }

    public void setMatchNumber(String matchNumber) {
        this.matchNumber = matchNumber;
    }

    public boolean isValid() {
        return isValid;
    }

    @Override
    public String toString() {
        return "DayGameTable{" +
                "date='" + date + '\'' +
                ", playerNames='" + playerNames + '\'' +
                ", matchPlayerName='" + matchPlayerName + '\'' +
                ", matchNumber='" + matchNumber + '\'' +
                '}';
    }
}
