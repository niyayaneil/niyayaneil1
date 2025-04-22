package com.leadtrans.dictservice.mgr.controller;


import com.leadtrans.dictservice.common.vo.PageResult;
import com.leadtrans.dictservice.common.vo.ResultVO;
import com.leadtrans.dictservice.mgr.controller.vo.GlobalPortAttriPageReqVO;
import com.leadtrans.dictservice.mgr.controller.vo.GlobalPortAttriReqVO;
import com.leadtrans.dictservice.mgr.controller.vo.GlobalPortAttriRespVO;
import com.leadtrans.dictservice.mgr.service.GlobalPortAttriService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>
 * 属性值属性表 前端控制器
 * </p>
 *
 * @author Aaron
 * @since 2025-04-15
 */
@RestController
@RequestMapping("/mgr/globalPortAttriAttris")
@Api(tags = "港口属性值管理接口")
public class GlobalPortAttriController {

    @Autowired
    private GlobalPortAttriService globalPortAttriService;

    // ------------------------ CRUD 操作 ------------------------
    @PostMapping
    @ApiOperation(value = "创建属性值", notes = "需提供属性值详细信息")
    public ResultVO<Long> create(@RequestBody @Valid GlobalPortAttriReqVO VO) {
        Long id = globalPortAttriService.create(VO);
        return ResultVO.success(id);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除属性值", notes = "根据ID物理删除")
    @ApiImplicitParam(name = "id", value = "属性值ID", required = true, paramType = "path")
    public ResultVO<String> delete(@PathVariable Long id) {
        globalPortAttriService.delete(id);
        return ResultVO.success();
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "更新属性值", notes = "全字段更新")
    @ApiImplicitParam(name = "id", value = "属性值ID", required = true, paramType = "path")
    public ResultVO<String> update(@PathVariable Long id,@RequestBody @Valid GlobalPortAttriReqVO VO) {
        globalPortAttriService.update(id, VO);
        return ResultVO.success();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "查询属性值详情", notes = "根据ID获取详细信息")
    @ApiImplicitParam(name = "id", value = "属性值ID", required = true, paramType = "path")
    public ResultVO<GlobalPortAttriRespVO> getById(@PathVariable Long id) {
        GlobalPortAttriRespVO VO = globalPortAttriService.getById(id);
        return ResultVO.success(VO);
    }

    @GetMapping
    @ApiOperation(value = "分页查询属性值", notes = "支持条件筛选")
    public ResultVO<PageResult<GlobalPortAttriRespVO>> page(GlobalPortAttriPageReqVO reqVO) {
        PageResult<GlobalPortAttriRespVO> page = globalPortAttriService.page(reqVO);
        return ResultVO.success(page);
    }

    // ------------------------ 其他业务操作 ------------------------
    @PatchMapping("/{id}/valid")
    @ApiOperation(value = "更新属性值状态", notes = "启用/禁用属性值")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "属性值ID", required = true, paramType = "path"),
            @ApiImplicitParam(name = "isValid", value = "状态：1-启用，0-禁用", required = true, paramType = "query")
    })
    public ResultVO<String> updateValid(
            @PathVariable Long id,
            @RequestParam String isValid) {
        globalPortAttriService.updateValid(id, isValid);
        return ResultVO.success();
    }
}

