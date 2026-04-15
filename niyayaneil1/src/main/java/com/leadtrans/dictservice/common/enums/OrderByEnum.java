package com.leadtrans.dictservice.common.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

//排序方式
@Getter
public enum OrderByEnum {
    //数据有效
    ASC("ASC","升序"),
    //数据无效
    DESC("DESC","降序");

    private String code;
    private String name;
    OrderByEnum(String code, String name){
        this.code = code;
        this.name = name;
    }
    public static List<String> getCodes(){
        return Arrays.stream(values()).map(v->v.code).collect(Collectors.toList());
    }

}
