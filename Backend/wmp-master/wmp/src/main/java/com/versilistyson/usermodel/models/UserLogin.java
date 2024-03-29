package com.versilistyson.usermodel.models;

import com.versilistyson.usermodel.logging.Loggable;

@Loggable
public class UserLogin
{
    private String username;
    private String password;

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
}
