package com.ruoyi.framework.handle;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.http.HttpStatus;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.ibatis.reflection.MetaObject;

import java.util.Date;

/**
 * MybatisPlus 字段注入
 *
 * @author Tellsea
 * @date 2022/9/1
 */
@Slf4j
public class CreateAndUpdateMetaObjectHandler implements MetaObjectHandler {
    private static final String createDate = "createDate";
    private static final String createBy = "createBy";
    private static final String updateDate = "updateDate";
    private static final String updateBy = "updateBy";
    private static final String createTime = "createTime";
    private static final String updateTime = "updateTime";
    @Override
    public void insertFill(MetaObject metaObject) {
        try {
            if (ObjectUtils.isNotEmpty(metaObject) && metaObject.getOriginalObject() instanceof BaseEntity) {
                BaseEntity baseEntity = (BaseEntity) metaObject.getOriginalObject();
                Date current = ObjectUtils.isNotEmpty(baseEntity.getCreateTime())
                        ? baseEntity.getCreateTime() : new Date();
                baseEntity.setCreateTime(current);
                baseEntity.setUpdateTime(current);
                String username = StringUtils.isNotBlank(baseEntity.getCreateBy())
                        ? baseEntity.getCreateBy() : getLoginUsername();
                // 当前已登录 且 创建人为空 则填充
                baseEntity.setCreateBy(username);
                // 当前已登录 且 更新人为空 则填充
                baseEntity.setUpdateBy(username);
            }else {
                LoginUser userInfo = SecurityUtils.getLoginUser();
                if (userInfo != null) {
                    if (metaObject.hasGetter(createBy) && metaObject.getValue(createBy) == null) {
                        this.setFieldValByName(createBy, userInfo.getUsername(), metaObject);
                    }
                    if (metaObject.hasGetter(updateBy) && metaObject.getValue(updateBy) == null) {
                        this.setFieldValByName(updateBy, userInfo.getUsername(), metaObject);
                    }
                }
                if (metaObject.hasGetter(createTime) && metaObject.getValue(createTime) == null) {
                    this.setFieldValByName(createTime, new Date(), metaObject);
                }
                if (metaObject.hasGetter(updateTime) && metaObject.getValue(updateTime) == null) {
                    this.setFieldValByName(updateTime, new Date(), metaObject);
                }
                if (metaObject.hasGetter(createDate) && metaObject.getValue(createDate) == null) {
                    this.setFieldValByName(createDate, new Date(), metaObject);
                }
                if (metaObject.hasGetter(updateDate) && metaObject.getValue(updateDate) == null) {
                    this.setFieldValByName(updateDate, new Date(), metaObject);
                }
            }
			// 默认设置状态1
            this.setFieldValByName("deleteStatus", 1, metaObject);
        } catch (Exception e) {
            throw new ServiceException("自动注入异常 => " + e.getMessage(), HttpStatus.HTTP_UNAUTHORIZED);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        try {
            if (ObjectUtil.isNotNull(metaObject) && metaObject.getOriginalObject() instanceof BaseEntity) {
                BaseEntity baseEntity = (BaseEntity) metaObject.getOriginalObject();
                Date current = new Date();
                // 更新时间填充(不管为不为空)
                baseEntity.setUpdateTime(current);
                String username = getLoginUsername();
                // 当前已登录 更新人填充(不管为不为空)
                if (StringUtils.isNotBlank(username)) {
                    baseEntity.setUpdateBy(username);
                }
            }else{
                LoginUser userInfo = SecurityUtils.getLoginUser();
                if(userInfo!=null){
                    if (metaObject.hasGetter(updateBy) && metaObject.getValue(updateBy) == null) {
                        this.setFieldValByName(updateBy, userInfo.getUsername(), metaObject);
                    }
                }
                if (metaObject.hasGetter(updateDate) && metaObject.getValue(updateDate) == null) {
                    this.setFieldValByName(updateDate, new Date(), metaObject);
                }
                if (metaObject.hasGetter(updateTime) && metaObject.getValue(updateTime) == null) {
                    this.setFieldValByName(updateTime, new Date(), metaObject);
                }
            }
        } catch (Exception e) {
            throw new ServiceException("自动注入异常 => " + e.getMessage(), HttpStatus.HTTP_UNAUTHORIZED);
        }
    }

    /**
     * 获取登录用户名
     */
    private String getLoginUsername() {
        LoginUser loginUser;
        try {
            loginUser = SecurityUtils.getLoginUser();
        } catch (Exception e) {
            log.warn("自动注入警告 => 用户未登录");
            return null;
        }
        return loginUser.getUsername();
    }

}
