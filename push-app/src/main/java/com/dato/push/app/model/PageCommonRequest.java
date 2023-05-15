package com.dato.push.app.model;

import lombok.Getter;
import lombok.Setter;

/**
 * 统一分页请求对象
 * @author sgz
 */
@Getter
@Setter
public class PageCommonRequest {

    /**
     * 页数
     */
    private Integer pageNumber;
    /**
     * 页数大小
     */
    private Integer pageSize;

}
