package com.ruoyi.flow.form.mapper;

import com.ruoyi.flow.form.req.KylinFiledInfoReq;
import com.ruoyi.flow.form.req.KylinTableInfoReq;
import com.ruoyi.flow.form.vo.KylinFiledInfoVO;
import com.ruoyi.flow.form.vo.KylinTableInfoVO;


import java.util.List;

public interface KylinDBCommonMapper  {

    List<KylinTableInfoVO> getAlltablename(KylinTableInfoReq req);
    List<KylinFiledInfoVO> getTableColumn(KylinFiledInfoReq req);
}
