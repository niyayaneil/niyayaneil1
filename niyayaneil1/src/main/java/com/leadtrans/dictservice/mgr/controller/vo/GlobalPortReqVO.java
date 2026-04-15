package com.leadtrans.dictservice.mgr.controller.vo;

import com.leadtrans.dictservice.common.utils.NumberUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Aaron
 * @since 2025-04-22
 */
@Setter
@Getter
@ApiModel(value = "港口新增对象", description = "用于新增和保存港口")
public class GlobalPortReqVO extends BaseReqVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "港口五字码")
    @NotBlank
    @Size(min = 5,max = 5, message = "Standard port code must be exactly 5 characters")
    private String code;

    @ApiModelProperty(value = "港口中文名")
    @NotBlank
    @Size(max = 64, message = "Chinese name must be at most 64 characters")
    private String nameCn;

    @ApiModelProperty(value = "港口英文名")
    @NotBlank
    @Size(max = 64, message = "English name must be at most 64 characters")
    private String nameEn;

    @NotBlank
    @ApiModelProperty(value = "国家编码")
    private String countryCode;

    @ApiModelProperty(value = "省份或州编码")
    private String stateCode;

    @ApiModelProperty(value = "城市编码")
    private String cityCode;

    @ApiModelProperty(value = "区域ID")
    private Long globalAreaId;

    @ApiModelProperty(value = "组名")
    private String groupName;

    @ApiModelProperty(value = "港口显示名")
    private String portFullName;

    public String getPortFullName() {
        if(StringUtils.hasText(stateCode) && !NumberUtils.isNumber(stateCode) && stateCode.length()==2){
            portFullName = this.nameEn +","+stateCode;
        }else{
            portFullName = this.nameEn;
        }
        return portFullName;
    }

}
