package com.fuyi.upms.rpc.service;

import com.alibaba.dubbo.common.json.JSONArray;
import com.alibaba.dubbo.common.json.JSONObject;
import com.alibaba.dubbo.config.annotation.Service;
import com.fuyi.common.annotation.BaseServiceAnnotation;
import com.fuyi.common.base.BaseServiceImpl;
import com.fuyi.upms.rpc.api.IUpmsPermissionService;
import com.fuyi.upms.rpc.entity.*;
import com.fuyi.upms.rpc.mapper.UpmsPermissionMapper;
import com.fuyi.upms.rpc.mapper.UpmsRolePermissionMapper;
import com.fuyi.upms.rpc.mapper.UpmsSystemMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Service
@BaseServiceAnnotation
public class UpmsPermissionServiceImpl extends BaseServiceImpl<UpmsPermissionMapper, UpmsPermission, UpmsPermissionExample> implements IUpmsPermissionService {

    @Autowired
    private UpmsRolePermissionMapper upmsRolePermissionMapper;

    @Autowired
    private UpmsPermissionMapper upmsPermissionMapper;

    @Autowired
    private UpmsSystemMapper upmsSystemMapper;

    @Override
    public Object selectPermissionByUserRoleId(List<UpmsRole> roles) {
        if (roles != null && roles.size() > 0) {
            for (UpmsRole role : roles) {
                UpmsRolePermissionExample upmsRolePermissionExample = new UpmsRolePermissionExample();
                upmsRolePermissionExample.createCriteria().andRoleIdEqualTo(role.getRoleId());
                List<UpmsRolePermission> upmsRolePermissions = upmsRolePermissionMapper.selectByExample(upmsRolePermissionExample);

                // 目录
                JSONArray folders = new JSONArray();
                for (UpmsRolePermission upmsRolePermission : upmsRolePermissions) {
                    UpmsPermission upmsPermissionFolder = upmsPermissionMapper.selectByPrimaryKey(upmsRolePermission.getPermissionId());

                    if (upmsPermissionFolder != null && upmsPermissionFolder.getType() != 1) {
                        continue;
                    }
                    JSONObject folder = new JSONObject();
                    folder.put("id", upmsPermissionFolder.getPermissionId());
                    folder.put("path", upmsPermissionFolder.getUri());
                    folder.put("component", upmsPermissionFolder.getPermissionValue());
                    folder.put("name", upmsPermissionFolder.getName());
                    folder.put("iconCls", upmsPermissionFolder.getIcon());
                    folders.add(folder);

                    // 菜单
                    JSONArray menus = new JSONArray();
                    for (UpmsRolePermission upmsRolePermissionMenu : upmsRolePermissions) {
                        UpmsPermission upmsPermissionMenu = upmsPermissionMapper.selectByPrimaryKey(upmsRolePermissionMenu.getPermissionId());

                        if ((upmsPermissionMenu != null && upmsPermissionMenu.getType() != 2)
                                || upmsPermissionMenu.getPid() != upmsPermissionFolder.getPermissionId()) {
                            continue;
                        }

                        JSONObject menu = new JSONObject();
                        menu.put("id", upmsPermissionMenu.getPermissionId());
                        menu.put("path", upmsPermissionMenu.getUri());
                        menu.put("component", upmsPermissionMenu.getPermissionValue());
                        menu.put("name", upmsPermissionMenu.getName());
                        menu.put("iconCls", upmsPermissionMenu.getIcon());
                        menus.add(menu);

                        // 按钮
                        JSONArray buttons = new JSONArray();
                        for (UpmsRolePermission upmsRolePermissionButton : upmsRolePermissions) {
                            UpmsPermission upmsPermissionButton = upmsPermissionMapper.selectByPrimaryKey(upmsRolePermissionButton.getPermissionId());

                            if ((upmsPermissionButton != null && upmsPermissionButton.getType() != 3)
                                    || upmsPermissionButton.getPid() != upmsPermissionMenu.getPermissionId()) {
                                continue;
                            }
                            JSONObject button = new JSONObject();
                            button.put("id", upmsPermissionButton.getPermissionId());
                            button.put("path", upmsPermissionButton.getUri());
                            button.put("component", upmsPermissionButton.getPermissionValue());
                            button.put("name", upmsPermissionButton.getName());
                            button.put("iconCls", upmsPermissionButton.getIcon());
                            buttons.add(button);
                        }
                        menu.put("children", buttons);
                    }
                    folder.put("children", menus);
                }
                return folders;
            }
        }
        return null;
    }

