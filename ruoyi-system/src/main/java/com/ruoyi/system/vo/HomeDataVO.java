package com.ruoyi.system.vo;

import lombok.Data;

import java.util.Date;

@Data
public class HomeDataVO {
    /**
     * 标题
     */
    private  String title;
    /**
     *发布时间 到达时间
     */
    private Date  publishTime;
    /**
     * 待处理数
     */
    private  Integer toNum;
}
