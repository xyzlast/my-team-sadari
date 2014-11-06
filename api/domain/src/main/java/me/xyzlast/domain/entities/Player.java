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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}