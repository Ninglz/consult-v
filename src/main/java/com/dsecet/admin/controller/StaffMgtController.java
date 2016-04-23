package com.dsecet.admin.controller;

import com.dsecet.admin.aop.annotation.OptsTrace;
import com.dsecet.core.domain.admin.OptsTraceLog;
import com.dsecet.core.domain.admin.Resources;
import com.dsecet.core.domain.admin.Role;
import com.dsecet.core.domain.admin.Staff;
import com.dsecet.core.service.admin.OptsTraceLogService;
import com.dsecet.core.service.admin.ResourcesService;
import com.dsecet.core.service.admin.RoleService;
import com.dsecet.core.service.admin.StaffService;
import com.dsecet.foundation.model.ErrorCode;
import com.dsecet.foundation.model.PageableResponse;
import com.dsecet.foundation.model.StatusResponse;
import com.dsecet.foundation.model.query.OptLogVo;
import com.dsecet.foundation.model.query.ResourcesVo;
import com.dsecet.foundation.model.query.RoleVo;
import com.dsecet.foundation.model.query.StaffVo;
import com.dsecet.util.ModelUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.function.Predicate;

/**
 * @author: lxl
 */

@Controller
@RequestMapping("/staff")
public class StaffMgtController{

    @Autowired
    private OptsTraceLogService optsTraceLogService;

    @Autowired
    private ResourcesService resourcesService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private StaffService staffService;

    @Autowired
    private Properties sysProperties;

    @RequestMapping(value = "/optLog", method = RequestMethod.GET)
    public String index(){
        return "staff/staff_log";
    }

