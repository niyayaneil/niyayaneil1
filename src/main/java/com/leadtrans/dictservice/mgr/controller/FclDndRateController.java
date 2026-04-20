package com.leadtrans.dictservice.mgr.controller;

import com.leadtrans.dictservice.common.vo.PageResult;
import com.leadtrans.dictservice.common.vo.ResultVO;
import com.leadtrans.dictservice.mgr.controller.vo.FclDndRatePageReqVO;
import com.leadtrans.dictservice.mgr.controller.vo.FclDndRateReqVO;
import com.leadtrans.dictservice.mgr.controller.vo.FclDndRateRespVO;
import com.leadtrans.dictservice.mgr.service.FclDndRateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/mgr/fclDndRates")
@Api(tags = "FCL DND费率管理接口")
public class FclDndRateController {

    @Autowired
    private FclDndRateService fclDndRateService;

    // ------------------------ CRUD 操作 ------------------------
    @PostMapping
    @ApiOperation(value = "创建DND费率", notes = "需提供船公司、费用类型等信息")
    public ResultVO<Long> create(@RequestBody @Valid FclDndRateReqVO VO) {
        Long id = fclDndRateService.create(VO);
        return ResultVO.success(id);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除DND费率", notes = "根据ID物理删除")
    @ApiImplicitParam(name = "id", value = "费率ID", required = true, paramType = "path")
    public ResultVO<String> delete(@PathVariable Long id) {
        fclDndRateService.delete(id);
        return ResultVO.success();
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "更新DND费率", notes = "全字段更新")
    @ApiImplicitParam(name = "id", value = "费率ID", required = true, paramType = "path")
    public ResultVO<String> update(@PathVariable Long id, @RequestBody FclDndRateReqVO VO) {
        fclDndRateService.update(id, VO);
        return ResultVO.success();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "查询DND费率详情", notes = "根据ID获取详细信息")
    @ApiImplicitParam(name = "id", value = "费率ID", required = true, paramType = "path")
    public ResultVO<FclDndRateRespVO> getById(@PathVariable Long id) {
        FclDndRateRespVO VO = fclDndRateService.getById(id);
        return ResultVO.success(VO);
    }

    @PostMapping("/page")
    @ApiOperation(value = "分页查询DND费率", notes = "支持条件筛选")
    public ResultVO<PageResult<FclDndRateRespVO>> page(@RequestBody FclDndRatePageReqVO reqVO) {
        PageResult<FclDndRateRespVO> page = fclDndRateService.page(reqVO);
        return ResultVO.success(page);
    }

    // ------------------------ 其他业务操作 ------------------------
    @PatchMapping("/{id}/valid")
    @ApiOperation(value = "更新DND费率状态", notes = "启用/禁用费率")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "费率ID", required = true, paramType = "path"),
            @ApiImplicitParam(name = "isValid", value = "状态：1-启用，0-禁用", required = true, paramType = "query")
    })
    public ResultVO<String> updateValid(
            @PathVariable Long id,
            @RequestParam Integer isValid) {
        fclDndRateService.updateValid(id, isValid);
        return ResultVO.success();
    }
}