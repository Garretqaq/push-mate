package com.dato.push.app.utils;

import cn.hutool.cache.Cache;
import cn.hutool.cache.CacheUtil;
import cn.hutool.core.date.DateUnit;
import com.dato.push.app.model.common.LoginUser;

/**
 * lru缓存工具类
 * @author sgz
 */
public class LRUCacheUtil {

    /**
     * k -> userId
     */
    private static final Cache<Integer, LoginUser> LFU_CACHE;

    static {
        LFU_CACHE = CacheUtil.newLFUCache(100);
    }

    public static void cache(LoginUser loginUser){
        LFU_CACHE.put(loginUser.getId(), loginUser, DateUnit.DAY.getMillis() * 2);
    }

    public static LoginUser getLoginUser(Integer userId){
        return LFU_CACHE.get(userId);
    }

    public static void removeUser(Integer userId){
        LFU_CACHE.remove(userId);
    }
}
