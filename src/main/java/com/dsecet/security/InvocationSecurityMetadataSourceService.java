package com.dsecet.security;

import com.dsecet.core.domain.admin.Resources;
import com.dsecet.core.domain.admin.Role;
import com.dsecet.core.service.admin.RoleService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author: lxl
 */
@Getter
@Setter
@Service
public class InvocationSecurityMetadataSourceService implements FilterInvocationSecurityMetadataSource{

    private PathMatcher match = new AntPathMatcher();

    @Autowired
    private RoleService roleService;

    private static ConcurrentMap<String, Collection<ConfigAttribute>> resourceMap = new ConcurrentHashMap<String, Collection<ConfigAttribute>>();

    @Transactional
    public void loadResourceDefined(){
        List<Role> roles = roleService.getAllRoles();
        roles.stream().forEach(
                e -> {
                    Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
                    ConfigAttribute ca = new SecurityConfig(e.getRoleCode());
                    atts.add(ca);
                    Set<Resources> resourcesList = e.getResourceses();
                    for(Resources resource : resourcesList){
                        this.resourceMap.put(resource.getPatternUrl(), atts);
                    }
        });
    }

    // According to a URL, Find out permission configuration of this URL.
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException{

        String url = ((FilterInvocation)object).getRequestUrl(); ;
        if (resourceMap == null) {
            loadResourceDefined();
        }
        Iterator<String> ite = resourceMap.keySet().iterator();
        while(ite.hasNext()){
            String checkUrl = ite.next();
            if(match.match(checkUrl,url)){
                if(resourceMap != null){
                    Collection<ConfigAttribute> returnCollection = resourceMap.get(checkUrl);
                    return returnCollection;
                }
            }
        }
        return null;
    }

    public boolean supports(Class<?> clazz){
        return true;
    }

    public Collection<ConfigAttribute> getAllConfigAttributes(){
        return null;
    }

    public ConcurrentMap<String, Collection<ConfigAttribute>> getResources(){
        return resourceMap;
    }

    public void refresh(){
        resourceMap.clear();
        loadResourceDefined();
    }

}
