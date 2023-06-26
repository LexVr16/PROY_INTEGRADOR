
package com.utp.integrador.model.dao;

import com.utp.integrador.model.CuentaBancaria;
import java.util.List;

/**
 *
 * @author Usuario
 */
public interface CuentaBancariaDao extends EntidadDao<CuentaBancaria, String>{
    List<CuentaBancaria> findAllByIdUsuario(String idUsuario);
}
