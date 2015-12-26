package org.wizindia.black.domain;

import org.wizindia.black.common.Enums.Role;

import javax.persistence.*;

/**
 * Created by hari_om on 13/9/15.
 */
@Entity
@Table(name="roles")
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="roles_id")
    private Integer id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    User user;

    @Column(name="role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    public Roles() {
    }

    public Roles(Integer id, Role role) {
        this.id = id;
        this.role = role;
    }

    public Roles(Integer id, Role role, User user) {
        this.id = id;
        this.role = role;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
