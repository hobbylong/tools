package com.company.service.impl;
import java.util.List;
import java.util.Map;
//问题及建议表
public interface TbQuestionSugguestService {
	List<TbQuestionSugguestBean> get(Map para,boolean isCount) throws Exception;
	int getCount(Map para) throws Exception;
	int getMax(Map para) throws Exception;
void insert(TbQuestionSugguestBean bean);
void update(TbQuestionSugguestBean bean);
void delete(TbQuestionSugguestBean bean);
}

