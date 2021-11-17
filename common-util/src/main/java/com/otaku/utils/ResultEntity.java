package com.otaku.utils;

/**
 * @author Otaku
 * @date 2021/10/18-14:58
 */
public class ResultEntity<T> {
    private static final String SUCCESS="SUCCESS";
    private static final String FAIL="FAIL";
    //用来处理当前请求是否成功还是失败
    private String result;
    //请求失败是返回的错误信息
    private String message;
    //要返回的数据
    private T data;

    /**
     * 请求处理成功且不需要返回数值时
     * @param <E>
     * @return
     */
    public static <E> ResultEntity<E> successWithoutData(){
        return new ResultEntity<E>(SUCCESS,null,null);
    }

    /**
     * 请求处理成功且需要返回数据时
     * @param data
     * @param <E>
     * @return
     */
    public static <E> ResultEntity<E> successWithoutData(E data){
        return new ResultEntity<>(SUCCESS, null, data);
    }

    /**
     * 请求处理失败，返回失败信息
     * @param message
     */
    public static <E> ResultEntity<E> failed(String message){
        return new ResultEntity<>(FAIL, message, null);
    }
    public ResultEntity(String result, String message, T data) {
        this.result = result;
        this.message = message;
        this.data = data;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResultEntity{" +
                "result='" + result + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
