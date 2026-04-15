package com.leadtrans.dictservice.common.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.leadtrans.dictservice.common.enums.StatusEnum;
import com.leadtrans.dictservice.common.utils.LoginUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MybatisPlusInsertUpdateHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {

        //版本信息
        Object version = getFieldValByName("version", metaObject);
        if (version == null) {
            setFieldValByName("version", "0" , metaObject);
        }
        //有效状态
        Object isValid = getFieldValByName("isValid", metaObject);
        if (isValid == null) {
            setFieldValByName("isValid", StatusEnum.valid.getCode() , metaObject);
        }
        //创建人
        Object createBy = getFieldValByName("systemCreateUser", metaObject);
        if (createBy == null) {
            setFieldValByName("systemCreateUser", LoginUtils.getUsername(), metaObject);
        }
        //修改人
        Object updateBy = getFieldValByName("systemUpdateUser", metaObject);
        if (updateBy == null) {
            setFieldValByName("systemUpdateUser", LoginUtils.getUsername(), metaObject);
        }

        Date now = new Date();
        //创建时间
        Object createTime = getFieldValByName("systemCreateTimeUtc", metaObject);
        if (createTime == null) {
            setFieldValByName("systemCreateTimeUtc", now, metaObject);
        }
        //修改时间
        Object updateTime = getFieldValByName("systemUpdateTimeUtc", metaObject);
        if (updateTime == null) {
            setFieldValByName("systemUpdateTimeUtc", now, metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        //版本信息
        Object version = getFieldValByName("version", metaObject);
        if (version == null) {
            version = "0";
        }else{
            version = String.valueOf(Integer.parseInt(version.toString())+1);
        }
        setFieldValByName("version", version , metaObject);
        setFieldValByName("systemUpdateUser", LoginUtils.getUsername(), metaObject);
        setFieldValByName("systemUpdateTimeUtc", new Date(), metaObject);
    }

}
