package com.leadtrans.dictservice.mgr.controller;


import com.leadtrans.dictservice.common.vo.PageResult;
import com.leadtrans.dictservice.common.vo.ResultVO;
import com.leadtrans.dictservice.mgr.controller.vo.ReadMePageReqVO;
import com.leadtrans.dictservice.mgr.controller.vo.ReadMeReqVO;
import com.leadtrans.dictservice.mgr.controller.vo.ReadMeRespVO;
import com.leadtrans.dictservice.mgr.service.ReadMeService;
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
@RequestMapping("/mgr/readMes")
@Api(tags = "帮助文档管理接口")
public class ReadMeController {

    @Autowired
    private ReadMeService readMeService;

    // ------------------------ CRUD 操作 ------------------------
    @PostMapping
    @ApiOperation(value = "创建帮助文档", notes = "需提供帮助文档详细信息")
    public ResultVO<Long> create(@RequestBody @Valid ReadMeReqVO VO) {
        Long id = readMeService.create(VO);
        return ResultVO.success(id);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除帮助文档", notes = "根据ID物理删除")
    @ApiImplicitParam(name = "id", value = "帮助文档ID", required = true, paramType = "path")
    public ResultVO<String> delete(@PathVariable Long id) {
        readMeService.delete(id);
        return ResultVO.success();
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "更新帮助文档", notes = "全字段更新")
    @ApiImplicitParam(name = "id", value = "帮助文档ID", required = true, paramType = "path")
    public ResultVO<String> update(@PathVariable Long id,@RequestBody @Valid ReadMeReqVO VO) {
        readMeService.update(id, VO);
        return ResultVO.success();
    }

    @GetMapping("/{code}")
    @ApiOperation(value = "查询帮助文档详情", notes = "根据Code获取详细信息")
    @ApiImplicitParam(name = "code", value = "帮助文档Code", required = true, paramType = "path")
    public ResultVO<ReadMeRespVO> getByCode(@PathVariable String code) {
        ReadMeRespVO VO = readMeService.getByCode(code);
        return ResultVO.success(VO);
    }

    @GetMapping
    @ApiOperation(value = "分页查询帮助文档", notes = "支持条件筛选")
    public ResultVO<PageResult<ReadMeRespVO>> page(ReadMePageReqVO reqVO) {
        PageResult<ReadMeRespVO> page = readMeService.page(reqVO);
        return ResultVO.success(page);
    }

    // ------------------------ 其他业务操作 ------------------------
    @PatchMapping("/{id}/valid")
    @ApiOperation(value = "更新帮助文档状态", notes = "启用/禁用帮助文档")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "帮助文档ID", required = true, paramType = "path"),
            @ApiImplicitParam(name = "isValid", value = "状态：1-启用，0-禁用", required = true, paramType = "query")
    })
    public ResultVO<String> updateValid(
            @PathVariable Long id,
            @RequestParam String isValid) {
        readMeService.updateValid(id, isValid);
        return ResultVO.success();
    }
}