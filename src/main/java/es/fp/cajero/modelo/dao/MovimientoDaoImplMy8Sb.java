package es.fp.cajero.modelo.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.fp.cajero.modelo.beans.Cuenta;
import es.fp.cajero.modelo.beans.Movimiento;
import es.fp.cajero.modelo.repository.IntMovimientosRepo;

/* @Service se encarga de gestionar las operaciones más importantes a nivel de la aplicación y aglutina llamadas a varios repositorios 
 * de forma simultánea. Su tarea fundamental es la de agregador.*/
@Service
public class MovimientoDaoImplMy8Sb implements IntMovimientoDao {

	/*Lo que hace un autowired es buscar un objeto manejado (beans) que implementen determinada interfaz para hacer referencia a él.
	 *  De esta manera no es neceario crear una instancia nueva del objeto cada vez que se necesite la funcionalidad de determinada clase */
	@Autowired
	private IntMovimientosRepo mRepo;
	
	
	//Con este metodo buscamos todos los movimientos de una cuenta según su idCuenta, para ello utilizamos nuestro 
	//metodo creado por Query en el Repository
	@Override
	public List<Movimiento> findMovimientosByIdCuenta(int idCuenta) {
		// TODO Auto-generated method stub
		return mRepo.findMovimientosByIdCuenta(idCuenta);
	}
	
	//Con este metodo añadimos un movimiento a la lista de movimientos
	@Override
	public int añadirMovimiento(Movimiento movimiento) {
		
		System.out.println("Llego para añadir el movimiento");
		System.out.println(movimiento.getCuenta().getIdCuenta());
		int filas=0;
		try {
			mRepo.save(movimiento);
			filas=1;
		}catch (Exception e) {
			e.printStackTrace();
			
		}
		return filas;
		
	}

	
	//Con este metodo devolvemos los 10 ultimos movimientos de una cuenta introducidos en una lista
	@Override
	public List<Movimiento> find10UltimosMovimientosbyIdCuenta(int idCuenta) {
		// TODO Auto-generated method stub
		List<Movimiento> lista=new ArrayList<Movimiento>();
		int contador=0;
		for (int i=mRepo.findMovimientosByIdCuenta(idCuenta).size()-1;i>=0;i--) {
			lista.add(mRepo.findMovimientosByIdCuenta(idCuenta).get(i));
			contador++;
			if (contador==10) {
				break;
			}
		}
		return lista;
	}

	


}
