package com.example.springbootmybatis.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.github.pagehelper.Page;


public class AjaxResponse {
    /**
     * 响应代码
     */
    private Integer code;

    /**
     * 响应消息
     */
    private String msg;

    /**
     * 响应数据
     */
    private Object data;

    /**
     * 默认构造函数
     */
    public AjaxResponse() {
    }

    /**
     * 构造函数
     *
     * @param code
     *            响应消息返回码
     */
    public AjaxResponse(Integer code) {
        this.code = code;
    }

    /**
     * 构造函数
     *
     * @param isSuccess
     *            以返回状态为初始化条件
     */
    public AjaxResponse(boolean isSuccess) {
        this();

        if (isSuccess) {
            this.code = AjaxResponse.CODE_SUCCESS;
            this.msg = AjaxResponse.MESSAGE_SUCCESS;
        }
        else {
            this.code = AjaxResponse.CODE_ERROR;
            this.msg = AjaxResponse.MESSAGE_ERROR;
        }
    }

    public AjaxResponse(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public AjaxResponse(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 常量代码：成功
     */
    public final static Integer CODE_SUCCESS = 0;

    /**
     * 常量代码：失败
     */
    public final static Integer CODE_ERROR = 1;

    /**
     * 常量消息：成功
     */
    public final static String MESSAGE_SUCCESS = "Operation Successed.";


    /**
     * 常量消息：失败
     */
    public final static String MESSAGE_ERROR = "Operation Error.";

    public final static String DESC_ERROR = "Operation Error.";

    /**
     * 操作成功实例
     *
     * @author zoe(zhongpanc@isoftstone.com)
     * @return 新的消息响应实例
     * @since 2016年9月22日
     */
    public static AjaxResponse success() {
        AjaxResponse ajax = new AjaxResponse();
        ajax.code = AjaxResponse.CODE_SUCCESS;
        ajax.msg = AjaxResponse.MESSAGE_SUCCESS;
        return ajax;
    }

    /**
     * 操作成功实例
     *
     * @author zoe(zhongpanc@isoftstone.com)
     * @param data
     *            返回数据
     * @return 新的消息响应实例
     * @since 2016年9月27日
     */
    public static AjaxResponse success(Object data) {
        AjaxResponse ajax = new AjaxResponse();
        ajax.code = AjaxResponse.CODE_SUCCESS;
        ajax.msg = AjaxResponse.MESSAGE_SUCCESS;
        ajax.data = data;
        return ajax;
    }

    /**
     * 重置状态为：成功
     *
     * @author zoe(zhongpanc@isoftstone.com)
     * @return 重置后的响应对象
     * @since 2016年9月23日
     */
    public AjaxResponse toSuccess() {
        this.code = AjaxResponse.CODE_SUCCESS;
        this.msg = AjaxResponse.MESSAGE_SUCCESS;
        return this;
    }

    /**
     * 操作失败实例
     *
     * @author zoe(zhongpanc@isoftstone.com)
     * @return 新的消息响应实例
     * @since 2016年9月22日
     */
    public static AjaxResponse error() {
        AjaxResponse ajax = new AjaxResponse();
        ajax.code = AjaxResponse.CODE_ERROR;
        ajax.msg = AjaxResponse.MESSAGE_ERROR;
        return ajax;
    }

    /**
     * 操作失败实例
     *
     * @author zoe(zhongpanc@isoftstone.com)
     * @param data
     *            返回数据
     * @return 新的消息响应实例
     * @since 2016年9月27日
     */
    public static AjaxResponse error(Object data) {
        AjaxResponse ajax = new AjaxResponse();
        ajax.code = AjaxResponse.CODE_ERROR;
        ajax.msg = AjaxResponse.MESSAGE_ERROR;
        ajax.data = data;
        return ajax;
    }

    /**
     * 重置状态为：失败
     *
     * @author zoe(zhongpanc@isoftstone.com)
     * @return 重置后的响应对象
     * @since 2016年9月23日
     */
    public AjaxResponse toError() {
        this.code = AjaxResponse.CODE_ERROR;
        this.msg = AjaxResponse.MESSAGE_ERROR;
        return this;
    }

    /**
     * 从自定义返回码生成消息响应
     *
     * @author zoe(zhongpanc@isoftstone.com)
     * @param code
     *            自定义返回码
     * @return 新的消息响应实例
     * @since 2016年9月22日
     */
    public static AjaxResponse fromCode(Integer code) {
        AjaxResponse ajax = new AjaxResponse();
        ajax.code = code;
        return ajax;
    }

    /**
     * 从自定义返回对象生成响应
     *
     * @author znbia(znbia@isoftstone.com)
     * @param data
     *            自定义返回对象
     * @return 新的消息响应对象
     * @since 2016年9月23日
     */
    public static AjaxResponse fromData(Object data) {
        AjaxResponse ajax = new AjaxResponse();
        ajax.data = data;
        return ajax;
    }

    public static AjaxResponse fromDBCode(Integer dbcode) {
        return fromDBCode(dbcode.longValue());
    }

    public static AjaxResponse fromDBCode(Long dbcode) {
        return dbcode > 0 ? success() : error();
    }

    /**
     * 生成 JSON 字元串消息
     *
     * @author zoe(zhongpanc@isoftstone.com)
     * @return 响应消息的 JSON 字元串
     * @since 2016年9月27日
     */
    public String toJSONString(boolean flag) {
        if (this.data instanceof Page) {
            return JSON.toJSONString(this.data , SerializerFeature.WriteNullStringAsEmpty);
        }
        else {
            if(flag) {
                return JSON.toJSONString(this);
            }else{
                return JSON.toJSONString(this, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty);
            }
        }
    }

    public String toJSONString() {
        if (this.data instanceof Page) {
            return JSON.toJSONString(this.data ,SerializerFeature.WriteNullStringAsEmpty);
        }
        else {
            return JSON.toJSONString(this, SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty);
        }
    }

    /**
     * @return the code
     * @see AjaxResponse#code
     */
    public Integer getCode() {
        return code;
    }

    /**
     * @param code
     *            the code to set
     * @see AjaxResponse#code
     */
    public AjaxResponse setCode(Integer code) {
        this.code = code;
        return this;
    }

    /**
     * @return the data
     * @see AjaxResponse#data
     */
    public Object getData() {
        return data;
    }

    /**
     * @param data
     *            the data to set
     * @see AjaxResponse#data
     */
    public AjaxResponse setData(Object data) {
        this.data = data;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
