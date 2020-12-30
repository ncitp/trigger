package com.ruqu.trigger.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Project ydimp
 * @Package cn.net.ydesign.ydimp.ydupm.po
 * @Author reny
 * @Date 2020/12/11 19:23
 * @Description 基础实体
 */
@Data
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 71951107387335132L;

    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    protected String id;

    @TableField(value = "create_date", fill = FieldFill.INSERT)
    private Date createDate;
    
    @TableField(value = "update_date", fill = FieldFill.INSERT_UPDATE)
    private Date updateDate;

}
