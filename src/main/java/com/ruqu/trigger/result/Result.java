package com.ruqu.trigger.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author Reny
 * @Date 2018-09-09 10:06
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "通用返回对象",description = "通用返回对象-Result<T>")
public class Result<T> {

    @ApiModelProperty(value = "返回代码",name = "code")
    private Integer code;
    @ApiModelProperty(value = "返回信息",name = "msg")
    private String msg;
    @ApiModelProperty(value = "总数据条数",name = "count")
    private Integer count;
    @ApiModelProperty(value = "返回数据",name = "data")
    private T data;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"code\":").append(code);
        sb.append(",\"msg\":\"").append(msg).append('\"');
        sb.append(",\"count\":").append(count);
        sb.append(",\"data\":").append(data);
        sb.append('}');
        return sb.toString();
    }

    /** 成功无返回值 **/
    public static Result success(){
        Result result = new Result();
        result.setCode(200);
        result.setMsg("操作成功");
        return result;
    }

    /** 成功且带数据 **/
    public static Result successData(Object object){
        Result result = new Result();
        result.setCode(ResultEnum.SUCCESS.getCode());
        result.setMsg(ResultEnum.SUCCESS.getMsg());
        result.setData(object);
        return result;
    }

    /** 成功枚举 **/
    public static Result successEnum(ResultEnum resultEnum){
        Result result = new Result();
        result.setCode(resultEnum.getCode());
        result.setMsg(resultEnum.getMsg());
        return result;
    }

    /** 成功自定义状态码和信息 **/
    public static Result successCode(Integer code,String msg){
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    /** 失败无返回值 **/
    public static Result error(){
        Result result = new Result();
        result.setCode(400);
        result.setMsg("操作失败");
        return result;
    }

    /** 失败枚举 **/
    public static Result errorEnum(ResultEnum resultEnum){
        Result result = new Result();
        result.setCode(resultEnum.getCode());
        result.setMsg(resultEnum.getMsg());
        return result;
    }

    /** 自定义失败 **/
    public static Result errorCode(Integer code,String msg){
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    /** 自定义失败详情 **/
    public static Result errorEnumAndInfo(ResultEnum resultEnum,String msg){
        Result result = new Result();
        result.setCode(resultEnum.getCode());
        result.setMsg(resultEnum.getMsg()+" , "+msg);
        return result;
    }

    /**------------使用链式编程，返回类本身-----------**/

    public Result data(Object object){
        this.setData((T) object);
        return this;
    }

    public Result code(Integer code){
        this.setCode(code);
        return this;
    }

    public Result count(Integer count){
        this.setCount(count);
        return this;
    }

    public Result msg(String msg){
        this.setMsg(msg);
        return this;
    }

}
