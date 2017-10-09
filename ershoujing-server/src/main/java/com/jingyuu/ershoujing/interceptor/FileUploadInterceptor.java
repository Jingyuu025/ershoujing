package com.jingyuu.ershoujing.interceptor;

import com.jingyuu.ershoujing.common.utils.CommonUtil;
import lombok.Data;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author owen
 * @date 2017-09-30
 *
 * https://my.oschina.net/scjelly/blog/523705
 */
@Data
public class FileUploadInterceptor extends HandlerInterceptorAdapter {
    // 最大上传
    private long maxSize;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        if (CommonUtil.isNotEmpty(request) && ServletFileUpload.isMultipartContent(request)) {
            ServletRequestContext ctx = new ServletRequestContext(request);
            long requestSize = ctx.contentLength();
            if (requestSize > maxSize) {
                throw new MaxUploadSizeExceededException(maxSize);
            }
        }
        return true;
    }
}
