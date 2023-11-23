package com.code.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.code.sys.entity.Code;
import com.code.sys.entity.Dept;

/**
 * 单位表 服务类
 * @author
 * @since 2023-11-17
 */
public interface CodeService extends IService<Code> {

    Code getByCode(String email, String code);
}
