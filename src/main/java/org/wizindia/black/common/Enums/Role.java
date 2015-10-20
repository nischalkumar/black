package org.wizindia.black.common.Enums;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by hari_om on 6/14/15.
 */
public enum Role implements GrantedAuthority {
    STUDENT{
        @Override
        public String getAuthorityString() { return Role.ADMIN.name(); }
    },
    TEACHER{
        @Override
        public String getAuthorityString() { return Role.ADMIN.name(); }
    },
    PARENT{
        @Override
        public String getAuthorityString() { return Role.ADMIN.name(); }
    },
    PRINCIPAL{
        @Override
        public String getAuthorityString() { return Role.ADMIN.name(); }
    },
    ADMIN {
        @Override
        public String getAuthorityString() { return Role.ADMIN.name(); }
    };


    @Override
    public String getAuthority() {
        return  this.getAuthorityString();
    }

    public String getAuthorityString(){
        return null;
    }
}
