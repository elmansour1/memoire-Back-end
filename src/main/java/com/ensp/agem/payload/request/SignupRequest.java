/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ensp.agem.payload.request;

import com.ensp.agem.data.Role;
import java.util.List;
import java.util.Set;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 *
 * @author mansour
 */
public class SignupRequest {
//    @NotBlank
//    @Size(min = 3, max = 20)
    private String username;
 
//    @NotBlank
//    @Size(max = 50)
//    @Email
    private String email;
    
    private List<Role> role;
    
//    @NotBlank
//    @Size(min = 6, max = 40)
    private String password;
  
    public String getUsername() {
        return username;
    }
 
    public void setUsername(String username) {
        this.username = username;
    }
 
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
    
    public List<Role> getRole() {
      return this.role;
    }
    
    public void setRole(List<Role> role) {
      this.role = role;
    }
}
