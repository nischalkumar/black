package org.wizindia.black.common.Enums;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by hari_om on 6/14/15.
 */
public enum Role implements GrantedAuthority {
    ANY{
        @Override
        public String getAuthorityString() { return Role.ANY.name(); }
    },
    STUDENT{
        @Override
        public String getAuthorityString() { return Role.STUDENT.name(); }
    },
    TEACHER{
        @Override
        public String getAuthorityString() { return Role.TEACHER.name(); }
    },
    PARENT{
        @Override
        public String getAuthorityString() { return Role.PARENT.name(); }
    },
    PRINCIPAL{
        @Override
        public String getAuthorityString() { return Role.PRINCIPAL.name(); }
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
