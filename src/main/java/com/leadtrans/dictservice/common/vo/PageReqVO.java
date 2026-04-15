package com.leadtrans.dictservice.common.vo;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.swagger.annotations.ApiModelProperty;
import lombok.Setter;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Setter
public class PageReqVO implements Serializable {

    @ApiModelProperty(value = "查询页码，默认1",required = true,example = "1",dataType= "int")
    private Integer pageNum;
    @ApiModelProperty(value = "每页条数，默认20",required = true,example = "20",dataType= "int")
    private Integer pageSize;

    @ApiModelProperty(value = "数据排序,格式：[字段名:排序方式(desc/asc)]",example = "systemUpdateTimeUtc:desc",dataType= "List",notes = "字段名:排序方式(desc/asc)")
    @JsonDeserialize(using = OrderBysDeserializer.class)
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

    // 自定义反序列化器，处理字符串和数组格式的orderBys
    public static class OrderBysDeserializer extends JsonDeserializer<List<String>> {
        @Override
        public List<String> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
            List<String> result = new ArrayList<>();
            if (p.currentToken().isScalarValue()) {
                // 处理字符串格式
                result.add(p.getValueAsString());
            } else if (p.currentToken().isStructStart()) {
                // 处理数组格式
                p.nextToken();
                while (!p.currentToken().isStructEnd()) {
                    if (p.currentToken().isScalarValue()) {
                        result.add(p.getValueAsString());
                    }
                    p.nextToken();
                }
            }
            return result;
        }
    }

}

