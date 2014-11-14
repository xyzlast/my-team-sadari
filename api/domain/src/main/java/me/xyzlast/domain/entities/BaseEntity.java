package me.xyzlast.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    protected Long id;

    @Type(type = "yes_no")
    protected boolean deleted;

    @JsonIgnore
    protected Date createDate;
    @JsonIgnore
    protected Date updateDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @PrePersist
    public void setPrePersist() {
        Date now = new Date();
        this.createDate = now;
        this.updateDate = now;
    }

    @PreUpdate
    public void setPreUpdate() {
        this.updateDate = new Date();
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof BaseEntity)) return false;
        BaseEntity that = (BaseEntity) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
