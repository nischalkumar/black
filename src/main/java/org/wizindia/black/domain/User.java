package org.wizindia.black.domain;

import org.hibernate.validator.constraints.NotEmpty;
import org.wizindia.black.common.Enums.Role;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by hari_om on 6/14/15.
 */
@Entity
@Table(name = "user")
public class User {

    @Id
    @Column(name="user_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotEmpty
    @Column(unique = true, nullable = false)
    private String login;

    @NotEmpty
    @Column(nullable = false)
    private String password;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Roles> rolesSet;

    //The default constructor only exists for the sake of JPA. You won’t use it directly, so it is designated as protected
    protected User() {
    }

    public User(String login, String password, Set<Roles> rolesSet) {
        this.login = login;
        this.password = password;
        this.rolesSet = rolesSet;
    }

    public User(User user) {
        super();
        this.id = user.getId();
        this.login = user.getLogin();
        this.password = user.getPassword();
        this.rolesSet = user.getRolesSet();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Roles> getRolesSet() {
        return rolesSet;
    }

    public void setRolesSet(Set<Roles> rolesSet) {
        this.rolesSet = rolesSet;
    }

    public Set<Role> getRoles() {
        Set<Role> roleSet= new HashSet<Role>();
        for( Roles roles : rolesSet) {
            roleSet.add(roles.getRole());
        }
        return roleSet;
    }
}
