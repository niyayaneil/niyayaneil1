package com.leadtrans.dictservice.mgr.controller.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OptionRespVO<T> {

    @ApiModelProperty(value = "选项值")
    private T value;
    @ApiModelProperty(value = "显示名")
    private String label;


}
