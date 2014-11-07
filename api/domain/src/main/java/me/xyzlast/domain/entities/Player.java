package me.xyzlast.domain.entities;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by ykyoon on 14. 11. 6.
 */
@Entity
@Table(name = "Player")
public class Player extends BaseEntity {
    private String name;
    private int defaultAmount;
    @Transient
    private int matchCount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDefaultAmount() {
        return defaultAmount;
    }

    public void setDefaultAmount(int defaultAmount) {
        this.defaultAmount = defaultAmount;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public void setMatchCount(int matchCount) {
        this.matchCount = matchCount;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof Player)) return false;
        Player player = (Player) o;
        return id.equals(player.getId());
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + defaultAmount + super.hashCode();
        return result;
    }

    public int getAmount() {
        return getDefaultAmount() + getMatchCount();
    }
}
