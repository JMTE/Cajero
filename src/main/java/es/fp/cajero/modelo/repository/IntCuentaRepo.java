package es.fp.cajero.modelo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.fp.cajero.modelo.beans.Cuenta;

//Al extender a JpaRepository, podemos utilizar los metodos y clases integradas dentro de Spring Boot para tratar bases de datos

public interface IntCuentaRepo extends JpaRepository<Cuenta, Integer>{

	 
	/* Tambien podemos usar la anotación Query de Spring Data, directamente modificando solo el repository. 
	 * */

}

