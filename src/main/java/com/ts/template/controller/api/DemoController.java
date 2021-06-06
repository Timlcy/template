//package com.dfdk.smartSafetyTools.controller.api;
//
//
//import com.baomidou.mybatisplus.core.metadata.IPage;
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import com.dfdk.smartSafetyTools.entity.model.SwineTool;
//import com.dfdk.smartSafetyTools.entity.vo.ToolsEquipmentVo;
//import com.dfdk.smartSafetyTools.service.SwineToolService;
//import io.swagger.annotations.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//import java.util.Map;
//
///***
// * DemoController相关Controller
// * @FileName DemoController
// * @Company
// * @author MJH
// * @Date 2019年11月21日
// * @version 1.0.0
// * @remark
// * @explain
// *
// */
//@Api(tags = {"api接口demo"})
//@RestController
//@RequestMapping("/api/")
//public class DemoController {
//
//    @Autowired
//    private SwineToolService swineToolService;
//
//
//    @PostMapping("/getAnaDataByDate")
//    @ApiOperation(value = "demo集合", notes = "demo集合")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "id", value = "点值", dataTypeClass = String.class),
//            @ApiImplicitParam(name = "startDate", value = "开始时间", dataTypeClass = String.class),
//            @ApiImplicitParam(name = "endDate", value = "结束时间", dataTypeClass = String.class)
//    })
//    public List<SwineTool> getAnaDataByDate(String id, String startDate, String endDate) {
//        return swineToolService.list();
//    }
//
//    @PostMapping(value = "/findSafetytoolsStatus")
//    @ApiOperation(value = "demo分页", notes = "demo分页")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "deviceId", value = "设备编号", dataTypeClass = String.class),
//            @ApiImplicitParam(name = "pageNum", value = "页码", defaultValue = "1", dataTypeClass =
//                    int.class, required = true),
//            @ApiImplicitParam(name = "pageSize", value = "条数", defaultValue = "10",
//                    dataTypeClass = int.class, required = true)
//    })
//    public IPage<SwineTool> findSafetytoolsStatus(String deviceId, int pageNum,
//                                                  int pageSize) {
//        IPage<SwineTool> iPage = new Page<>(pageNum, pageSize);
//        return swineToolService.page(iPage);
//    }
//
//    @PostMapping(value = "/getCount")
//    @ApiOperation(value = "demo联合查询数量", notes = "demo联合查询数量")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "deviceId", value = "设备编号", dataTypeClass = String.class)
//    })
//    public Map<String, String> getCount(String deviceId) {
////        return swineToolService.getCount();
//        return null;
//    }
//    @PostMapping(value = "/saveToolScrappedRegistration")
//    @ApiOperation(value = "报废登记", notes = "报废登记")
//    public boolean saveToolScrappedRegistration(@RequestBody ScrappedRegistrationVo defectRegistrationVo) {
//        return swinePlanjobService.saveToolScrappedRegistration(defectRegistrationVo);
//    }
//
//
//}
