package com.dato.push.app.model.common;

import lombok.Getter;
import lombok.Setter;

/**
 * 抽象统一分页对象
 */
@Getter
@Setter
public abstract class AbstractPageRequest {

    /**
     * 分页索引
     */
    private Integer pageIndex;

    /**
     * 分页大小
     */
    private Integer pageSize;
}
