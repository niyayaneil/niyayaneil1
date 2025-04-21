package com.leadtrans.dictservice.common.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Setter
public class PageReqVO implements Serializable {

    @ApiModelProperty(value = "查询页码，默认1",required = true,example = "1",dataType= "int")
    private Integer pageNum;
    @ApiModelProperty(value = "每页条数，默认20",required = true,example = "20",dataType= "int")
    private Integer pageSize;

    @ApiModelProperty(value = "数据排序,格式：[字段名:排序方式(desc/asc)]",example = "systemUpdateTimeUtc:desc",dataType= "List",notes = "字段名:排序方式(desc/asc)")
    private List<String> orderBys;

    public Integer getPageNum() {
        if(this.pageNum==null||this.pageNum<=0){
            this.pageNum = 1;
        }
        return this.pageNum;
    }
    public Integer getPageSize() {
        if(this.pageSize==null||this.pageSize<=0){
            this.pageSize = 20;
        }
        return this.pageSize;
    }

    public List<String> getOrderBys() {
        return orderBys == null?List.of():orderBys;
    }



}
