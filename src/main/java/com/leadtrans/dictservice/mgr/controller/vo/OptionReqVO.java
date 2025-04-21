package com.leadtrans.dictservice.mgr.controller.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class OptionReqVO {

    @ApiModelProperty(value = "选项类型")
    private Map<String, Map<String,String>> optionTypes;

}
