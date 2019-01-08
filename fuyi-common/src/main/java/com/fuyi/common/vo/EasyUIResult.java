package com.fuyi.common.vo;

import java.io.Serializable;
import java.util.List;

/**
 * EasyUI dataGrid 返回数据封装bean
 */
public class EasyUIResult implements Serializable {

	private static final long serialVersionUID = -9033455226856573945L;

	private Long total;
	
	private List<?> rows;

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}

}
