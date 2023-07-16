
package com.utp.integrador.model.dao;

import com.utp.integrador.model.Moneda;
import java.util.List;

/**
 *
 * @author Usuario
 */
public interface MonedaDao extends EntidadDao<Moneda, String>{
    List<Moneda> getLastDistinctNombreMoneda();
    Moneda getLastExchangeRate(String nombreMoneda);
    Moneda getMonedaByNombre(String nombreMoneda);
}
