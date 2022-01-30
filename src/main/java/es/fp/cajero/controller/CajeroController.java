
/*--------------------------------------------CONTROLADOR PARA PANEL DEL CAJERO--------------------------------------------------*/
package es.fp.cajero.controller;

/** @author JMTE */

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import es.fp.cajero.modelo.beans.Cuenta;
import es.fp.cajero.modelo.beans.Movimiento;
import es.fp.cajero.modelo.dao.IntCuentaDao;
import es.fp.cajero.modelo.dao.IntMovimientoDao;


//La anotación @Controller indica que esta clase en particular cumple la función de controlador.
@Controller
//Cuando @RequestMapping se usa en el nivel de clase, crea un URI base para el que se usará el controlador.
@RequestMapping("cajero")

public class CajeroController {

	/*Lo que hace un autowired es buscar un objeto manejado (beans) que implementen determinada interfaz para hacer referencia a él.
	 *  De esta manera no es neceario crear una instancia nueva del objeto cada vez que se necesite la funcionalidad de determinada clase */
	@Autowired
	private HttpSession misesion;
	
	
	@Autowired
	private IntCuentaDao icuen;
	
	@Autowired
	private IntMovimientoDao imov;
	
	//Esta es la direccion a la que nos envia despues del login si la cuenta logueada se encuentra en la base de datos
	@GetMapping ("/")
	public String verPrincipal (Model model ) {
		
		//Recuperamos la cuenta que tenemos en la sesion guardada
		Cuenta cuenta=(Cuenta) misesion.getAttribute("cuenta");
		
		//Como en esta pagina principal queremos mostrar solo los 10 ultimos movimientos, creamos una lista nueva y los introducimos en ella
		//con nuestro metodo creado en el interfaz
		List<Movimiento> lista=new ArrayList<Movimiento>();
		lista=imov.find10UltimosMovimientosbyIdCuenta(cuenta.getIdCuenta());
		
		
		model.addAttribute("listamovimientos", lista);
		model.addAttribute("Cuenta", cuenta);
		model.addAttribute("mensajeMovimientos", "10 Últimos Movimientos");
		model.addAttribute("mensajeMasMovimientos", "Más Movimientos");
		
		return "cajero";
	}
	
	//Presentamos el jsp correspondiente a ingresar, que es un formulario.
	@GetMapping ("/ingresar")
	public String ingresarDinero(Model model) {
		
		return "ingreso";
		
	}
	
	//Traemos los datos del formulario con el PostMapping, en este caso traemos solo la cantidad, ya que es el unico campo
	@PostMapping("/ingresar")
	public String ingresarDinero(Model model, int cantidad ) {
		//Recuperamos la cuenta
		Cuenta cuenta=(Cuenta) misesion.getAttribute("cuenta");
		//Creamos un nuevo movimiento
		Movimiento movimiento=new Movimiento();
		//Le introducimos los valoers al nuevo movimiento
		movimiento.setCuenta(cuenta);
		movimiento.setFecha(new Date());
		movimiento.setOperacion("Ingreso");
		movimiento.setCantidad(cantidad);
		//Añadimos el movimiento a la lista de movimientos
		imov.añadirMovimiento(movimiento);
		
		//Actualizamos el saldo de la cuenta
		cuenta.setSaldo(cuenta.getSaldo()+cantidad);
		//Actualizamos los valores en la BBDD
		icuen.actualizarCuenta(cuenta);
		
		model.addAttribute("cuenta", cuenta);
		model.addAttribute("Cuenta", cuenta);
		
		return "redirect:/cajero/";
	}
	
	//Nos gestiona la presentación  del jsp correspondiente a extraer que es un formulario.
	@GetMapping("/extraer")
	public String extraerDinero(Model model) {
		//Recuperamos la cuenta
		Cuenta cuenta=(Cuenta) misesion.getAttribute("cuenta");
		
		//Establecemos que si la cuenta es de ahorro, no se puede sacar dinero por el cajero, nos presente un mensaje y nos devuelva a cajero.
		if (cuenta.getTipoCuenta().equals("ahorro")) {
			List<Movimiento> lista=new ArrayList<Movimiento>();
			lista=imov.find10UltimosMovimientosbyIdCuenta(cuenta.getIdCuenta());
			
			model.addAttribute("listamovimientos", lista);
			model.addAttribute("mensajeMovimientos", "10 Últimos Movimientos");
			model.addAttribute("error", "Esta es una cuenta de AHORRO, no se puede sacar dinero, solo ingresos y transferencias");
			model.addAttribute("Cuenta", cuenta);
			return "cajero";
		//Si la cuenta no es de ahorro, nos lleva al jsp de extraccion.
		}else {
			return "extraccion";
		}
			
	}
	
