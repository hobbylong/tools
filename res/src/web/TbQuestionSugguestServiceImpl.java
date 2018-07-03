package com.company.service.impl;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//问题及建议表
public class TbQuestionSugguestServiceImpl implements TbQuestionSugguestService {
	private final static Logger log = LoggerFactory.getLogger(TbQuestionSugguestServiceImpl.class);
	
	@Override
	public List<TbQuestionSugguestBean> get(Map para,boolean isCount) throws Exception {
		// TODO Auto-generated method stub
		ArrayList<TbQuestionSugguestBean> beans = new ArrayList();
WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
ObjectDao dao=(ObjectDao) wac.getBean("objectDao");
		String where = "";
		int pageSize = 10;
		int currIndex = 0;
		
		Iterator it = para.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			Object key = entry.getKey();
			Object value = entry.getValue();
			log.info("key=" + key + " value=" + value);
			if ("pageSize".equals(key)) {
				if (value != null) {
					pageSize = Integer.valueOf(value + "");
				}
			} else if ("currIndex".equals(key)) {
				if (value != null)
					currIndex = Integer.valueOf(value + "");
			}
			else {
				where += key + " like '%" + value + "%' and ";
			}
		}
		if (where.lastIndexOf("and") != -1)
			where = (String) where.subSequence(0, where.lastIndexOf("and"));
		
		if(where.equals(""))
		{
			
		}else
		{
			where=" where "+where;
		}
		//oracle 分页
		String pageHead = "select y.* from(select z.*,rownum as rn from (";
		String pageEnd = ") z where rownum <= " + (pageSize + currIndex) + " ) y where y.rn > " + currIndex;
		//mysql 分页
		//String pageHead = "";
		//String pageEnd = " limit #{currIndex},#{pageSize} ";
		List tableBeans=new ArrayList();
		List tableBeanShortName=new ArrayList();
tableBeans.add(TbQuestionSugguestBean.class);

tableBeanShortName.add("sugguest");

		if(isCount==true){
		String sql="select count(*) from TbQuestionSugguest";
		List result = dao.findBySql(sql,new HashMap());
return result;
}
		String sql=pageHead+"select * from TbQuestionSugguest"+pageEnd;
		
		List rows = dao.findBySql(sql,new HashMap(),tableBeans,tableBeanShortName);
		
		for (int i = 0; i < rows.size(); i++) {
//多个表时
//Object[] objects = (Object[]) rows.get(i);
//ABean aBean = (ABean) objects[0];  
//BBean bBean = (BBean) objects[1];  
TbQuestionSugguestBean bean=(TbQuestionSugguestBean)rows.get(i);
			beans.add(bean);
		}
		
		
		return beans;
	}
	@Override
	public int getCount(Map para) throws Exception {
		// TODO Auto-generated method stub
List result=get(Map para,true);
 if(result!=null && result.size()>0){
Object obj=result.get(0);
if(obj instanceof BigDecimal )
{
return ((BigDecimal) result.get(0)).intValue();
}else{
return ((Long) result.get(0)).intValue();
}
}
		return 0;
	}
	@Override
	public int getMax(Map para) throws Exception {
		// TODO Auto-generated method stub
WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
ObjectDao dao=(ObjectDao) wac.getBean("objectDao");
String sql=select max(${columnName}) from TbQuestionSugguest;
List max = dao.findBySql(sql,new HashMap());
if(max!=null && max.size()>0){
Object obj=max.get(0);
if(obj instanceof BigDecimal )
{
return ((BigDecimal) max.get(0)).intValue();
}else{
return ((Long) max.get(0)).intValue();
}
}
return 0;
	}
	@Override
public void  insert(TbQuestionSugguestBean bean){
WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
ObjectDao dao=(ObjectDao) wac.getBean("objectDao");
	
		try {
			dao.save(bean);
			
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return;
	}
	@Override
public void update(TbQuestionSugguestBean bean){
WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
ObjectDao dao=(ObjectDao) wac.getBean("objectDao");
		try {
			dao.saveOrUpdate(bean);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
public void   delete(TbQuestionSugguestBean bean){
WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
ObjectDao dao=(ObjectDao) wac.getBean("objectDao");
try {
	dao.delete(bean);
} catch (Exception e) {
	e.printStackTrace();
}
	}
}

