package com.dsecet.util;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created on 2016年3月30日
 * <p>Title:       类型帮助类</p>
 * <p>Description: 类型转换</p>
 * <p>Copyright:   Copyright (c) 2016</p>
 * <p>Company:     联创梦享集团</p>
 * <p>Department:  成都研发中心</p>
 * @author         宁良竹 lz.ning@defshare.org
 * @version        1.0
 */
public class TypeHelper {

	/**
	 * <p>Description: ResultSet转List</p>
	 * @author:宁良竹
	 * @update: 2016年3月30日
	 * @param rs
	 * @return
	 * @throws java.sql.SQLException
	 */
	public static List resultSetToList(ResultSet rs) throws java.sql.SQLException {   
        if (rs == null)   
            return Collections.EMPTY_LIST;   
        ResultSetMetaData md = rs.getMetaData(); //得到结果集(rs)的结构信息，比如字段数、字段名等   
        int columnCount = md.getColumnCount(); //返回此 ResultSet 对象中的列数   
        List list = new ArrayList();   
        Map rowData = new HashMap();   
        while (rs.next()) {   
         rowData = new HashMap(columnCount);   
         for (int i = 1; i <= columnCount; i++) {   
                 rowData.put(md.getColumnName(i), rs.getObject(i));   
         }   
         list.add(rowData);   
         System.out.println("list:" + list.toString());   
        }   
        return list;   
}  
}