    @RequestMapping(value = "/optLog/list", method = RequestMethod.GET)
    public
    @ResponseBody
    PageableResponse getOptLog(@RequestParam(required = false) String username,
                               @RequestParam(required = false) String optLogDate,
                               @RequestParam(required = false) String operation,
                               Pageable pageable) throws Exception{
        Long beg = null;
        Long end = null;
        if(!StringUtils.isBlank(optLogDate)){
            String[] dates = optLogDate.split("/");
            beg = ModelUtils.parseToMillis(dates[0] + " 00:00:00:000");
            end = ModelUtils.parseToMillis(dates[1] + " 23:59:59:999");
        }
        Page<OptsTraceLog> logsPage = optsTraceLogService.findOptsTraceLogByConditions(username, beg, end, operation, pageable);
        return PageableResponse.of(OptLogVo.of(logsPage), logsPage.getContent().size(), (int) logsPage.getTotalElements());
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView staffIndex(){
        List<Role> roles = roleService.getAllRoles();
        return new ModelAndView("staff/staff_list").addObject("roles", roles);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public
    @ResponseBody
    PageableResponse list(StaffVo vo,
                          Pageable pageable){
        Page<Staff> staffPage = staffService.getByConditions(vo, pageable);
        return PageableResponse.of(StaffVo.of(staffPage), staffPage.getContent().size(), (int) staffPage.getTotalElements());
    }

    @RequestMapping(value = "/resources/", method = RequestMethod.GET)
    public String resourcesIndex(){
        return "staff/resources_list";
    }

    @RequestMapping(value = "/resources/list", method = RequestMethod.GET)
    public
    @ResponseBody
    PageableResponse resourcesList(Pageable pageable){
        Page<Resources> resourcesPage = resourcesService.getResources(pageable);
        return PageableResponse.of(ResourcesVo.of(resourcesPage), resourcesPage.getContent().size(), (int) resourcesPage.getTotalElements());
    }

    @RequestMapping(value = "/role/", method = RequestMethod.GET)
    public ModelAndView roleIndex(){
        List<Resources> resources = resourcesService.getAllResources();
        return new ModelAndView("staff/role_list").addObject("resources", resources);
    }

    @RequestMapping(value = "/role/list", method = RequestMethod.GET)
    public
    @ResponseBody
    PageableResponse roleList(Pageable pageable){
        Page<Role> rolePage = roleService.getRoles(pageable);
        return PageableResponse.of(RoleVo.of(rolePage), rolePage.getContent().size(), (int) rolePage.getTotalElements());
    }

    @OptsTrace(value = OptsTraceLog.OperationType.CHANGE_ROLE_RESOURCE)
    @RequestMapping(value = "/role/add", method = RequestMethod.POST)
    public
    @ResponseBody
    StatusResponse roleAdd(@RequestParam("id") Long id,
                           @RequestParam(value = "name", required = false) String name,
                           @RequestParam(value = "code", required = false) String code,
                           @RequestParam("resources") Long[] resources){
        try{
            Role role;
            if(id != null){
                List<Long> updateResources = Arrays.asList(resources);
                role = roleService.getById(id);
                role.getResourceses().removeIf(new Predicate<Resources>(){
                    public boolean test(Resources r){
                        return !updateResources.contains(r.getId());
                    }
                });
                updateResources.stream().forEach(m -> {
                    if(role.getResourceses().stream().filter(s -> s.getId().equals(m)).count() < 1){
                        role.getResourceses().add(resourcesService.getById(m));
                    }
                });
                role.getResourceses().add(resourcesService.getById(Long.valueOf(sysProperties.getProperty("default.resource.own.id"))));
                roleService.save(role);
            }else{
                Arrays.fill(resources,Long.valueOf(sysProperties.getProperty("default.resource.own.id")));
                role = roleService.save(name, code, resources);
            }
            return StatusResponse.success(role);
        }catch(Exception e){
            return StatusResponse.error(ErrorCode.ROLE_ADD_FAILED);
        }

    }



    @OptsTrace(value = OptsTraceLog.OperationType.STAFF_ADD)
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public
    @ResponseBody
    StatusResponse add(@RequestParam("id") Long id,
                       @RequestParam(value = "username", required = true) String username,
                       @RequestParam(value = "password", required = false) String password,
                       @RequestParam(value = "comfirmed", required = false) String comfirmed,
                       @RequestParam(value = "roles", required = true) Long[] roles){
        StatusResponse sr = checkStaffParam(id, username, password, comfirmed);
        if(sr != null)
            return sr;
        try{
            Staff staff;
            if(id != null){
                List<Long> updateRole = Arrays.asList(roles);
                staff = staffService.getById(id);
                if(staff == null)
                    return StatusResponse.error(ErrorCode.USER_NO_SUCH);
                staff.username(username);
                staff.getRoles().removeIf(new Predicate<Role>(){
                    public boolean test(Role r){
                        return !updateRole.contains(r.getId());
                    }
                });
                updateRole.stream().forEach(e -> {
                    if(staff.getRoles().stream().filter(s -> s.getId().equals(e)).count() < 1){
                        staff.getRoles().add(roleService.getById(e));
                    }
                });
                staffService.update(staff);
            }else{
                staff = staffService.save(username, password, null, null, null, null, roles);
            }
            return StatusResponse.success(staff);
        }catch(Exception e){
            return StatusResponse.error(ErrorCode.USER_ADD_FAILED);
        }
    }

    private StatusResponse checkStaffParam(Long id, String username,String password,String comfirmed){
        if(id != null){
            if(StringUtils.isNotBlank(username)){
                if(!password.equals(comfirmed))
                    return StatusResponse.error(ErrorCode.PASSWORD_NOT_SAME);
            }
        }else{
            if(StringUtils.isBlank(username))
                return StatusResponse.error(ErrorCode.USER_EMPTY);
            if(StringUtils.isBlank(password))
                return StatusResponse.error(ErrorCode.PASSWORD_EMPTY);
            if(!password.equals(comfirmed))
                return StatusResponse.error(ErrorCode.PASSWORD_NOT_SAME);
        }
        return null;
    }

    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public
    @ResponseBody
    StatusResponse detail(@RequestParam("id") Long id){
        Staff staff = null;
        try{
            staff = staffService.getById(id);
            if(staff == null)
                return StatusResponse.error(ErrorCode.USER_NO_SUCH);
        }catch(Exception e){
            return StatusResponse.error(ErrorCode.BAD_CREDENTIAL);
        }
        return StatusResponse.success(StaffVo.of(staff));
    }

    @RequestMapping(value = "/role/detail", method = RequestMethod.GET)
    public
    @ResponseBody
    StatusResponse roleDetail(@RequestParam("id") Long id){
        Role role = null;
        try{
            role = roleService.getById(id);
            if(role == null)
                return StatusResponse.error(ErrorCode.ROLE_NO_SUCH);
        }catch(Exception e){
            return StatusResponse.error(ErrorCode.BAD_CREDENTIAL);
        }
        return StatusResponse.success(RoleVo.of(role));
    }


    @OptsTrace(value = OptsTraceLog.OperationType.STAFF_STATUS)
    @RequestMapping(value = "/status", method = RequestMethod.POST)
    public
    @ResponseBody
    StatusResponse update(
            @RequestParam("staffId") Long staffId, Boolean status){
        try{
            Staff staff = staffService.getById(staffId);
            if(staff == null)
                return StatusResponse.error(ErrorCode.USER_NO_SUCH);
            staff.setActive(status);
            staffService.update(staff);
        }catch(Exception e){
            return StatusResponse.error(ErrorCode.BAD_CREDENTIAL);
        }
        return StatusResponse.success(status);
    }

    @OptsTrace(value = OptsTraceLog.OperationType.STAFF_DEL)
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    public
    @ResponseBody
    StatusResponse del(
            @RequestParam("staffId") Long staffId){
        try{
            Staff s = staffService.getById(staffId);
            if(s == null){
                return StatusResponse.error(ErrorCode.USER_NO_SUCH);
            }
            staffService.del(staffId);
        }catch(Exception e){
            return StatusResponse.error(ErrorCode.BAD_CREDENTIAL);
        }
        return StatusResponse.success();
    }

    @OptsTrace(value = OptsTraceLog.OperationType.CHANGE_ROLE_RESOURCE)
    @RequestMapping(value = "/role/del", method = RequestMethod.POST)
    public
    @ResponseBody
    StatusResponse roleDel(
            @RequestParam("roleId") Long roleId){
        try{
            if(roleService.getById(roleId) == null){
                return StatusResponse.error(ErrorCode.ROLE_NO_SUCH);
            }
            if(!roleService.isDel(roleId)){
                return StatusResponse.error(ErrorCode.ROLE_NOT_DEL);
            }

            roleService.del(roleId);
        }catch(Exception e){
            return StatusResponse.error(ErrorCode.BAD_CREDENTIAL);
        }
        return StatusResponse.success();
    }
}
