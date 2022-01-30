package es.fp.cajero.modelo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import es.fp.cajero.modelo.beans.Movimiento;

//Al extender a JpaRepository, podemos utilizar los metodos y clases integradas dentro de Spring Boot para tratar bases de datos
public interface IntMovimientosRepo extends CrudRepository<Movimiento, Integer>{

	/* Tambien podemos usar la anotación Query de Spring Data, directamente modificando solo el repository. 
	 * En este caso, creamos un método que devuelva la lista de movimientos dado un idCuenta*/
	
	@Query("SELECT m FROM Movimiento m WHERE m.cuenta.idCuenta = ?1")
	public List<Movimiento> findMovimientosByIdCuenta(int idCuenta);
	
	
	
}
