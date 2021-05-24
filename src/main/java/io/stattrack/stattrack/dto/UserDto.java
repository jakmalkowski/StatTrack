package io.stattrack.stattrack.dto;

import io.stattrack.stattrack.models.UserModel;

public class UserDto {
    private String id;
    private String uname;
    private String password;
    private String passwordRepeat;
    private String email;

    public UserDto(UserModel userModel) {
        this.id = userModel.getId();
        this.uname = userModel.getUname();
        this.password = userModel.getPassword();
        this.email = userModel.getEmail();
    }

    public UserDto() {

    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getPasswordRepeat() {
        return passwordRepeat;
    }

    public void setPasswordRepeat(String passwordRepeat) {
        this.passwordRepeat = passwordRepeat;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
