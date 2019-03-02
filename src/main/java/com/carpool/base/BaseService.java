package com.carpool.base;

import com.carpool.enums.FailInfoEnum;

import java.util.Map;

/**
 * @author sunLei on 2018/11/17 18:55
 * @version 1.0
 * @apiNote
 */
public abstract class BaseService {
    public ServiceResult success(){
        ServiceResult serviceResult=new ServiceResult();
        serviceResult.setSuccess(true);
        serviceResult.setId(1);
        serviceResult.setInfo("success");
        return serviceResult;
    }
    public ServiceResult success(Map<String,Object> data){
        ServiceResult serviceResult=new ServiceResult();
        serviceResult.setSuccess(true);
        serviceResult.setId(1);
        serviceResult.setInfo("success");
        serviceResult.setData(data);
        return serviceResult;
    }
    public ServiceResult success(String info){
        ServiceResult serviceResult=new ServiceResult();
        serviceResult.setSuccess(true);
        serviceResult.setId(1);
        serviceResult.setInfo(info);
        return serviceResult;
    }
    protected ServiceResult failInput(){
        ServiceResult serviceResult=new ServiceResult();
        serviceResult.setSuccess(false);
        serviceResult.setId(0);
        serviceResult.setInfo(FailInfoEnum.fail1.getInfo());
        return serviceResult;
    }
    protected ServiceResult failDataBase(){
        ServiceResult serviceResult=new ServiceResult();
        serviceResult.setSuccess(false);
        serviceResult.setId(0);
        serviceResult.setInfo(FailInfoEnum.fail2.getInfo());
        return serviceResult;
    }
    protected ServiceResult fail(){
        ServiceResult serviceResult=new ServiceResult();
        serviceResult.setSuccess(false);
        serviceResult.setId(0);
        serviceResult.setInfo("fail");
        return serviceResult;
    }
    protected ServiceResult fail(String info){
        ServiceResult serviceResult=new ServiceResult();
        serviceResult.setSuccess(false);
        serviceResult.setId(0);
        serviceResult.setInfo(info);
        return serviceResult;
    }
    public static class ServiceResult{
        private boolean isSuccess;
        private int id;
        private String info;
        private Map<String,Object> data;
        public boolean isSuccess() {
            return isSuccess;
        }

        public void setSuccess(boolean success) {
            isSuccess = success;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public Map<String, Object> getData() {
            return data;
        }

        public void setData(Map<String, Object> data) {
            this.data = data;
        }
    }
}
