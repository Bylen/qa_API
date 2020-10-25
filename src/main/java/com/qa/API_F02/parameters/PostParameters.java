package com.qa.API_F02.parameters;

/**
 * @author urPaPa
 * @date 2020/9/17 10:15
 */
public class PostParameters {
//    private String userName;
    private String email;
    private String password;

    public PostParameters(){

    }


    //login
    public PostParameters(String email, String password){
//        this.userName = userName;
        this.email = email;
        this.password = password;
    }

//    public String getUserName() {
//        return userName;
//    }
//
//    public void setUserName(String userName) {
//        this.userName = userName;
//    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
