package com.fuyi.common.vo;

import java.io.Serializable;
import java.util.List;

/**
 * EasyUI dataGrid 返回数据封装bean
 */
public class EasyUIDataGridResult implements Serializable {

	private static final long serialVersionUID = -9033455226856573945L;

	private Integer total;
	
	private List<?> rows;

	public static EasyUIDataGridResult build(Integer total, List rows) {
		return new EasyUIDataGridResult(total, rows);
	}

	private EasyUIDataGridResult(Integer total, List<?> rows) {
		this.total = total;
		this.rows = rows;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}

}
