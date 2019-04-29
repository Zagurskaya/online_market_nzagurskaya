package com.gmail.zagurskaya.service.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserDTO {

    private Long id;
    @NotNull
    @Size(max = 70)
    private String username;
    @NotNull
    @Size(max = 200)
    private String password;
    @NotNull
    private Long roleId;

    public UserDTO(@NotNull @Size(max = 70) String username, @NotNull @Size(max = 200) String password, @NotNull Long roleId) {
        this.username = username;
        this.password = password;
        this.roleId = roleId;
    }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public String getUsername() {

        return username;
    }

    public void setUsername(String username) {

        this.username = username;
    }

    public String getPassword() {

        return password;
    }

    public void setPassword(String password) {

        this.password = password;
    }

    public Long getRoleId() {

        return roleId;
    }

    public void setRoleId(Long roleId) {

        this.roleId = roleId;
    }
}
