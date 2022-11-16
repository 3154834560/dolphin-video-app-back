package com.example.dolphin.domain.entity;

import com.example.dolphin.acomm.infrastructure.support.SnowflakeIdGenerator;
import com.example.dolphin.domain.enums.SexEnum;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author 王景阳
 * @date 2022/10/29 20:40
 */

@EqualsAndHashCode
@Data
@Entity
public class User {

    @Id
    @GeneratedValue(generator = "snowflake")
    @GenericGenerator(name = "snowflake", strategy = SnowflakeIdGenerator.TYPE)
    @Column(length = 36)
    private String id;

    private String userName;

    private String name;

    private String nick;

    private String password;

    private String headPortraitUrl;

    private String headPortraitName;

    @Enumerated(value = EnumType.STRING)
    private SexEnum sex;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime birthday;

    private String phone;

    private String introduction;

    private boolean isAdmin;

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

    @JoinColumn(name = "parent_id")
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Video> videos;

    public void addVideos(Video video) {
        this.videos.add(video);
    }


}
