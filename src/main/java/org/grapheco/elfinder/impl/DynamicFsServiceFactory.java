package org.grapheco.elfinder.impl;

import org.grapheco.elfinder.localfs.LocalFsVolume;
import org.grapheco.elfinder.service.FsService;
import org.grapheco.elfinder.service.FsServiceFactory;
import org.grapheco.elfinder.service.FsVolume;
import org.springframework.beans.factory.annotation.Autowired;
import top.entity.User;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: SpringMVC_template
 * @description: 根据HTTPRequest返回不同用户的FsService
 * @author: T_yang
 * @create: 2021-07-07 06:33
 **/
public class DynamicFsServiceFactory implements FsServiceFactory {
    @Autowired
    FsService _fsService;


//使用时配置volmueMap
    @Override
    public FsService getFileService(HttpServletRequest request,
                                    ServletContext servletContext)
    {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        String username = user.getUsername();
        Map<String,FsVolume> volumeMap = new HashMap<>();
        FsVolume volume_1 = new LocalFsVolume();
        FsVolume volume_2 = new LocalFsVolume();
        volume_1.setName("Myfiles");
        volume_1.setRootDir(new File("/home/yang/webfile/"+username+"/a"));

        volumeMap.put("A",volume_1);
        _fsService.setVolumeMap(volumeMap);
        return _fsService;
    }

    public FsService getFsService()
    {
        return _fsService;
    }
//根据不同用户的HTTP Request，返回一定FsService
    public void setFsService(FsService fsService)
    {
        _fsService = fsService;
    }
}
