package com.leadtrans.dictservice.common.vo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class PageResult<T> {
    private List<T> list;
    private long total;
    private long pageNum;
    private long pageSize;
    private long pageCount;

    public PageResult(){

    }
    public PageResult(IPage iPage,List<T> list){
        this.pageNum = iPage.getCurrent();
        this.pageSize = iPage.getSize();
        this.pageCount = iPage.getPages();
        this.total = iPage.getTotal();
        this.list = list;

    }
}
