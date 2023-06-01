/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.utp.integrador.model.dao;

import com.utp.integrador.model.Usuario;

/**
 *
 * @author Usuario
 */
public interface LoginDao {
    public Usuario validateLogin(String dni, String password);
}
