package org.ko.prototype.support.view;


import org.ko.prototype.support.bean.BaseBean;
import org.ko.prototype.support.type.AppCode;

public class View<T> extends BaseBean {

    private String code;

    private String massage;

    private T model;

    public View() {
        this(AppCode.SUCCESS);
    }

    public View(AppCode appCode) {
        this.code = appCode.getCode();
        this.massage = appCode.getMessage();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }

    public T getModel() {
        return model;
    }

    public void setModel(T model) {
        this.model = model;
    }
}
