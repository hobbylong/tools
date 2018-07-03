

 drop table t_tb_question_sugguest;
CREATE TABLE t_tb_question_sugguest(
QuestionID     NUMBER   NOT NULL,
QuestionType     VARCHAR2(100 BYTE),
     VARCHAR2(100 BYTE),
ApplicationName     VARCHAR2(100 BYTE),
ModelName     VARCHAR2(100 BYTE),
Question     VARCHAR2(100 BYTE),
UploadfileUrl     VARCHAR2(100 BYTE),
FeedbackName     VARCHAR2(100 BYTE),
FeedbackPhone     VARCHAR2(100 BYTE),
FeedbackOrg     VARCHAR2(100 BYTE),
FeedbackDateTime     VARCHAR2(100 BYTE),
DealPhone     VARCHAR2(100 BYTE),
DealDateTime     VARCHAR2(100 BYTE),
DealProgress     VARCHAR2(100 BYTE),
DealMsg     VARCHAR2(100 BYTE),
DealState     VARCHAR2(100 BYTE),
lastModifyTime     VARCHAR2(100 BYTE),
lastModifyTimeName     VARCHAR2(100 BYTE),
lastModifyTimeChannel     VARCHAR2(100 BYTE),
)TABLESPACE TBS_QS_AGENT;
alter table t_tb_question_sugguest add primary key (QuestionID) using index TABLESPACE IDX_TBS_QS_AGENT;
comment  on  column  t_tb_question_sugguest.QuestionID   is  '上报ID';
comment  on  column  t_tb_question_sugguest.QuestionType   is  '上报类型';
comment  on  column  t_tb_question_sugguest.   is  '';
comment  on  column  t_tb_question_sugguest.ApplicationName   is  '涉及系统';
comment  on  column  t_tb_question_sugguest.ModelName   is  '功能模块名称';
comment  on  column  t_tb_question_sugguest.Question   is  '问题或需求描述';
comment  on  column  t_tb_question_sugguest.UploadfileUrl   is  '附件上传路径';
comment  on  column  t_tb_question_sugguest.FeedbackName   is  '反馈人姓名';
comment  on  column  t_tb_question_sugguest.FeedbackPhone   is  '反馈人联系方式';
comment  on  column  t_tb_question_sugguest.FeedbackOrg   is  '反馈人机构名称';
comment  on  column  t_tb_question_sugguest.FeedbackDateTime   is  '提交日期';
comment  on  column  t_tb_question_sugguest.DealPhone   is  '处理人电话';
comment  on  column  t_tb_question_sugguest.DealDateTime   is  '处理时间';
comment  on  column  t_tb_question_sugguest.DealProgress   is  '处理进度';
comment  on  column  t_tb_question_sugguest.DealMsg   is  '处理意见（反馈意见）';
comment  on  column  t_tb_question_sugguest.DealState   is  '处理状态';
comment  on  column  t_tb_question_sugguest.lastModifyTime   is  '最后修改时间';
comment  on  column  t_tb_question_sugguest.lastModifyTimeName   is  '最后修改人';
comment  on  column  t_tb_question_sugguest.lastModifyTimeChannel   is  '最后修改渠道';

