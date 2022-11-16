package com.example.dolphin.domain.entity;

import com.example.dolphin.acomm.infrastructure.support.SnowflakeIdGenerator;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author 王景阳
 * @date 2022/11/10 18:41
 */
@EqualsAndHashCode
@Data
@Entity
public class Collection {

    @Id
    @GeneratedValue(generator = "snowflake")
    @GenericGenerator(name = "snowflake", strategy = SnowflakeIdGenerator.TYPE)
    @Column(length = 36)
    private String id;

    private String userName;

    private String videoId;

    public Collection() {
    }

    public Collection(String userName, String videoId) {
        this.userName = userName;
        this.videoId = videoId;
    }
}
