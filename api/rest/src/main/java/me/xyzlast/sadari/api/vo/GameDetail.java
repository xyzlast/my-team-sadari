package me.xyzlast.sadari.api.vo;

import com.mysema.commons.lang.Assert;
import me.xyzlast.domain.entities.Game;
import me.xyzlast.domain.entities.Player;
import me.xyzlast.domain.entities.PlayerResult;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Created by ykyoon on 14. 11. 14.
 */
public class GameDetail {
    private Date date;
    private String matchingNumber;
    private long cost;
    private String description;
    private Long id;
    private List<GamePlayerDetail> players;

    public GameDetail(List<Player> players) {
        this.players = new ArrayList<>();
        this.date = new Date();
        Integer value = 1;
        for(Player player : players) {
            GamePlayerDetail playerDetail = new GamePlayerDetail();
            playerDetail.setOwnNumber(value.toString());
            playerDetail.setPlayer(player);
            playerDetail.setJoined(true);
            value++;
            this.players.add(playerDetail);
        }
    }

    public GameDetail(Game game, List<Player> players) {
        this.id = game.getId();
        this.date = game.getDate();
        this.matchingNumber = game.getMatchingNumber();
        this.cost = game.getCost();
        this.description = game.getDescription();
        this.players = new ArrayList<>();

        List<PlayerResult> gameResults = game.getResults();
        for(Player player : players) {
            GamePlayerDetail gamePlayer = new GamePlayerDetail();
            gamePlayer.setPlayer(player);
            for(PlayerResult gameResult : gameResults) {
                if(!gamePlayer.isJoined()) {
                    gamePlayer.setJoined(gameResult.getPlayer().getId().equals(player.getId()));
                    if(gamePlayer.isJoined()) {
                        gamePlayer.setOwnNumber(gameResult.getOwnNumber());
                    }
                }
            }
            if(gamePlayer.isJoined()) {
                gamePlayer.setMatched(game.getMatchingPlayer().getId().equals(player.getId()));
            }
            this.players.add(gamePlayer);
        }
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMatchingNumber() {
        return matchingNumber;
    }

    public void setMatchingNumber(String matchingNumber) {
        this.matchingNumber = matchingNumber;
    }

    public long getCost() {
        return cost;
    }

    public void setCost(long cost) {
        this.cost = cost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<GamePlayerDetail> getPlayers() {
        return players;
    }

    public void setPlayers(List<GamePlayerDetail> players) {
        this.players = players;
    }
}
