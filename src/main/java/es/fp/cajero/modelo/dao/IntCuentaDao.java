package es.fp.cajero.modelo.dao;

import java.util.List;

import es.fp.cajero.modelo.beans.Cuenta;

//Aqui implementamos nuestro interfaz de Cuenta con los metodos de nuestra lógica y que serán necesarios en nuestra aplicacion
public interface IntCuentaDao {
	
	List<Cuenta> findAll();
	
	Cuenta findById(int idCuenta);
	
	int actualizarCuenta(Cuenta cuenta);
	

}
