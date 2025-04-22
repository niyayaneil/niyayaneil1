package com.leadtrans.dictservice.mgr.controller.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
@ApiModel(value = "帮助文档新增对象", description = "用于新增和保存帮助文档")
public class ReadMeReqVO extends BaseReqVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "标题")
    @NotBlank
    @Size(max = 128, message = "Title must be at most 128 characters")
    private String title;

    @ApiModelProperty(value = "代码")
    @NotBlank
    @Size(max = 128, message = "Code must be at most 128 characters")
    private String code;

    @ApiModelProperty(value = "内容")
    @NotBlank
    private String content;

    @ApiModelProperty(value = "类别")
    @NotNull
    private Integer type;

}
