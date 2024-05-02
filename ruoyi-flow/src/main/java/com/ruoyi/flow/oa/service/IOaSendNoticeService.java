package com.ruoyi.flow.oa.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.flow.oa.entity.OaTempNoticeEntity;
import com.ruoyi.flow.oa.vo.ProcessChartVO;
import com.ruoyi.flow.oa.vo.QuerySendNoticeVO;
import com.ruoyi.flow.vo.HomeDataVO;
import com.ruoyi.flow.vo.PageVO;
import com.ruoyi.flow.vo.R;
import com.ruoyi.flow.oa.entity.OaSendNoticeEntity;

import java.util.List;

/**
 * <p>
 * 通知发送表 服务类
 * </p>
 *
 * @author 刘亚平
 * @since 2023-01-15
 */
public interface IOaSendNoticeService extends IService<OaSendNoticeEntity> {
    /**
     * 通过主键获取数据
     *
     * @param fid 主键
     * @return 是否成功
     */
    OaSendNoticeEntity getById(Long id);

    /**
     * 通过主键删除数据
     *
     * @param fid 主键
     * @return 是否成功
     */
    boolean deleteById(Long fid);


    /**
     * 修改
     *
     * @param oasendnoticeentity
     * @return true代表更新成功  false代表更新失败
     */
    R<OaSendNoticeEntity> saveOaSendNoticeEntity(OaSendNoticeEntity oasendnoticeentity);

    /**
     * 批量删除
     *
     * @param ids 要删除的集合列表
     * @return true代表删除成功  false代表删除失败
     */
    boolean deleteOaSendNoticeEntityList(List<Integer> ids);

    /**
     * 分页查询
     *
     * @param pageNo 页数
     * @param size   一页最大的条数
     * @return Page<OaSendNoticeEntity>
     */
    R<List<OaSendNoticeEntity>> getOaSendNoticeEntityList(PageVO<OaSendNoticeEntity> req);

    R<List<QuerySendNoticeVO>> querySendNotice(PageVO<QuerySendNoticeVO> oaTempNotice);

    /**
     * 获取首页公告信息
     * @return
     */
    HomeDataVO getHomeData();

}

