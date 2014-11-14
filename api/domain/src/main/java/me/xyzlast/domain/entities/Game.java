package me.xyzlast.domain.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ykyoon on 14. 11. 6.
 */
@Entity
@Table(name = "Game")
public class Game extends BaseEntity {
    private Date date;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
    private List<PlayerResult> results = new ArrayList<>();
    private String matchingNumber;
    private long cost;
    private String description;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<PlayerResult> getResults() {
        return results;
    }

    public void setResults(List<PlayerResult> results) {
        this.results = results;
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

    public Player getMatchingPlayer() {
        return getResults().stream()
                .filter(r -> r.getOwnNumber().equals(getMatchingNumber()))
                .findFirst().get().getPlayer();
    }
}
