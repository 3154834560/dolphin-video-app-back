package com.example.dolphin.domain.entity;

import com.example.dolphin.acomm.domain.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

/**
 * @author 王景阳
 * @date 2022/11/10 18:31
 */
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Concern extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private User concernedUser;

    public Concern(User user, User concernedUser) {
        this.user = user;
        this.concernedUser = concernedUser;
    }
}
