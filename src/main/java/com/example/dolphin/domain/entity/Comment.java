package com.example.dolphin.domain.entity;

import com.example.dolphin.acomm.infrastructure.support.SnowflakeIdGenerator;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

/**
 * @author 王景阳
 * @date 2022/11/10 15:55
 */
@EqualsAndHashCode
@Data
@Entity
public class Comment {

    @Id
    @GeneratedValue(generator = "snowflake")
    @GenericGenerator(name = "snowflake", strategy = SnowflakeIdGenerator.TYPE)
    @Column(length = 36)
    private String id;

    private String userName;

    private String content;

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

    public Comment() {
    }

    public Comment(String userName, String content) {
        this.content = content;
        this.userName = userName;
    }


}
