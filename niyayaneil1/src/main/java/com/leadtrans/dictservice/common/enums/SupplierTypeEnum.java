package com.leadtrans.dictservice.common.enums;

import lombok.Getter;

@Getter
public enum SupplierTypeEnum {
    LCLAddPrice("LCLAddPrice","LCLAddPrice"),
    DeparturePortWarehouse("DeparturePortWarehouse","DeparturePortWarehouse"),
    DestinationPortWarehouse("DestinationPortWarehouse","DestinationPortWarehouse");

    private String code;
    private String name;
    SupplierTypeEnum(String code,String name){
        this.code = code;
        this.name = name;
    }

}