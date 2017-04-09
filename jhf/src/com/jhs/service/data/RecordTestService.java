package com.jhs.service.data;

import java.util.Date;
import java.util.List;

import com.jfinal.kit.PropKit;
import com.jfinal.weixin.sdk.api.ApiConfig;
import com.jfinal.weixin.sdk.api.ApiConfigKit;
import com.jfinal.weixin.sdk.api.ApiResult;
import com.jfinal.weixin.sdk.api.TemplateData;
import com.jfinal.weixin.sdk.api.TemplateMsgApi;
import com.jhs.common.model.DataPhysique;
import com.jhs.common.model.DataSolution;
import com.jhs.common.model.RecordTest;

/**
 * 辩证检测Service
 * 
 * @author 天瑞
 *
 */
public class RecordTestService {

	public static RecordTestService me = new RecordTestService();

	private static final RecordTest dao = new RecordTest().dao();

	/**
	 * 检测记录添加
	 * 
	 * @param openId
	 *            微信用户openId
	 * @param demandNo
	 *            需求编号
	 * @param imageData
	 *            舌像数据
	 * @param feature
	 *            所属特性分类
	 * @param color
	 *            舌像检测结果
	 * @param scoreList
	 *            分数
	 */
	public boolean create(String openId, String demandNo, String imageData, String feature, String color,
			String scoreList) {
		// 初始变量
		String physiqueNo = null;
		String physiqueName = null;
		int max = 0;
		int sum = 0;
		int item = 0;
		int temp;
		DataPhysique dp;
		// 获取体质列表
		List<DataPhysique> pList = DataPhysiqueService.me.queryByDemand(demandNo);
		// 获取最大分数
		String[] list = scoreList.split("I");
		for (int i = 0; i < pList.size(); i++) {
			sum = 0;
			item = 0;
			temp = 0;
			dp = (DataPhysique) pList.get(i);
			// 合计总分
			for (int j = 0; j < list.length; j++) {
				if (list[j].split("H")[0].equals(dp.getPhysiqueNo())) {
					item = item + 1;
					sum += Integer.parseInt(list[j].split("H")[1]);
				}
			}
			// 最优值
			temp = (int) ((((sum - item) * 1.0) / (item * 4)) * 100);
			// 计算舌苔关联度
			if (dp.getPhysiqueGuanlian().indexOf(color) > 0) {
				temp += 5;
			}
			// 如果是祛痘检测，计算位置关联度
			if (demandNo.equals("02")) {
				if (dp.getPhysiqueGuanlian().indexOf(feature) > 0) {
					temp += 5;
				}
			}
			// 防止超出
			if (temp > 100) {
				temp = 100;
			}

			if (temp >= max) {
				physiqueNo = dp.getPhysiqueNo();
				physiqueName = dp.getPhysiqueName();
				max = temp;
			}
		}
		// 保存数据
		RecordTest rt = new RecordTest();
		rt.setUserId(openId);
		rt.setDemandNo(demandNo);
		rt.setPhysiqueNo(physiqueNo);
		rt.setSolutionNo("");
		rt.setTestFeature(feature);
		rt.setTestTongue(imageData);
		rt.setTestTongueResult(color);
		rt.setTestScore(max);
		rt.setTestScoreList(scoreList);
		rt.setTestTime(new Date());
		if (rt.save()) {
			String typeName = "";
			if (demandNo.equals("02")) {
				typeName = "祛痘检测";
			} else if (demandNo.equals("07")) {
				typeName = "日常护肤检测";
			}
			DataSolution ds = DataSolutionService.me.queryByPhysiqueAndScore(physiqueNo, max);
			if (ds != null) {
				ApiConfigKit.setThreadLocalApiConfig(new ApiConfig());
				ApiResult result = TemplateMsgApi.send(TemplateData.New()
						// 消息接收者
						.setTouser(openId)
						// 模板id
						.setTemplate_id("-UeDTpo8DDAHQmQdsuFHaotkk_-OlPwfBusBoH79OQk").setTopcolor("#eb414a")
						.setUrl(PropKit.get("url") + "/weixin/solution?no="+ds.getSolutionNo())
						// 模板参数
						.add("first", "您好，您的检测结果出来了\n", "#999").add("keyword1", typeName, "#999")
						.add("keyword2", "您的肤质属于[" + physiqueName + "]型", "#999")
						.add("remark", "\n点击查看我们为您定制的护理方案。", "#999").build());
				TemplateMsgApi.send(result.getJson());
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}
