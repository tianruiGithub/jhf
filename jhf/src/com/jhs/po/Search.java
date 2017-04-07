package com.jhs.po;

/**
 * list页面参数po类
 * 
 * @author 天瑞
 *
 */
public class Search {

	private int page; // 页码
	private int row; // 显示行数
	private String keywords;// 关键字
	private String type1; // 所属类别1
	private String type2; // 所属类别2
	private String date1; // 日期1
	private String date2; // 日期2
	private String sort; // 排序列
	private String order; // 排序方向

	/**
	 * @return the page
	 */
	public int getPage() {
		return page;
	}

	/**
	 * @param page
	 *            the page to set
	 */
	public void setPage(int page) {
		this.page = page;
	}

	/**
	 * @return the row
	 */
	public int getRow() {
		return row;
	}

	/**
	 * @param row
	 *            the row to set
	 */
	public void setRow(int row) {
		this.row = row;
	}

	/**
	 * @return the keywords
	 */
	public String getKeywords() {
		return keywords;
	}

	/**
	 * @param keywords
	 *            the keywords to set
	 */
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	/**
	 * @return the type1
	 */
	public String getType1() {
		return type1;
	}

	/**
	 * @param type1
	 *            the type1 to set
	 */
	public void setType1(String type1) {
		this.type1 = type1;
	}

	/**
	 * @return the type2
	 */
	public String getType2() {
		return type2;
	}

	/**
	 * @param type2
	 *            the type2 to set
	 */
	public void setType2(String type2) {
		this.type2 = type2;
	}

	/**
	 * @return the date1
	 */
	public String getDate1() {
		return date1;
	}

	/**
	 * @param date1
	 *            the date1 to set
	 */
	public void setDate1(String date1) {
		this.date1 = date1;
	}

	/**
	 * @return the date2
	 */
	public String getDate2() {
		return date2;
	}

	/**
	 * @param date2
	 *            the date2 to set
	 */
	public void setDate2(String date2) {
		this.date2 = date2;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

}
