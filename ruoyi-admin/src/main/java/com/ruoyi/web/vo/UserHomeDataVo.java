package com.ruoyi.web.vo;

import com.ruoyi.system.vo.HomeDataVO;
import lombok.Data;

/***
 * 小程序首页信息
 */
@Data
public class UserHomeDataVo {
    private HomeDataVO news;
    private HomeDataVO notice;
    private HomeDataVO oaSendNoticeEntity;
    private HomeDataVO oaPorcessinsEntity;
    private HomeDataVO formQuestionConfig;
}
