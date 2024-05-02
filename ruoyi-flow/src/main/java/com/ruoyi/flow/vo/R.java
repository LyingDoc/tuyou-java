package com.ruoyi.flow.vo;

import java.io.Serializable;

public class R<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private int code;
    private String msg;
    private T data;
    private Boolean success=false;
    private long totalcount;

    public long getTotalcount() {
        return totalcount;
    }

    public void setTotalcount(long totalcount) {
        this.totalcount = totalcount;
    }
    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public void setCode(int code){
        this.code=code;
    }
    public int getCode(){
        return this.code;
    }

    public void setMsg(String msg){
        this.msg=msg;
    }
    public String getMsg(){
        return this.msg;
    }


    public void setData(T data){
        this.data=data;
    }
    public T getData(){
        return this.data;
    }

    private final static R<?> EMPTY = new R<>();

    public static <T> R<T> empty() {
        @SuppressWarnings("unchecked")
        R<T> t = (R<T>) EMPTY;
        return t;
    }

    public R() {

    }

    /* 成功 构造函数 */
    public R(String msg, T data) {
        this.code = 0;
        this.success=true;
        this.msg = msg;
        this.data = data;
    }

    /* 失败 构造函数 */
    public R(String msg) {
        this.code = -1;
        this.success=false;
        this.msg = msg;
        this.data =  null;
    }
    public static <T> R<T> newOk(){
        R<T> r= new R<T>("操作成功");
        r.setCode(0);
        return r;
    }
    public static <T> R<T> newOk(T data){
        return new R<T>("操作成功",data);
    }
    public static <T> R<T> newOk(T data, long _totalcount){
        R<T> result=  new R<T>("操作成功",data);
        result.setTotalcount(_totalcount);
        return result;
    }
    public static <T> R<T> newError(String msg){
        return new R<T>(msg);
    }
    public static <T> R<T> newError(){
        return new R<T>("操作错误");
    }
//    /* 错误返回 */
//    public Result<T> error(String msg) {
//        return new Result<T>(msg);
//    }
//    /* 错误返回 */
//    public Result<T> error(String msg, T data) {
//        Result<T> result=  new Result<T>(msg,data);
//        result.setCode(-1);
//        result.setSuccess(false);
//        return result;
//    }
//    /* 正确返回 */
//    public Result<T> success(String msg) {
//        return new Result<T>(msg,null);
//    }
//    /* 正确返回 */
//    public Result<T> success(String msg, T data) {
//        return new Result<T>(msg,data);
//    }
//    public Result<T> success(String msg, T data, int _totalcount) {
//        Result<T> result=  new Result<T>(msg,data);
//        result.setTotalcount(_totalcount);
//        return result;
//    }
}
