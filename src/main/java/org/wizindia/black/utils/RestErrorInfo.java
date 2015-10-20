package org.wizindia.black.utils;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by hari_om on 21/10/15.
 */
@XmlRootElement
public class RestErrorInfo {
    public final String detail;
    public final String message;

    public RestErrorInfo(Exception ex, String detail) {
        this.message = ex.getLocalizedMessage();
        this.detail = detail;
    }
}
