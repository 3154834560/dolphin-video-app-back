package com.example.dolphin.domain.entity;

import com.example.dolphin.acomm.infrastructure.support.SnowflakeIdGenerator;
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
 * @date 2022/10/29 20:36
 */
@EqualsAndHashCode
@Data
@Entity
public class Video {

    /**
     * 数据库 id
     */
    @Id
    @GeneratedValue(generator = "snowflake")
    @GenericGenerator(name = "snowflake", strategy = SnowflakeIdGenerator.TYPE)
    @Column(length = 36)
    private String id;

    /**
     * 视频播放Url
     */
    private String url;

    /**
     * 视频名
     */
    private String videoName;

    /**
     * 视频作者，对应用户名
     */
    private String author;

    /**
     * 视频作者昵称
     */
    private String authorNick;

    /**
     * 视频简介
     */
    private String introduction;

    /**
     * 视频封面
     */
    private String coverUrl;

    /**
     * 视频封面名
     */
    private String coverName;

    /**
     * 点赞数
     */
    private long numbers;

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
    private List<Comment> comments;

    public void addComment(Comment comment){
        this.comments.add(comment);
    }

}
