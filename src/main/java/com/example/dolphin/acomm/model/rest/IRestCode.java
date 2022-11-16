package com.example.dolphin.acomm.model.rest;

/**
 * 自定义响应码接口
 *
 * @author ankelen
 * @date 2022-10-14 13:27
 */
public interface IRestCode {
    Integer getCode();

    String getMsg();

    Boolean getFlag();
}
