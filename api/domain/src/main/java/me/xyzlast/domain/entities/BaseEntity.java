package me.xyzlast.domain.entities;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by ykyoon on 14. 11. 6.
 */
@MappedSuperclass
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Type(type = "yes_no")
    private boolean deleted;

    private Date createDate;
    private Date updateDate;

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Date getCreateDate() {
        return createDate;
    }

    @PrePersist
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    @PreUpdate
    @PrePersist
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
