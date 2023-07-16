
package com.utp.integrador.model.dao;

import com.utp.integrador.model.CuentaBancaria;
import java.util.List;

/**
 *
 * @author Usuario
 */
public interface CuentaBancariaDao extends EntidadDao<CuentaBancaria, String>{
    List<CuentaBancaria> findAllAccountsByDNI(String dni);
    void updateSaldo(CuentaBancaria cuentaBancaria);
}
