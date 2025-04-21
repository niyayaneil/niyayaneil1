package com.leadtrans.dictservice.common.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

//数据记录是否有效
@Getter
public enum StatusEnum {
    //数据有效
    valid("1","Valid"),
    //数据无效
    invalid("0","Invalid");

    private String code;
    private String name;
    StatusEnum(String code, String name){
        this.code = code;
        this.name = name;
    }
    public static List<String> getCodes(){
        return Arrays.stream(values()).map(v->v.code).collect(Collectors.toList());
    }

}
