package com.jingyuu.ershoujing.common.exception;

import com.jingyuu.ershoujing.common.statics.enums.ErrorEnum;
import lombok.Getter;

/**
 * @author owen
 * @date 2017-09-07
 */
@Getter
public class JyuException extends Exception {
    // 错误编码
    private Integer errCode;
    // 错误信息
    private String errMessage;

    /**
     * 默认异常
     */
    public JyuException() {
        this(ErrorEnum.UNKNOW_ERROR);
    }

    /**
     * @param errorEnum 错误枚举
     */
    public JyuException(ErrorEnum errorEnum) {
        super(errorEnum.getErrMessage());
        this.errCode = errorEnum.getErrCode();
        this.errMessage = errorEnum.getErrMessage();
    }

    /**
     * @param errorEnum   错误枚举
     * @param description 错误详细内容
     */
    public JyuException(ErrorEnum errorEnum, String description) {
        super(errorEnum.getErrMessage().concat(":").concat(description));
        this.errCode = errorEnum.getErrCode();
        this.errMessage = errorEnum.getErrMessage();
    }

    /**
     * 屏蔽异常堆栈
     */
    @Override
    public Throwable fillInStackTrace() {
        return null;
    }


    /***
     * 获取业务处里类调用链，方便异常时查问题
     *
     * 注意：仅显示业务类调用链，以com.jingyuu，动态代理类也不在其中
     *
     * @return
     */
    private String getStrackTrace() {
        StackTraceElement[] ste;
        if (null == super.getCause()) {
            ste = Thread.currentThread().getStackTrace();
        } else {
            ste = super.getCause().getStackTrace();
        }
        StringBuilder trace = new StringBuilder();

        for (int i = 0, n = ste == null ? 0 : ste.length; i < n; i++) {
            String methodName = ste[i].getMethodName();
            int lineNumber = ste[i].getLineNumber();
            String className = ste[i].getClassName();

            if (null == className || !className.startsWith("com.jingyuu") || className.contains("$$") || className.contains(this.getClass().getName())) {
                continue;
            }

            trace.append(className).append(".").append(methodName).append(":").append(lineNumber).append(System.getProperty("line.separator", "\r\n"));
        }
        return trace.toString();
    }
}
