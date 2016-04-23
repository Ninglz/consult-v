package com.dsecet.security.filter;


import com.dsecet.core.service.ImageService;
import com.dsecet.util.image.ImageType;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * User: lxl
 */
public class ImageViewFilter implements Filter {

    private ServletContext servletContext;

    private static final Map<String, String> mimeTable = new HashMap<String, String>();
    {
        mimeTable.put("jpg", "jpeg");
        mimeTable.put("jpeg", "jpeg");
        mimeTable.put("png", "png");
        mimeTable.put("gif", "gif");
        mimeTable.put("bmp", "jpeg");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        servletContext = filterConfig.getServletContext();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            Properties sysProperties = getSpringContext().getBean("sysProperties", Properties.class);
            ImageService imageService = getSpringContext().getBean("imageService", ImageService.class);

            HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse resp = (HttpServletResponse) response;
            String uri = req.getRequestURI();
            String prefix = sysProperties.getProperty("product.image.view.prefix");
            if (uri.startsWith(prefix)) {
                Object rawImageType = req.getParameter("image_type");
                ImageType imageType = null;
                if (rawImageType != null) {
                    imageType = ImageType.valueOf(((String) rawImageType).toUpperCase());
                }
                String fileName = uri.substring(uri.indexOf(prefix) + prefix.length(), uri.length());
                Path path = imageService.getImage(fileName, imageType);
                if (path == null) {
                    path = imageService.getImagePrevious(request,fileName,imageType);
                }
                if (path == null) {
                    chain.doFilter(request, response);
                    return;
                }
                String extension = path.toString().substring(path.toString().lastIndexOf(".") + 1);
                resp.setHeader("Cache-Control", "max-age=3600, must-revalidate");
                resp.setContentType("image/" + mimeTable.get(extension.toLowerCase()));
                resp.setCharacterEncoding("UTF-8");
                ServletOutputStream out = resp.getOutputStream();
                Files.copy(path, out);
            } else {
                chain.doFilter(request, response);
                return;
            }
        } catch (Exception e) {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        servletContext = null;
    }

    private WebApplicationContext getSpringContext() {
        return WebApplicationContextUtils.getWebApplicationContext(servletContext);
    }
}
