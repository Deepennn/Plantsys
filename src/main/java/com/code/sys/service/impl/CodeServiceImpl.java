package com.code.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.code.sys.entity.Code;
import com.code.sys.entity.Dept;
import com.code.sys.mapper.CodeMapper;
import com.code.sys.mapper.DeptMapper;
import com.code.sys.service.CodeService;
import com.code.sys.service.DeptService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 单位表 服务实现类
 * </p>
 *
 * @author
 * @since 2023-11-17
 */
@Service
public class CodeServiceImpl extends ServiceImpl<CodeMapper, Code> implements CodeService {

    @Override
    public Code getByCode(String email, String code) {
        return this.baseMapper.getByCode(email, code);
    }
}
