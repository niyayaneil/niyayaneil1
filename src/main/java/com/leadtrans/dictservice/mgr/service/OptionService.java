package com.leadtrans.dictservice.mgr.service;

import com.leadtrans.dictservice.mgr.controller.vo.OptionRespVO;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface OptionService {

    Map<String, List<OptionRespVO>> getOptions(Map<String, Map<String,String>> reqVO);

    Collection<String> getRegisters();
}
