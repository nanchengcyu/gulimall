package cn.nanchengyu.mybatisplus.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

@Data

public class User {
//    @TableId
    private Long id;
    private String name;
    private Integer age;
    private String email;
//    @TableLogic
//    private Integer isDeleted;
}
