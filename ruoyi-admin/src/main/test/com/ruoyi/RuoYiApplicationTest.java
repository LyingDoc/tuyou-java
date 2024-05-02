package com.ruoyi;

import com.alibaba.fastjson2.JSON;
import com.ruoyi.business.service.ITTestInfoService;
import com.ruoyi.business.vo.TTestInfoVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RuoYiApplicationTest {

    @Autowired
    private ITTestInfoService testInfoService;

    @Test
    public void test() {
    }
}
