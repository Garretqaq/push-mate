package com.dato.push.app.model.common;

import com.dato.push.app.dao.PushPlatformConfig;
import com.mybatisflex.core.paginate.Page;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

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
    private Long totalPage;

    /**
     * 总数
     */
    private Long totalCount;

    /**
     * 数据
     */
    private List<T> list;

    /**
     * 对象转换
     * @param page mybatis分页对象
     * @return 统一结果对象
     * @param <T> 对象类型
     */
    public static <T> PageCommonResponse<T> covert(Page<T> page) {
        PageCommonResponse<T> response = new PageCommonResponse<>();
        response.setPageIndex(page.getPageNumber());
        response.setPageSize(page.getPageSize());
        response.setTotalCount(page.getTotalRow());
        response.setTotalPage(page.getTotalPage());
        response.setList(page.getRecords());
        return response;
    }

    /**
     * 对象转换
     * @param page mybatis分页对象
     * @param function 转换函数
     * @return 统一结果对象
     * @param <T> 对象类型
     */
    public static <T,R> PageCommonResponse<R> covert(Page<T> page, Function<T, R> function) {
        PageCommonResponse<R> response = new PageCommonResponse<>();
        response.setPageIndex(page.getPageNumber());
        response.setPageSize(page.getPageSize());
        response.setTotalCount(page.getTotalRow());
        response.setTotalPage(page.getTotalPage());

        // 需要转换对象
        List<T> records = page.getRecords();
        // 转换结果
        List<R> results = records.stream().map(function).toList();
        response.setList(results);
        return response;
    }
}
