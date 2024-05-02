package com.ruoyi.flow.comm;


import com.ruoyi.common.constant.CacheConstants;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.flow.form.vo.ApiConfigVO;
import com.ruoyi.flow.vo.OnLineFormVO;

import java.util.Collection;

public class OnLineFormUtils {
    /**
     * 设置字典缓存
     *
     * @param key       参数键
     * @param dictDatas 字典数据列表
     */
    public static void setOnLineFormCache(String key, OnLineFormVO onLineFormVO) {
        SpringUtils.getBean(RedisCache.class).setCacheObject(getCacheKey(key), onLineFormVO);
    }

    /**
     * 获取字典缓存
     *
     * @param key 参数键
     * @return dictDatas 字典数据列表
     */
    public static OnLineFormVO getOnLineFormCache(String key) {
        OnLineFormVO arrayCache = SpringUtils.getBean(RedisCache.class).getCacheObject(getCacheKey(key));
        return arrayCache;
    }


    /**
     * 删除指定字典缓存
     *
     * @param key 字典键
     */
    public static void removeOnLineFormCache(String key) {
        SpringUtils.getBean(RedisCache.class).deleteObject(getCacheKey(key));
    }

    /**
     * 清空字典缓存
     */
    public static void clearOnLineFormCache() {
        Collection<String> keys = SpringUtils.getBean(RedisCache.class).keys(CacheConstants.ON_LINE_FORM + "*");
        SpringUtils.getBean(RedisCache.class).deleteObject(keys);
    }

    /**
     * 设置cache key
     *
     * @param configKey 参数键
     * @return 缓存键key
     */
    public static String getCacheKey(String configKey) {
        return CacheConstants.ON_LINE_FORM + configKey;
    }
}
