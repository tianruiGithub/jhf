package com.jhs.util;

import com.jhs.po.Search;

/**
 * list页面查询工具类
 * 
 * @author 天瑞
 *
 */
public class SQLUtil {

	private static int page = 1;
	private static int row = 20;

	/**
	 * 拼接sql语句
	 * 
	 * @param table
	 * @param keywordsSQL
	 * @param type1SQL
	 * @param type2SQL
	 * @param dateSQL
	 * @return
	 */
	public static String makeSQL(Search search, String table, String[] keywordsSQL, String type1SQL, String type2SQL,
			String dateSQL) {

		StringBuilder sql = new StringBuilder();
		// 设置要查询表
		if (table == null || table.equals(""))
			throw new IllegalArgumentException("'TABLE' must be not null");
		if(table.contains("where"))
			sql.append("from " + table);
		else
			sql.append("from " + table + " where 1=1");
		
		// 判断search是否为空
		if (search == null) 
			return sql.toString();
		// 设置页码
		page = search.getPage();

		// 设置显示行数
		row = search.getRow();
		
		// 设置关键字查询
		if (search.getKeywords() != null && !search.getKeywords().equals("") && keywordsSQL != null) {
			sql.append(" and (");
			for (int i = 0; i < keywordsSQL.length; i++) {
				if (i == 0) {
					sql.append(keywordsSQL[i] + " like '%" + search.getKeywords() + "%'");
				} else {
					sql.append(" or " + keywordsSQL[i] + " like '%" + search.getKeywords() + "%'");
				}
			}
			sql.append(") ");
		}
		// 设置类别查询
		if (search.getType1() != null && type1SQL != null && !search.getType1().equals("")) 
			sql.append(" and " + type1SQL + " = '" + search.getType1() + "'");
		
		if (search.getType2() != null && type2SQL != null && !search.getType2().equals("")) 
			sql.append(" and " + type2SQL + " = '" + search.getType2() + "'");
		
		// 设置日期查询
		if (search.getDate1() != null && search.getDate2() == null) 
			sql.append(" and " + dateSQL + " = '" + search.getDate1() + "'");
		else if (search.getDate1() != null && search.getDate2() != null) {
			sql.append(" and " + dateSQL + " >= '" + search.getDate1() + " 00:00:00'");
			sql.append(" and " + dateSQL + " <= '" + search.getDate2() + " 59:59:59'");
		}
		//设置排序
		if(search.getSort() != null && !search.getSort().equals(""))
			sql.append(" order by " +search.getSort() +" "+search.getOrder());
		
		return sql.toString();
	}

	/**
	 * @return the page
	 */
	public static int getPage() {
		return page;
	}

	/**
	 * @param page
	 *            the page to set
	 */
	public void setPage(int page) {
		SQLUtil.page = page;
	}

	/**
	 * @return the row
	 */
	public static int getRow() {
		return row;
	}

	/**
	 * @param row
	 *            the row to set
	 */
	public void setRow(int row) {
		SQLUtil.row = row;
	}

}
