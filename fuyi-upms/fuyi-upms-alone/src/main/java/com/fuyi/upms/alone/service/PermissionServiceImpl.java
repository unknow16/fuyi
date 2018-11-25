package com.fuyi.upms.alone.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fuyi.upms.dao.entity.UpmsPermission;
import com.fuyi.upms.dao.entity.UpmsRole;
import com.fuyi.upms.dao.entity.UpmsRolePermission;
import com.fuyi.upms.dao.entity.UpmsRolePermissionExample;
import com.fuyi.upms.dao.mapper.UpmsPermissionMapper;
import com.fuyi.upms.dao.mapper.UpmsRolePermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName PermissionServiceImpl
 * @Description TODO
 * @Author Administrator
 * @Date 2018/11/23 16:35
 * @Version 1.0
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private UpmsRolePermissionMapper upmsRolePermissionMapper;

    @Autowired
    private UpmsPermissionMapper upmsPermissionMapper;

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
}