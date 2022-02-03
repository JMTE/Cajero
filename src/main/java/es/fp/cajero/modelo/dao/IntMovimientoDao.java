package es.fp.cajero.modelo.dao;

import java.util.List;

import es.fp.cajero.modelo.beans.Cuenta;
import es.fp.cajero.modelo.beans.Movimiento;

//Aqui implementamos nuestro interfaz de Movimiento con los metodos de nuestra lógica y que serán necesarios en nuestra aplicacion
public interface IntMovimientoDao {

	
	List<Movimiento> findMovimientosByIdCuenta(int idCuenta);
	
	List<Movimiento> find10UltimosMovimientosbyIdCuenta(int idCuenta);
	
	int movimientoExtraccion(Cuenta cuenta, double cantidad);
	
	int movimientoIngreso(Cuenta cuenta, double cantidad);
	
	
	

	
	
}
