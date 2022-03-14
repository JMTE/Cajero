package es.fp.cajero.modelo.dao;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.fp.cajero.modelo.beans.Cuenta;
import es.fp.cajero.modelo.repository.IntCuentaRepo;

/* @Service se encarga de gestionar las operaciones más importantes a nivel de la aplicación y aglutina llamadas a varios repositorios 
 * de forma simultánea. Su tarea fundamental es la de agregador.*/

@Service
public class CuentaDaoImplMy8Sb implements IntCuentaDao {

	/*Lo que hace un autowired es buscar un objeto manejado (beans) que implementen determinada interfaz para hacer referencia a él.
	   De esta manera no es neceario crear una instancia nueva del objeto cada vez que se necesite la funcionalidad de determinada clase */
	
	@Autowired
	private IntCuentaRepo CRepo ;
	
	
	
	@Override
	public List<Cuenta> findAll() {
		
		return CRepo.findAll();
	}


	//Con este metodo buscamos una cuenta segun su idCuenta y si no existe devolvemos un null.
	@Override
	public Cuenta findById(int idCuenta) {
		// TODO Auto-generated method stub
		Cuenta cuenta= new Cuenta();
		cuenta.setIdCuenta(idCuenta);
		int pos=CRepo.findAll().indexOf(cuenta);
		if (pos==-1) {
			return null;
		}
		else {
			return CRepo.getById(idCuenta);
		}
		
	}

	

	@Override
	public int ingresarDinero(Cuenta cuenta, double cantidad) {
		// TODO Auto-generated method stub
		
		//Actualizamos el saldo de la cuenta
		cuenta.setSaldo(cuenta.getSaldo()+cantidad);
		//Actualizamos los valores en la BBDD
		int filas=0;
		try {
			CRepo.save(cuenta);
			filas=1;
		}catch (Exception e) {
			e.printStackTrace();
			
		}
		return filas;
		
	}

	@Override
	public int extraerDinero(Cuenta cuenta, double cantidad) {
		// TODO Auto-generated method stub
		//Actualizamos el saldo de la cuenta
		cuenta.setSaldo(cuenta.getSaldo()-cantidad);
		
		//Actualizamos los valores en la BBDD
				int filas=0;
				try {
					CRepo.save(cuenta);
					filas=1;
				}catch (Exception e) {
					e.printStackTrace();
					
				}
				return filas;
	}

}
