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
        
        String username = LoginUtils.getUsername();
        Date now = new Date();
        
        //老表字段 - 创建人
        Object createBy = getFieldValByName("systemCreateUser", metaObject);
        if (createBy == null) {
            setFieldValByName("systemCreateUser", username, metaObject);
        }
        //老表字段 - 修改人
        Object updateBy = getFieldValByName("systemUpdateUser", metaObject);
        if (updateBy == null) {
            setFieldValByName("systemUpdateUser", username, metaObject);
        }
        //老表字段 - 创建时间
        Object createTime = getFieldValByName("systemCreateTimeUtc", metaObject);
        if (createTime == null) {
            setFieldValByName("systemCreateTimeUtc", now, metaObject);
        }
        //老表字段 - 修改时间
        Object updateTime = getFieldValByName("systemUpdateTimeUtc", metaObject);
        if (updateTime == null) {
            setFieldValByName("systemUpdateTimeUtc", now, metaObject);
        }
        
        //新表字段 - 创建人
        Object createUser = getFieldValByName("createUser", metaObject);
        if (createUser == null) {
            setFieldValByName("createUser", username, metaObject);
        }
        //新表字段 - 修改人
        Object updateUser = getFieldValByName("updateUser", metaObject);
        if (updateUser == null) {
            setFieldValByName("updateUser", username, metaObject);
        }
        //新表字段 - 创建时间
        Object newCreateTime = getFieldValByName("createTime", metaObject);
        if (newCreateTime == null) {
            setFieldValByName("createTime", now, metaObject);
        }
        //新表字段 - 修改时间
        Object newUpdateTime = getFieldValByName("updateTime", metaObject);
        if (newUpdateTime == null) {
            setFieldValByName("updateTime", now, metaObject);
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
        
        String username = LoginUtils.getUsername();
        Date now = new Date();
        
        //老表字段
        setFieldValByName("systemUpdateUser", username, metaObject);
        setFieldValByName("systemUpdateTimeUtc", now, metaObject);
        
        //新表字段
        setFieldValByName("updateUser", username, metaObject);
        setFieldValByName("updateTime", now, metaObject);
    }

}
