import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.dfdk.smartSafetyTools.entity.model.SwineClaimoccupation;
import com.dfdk.smartSafetyTools.entity.vo.ToolsEquipmentVo;
import com.ts.template.utils.IdFactory;
import com.google.common.base.CharMatcher;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName TestUUID
 * @Description 测试UUID
 * @Author 林春永
 * @Date 2021/4/25
 * @Version 1.0
 **/
public class TestUtils {

    @Test
    public void testUUID() {
        String s = IdUtil.simpleUUID();
        System.out.println(s);
    }


    @Test
    public void getSYSTime() {
        System.out.println(System.currentTimeMillis());

    }


    @Test
    public void getTime() {
        System.out.println(Long.valueOf("1620788842000"));

    }

    @Test
    public void fenzu() {
        List<ToolsEquipmentVo> toolsEquipmentRequisitionList = new ArrayList<>();

        ToolsEquipmentVo toolsEquipmentVo = new ToolsEquipmentVo();
        toolsEquipmentVo.setAffiliationId("第一所");
        toolsEquipmentVo.setToolsCategoryId("第一分类");
        toolsEquipmentVo.setPackageId("第一包");

        ToolsEquipmentVo toolsEquipmentVo2 = new ToolsEquipmentVo();
        toolsEquipmentVo2.setAffiliationId("第二所");
        toolsEquipmentVo2.setToolsCategoryId("第一分类");
        toolsEquipmentVo2.setPackageId("第一包");

        ToolsEquipmentVo toolsEquipmentVo3 = new ToolsEquipmentVo();
        toolsEquipmentVo3.setAffiliationId("第二所");
        toolsEquipmentVo3.setToolsCategoryId("第三分类");
        toolsEquipmentVo3.setPackageId("第四包");

        ToolsEquipmentVo toolsEquipmentVo4 = new ToolsEquipmentVo();
        toolsEquipmentVo4.setAffiliationId("第二所");
        toolsEquipmentVo4.setToolsCategoryId("第一分类");
        toolsEquipmentVo4.setPackageId("第一包");

        toolsEquipmentRequisitionList.add(toolsEquipmentVo);
        toolsEquipmentRequisitionList.add(toolsEquipmentVo2);
        toolsEquipmentRequisitionList.add(toolsEquipmentVo3);
        toolsEquipmentRequisitionList.add(toolsEquipmentVo4);


        Map<String, Long> countMap =
                toolsEquipmentRequisitionList.stream().collect(Collectors.groupingBy
                        (o -> o.getAffiliationId() + "_" + o.getToolsCategoryId() + "_" + o.getPackageId(), Collectors.counting()));
        List<SwineClaimoccupation> countRecords = countMap.keySet().stream().map(key -> {
            String[] temp = key.split("_");
            String affiliationId = temp[0];
            String toolsCategoryId = temp[1];
            String packageId = temp[2];

            SwineClaimoccupation swineClaimoccupation = new SwineClaimoccupation();
            swineClaimoccupation.setSubareaId(affiliationId);
            swineClaimoccupation.setTooltypeId(toolsCategoryId);
            swineClaimoccupation.setPackageId(packageId);
            swineClaimoccupation.setTime(System.currentTimeMillis());
            swineClaimoccupation.setNumber(countMap.get(key).intValue());
            return swineClaimoccupation;
        }).collect(Collectors.toList());

        System.out.println(countRecords);
    }

