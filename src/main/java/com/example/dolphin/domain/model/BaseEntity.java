package com.example.dolphin.domain.model;

import com.example.dolphin.infrastructure.support.SnowflakeIdGenerator;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author ankelen
 * @date 2022-10-14 13:27
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 数据库 id
     */
    @Id
    @GeneratedValue(generator = "snowflake")
    @GenericGenerator(name = "snowflake", strategy = SnowflakeIdGenerator.TYPE)
    @Column(length = 36)
    private String id;

    /**
     * 创建人
     */
    @Column(length = 50, updatable = false)
    @CreatedBy
    private String createBy;
    /**
     * 最后更新人
     */
    @Column(length = 50)
    @LastModifiedBy
    private String updateBy;

    /**
     * 创建时间
     */
    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime createAt;
    /**
     * 最后更新时间
     */
    @UpdateTimestamp
    private LocalDateTime updateAt;

    /**
     * 删除标识
     */
    private Boolean deleted = false;

    //region gt

    public String getId() {
        return id;
    }

    public String getCreateBy() {
        return createBy;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    //endregion

    //region equals&hash&toString

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BaseEntity that = (BaseEntity) o;
        // 两个id为null的entity会被认为是同一个实体
        return getId() != null ? getId().equals(that.getId()) : that.getId() == null;
    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : System.identityHashCode(this);
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "id='" + id + '\'' +
                ", createBy='" + createBy + '\'' +
                ", updateBy='" + updateBy + '\'' +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
                ", deleted=" + deleted +
                '}';
    }

}
