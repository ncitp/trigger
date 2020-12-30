package com.ruqu.trigger.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * @Project TriggerTools
 * @Package com.ruqu.trigger.entity
 * @Author reny
 * @Date 2020 12 2020/12/19 17:54
 * @Description
 */
@Data
@TableName(value = "config")
@ApiModel(value = "ConfigEntity", description = "配置")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ConfigEntity extends BaseEntity{

    @ApiModelProperty(value = "方法",name = "method")
    private String method;
    @ApiModelProperty(value = "地址",name = "url")
    private String url;
    @ApiModelProperty(value = "端口",name = "post")
    private Integer post;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":\"")
                .append(id).append('\"');
        sb.append(",\"method\":\"")
                .append(method).append('\"');
        sb.append(",\"url\":\"")
                .append(url).append('\"');
        sb.append(",\"post\":\"")
                .append(post).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
