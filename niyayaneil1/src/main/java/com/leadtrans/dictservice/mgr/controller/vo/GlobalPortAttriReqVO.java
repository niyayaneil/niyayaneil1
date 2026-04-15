package com.leadtrans.dictservice.mgr.controller.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

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
@ApiModel(value = "属性值新增对象", description = "用于新增和保存属性值")
public class GlobalPortAttriReqVO extends BaseReqVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "Type")
    @NotBlank
    @Size(max = 32, message = "Type must be at most 32 characters")
    private String type;

    @ApiModelProperty(value = "Code")
    @NotBlank
    @Size(max = 32, message = "Code must be at most 32 characters")
    private String code;

    @ApiModelProperty(value = "Name")
    @NotBlank
    @Size(max = 32, message = "Name must be at most 32 characters")
    private String name;


}
