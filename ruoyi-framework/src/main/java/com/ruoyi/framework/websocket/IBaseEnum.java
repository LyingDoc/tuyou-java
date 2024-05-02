package com.ruoyi.framework.websocket;


import com.baomidou.mybatisplus.core.enums.IEnum;

import java.io.Serializable;

public interface IBaseEnum<T extends Serializable> extends IEnum<T> {
    String getDescription();
}
