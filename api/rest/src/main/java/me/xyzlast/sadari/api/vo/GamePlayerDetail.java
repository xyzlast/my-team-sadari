package me.xyzlast.sadari.api.vo;

import me.xyzlast.domain.entities.Player;

import java.util.Date;

/**
 * Created by ykyoon on 14. 11. 14.
 */
public class GamePlayerDetail {
    private Player player;
    private boolean joined;
    private boolean matched;
    private String ownNumber;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public boolean isJoined() {
        return joined;
    }

    public void setJoined(boolean joined) {
        this.joined = joined;
    }

    public boolean isMatched() {
        return matched;
    }

    public void setMatched(boolean matched) {
        this.matched = matched;
    }

    public String getOwnNumber() {
        return ownNumber;
    }

    public void setOwnNumber(String ownNumber) {
        this.ownNumber = ownNumber;
    }
}
