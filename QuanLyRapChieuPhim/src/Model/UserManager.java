/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author ASUS
 */
import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;
import java.util.List;

public class UserManager {
    private List<User> userList = new ArrayList<>();

    // Đăng ký tài khoản người dùng
    public boolean registerUser(String username, String password) {
        for (User user : userList) {
            if (user.getUsername().equals(username)) {
                return false; // Tên người dùng đã tồn tại
            }
        }
        userList.add(new User(username, password));
        return true;
    }

    // Đăng nhập người dùng
    public User loginUser(String username, String password) {
        for (User user : userList) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null; // Sai tên đăng nhập hoặc mật khẩu
    }

    @Override
    public String toString() {
        return "UserManager{" + "userList=" + userList + '}';
    }
    
}


