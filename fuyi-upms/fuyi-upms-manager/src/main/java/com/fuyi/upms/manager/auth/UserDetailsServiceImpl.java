package com.fuyi.upms.manager.auth;

import com.fuyi.upms.rpc.entity.UpmsUserExample;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * spring security 中从db获取userDetail的Service
 */
public class UserDetailsServiceImpl implements UserDetailsService {

//    @Autowired
//    private UpmsUserMapper upmsUserMapper;
//
//    @Autowired
//    private UpmsUserRoleMapper upmsUserRoleMapper;
//
//    @Autowired
//    private UpmsRoleMapper upmsRoleMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //从数据库查出user
        UpmsUserExample upmsUserExample = new UpmsUserExample();
        upmsUserExample.createCriteria().andUsernameEqualTo(username);
//        List<UpmsUser> upmsUsers = upmsUserMapper.selectByExample(upmsUserExample);
//
//
//        if (upmsUsers != null && upmsUsers.size() >= 1) {
//            UpmsUser user = upmsUsers.get(0);
//
//            UpmsUserRoleExample upmsUserRoleExample = new UpmsUserRoleExample();
//            upmsUserRoleExample.createCriteria().andUserIdEqualTo(user.getUserId());
//            List<UpmsUserRole> upmsUserRoles = upmsUserRoleMapper.selectByExample(upmsUserRoleExample);
//
//            ArrayList<UpmsRole> upmsRoles = new ArrayList<>(upmsUserRoles.size());
//            for (UpmsUserRole upmsUserRole : upmsUserRoles) {
//                UpmsRole upmsRole = upmsRoleMapper.selectByPrimaryKey(upmsUserRole.getRoleId());
//                upmsRoles.add(upmsRole);
//            }
//            user.setRoles(upmsRoles);
//
//            return new UserDetailsImpl(user);
//        } else {
//            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
//        }
        return null;
    }
}
