package com.leadtrans.dictservice.common.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderByVO {

    @ApiModelProperty(value = "排序字段",required = true,example = "systemUpdateTimeUtc",dataType= "String")
    private String orderField;

    @ApiModelProperty(value = "排序方式",required = true,example = "DESC",dataType= "String",notes = "ASC/DESC")
    private String orderType;
}