    @Override
    public Object selectPermissionTree() {
        // 系统
        UpmsSystemExample upmsSystemExample = new UpmsSystemExample();
        upmsSystemExample.createCriteria()
                .andStatusEqualTo((byte) 1);
        upmsSystemExample.setOrderByClause("orders asc");
        List<UpmsSystem> upmsSystems = upmsSystemMapper.selectByExample(upmsSystemExample);

        JSONArray systems = new JSONArray();
        if (upmsSystems != null && upmsSystems.size() > 0) {
            for (UpmsSystem upmsSystem : upmsSystems) {
                JSONObject system = new JSONObject();
                system.put("id", upmsSystem.getSystemId());
                system.put("name", upmsSystem.getTitle());
                systems.add(system);

                UpmsPermissionExample upmsPermissionExample = new UpmsPermissionExample();
                upmsPermissionExample.createCriteria().andSystemIdEqualTo(upmsSystem.getSystemId()).andStatusEqualTo((byte)1);
                upmsPermissionExample.setOrderByClause("orders asc");
                List<UpmsPermission> upmsPermissions = upmsPermissionMapper.selectByExample(upmsPermissionExample);

                // 目录
                JSONArray folders = new JSONArray();
                for (UpmsPermission upmsPermissionFolder : upmsPermissions) {
                    if (upmsPermissionFolder.getType() != 1) {
                        continue;
                    }

                    JSONObject folder = new JSONObject();
                    folder.put("id", upmsPermissionFolder.getPermissionId());
                    folder.put("name", upmsPermissionFolder.getName());
                    folders.add(folder);

                    // 菜单
                    JSONArray menus = new JSONArray();
                    for (UpmsPermission upmsPermissionMenu : upmsPermissions) {
                        if (upmsPermissionMenu.getType() != 2 || upmsPermissionMenu.getPid().intValue() != upmsPermissionFolder.getPermissionId().intValue()) {
                            continue;
                        }

                        JSONObject menu = new JSONObject();
                        menu.put("id", upmsPermissionMenu.getPermissionId());
                        menu.put("name", upmsPermissionMenu.getName());
                        menus.add(menu);

                        // 按钮
                        JSONArray buttons = new JSONArray();
                        for (UpmsPermission upmsPermissionButton : upmsPermissions) {
                            if (upmsPermissionButton.getType() != 3 || upmsPermissionButton.getPid().intValue() != upmsPermissionMenu.getPermissionId().intValue()) {
                                continue;
                            }

                            JSONObject button = new JSONObject();
                            button.put("id", upmsPermissionButton.getPermissionId());
                            button.put("name", upmsPermissionButton.getName());
                            buttons.add(button);
                        }
                        menu.put("children", buttons);
                    }
                    folder.put("children", menus);
                }
                system.put("children", folders);
            }
        }
        return systems;
    }

    @Override
    public Object selectPermissionListByRoleId(int roleId) {
        UpmsRolePermissionExample upmsRolePermissionExample = new UpmsRolePermissionExample();
        upmsRolePermissionExample.createCriteria().andRoleIdEqualTo(roleId);
        List<UpmsRolePermission> upmsRolePermissions = upmsRolePermissionMapper.selectByExample(upmsRolePermissionExample);

        List<Integer> checkedPermissions = new ArrayList<>(upmsRolePermissions.size());
        for (UpmsRolePermission upmsRolePermission : upmsRolePermissions) {
            checkedPermissions.add(upmsRolePermission.getPermissionId());
        }
        return checkedPermissions;
    }
}
