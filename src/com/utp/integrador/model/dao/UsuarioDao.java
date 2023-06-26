
package com.utp.integrador.model.dao;

import com.utp.integrador.model.Usuario;
import java.util.List;

/**
 *
 * @author Usuario
 */
public interface UsuarioDao extends EntidadDao<Usuario, String>{
    Usuario findUsuarioByDNI(String dni);
}
