package cn.coblog.service.imp;

import cn.coblog.mapper.RolesMapper;
import cn.coblog.service.RolesService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 角色接口实现
 * @author chens
 * @version 1.0.0
 * @date 2020/8/10
 */
@Service
public class RolesServiceImpl implements RolesService {

    @Resource
    private RolesMapper rolesMapper;

}