    @Test
    public void t1() {
        String params = "{\"jobId\":\"1018466\",\"recipientsId\":\"123\",\"returnDate\":\"\"," +
                "\"toolsEquipmentRequisitionList\":[{\"packageId\":\"7\",\"packageName\":\"D包\"," +
                "\"name\":\"万用表\",\"nameId\":\"7\",\"specificationModel\":null," +
                "\"toolsCategory\":\"万用表\"," +
                "\"toolsCategoryId\":\"1018384\",\"voltageLevel\":null,\"voltageLevelId\":null," +
                "\"affiliation\":\"东镇供电所\",\"affiliationId\":\"1018082\"," +
                "\"applicationNumber\":\"1\"," +
                "\"inventoryQuantity\":\"1\",\"claimedQuantity\":null},{\"packageId\":\"7\"," +
                "\"packageName\":\"D包\",\"name\":\"兆欧表\",\"nameId\":\"6\"," +
                "\"specificationModel\":\"5000V\",\"toolsCategory\":\"兆欧表\"," +
                "\"toolsCategoryId\":\"1018384\",\"voltageLevel\":null,\"voltageLevelId\":null," +
                "\"affiliation\":\"东镇供电所\",\"affiliationId\":\"1018082\"," +
                "\"applicationNumber\":\"1\"," +
                "\"inventoryQuantity\":\"1\",\"claimedQuantity\":null},{\"packageId\":\"7\"," +
                "\"packageName\":\"D包\",\"name\":\"升降板\",\"nameId\":\"15\"," +
                "\"specificationModel\":null," +
                "\"toolsCategory\":\"升降板\",\"toolsCategoryId\":\"1018385\",\"voltageLevel\":null," +
                "\"voltageLevelId\":null,\"affiliation\":\"东镇供电所\",\"affiliationId\":\"1018082\"," +
                "\"applicationNumber\":\"1\",\"inventoryQuantity\":\"1\"," +
                "\"claimedQuantity\":null}," +
                "{\"packageId\":\"7\",\"packageName\":\"D包\",\"name\":\"卡线器\",\"nameId\":\"17\"," +
                "\"specificationModel\":null,\"toolsCategory\":\"卡线器\"," +
                "\"toolsCategoryId\":\"1018386\"," +
                "\"voltageLevel\":null,\"voltageLevelId\":null,\"affiliation\":\"东镇供电所\"," +
                "\"affiliationId\":\"1018082\",\"applicationNumber\":\"1\"," +
                "\"inventoryQuantity\":\"1\"," +
                "\"claimedQuantity\":null},{\"packageId\":\"7\",\"packageName\":\"D包\"," +
                "\"name\":\"卸扣\"," +
                "\"nameId\":\"16\",\"specificationModel\":null,\"toolsCategory\":\"卸扣\"," +
                "\"toolsCategoryId\":\"1018386\",\"voltageLevel\":null,\"voltageLevelId\":null," +
                "\"affiliation\":\"东镇供电所\",\"affiliationId\":\"1018082\"," +
                "\"applicationNumber\":\"1\"," +
                "\"inventoryQuantity\":\"1\",\"claimedQuantity\":null},{\"packageId\":\"7\"," +
                "\"packageName\":\"D包\",\"name\":\"压线钳\",\"nameId\":\"20\"," +
                "\"specificationModel\":\"电动\"," +
                "\"toolsCategory\":\"压线钳\",\"toolsCategoryId\":\"1018386\",\"voltageLevel\":null," +
                "\"voltageLevelId\":null,\"affiliation\":\"东镇供电所\",\"affiliationId\":\"1018082\"," +
                "\"applicationNumber\":\"1\",\"inventoryQuantity\":\"1\"," +
                "\"claimedQuantity\":null}," +
                "{\"packageId\":\"7\",\"packageName\":\"D包\",\"name\":\"吊物绳\",\"nameId\":\"29\"," +
                "\"specificationModel\":null,\"toolsCategory\":\"吊物绳\"," +
                "\"toolsCategoryId\":\"1018386\"," +
                "\"voltageLevel\":null,\"voltageLevelId\":null,\"affiliation\":\"东镇供电所\"," +
                "\"affiliationId\":\"1018082\",\"applicationNumber\":\"1\"," +
                "\"inventoryQuantity\":\"1\"," +
                "\"claimedQuantity\":null},{\"packageId\":\"7\",\"packageName\":\"D包\"," +
                "\"name\":\"后备保护钢丝绳\",\"nameId\":\"19\",\"specificationModel\":null," +
                "\"toolsCategory\":\"后备保护钢丝绳\",\"toolsCategoryId\":\"1018386\"," +
                "\"voltageLevel\":null," +
                "\"voltageLevelId\":null,\"affiliation\":\"东镇供电所\",\"affiliationId\":\"1018082\"," +
                "\"applicationNumber\":\"1\",\"inventoryQuantity\":\"1\"," +
                "\"claimedQuantity\":null}," +
                "{\"packageId\":\"7\",\"packageName\":\"D包\",\"name\":\"套筒板手\",\"nameId\":\"28\"," +
                "\"specificationModel\":null,\"toolsCategory\":\"套筒板手\"," +
                "\"toolsCategoryId\":\"1018386\"," +
                "\"voltageLevel\":null,\"voltageLevelId\":null,\"affiliation\":\"东镇供电所\"," +
                "\"affiliationId\":\"1018082\",\"applicationNumber\":\"1\"," +
                "\"inventoryQuantity\":\"1\"," +
                "\"claimedQuantity\":null},{\"packageId\":\"7\",\"packageName\":\"D包\"," +
                "\"name\":\"安全围栏\"," +
                "\"nameId\":\"31\",\"specificationModel\":null,\"toolsCategory\":\"安全围栏\"," +
                "\"toolsCategoryId\":\"1018388\",\"voltageLevel\":null,\"voltageLevelId\":null," +
                "\"affiliation\":\"东镇供电所\",\"affiliationId\":\"1018082\"," +
                "\"applicationNumber\":\"1\"," +
                "\"inventoryQuantity\":\"1\",\"claimedQuantity\":null},{\"packageId\":\"7\"," +
                "\"packageName\":\"D包\",\"name\":\"安全带\",\"nameId\":\"13\"," +
                "\"specificationModel\":null," +
                "\"toolsCategory\":\"安全带\",\"toolsCategoryId\":\"1018385\",\"voltageLevel\":null," +
                "\"voltageLevelId\":null,\"affiliation\":\"东镇供电所\",\"affiliationId\":\"1018082\"," +
                "\"applicationNumber\":\"1\",\"inventoryQuantity\":\"1\"," +
                "\"claimedQuantity\":null}," +
                "{\"packageId\":\"7\",\"packageName\":\"D包\",\"name\":\"手锤\",\"nameId\":\"24\"," +
                "\"specificationModel\":null,\"toolsCategory\":\"手锤\"," +
                "\"toolsCategoryId\":\"1018386\"," +
                "\"voltageLevel\":null,\"voltageLevelId\":null,\"affiliation\":\"东镇供电所\"," +
                "\"affiliationId\":\"1018082\",\"applicationNumber\":\"1\"," +
                "\"inventoryQuantity\":\"1\"," +
                "\"claimedQuantity\":null},{\"packageId\":\"7\",\"packageName\":\"D包\"," +
                "\"name\":\"接地线\"," +
                "\"nameId\":\"1\",\"specificationModel\":\"10kV接地线\",\"toolsCategory\":\"接地线\"," +
                "\"toolsCategoryId\":\"1018383\",\"voltageLevel\":\"10kV\"," +
                "\"voltageLevelId\":\"10000\"," +
                "\"affiliation\":\"东镇供电所\",\"affiliationId\":\"1018082\"," +
                "\"applicationNumber\":\"2\"," +
                "\"inventoryQuantity\":\"2\",\"claimedQuantity\":null},{\"packageId\":\"7\"," +
                "\"packageName\":\"D包\",\"name\":\"接地线\",\"nameId\":\"1\"," +
                "\"specificationModel\":\"0" +
                ".4kV接地线\",\"toolsCategory\":\"接地线\",\"toolsCategoryId\":\"1018383\"," +
                "\"voltageLevel\":\"400V\",\"voltageLevelId\":\"400\",\"affiliation\":\"东镇供电所\"," +
                "\"affiliationId\":\"1018082\",\"applicationNumber\":\"1\"," +
                "\"inventoryQuantity\":\"2\"," +
                "\"claimedQuantity\":null},{\"packageId\":\"7\",\"packageName\":\"D包\"," +
                "\"name\":\"撬棍\"," +
                "\"nameId\":\"25\",\"specificationModel\":null,\"toolsCategory\":\"撬棍\"," +
                "\"toolsCategoryId\":\"1018386\",\"voltageLevel\":null,\"voltageLevelId\":null," +
                "\"affiliation\":\"东镇供电所\",\"affiliationId\":\"1018082\"," +
                "\"applicationNumber\":\"1\"," +
                "\"inventoryQuantity\":\"1\",\"claimedQuantity\":null},{\"packageId\":\"7\"," +
                "\"packageName\":\"D包\",\"name\":\"滑车\",\"nameId\":\"22\"," +
                "\"specificationModel\":null," +
                "\"toolsCategory\":\"滑车\",\"toolsCategoryId\":\"1018386\",\"voltageLevel\":null," +
                "\"voltageLevelId\":null,\"affiliation\":\"东镇供电所\",\"affiliationId\":\"1018082\"," +
                "\"applicationNumber\":\"1\",\"inventoryQuantity\":\"1\"," +
                "\"claimedQuantity\":null}," +
                "{\"packageId\":\"7\",\"packageName\":\"D包\",\"name\":\"照明灯具\",\"nameId\":\"30\"," +
                "\"specificationModel\":null,\"toolsCategory\":\"照明灯具\"," +
                "\"toolsCategoryId\":\"1018387\"," +
                "\"voltageLevel\":null,\"voltageLevelId\":null,\"affiliation\":\"东镇供电所\"," +
                "\"affiliationId\":\"1018082\",\"applicationNumber\":\"1\"," +
                "\"inventoryQuantity\":\"1\"," +
                "\"claimedQuantity\":null},{\"packageId\":\"7\",\"packageName\":\"D包\"," +
                "\"name\":\"电线剪\"," +
                "\"nameId\":\"21\",\"specificationModel\":null,\"toolsCategory\":\"电线剪\"," +
                "\"toolsCategoryId\":\"1018386\",\"voltageLevel\":null,\"voltageLevelId\":null," +
                "\"affiliation\":\"东镇供电所\",\"affiliationId\":\"1018082\"," +
                "\"applicationNumber\":\"1\"," +
                "\"inventoryQuantity\":\"1\",\"claimedQuantity\":null},{\"packageId\":\"7\"," +
                "\"packageName\":\"D包\",\"name\":\"紧线器\",\"nameId\":\"18\"," +
                "\"specificationModel\":null," +
                "\"toolsCategory\":\"紧线器\",\"toolsCategoryId\":\"1018386\",\"voltageLevel\":null," +
                "\"voltageLevelId\":null,\"affiliation\":\"东镇供电所\",\"affiliationId\":\"1018082\"," +
                "\"applicationNumber\":\"1\",\"inventoryQuantity\":\"1\"," +
                "\"claimedQuantity\":null}," +
                "{\"packageId\":\"7\",\"packageName\":\"D包\",\"name\":\"绝缘手套\",\"nameId\":\"4\"," +
                "\"specificationModel\":\"10kV绝缘手套\",\"toolsCategory\":\"绝缘手套\"," +
                "\"toolsCategoryId\":\"1018383\",\"voltageLevel\":\"10kV\"," +
                "\"voltageLevelId\":\"10000\"," +
                "\"affiliation\":\"东镇供电所\",\"affiliationId\":\"1018082\"," +
                "\"applicationNumber\":\"1\"," +
                "\"inventoryQuantity\":\"2\",\"claimedQuantity\":null},{\"packageId\":\"7\"," +
                "\"packageName\":\"D包\",\"name\":\"绝缘手套\",\"nameId\":\"4\"," +
                "\"specificationModel\":\"0" +
                ".4kV绝缘手套\",\"toolsCategory\":\"绝缘手套\",\"toolsCategoryId\":\"1018383\"," +
                "\"voltageLevel\":\"400V\",\"voltageLevelId\":\"400\",\"affiliation\":\"东镇供电所\"," +
                "\"affiliationId\":\"1018082\",\"applicationNumber\":\"1\"," +
                "\"inventoryQuantity\":\"2\"," +
                "\"claimedQuantity\":null},{\"packageId\":\"7\",\"packageName\":\"D包\"," +
                "\"name\":\"绝缘棒\"," +
                "\"nameId\":\"2\",\"specificationModel\":\"10kV绝缘棒\",\"toolsCategory\":\"绝缘棒\"," +
                "\"toolsCategoryId\":\"1018383\",\"voltageLevel\":\"10kV\"," +
                "\"voltageLevelId\":\"10000\"," +
                "\"affiliation\":\"东镇供电所\",\"affiliationId\":\"1018082\"," +
                "\"applicationNumber\":\"1\"," +
                "\"inventoryQuantity\":\"1\",\"claimedQuantity\":null},{\"packageId\":\"7\"," +
                "\"packageName\":\"D包\",\"name\":\"绝缘靴\",\"nameId\":\"3\"," +
                "\"specificationModel\":\"10kV绝缘靴\",\"toolsCategory\":\"绝缘靴\"," +
                "\"toolsCategoryId\":\"1018383\",\"voltageLevel\":\"10kV\"," +
                "\"voltageLevelId\":\"10000\"," +
                "\"affiliation\":\"东镇供电所\",\"affiliationId\":\"1018082\"," +
                "\"applicationNumber\":\"1\"," +
                "\"inventoryQuantity\":\"1\",\"claimedQuantity\":null},{\"packageId\":\"7\"," +
                "\"packageName\":\"D包\",\"name\":\"脚扣\",\"nameId\":\"14\"," +
                "\"specificationModel\":null," +
                "\"toolsCategory\":\"脚扣\",\"toolsCategoryId\":\"1018385\",\"voltageLevel\":null," +
                "\"voltageLevelId\":null,\"affiliation\":\"东镇供电所\",\"affiliationId\":\"1018082\"," +
                "\"applicationNumber\":\"1\",\"inventoryQuantity\":\"1\"," +
                "\"claimedQuantity\":null}," +
                "{\"packageId\":\"7\",\"packageName\":\"D包\",\"name\":\"铁铲\",\"nameId\":\"26\"," +
                "\"specificationModel\":null,\"toolsCategory\":\"铁铲\"," +
                "\"toolsCategoryId\":\"1018386\"," +
                "\"voltageLevel\":null,\"voltageLevelId\":null,\"affiliation\":\"东镇供电所\"," +
                "\"affiliationId\":\"1018082\",\"applicationNumber\":\"1\"," +
                "\"inventoryQuantity\":\"1\"," +
                "\"claimedQuantity\":null},{\"packageId\":\"7\",\"packageName\":\"D包\"," +
                "\"name\":\"铁锹\"," +
                "\"nameId\":\"27\",\"specificationModel\":null,\"toolsCategory\":\"铁锹\"," +
                "\"toolsCategoryId\":\"1018386\",\"voltageLevel\":null,\"voltageLevelId\":null," +
                "\"affiliation\":\"东镇供电所\",\"affiliationId\":\"1018082\"," +
                "\"applicationNumber\":\"1\"," +
                "\"inventoryQuantity\":\"1\",\"claimedQuantity\":null},{\"packageId\":\"7\"," +
                "\"packageName\":\"D包\",\"name\":\"链条葫芦\",\"nameId\":\"23\"," +
                "\"specificationModel\":null," +
                "\"toolsCategory\":\"链条葫芦\",\"toolsCategoryId\":\"1018386\"," +
                "\"voltageLevel\":null," +
                "\"voltageLevelId\":null,\"affiliation\":\"东镇供电所\",\"affiliationId\":\"1018082\"," +
                "\"applicationNumber\":\"1\",\"inventoryQuantity\":\"1\"," +
                "\"claimedQuantity\":null}," +
                "{\"packageId\":\"7\",\"packageName\":\"D包\",\"name\":\"验电器\",\"nameId\":\"5\"," +
                "\"specificationModel\":\"10kV验电器\",\"toolsCategory\":\"验电器\"," +
                "\"toolsCategoryId\":\"1018383\",\"voltageLevel\":\"10kV\"," +
                "\"voltageLevelId\":\"10000\"," +
                "\"affiliation\":\"东镇供电所\",\"affiliationId\":\"1018082\"," +
                "\"applicationNumber\":\"1\"," +
                "\"inventoryQuantity\":\"1\",\"claimedQuantity\":null}]}";
        String jsonString = CharMatcher.breakingWhitespace().removeFrom(params);
        JSONObject jsonObject = JSONUtil.parseObj(jsonString);

        String jobId = jsonObject.getStr("jobId");
        String recipientsId = jsonObject.getStr("recipientsId");
        String returnDate = jsonObject.getStr("returnDate");

        JSONArray jsonArray = JSONUtil.parseArray(jsonObject.get(
                "toolsEquipmentRequisitionList"));

        List<ToolsEquipmentVo> toolsEquipmentRequisitionList = new ArrayList<>();
        for (Object o : jsonArray) {
            JSONObject jo = (JSONObject) o;
            ToolsEquipmentVo toolsEquipmentVo = JSONUtil.toBean(jo, ToolsEquipmentVo.class);
            toolsEquipmentRequisitionList.add(toolsEquipmentVo);
        }

        List<Map<String, Object>> changeList = new ArrayList<>();

        for (ToolsEquipmentVo toolsEquipmentVo : toolsEquipmentRequisitionList) {
            String toolsCategoryId = toolsEquipmentVo.getToolsCategoryId();
            String nameId = toolsEquipmentVo.getNameId();
            String specificationModel = toolsEquipmentVo.getSpecificationModel();
            String voltageLevelId = toolsEquipmentVo.getVoltageLevelId();
            Map map = new HashMap();
            map.put("id", 1);
            Map<String, Object> map1 = BeanUtil.beanToMap(toolsEquipmentVo);
            map1.putAll(map);
            changeList.add(map1);
        }


        Map<String, Long> countMap =
                changeList.stream().collect(Collectors.groupingBy
                        (o -> o.get("affiliationId") + "_" + o.get("id") + "_" + o.get("packageId"
                        ), Collectors.counting()));
        List<SwineClaimoccupation> countRecords = countMap.keySet().stream().map(key -> {
            String[] temp = key.split("_");
            String affiliationId = temp[0];
            String toolTypeId = temp[1];
            String packageId = temp[2];

            SwineClaimoccupation swineClaimoccupation = new SwineClaimoccupation();
            swineClaimoccupation.setSubareaId(affiliationId);
            swineClaimoccupation.setTooltypeId(toolTypeId);
            swineClaimoccupation.setPackageId(packageId);
//            swineClaimoccupation.setTime(currentTimeMillis);
            swineClaimoccupation.setNumber(countMap.get(key).intValue());
//            swineClaimoccupation.setClaimedflag(saveOrSumbit);
//            swineClaimoccupation.setToolclaimId(swinePlanjob.getToolclaimId());
            swineClaimoccupation.setId(IdFactory.getSimpleUUID());
            return swineClaimoccupation;
        }).collect(Collectors.toList());


    }

}
