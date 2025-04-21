package com.leadtrans.dictservice.mgr.controller.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Aaron
 * @since 2025-04-15
 */
@Setter
@Getter
@ApiModel(value = "基本对象", description = "")
public class BaseReqVO implements Serializable {
    @ApiModelProperty(value = "是否可用：1可用，0不可用")
    @Pattern(regexp = "1|0", message = "Status must be Invalid or Valid.")
    private String isValid;

}
