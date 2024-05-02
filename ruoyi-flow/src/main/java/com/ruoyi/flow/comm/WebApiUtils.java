package com.ruoyi.flow.comm;


import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.common.constant.CacheConstants;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.flow.form.vo.ApiConfigVO;

import java.util.Collection;

public class WebApiUtils {
    /**
     * 设置字典缓存
     *
     * @param key       参数键
     * @param dictDatas 字典数据列表
     */
    public static void setWebApiCache(String key, ApiConfigVO apiConfigVO) {
        SpringUtils.getBean(RedisCache.class).setCacheObject(getCacheKey(key), apiConfigVO);
    }

    /**
     * 获取字典缓存
     *
     * @param key 参数键
     * @return dictDatas 字典数据列表
     */
    public static ApiConfigVO getWebApiCache(String key) {
        ApiConfigVO arrayCache = SpringUtils.getBean(RedisCache.class).getCacheObject(getCacheKey(key));
        return arrayCache;
    }


    /**
     * 删除指定字典缓存
     *
     * @param key 字典键
     */
    public static void removeWebApiCache(String key) {
        SpringUtils.getBean(RedisCache.class).deleteObject(getCacheKey(key));
    }

    /**
     * 清空字典缓存
     */
    public static void clearWebApiCache() {
        Collection<String> keys = SpringUtils.getBean(RedisCache.class).keys(CacheConstants.WEB_API_CODE + "*");
        SpringUtils.getBean(RedisCache.class).deleteObject(keys);
    }

    /**
     * 设置cache key
     *
     * @param configKey 参数键
     * @return 缓存键key
     */
    public static String getCacheKey(String configKey) {
        return CacheConstants.WEB_API_CODE + configKey;
    }
}
