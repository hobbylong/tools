package com.company.pojo;
import java.util.List;
import java.util.Map;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
//问题及建议表
@Entity
@Table(name = "TbQuestionSugguest")
public class TbQuestionSugguestBean {
	public Integer QuestionID;/**上报ID*/
	public String QuestionType;/**上报类型*/
	public String ;/***/
	public String ApplicationName;/**涉及系统*/
	public String ModelName;/**功能模块名称*/
	public String Question;/**问题或需求描述*/
	public String UploadfileUrl;/**附件上传路径*/
	public String FeedbackName;/**反馈人姓名*/
	public String FeedbackPhone;/**反馈人联系方式*/
	public String FeedbackOrg;/**反馈人机构名称*/
	public String FeedbackDateTime;/**提交日期*/
	public String DealPhone;/**处理人电话*/
	public String DealDateTime;/**处理时间*/
	public String DealProgress;/**处理进度*/
	public String DealMsg;/**处理意见（反馈意见）*/
	public String DealState;/**处理状态*/
	public String lastModifyTime;/**最后修改时间*/
	public String lastModifyTimeName;/**最后修改人*/
	public String lastModifyTimeChannel;/**最后修改渠道*/
	public Integer getQuestionID() {
		return QuestionID;
	}
	public void setQuestionID(Integer QuestionID) {
		this.QuestionID = QuestionID;
	}
	public Integer getQuestionID() {
		return QuestionID;
	}
	public void setQuestionID(Integer QuestionID) {
		this.QuestionID = QuestionID;
	}
	public String getQuestionType() {
		return QuestionType;
	}
	public void setQuestionType(String QuestionType) {
		this.QuestionType = QuestionType;
	}
	public String getQuestionType() {
		return QuestionType;
	}
	public void setQuestionType(String QuestionType) {
		this.QuestionType = QuestionType;
	}
	public String get() {
		return ;
	}
	public void set(String ) {
		this. = ;
	}
	public String get() {
		return ;
	}
	public void set(String ) {
		this. = ;
	}
	public String getApplicationName() {
		return ApplicationName;
	}
	public void setApplicationName(String ApplicationName) {
		this.ApplicationName = ApplicationName;
	}
	public String getApplicationName() {
		return ApplicationName;
	}
	public void setApplicationName(String ApplicationName) {
		this.ApplicationName = ApplicationName;
	}
	public String getModelName() {
		return ModelName;
	}
	public void setModelName(String ModelName) {
		this.ModelName = ModelName;
	}
	public String getModelName() {
		return ModelName;
	}
	public void setModelName(String ModelName) {
		this.ModelName = ModelName;
	}
	public String getQuestion() {
		return Question;
	}
	public void setQuestion(String Question) {
		this.Question = Question;
	}
	public String getQuestion() {
		return Question;
	}
	public void setQuestion(String Question) {
		this.Question = Question;
	}
	public String getUploadfileUrl() {
		return UploadfileUrl;
	}
	public void setUploadfileUrl(String UploadfileUrl) {
		this.UploadfileUrl = UploadfileUrl;
	}
	public String getUploadfileUrl() {
		return UploadfileUrl;
	}
	public void setUploadfileUrl(String UploadfileUrl) {
		this.UploadfileUrl = UploadfileUrl;
	}
	public String getFeedbackName() {
		return FeedbackName;
	}
	public void setFeedbackName(String FeedbackName) {
		this.FeedbackName = FeedbackName;
	}
	public String getFeedbackName() {
		return FeedbackName;
	}
	public void setFeedbackName(String FeedbackName) {
		this.FeedbackName = FeedbackName;
	}
	public String getFeedbackPhone() {
		return FeedbackPhone;
	}
	public void setFeedbackPhone(String FeedbackPhone) {
		this.FeedbackPhone = FeedbackPhone;
	}
	public String getFeedbackPhone() {
		return FeedbackPhone;
	}
	public void setFeedbackPhone(String FeedbackPhone) {
		this.FeedbackPhone = FeedbackPhone;
	}
	public String getFeedbackOrg() {
		return FeedbackOrg;
	}
	public void setFeedbackOrg(String FeedbackOrg) {
		this.FeedbackOrg = FeedbackOrg;
	}
	public String getFeedbackOrg() {
		return FeedbackOrg;
	}
	public void setFeedbackOrg(String FeedbackOrg) {
		this.FeedbackOrg = FeedbackOrg;
	}
	public String getFeedbackDateTime() {
		return FeedbackDateTime;
	}
	public void setFeedbackDateTime(String FeedbackDateTime) {
		this.FeedbackDateTime = FeedbackDateTime;
	}
	public String getFeedbackDateTime() {
		return FeedbackDateTime;
	}
	public void setFeedbackDateTime(String FeedbackDateTime) {
		this.FeedbackDateTime = FeedbackDateTime;
	}
	public String getDealPhone() {
		return DealPhone;
	}
	public void setDealPhone(String DealPhone) {
		this.DealPhone = DealPhone;
	}
	public String getDealPhone() {
		return DealPhone;
	}
	public void setDealPhone(String DealPhone) {
		this.DealPhone = DealPhone;
	}
	public String getDealDateTime() {
		return DealDateTime;
	}
	public void setDealDateTime(String DealDateTime) {
		this.DealDateTime = DealDateTime;
	}
	public String getDealDateTime() {
		return DealDateTime;
	}
	public void setDealDateTime(String DealDateTime) {
		this.DealDateTime = DealDateTime;
	}
	public String getDealProgress() {
		return DealProgress;
	}
	public void setDealProgress(String DealProgress) {
		this.DealProgress = DealProgress;
	}
	public String getDealProgress() {
		return DealProgress;
	}
	public void setDealProgress(String DealProgress) {
		this.DealProgress = DealProgress;
	}
	public String getDealMsg() {
		return DealMsg;
	}
	public void setDealMsg(String DealMsg) {
		this.DealMsg = DealMsg;
	}
	public String getDealMsg() {
		return DealMsg;
	}
	public void setDealMsg(String DealMsg) {
		this.DealMsg = DealMsg;
	}
	public String getDealState() {
		return DealState;
	}
	public void setDealState(String DealState) {
		this.DealState = DealState;
	}
	public String getDealState() {
		return DealState;
	}
	public void setDealState(String DealState) {
		this.DealState = DealState;
	}
	public String getLastModifyTime() {
		return lastModifyTime;
	}
	public void setLastModifyTime(String lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}
	public String getLastModifyTime() {
		return lastModifyTime;
	}
	public void setLastModifyTime(String lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}
	public String getLastModifyTimeName() {
		return lastModifyTimeName;
	}
	public void setLastModifyTimeName(String lastModifyTimeName) {
		this.lastModifyTimeName = lastModifyTimeName;
	}
	public String getLastModifyTimeName() {
		return lastModifyTimeName;
	}
	public void setLastModifyTimeName(String lastModifyTimeName) {
		this.lastModifyTimeName = lastModifyTimeName;
	}
	public String getLastModifyTimeChannel() {
		return lastModifyTimeChannel;
	}
	public void setLastModifyTimeChannel(String lastModifyTimeChannel) {
		this.lastModifyTimeChannel = lastModifyTimeChannel;
	}
	public String getLastModifyTimeChannel() {
		return lastModifyTimeChannel;
	}
	public void setLastModifyTimeChannel(String lastModifyTimeChannel) {
		this.lastModifyTimeChannel = lastModifyTimeChannel;
	}
}

