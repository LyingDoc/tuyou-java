package com.ruoyi.flow.form.service.impl;


import com.ruoyi.flow.form.vo.DefaultFieldInfoVO;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("prototype")
public class BuildFormMybatisInterfaceService extends BuildMybatisInterfaceService {
    public   BuildFormMybatisInterfaceService(){
        this.buildType=1;
        DefaultFieldInfoVO defaultFieldInfoVO=new DefaultFieldInfoVO();
        defaultFieldInfoVO.setFieldName("fid");
        defaultFieldInfoVO.setFieldType(" varchar(20) NOT NULL ");
        defaultFieldInfoVO.setIsPublic(true);
        defaultFieldInfoVO.setDescribe("主键");
        defaultFieldInfoVO.setIsPrimaryKey(true);
        defaultFieldInfoVO.setFieldAssignment("#{$tableNewId}");
        defaultFieldlist.add(defaultFieldInfoVO);
        DefaultFieldInfoVO defaultFieldInfoVO2=new DefaultFieldInfoVO();
        defaultFieldInfoVO2.setFieldName("fcreationdate");
        defaultFieldInfoVO2.setFieldType("  datetime(0) DEFAULT NULL ");
        defaultFieldInfoVO2.setIsPublic(true);
        defaultFieldInfoVO2.setIsGridShow(true);
        defaultFieldInfoVO2.setDescribe("创建时间");
        defaultFieldInfoVO2.setFieldAssignment("now()");
        defaultFieldInfoVO2.setWhereSql("<if test=\"fcreationdate!=null and fcreationdate.size()>0\">and maintable.fcreationdate between #{fcreationdate[0]} and #{fcreationdate[1]}  </if>");
        defaultFieldlist.add(defaultFieldInfoVO2);
        DefaultFieldInfoVO defaultFieldInfoVO3=new DefaultFieldInfoVO();
        defaultFieldInfoVO3.setFieldName("fcreateby");
        defaultFieldInfoVO3.setFieldType(" varchar(20)   DEFAULT NULL ");
        defaultFieldInfoVO3.setIsPublic(true);
        defaultFieldInfoVO3.setDescribe("创建人");
        defaultFieldInfoVO3.setFieldAssignment("#{$user.userCode}");
        defaultFieldlist.add(defaultFieldInfoVO3);
        DefaultFieldInfoVO defaultFieldInfoVO4=new DefaultFieldInfoVO();
        defaultFieldInfoVO4.setFieldName("fcreatebyname");
        defaultFieldInfoVO4.setFieldType(" varchar(50)   DEFAULT NULL ");
        defaultFieldInfoVO4.setIsPublic(true);
        defaultFieldInfoVO4.setDescribe("创建人");
        defaultFieldInfoVO4.setIsGridShow(true);
        defaultFieldInfoVO4.setFieldAssignment("#{$user.userName}");
        defaultFieldInfoVO4.setWhereSql("<if test=\"fcreatebyname!=null and fcreatebyname!=''\">and maintable.fcreatebyname  like CONCAT('%',#{fcreatebyname},'%')  </if>");
        defaultFieldlist.add(defaultFieldInfoVO4);
        DefaultFieldInfoVO defaultFieldInfoVO5=new DefaultFieldInfoVO();
        defaultFieldInfoVO5.setFieldName("flastupdatedate");
        defaultFieldInfoVO5.setFieldType(" datetime(0) DEFAULT NULL ");
        defaultFieldInfoVO5.setIsPublic(true);
        defaultFieldInfoVO5.setDescribe("修改时间");
        defaultFieldInfoVO5.setIsUpdate(true);
        defaultFieldInfoVO5.setIsGridShow(true);
        defaultFieldInfoVO5.setFieldAssignment("now()");
        defaultFieldInfoVO5.setWhereSql("<if test=\"flastupdatedate!=null   and flastupdatedate.size()>0\">and maintable.flastupdatedate between #{flastupdatedate[0]} and #{flastupdatedate[1]}  </if>");

        defaultFieldlist.add(defaultFieldInfoVO5);
        DefaultFieldInfoVO defaultFieldInfoVO6=new DefaultFieldInfoVO();
        defaultFieldInfoVO6.setFieldName("flastupdateby");
        defaultFieldInfoVO6.setFieldType(" varchar(20)   DEFAULT NULL ");
        defaultFieldInfoVO6.setIsPublic(true);
        defaultFieldInfoVO6.setIsUpdate(true);
        defaultFieldInfoVO6.setDescribe("修改人");
        defaultFieldInfoVO6.setFieldAssignment("#{$user.userCode}");
        defaultFieldlist.add(defaultFieldInfoVO6);
        DefaultFieldInfoVO defaultFieldInfoVO7=new DefaultFieldInfoVO();
        defaultFieldInfoVO7.setFieldName("flastupdatebyname");
        defaultFieldInfoVO7.setFieldType(" varchar(50)   DEFAULT NULL");
        defaultFieldInfoVO7.setIsPublic(true);
        defaultFieldInfoVO7.setIsUpdate(true);
        defaultFieldInfoVO7.setIsGridShow(true);
        defaultFieldInfoVO7.setFieldAssignment("#{$user.userName}");
        defaultFieldInfoVO7.setDescribe("修改人");
        defaultFieldInfoVO7.setWhereSql("<if test=\"flastupdatebyname!=null and   flastupdatebyname!=''\">and maintable.flastupdatebyname  like CONCAT('%',#{flastupdatebyname},'%') </if>");
        defaultFieldlist.add(defaultFieldInfoVO7);
        DefaultFieldInfoVO defaultFieldInfoVO8=new DefaultFieldInfoVO();
        defaultFieldInfoVO8.setFieldName("organcode");
        defaultFieldInfoVO8.setFieldType(" varchar(200)   DEFAULT NULL");
        defaultFieldInfoVO8.setIsPublic(true);
        defaultFieldInfoVO8.setIsUpdate(false);
        defaultFieldInfoVO8.setFieldAssignment("#{$user.officeCode}");
        defaultFieldInfoVO8.setDescribe("组织编码");
        defaultFieldlist.add(defaultFieldInfoVO8);
    }
}
