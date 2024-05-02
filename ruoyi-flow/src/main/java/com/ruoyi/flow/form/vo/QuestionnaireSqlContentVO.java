package com.ruoyi.flow.form.vo;


import com.ruoyi.flow.vo.HeadEntityVO;
import lombok.Data;

import java.util.List;

@Data
public class QuestionnaireSqlContentVO {
    private StringBuilder tableEidtSql;
    private StringBuilder selectListSql;
    private StringBuilder insertFiledSql;
    private StringBuilder updateFiledSql;
    private StringBuilder newupdateFiledSql;
    private StringBuilder insertChildTableSql;
    private StringBuilder saveInfoSql;
    private  String  columnType;
    private  String filed;
    /**
     * 统计查询字段
     */
    private StringBuilder chartQuerySql;
    /**
     * 明细列表返回列
     */
    private List<HeadEntityVO> headEntity;
}
