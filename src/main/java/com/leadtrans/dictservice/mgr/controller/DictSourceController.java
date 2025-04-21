package com.leadtrans.dictservice.mgr.controller;


import com.leadtrans.dictservice.common.vo.PageResult;
import com.leadtrans.dictservice.common.vo.ResultVO;
import com.leadtrans.dictservice.mgr.controller.vo.DictSourcePageReqVO;
import com.leadtrans.dictservice.mgr.controller.vo.DictSourceRespVO;
import com.leadtrans.dictservice.mgr.service.DictSourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Aaron
 * @since 2025-04-15
 */
@RestController
@RequestMapping("/mgr/dictSources")
@Api(tags = "Source管理接口")
public class DictSourceController {

    @Autowired
    private DictSourceService dictSourceService;


    @GetMapping
    @ApiOperation(value = "分页查询Source", notes = "支持条件筛选")
    public ResultVO<PageResult<DictSourceRespVO>> page(DictSourcePageReqVO reqVO) {
        PageResult<DictSourceRespVO> page = dictSourceService.page(reqVO);
        return ResultVO.success(page);
    }

}

