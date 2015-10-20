package org.wizindia.black.common;

import java.util.List;

/**
 * Created by hari_om on 1/10/15.
 */
public class DaoResponse {
    Object object;
    List<Exception> exceptions;

    public DaoResponse(Object object, List<Exception> exceptions) {
        this.object = object;
        this.exceptions = exceptions;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public List<Exception> getExceptions() {
        return exceptions;
    }

    public void setExceptions(List<Exception> exceptions) {
        this.exceptions = exceptions;
    }
}
