package com.dato.push.app.model.common;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 分页统一返回对象
 * @author sgz
 */
@Getter
@Setter
public class PageCommonResponse<T> {

    /**
     * 分页索引
     */
    private Integer pageIndex;

    /**
     * 分页大小
     */
    private Integer pageSize;

    /**
     * 分页总数
     */
    private Integer totalPage;

    /**
     * 总数
     */
    private Integer totalCount;

    /**
     * 数据
     */
    private List<T> list;
}
