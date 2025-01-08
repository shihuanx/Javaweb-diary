package com.shihuanx.diary.entity;

import java.time.LocalDateTime;

public class OperateLog {
        private Integer id;
        private Integer userId;
        private LocalDateTime operateTime;
        private String className;
        private String methodName;
        private String params;
        private String returnValue;
        private long costTime;

        public OperateLog(){}
        public OperateLog(Integer id, Integer userId, LocalDateTime operateTime, String className, String methodName,String params,String returnValue,long costTime) {
            this.id=id;
            this.userId=userId;
            this.operateTime=operateTime;
            this.className=className;
            this.methodName=methodName;
            this.params=params;
            this.returnValue=returnValue;
            this.costTime=costTime;
        }

        public Integer getId() {return id;}

        public void setId(Integer id) {this.id = id;}

        public Integer getUserId() {return userId;}

        public void setUserId(Integer userId) {this.userId = userId;}

        public LocalDateTime getOperateTime() {return operateTime;}

        public void setOperateTime(LocalDateTime operateTime) {this.operateTime = operateTime;}

        public String getClassName() {return className;}

        public void setClassName(String className) {this.className = className;}

        public String getMethodName() {return methodName;}

        public void setMethodName(String methodName) {this.methodName = methodName;}

        public String getParams() {return params;}

        public void setParams(String params) {this.params = params;}

        public String getReturnValue() {return returnValue;}

        public void setReturnValue(String returnValue) {this.returnValue = returnValue;}

        public long getCostTime() {return costTime;}

        public void setCostTime(long costTime) {this.costTime = costTime;}
}
