package com.atguigu.gulimall.product.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * ClassName: AttrRespVo
 * Package: com.atguigu.gulimall.product.vo
 * Description:
 *
 * @Author 南城余
 * @Create 2024/3/11 21:10
 * @Version 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AttrRespVo extends AttrVo {
    private String catelogName;
    private String groupName;
    private Long[] catelogPath;

}
