package com.leadtrans.dictservice.mgr.controller;


import com.leadtrans.dictservice.common.vo.PageResult;
import com.leadtrans.dictservice.common.vo.ResultVO;
import com.leadtrans.dictservice.mgr.controller.vo.DictCityRespVO;
import com.leadtrans.dictservice.mgr.controller.vo.DictCityPageReqVO;
import com.leadtrans.dictservice.mgr.controller.vo.DictCityReqVO;
import com.leadtrans.dictservice.mgr.service.DictCityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Aaron
 * @since 2025-04-15
 */
@RestController
@RequestMapping("/mgr/dictCities")
@Api(tags = "城市管理接口")
public class DictCityController {

    @Autowired
    private DictCityService dictCityService;

    // ------------------------ CRUD 操作 ------------------------
    @PostMapping
    @ApiOperation(value = "创建城市", notes = "需提供城市详细信息")
    public ResultVO<Long> create(@RequestBody @Valid DictCityReqVO VO) {
        Long id = dictCityService.create(VO);
        return ResultVO.success(id);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除城市", notes = "根据ID物理删除")
    @ApiImplicitParam(name = "id", value = "城市ID", required = true, paramType = "path")
    public ResultVO<String> delete(@PathVariable Long id) {
        dictCityService.delete(id);
        return ResultVO.success();
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "更新城市", notes = "全字段更新")
    @ApiImplicitParam(name = "id", value = "城市ID", required = true, paramType = "path")
    public ResultVO<String> update(@PathVariable Long id,@RequestBody @Valid DictCityReqVO VO) {
        dictCityService.update(id, VO);
        return ResultVO.success();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "查询城市详情", notes = "根据ID获取详细信息")
    @ApiImplicitParam(name = "id", value = "城市ID", required = true, paramType = "path")
    public ResultVO<DictCityRespVO> getById(@PathVariable Long id) {
        DictCityRespVO VO = dictCityService.getById(id);
        return ResultVO.success(VO);
    }

    @GetMapping
    @ApiOperation(value = "分页查询城市", notes = "支持条件筛选")
    public ResultVO<PageResult<DictCityRespVO>> page(DictCityPageReqVO reqVO) {
        PageResult<DictCityRespVO> page = dictCityService.page(reqVO);
        return ResultVO.success(page);
    }

    // ------------------------ 其他业务操作 ------------------------
    @PatchMapping("/{id}/valid")
    @ApiOperation(value = "更新城市状态", notes = "启用/禁用城市")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "城市ID", required = true, paramType = "path"),
            @ApiImplicitParam(name = "isValid", value = "状态：1-启用，0-禁用", required = true, paramType = "query")
    })
    public ResultVO<String> updateValid(
            @PathVariable Long id,
            @RequestParam String isValid) {
        dictCityService.updateValid(id, isValid);
        return ResultVO.success();
    }
}

