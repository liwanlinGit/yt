package com.study.util;

import java.util.List;

/**
 * 系统结果工具类
 * 
 * @author Easong
 *
 */
public class ResultUtil {

	/**
	 * 创建错误结果
	 * 
	 * @param message
	 * @return
	 */
	public static ResultInfo createFail(String message) {
		return new ResultInfo(ResultInfo.TYPE_RESULT_FAIL, message);
	}

	/**
	 * 创建敬告提示结果
	 * 
	 * @param message
	 * @return
	 */
	public static ResultInfo createWarning(String message) {
		return new ResultInfo(ResultInfo.TYPE_RESULT_WARN, message);
	}

	/**
	 * 创建成功提示结果
	 * 
	 * @param message
	 * @return
	 */
	public static ResultInfo createSuccess(String message) {

		return new ResultInfo(ResultInfo.TYPE_RESULT_SUCCESS, message);
	}

	/**
	 * 创建普通信息提示结果
	 * 
	 * @param message
	 * @return
	 */
	public static ResultInfo createInfo(String message) {
		return new ResultInfo(ResultInfo.TYPE_RESULT_INFO, message);
	}



	/**
	 * 创建datagrid结果
	 * 
	 * @param total
	 * @param rows
	 * @return
	 */
	public static <T> DataGridResultInfo createDataGridResult(long total, List<T> rows) {
		DataGridResultInfo dataGridResultInfo = new DataGridResultInfo();
		dataGridResultInfo.setRows(rows);
		dataGridResultInfo.setTotal(total);
		return dataGridResultInfo;
	}

	public static void main(String[] args) {

	}

}
