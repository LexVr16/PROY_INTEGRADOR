
package com.utp.integrador.model.dao;

import com.utp.integrador.model.Usuario;

/**
 *
 * @author Usuario
 */
public interface LoginDao {
    public Usuario validateLogin(String dni, String password);
}