	//Con PostMapping recuperamos los valores del formulario de extraccion, en este caso la cantidad introducida.
	@PostMapping("/extraer")
	public String extraerDinero(Model model,int cantidad) {
		//Recuperamos la cuenta
		Cuenta cuenta=(Cuenta)misesion.getAttribute("cuenta");
		
		//Si la cuenta no tiene saldo suficiente, presentamos un mensaje y seguimos en la mismo jsp de extraccion.
		if (cuenta.getSaldo()<=cantidad) {
			model.addAttribute("fallo", "No hay suficiente dinero en la cuenta");
			return "extraccion";
		
		//Si la cuenta tiene saldo disponible para ese importe de extracción gestionamos el movimiento
		}else {
		//Creamos un moviemiento nuevo e introducimos sus valores correspondientes
		Movimiento movimiento=new Movimiento();
		movimiento.setCuenta(cuenta);
		movimiento.setFecha(new Date());
		movimiento.setOperacion("Extracción");
		movimiento.setCantidad(cantidad);
		
		//Añadimos ese movimiento a la lista de movimientos
		imov.añadirMovimiento(movimiento);
		
		//Actualizamos el saldo de la cuenta
		cuenta.setSaldo(cuenta.getSaldo()-cantidad);
		
		//Actualizamos la cuenta en la BBDD
		icuen.actualizarCuenta(cuenta);
		
		model.addAttribute("cuenta", cuenta);
		
		return "redirect:/cajero/";
		}
	}
	
	
	//Aqui gestionaremos la presentación del jsp correspondiente a la transferencia que es un formulario
	@GetMapping("/transferencia")
	public String  transferirDinero(Model model) {
		
		return "transferencia";
	}
	
	
	//Con PostMapping recuperamos los valores del formulario de transferencia
	@PostMapping("/transferencia")
	public String transferirDinero(Model model, int cantidad, int idCuenta) {
		
		//Recuperamos la cuenta con la que estamos trabajando
		Cuenta cuenta=(Cuenta)misesion.getAttribute("cuenta");
		//Creamos una nueva cuenta que es la cuenta de destino del dinero
		Cuenta cuentaDestino=icuen.findById(idCuenta);
		
		//Si la cuenta de destino es la misma que la de origen o la cuenta de destino no existe, mostramos un mensaje y seguimos en transferencia.
		if (cuenta.equals(cuentaDestino)||cuentaDestino==null) {
			model.addAttribute("fallo", "No se puede hacer una transferencia a la misma cuenta o a otra inexistente");
			return "transferencia";
		
		//Si la cuenta existe y no es la misma de origen, gestionamos la transferencia
		}else {
			//Si el saldo es insuficiente en la cuenta de origen para realizar la transferencia, presentamos mensaje y seguimos en transferencia
			if (cuenta.getSaldo()<=cantidad) {
				model.addAttribute("fallo", "No hay suficiente dinero en la cuenta");
				return "transferencia";
			//Si existe saldo suficiente, gestionamos la transferencia
			}else {
				//Actualizamos el saldo en la cuenta de origen
				cuenta.setSaldo(cuenta.getSaldo()-cantidad);
				//Actualizamos el saldo en la cuenta de destino
				cuentaDestino.setSaldo(cuentaDestino.getSaldo()+cantidad);
				//Actualizamos el valor de la cuenta de origen
				icuen.actualizarCuenta(cuenta);
				//Actualizamos el valor de la cuenta de destino
				icuen.actualizarCuenta(cuentaDestino);
				
				//Creamos el movimiento creado en la cuenta de origen y lo añadimos a la lista de movimientos
				Movimiento movimiento=new Movimiento();
				movimiento.setCuenta(cuenta);
				movimiento.setFecha(new Date());
				movimiento.setOperacion("Extracción");
				movimiento.setCantidad(cantidad);
				imov.añadirMovimiento(movimiento);
				
				//Creamos el movimiento creado en la cuenta de destino y lo añadimos a lista de movimientos
				Movimiento movimientoDestino=new Movimiento();
				movimientoDestino.setCuenta(cuentaDestino);
				movimientoDestino.setFecha(new Date());
				movimientoDestino.setOperacion("Ingreso");
				movimientoDestino.setCantidad(cantidad);
				imov.añadirMovimiento(movimientoDestino);
				
				model.addAttribute("Cuenta", cuenta);
				return "redirect:/cajero/";
			}
			
		}
		
		
	}
	
	
	//Aqui gestionaremos la visualizacion de TODOS los movimientos existentes en una cuenta
	@GetMapping("/verMovimientos")
	
	public String verMovimientos(Model model) {
		
		Cuenta cuenta=(Cuenta)misesion.getAttribute("cuenta");
		
		model.addAttribute("listamovimientos", imov.findMovimientosByIdCuenta(cuenta.getIdCuenta()));
		model.addAttribute("Cuenta", cuenta);
		model.addAttribute("mensajeMovimientos", "Todos los movimientos");
		
		
		return "cajero";
		
	}
	
	
}
