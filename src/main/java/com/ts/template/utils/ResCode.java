package com.ts.template.utils;

import java.util.HashMap;
import java.util.Map;

public class ResCode {

	//数据库CRUD状态码
	public static final Integer CODE_7531 = 7531;
	public static final Integer CODE_7532 = 7532;
	public static final Integer CODE_7533 = 7533;
	public static final Integer CODE_7534 = 7534;
	public static final Integer CODE_7535 = 7535;
	public static final Integer CODE_7536 = 7536;
	public static final Integer CODE_7537 = 7537;
	public static final Integer CODE_7538 = 7538;
	
	//校验状态码
	public static final Integer CODE_7551 = 7551;
	public static final Integer CODE_7552 = 7552;
	public static final Integer CODE_7553 = 7553;
	public static final Integer CODE_7554 = 7554;
	public static final Integer CODE_7555 = 7555;
	public static final Integer CODE_7556 = 7556;
	public static final Integer CODE_7557 = 7557;

	/** ———————Network状态码Start——————— */

	//服务器错误
	public static final Integer CODE_7567 = 7567;
	//参数校验错误
	public static final Integer CODE_7568 = 7568;

	/** ———————Network状态码End——————— */

	public static Map<Integer,String> codeMap = new HashMap<Integer,String>();
	
	static {
		codeMap.put(CODE_7531, "添加成功");
		codeMap.put(CODE_7532, "添加失败");
		codeMap.put(CODE_7533, "修改成功");
		codeMap.put(CODE_7534, "修改失败");
		codeMap.put(CODE_7535, "删除成功");
		codeMap.put(CODE_7536, "删除失败");
		codeMap.put(CODE_7537, "不能为空");
		codeMap.put(CODE_7538, "没有对应的值");
		codeMap.put(CODE_7551, "用户名错误");
		codeMap.put(CODE_7552, "用户名不存在");
		codeMap.put(CODE_7553, "用户已存在");
		codeMap.put(CODE_7554, "用户已冻结");
		codeMap.put(CODE_7555, "密码错误");
		codeMap.put(CODE_7556, "密码数次超过了5次");
		codeMap.put(CODE_7557, "验证码错误");
		// 新增——服务器错误
		codeMap.put(CODE_7567, "服务器错误");
		codeMap.put(CODE_7568, "参数校验错误");
	}
	
	public static String getVal (Integer key) {
		return codeMap.get(key);
	}
}
