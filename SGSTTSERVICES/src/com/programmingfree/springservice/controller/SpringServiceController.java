package com.programmingfree.springservice.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.programmingfree.springservice.dao.UserService;
import com.programmingfree.springservice.domain.User;


@RestController
@RequestMapping("/service/user/")
public class SpringServiceController {
	
	UserService userService=new UserService();	
	  //NOMBRE TRASLADISTA 	    
	@RequestMapping(value = "/{id}", method = RequestMethod.GET,headers="Accept=application/json")
	public User getUser(@PathVariable int id) {
		User user=userService.getNombretrasladista(id);
		return user;
	}
	
	
	  //FECHA Y HORA DEL SERVICIO
	@RequestMapping(value = "/HoraFecha/{id}", method = RequestMethod.GET,headers="Accept=application/json")
	public User getFechaHora(@PathVariable int id) {
		User user=userService.getHoraFecha(id);
		return user;
	}
	
	  //FECHA Y HORA DEL SISTEMA	
	@RequestMapping(value = "/HoraFechaSistema/", method = RequestMethod.GET,headers="Accept=application/json")
	public User getFechaHorasistema() {
		User user=userService.getHoraFechaSistema();
		return user;
	}
	
	@RequestMapping(value = "/getCurdate/", method = RequestMethod.GET,headers="Accept=application/json")
	public User getCurdate() {
		User user=userService.getCurdate();
		return user;
	}
	
	@RequestMapping(value = "/HoraSistema/", method = RequestMethod.GET,headers="Accept=application/json")
	public User getHorasistema() {
		User user=userService.getHora();
		return user;
	}
	
	  //ORIGEN Y DESTINO DEL SERVICIO
	@RequestMapping(value = "/OrigenDestino/{id}", method = RequestMethod.GET,headers="Accept=application/json")
	public User getOrigenDestino(@PathVariable int id) {
		User user=userService.getOrigenDestino(id);
		return user;
	}
	
	  //ID DETALLE SERVICIO
	@RequestMapping(value = "/idDetalleservicio/{id}", method = RequestMethod.GET,headers="Accept=application/json")
	public User getiddetalleservicio(@PathVariable int id) {
		User user=userService.getidDetalleservicio(id);
		return user;
	}
	
	//CUENTA DETALLES SERVICIO
	@RequestMapping(value = "/getcuenta/{id}", method = RequestMethod.GET,headers="Accept=application/json")
	public User getcuenta(@PathVariable int id) {
		User user=userService.getcuenta(id);
		return user;
	}
	
	  //ID TIPO DESCRIPCION SERVICIO
	@RequestMapping(value = "/IdDescripcionservicio/{id}", method = RequestMethod.GET,headers="Accept=application/json")
	public User getIdDescripcionservicio(@PathVariable int id) {
		User user=userService.getIdDescripcionservicio(id);
		return user;
	}
	
	  //DESCRIPCION SERVICIO
	@RequestMapping(value = "/Descripcionservicio/{id}", method = RequestMethod.GET,headers="Accept=application/json")
	public User getDescripcionservicio(@PathVariable int id) {
		User user=userService.getDescripcionservicio(id);
		return user;
	}
	
	  //ID PAX 
	@RequestMapping(value = "/idPax/{id}", method = RequestMethod.GET,headers="Accept=application/json")
	public User getidPax(@PathVariable int id) {
		User user=userService.getidPax(id);
		return user;
	}
	
	
	  //NOMBRE PAX 
	@RequestMapping(value = "/Nombrepax/{id}", method = RequestMethod.GET,headers="Accept=application/json")
	public User getNombrepax(@PathVariable int id) {
		User user=userService.getNombrepax(id);
		return user;
	}
	
	@RequestMapping(value = "/getpaxsd/{id}", method = RequestMethod.GET,headers="Accept=application/json")
	public User getpaxsd(@PathVariable int id) {
		User user=userService.getpaxsd(id);
		return user;
	}
	
	  //NICK ADMIN
	@RequestMapping(value = "/nickadmin/{obj}/sd/{obj2}/cl/{obj3}", method = RequestMethod.GET,headers="Accept=application/json")
	public User getnickadmin(@PathVariable String obj,@PathVariable String obj2,@PathVariable String obj3) {
		User user=userService.getnickadmin(obj,obj2,obj3);
		return user;
	}
	
	
	  //CLAVE ADMIN
	@RequestMapping(value = "/claveadmin/{obj}/sde/{obj2}", method = RequestMethod.GET,headers="Accept=application/json")
	public User getclaveadmin(@PathVariable String obj,@PathVariable String obj2) {
		User user=userService.getclaveadmin(obj,obj2);
		return user;
	}
	
	@RequestMapping(value="/lista/{id}",method = RequestMethod.GET,headers="Accept=application/json")
	public List<User> getAllUsers(@PathVariable int id) {
		List<User> users=userService.getAllUsers(id);
		return users;
	}
	
	@RequestMapping(value="/listall/",method = RequestMethod.GET,headers="Accept=application/json")
	public List<User> getAllUsersadmin() {
		List<User> users=userService.getAllUsersadmin();
		return users;
	}
	
	@RequestMapping(value="/listTipoServicio/",method = RequestMethod.GET,headers="Accept=application/json")
	public List<User> getAllTipoServicio() {
		List<User> users=userService.getAllTipoServicio();
		return users;
	}
	
	//LISTA INCIDENCIAS
	@RequestMapping(value="/listaincidencias/",method = RequestMethod.GET,headers="Accept=application/json")
	public List<User> getAllincidencias() {
		List<User> users=userService.getAllincidencias();
		return users;
	}
	
	//LISTA ID INCIDENCIAS
	@RequestMapping(value="/listaidincidenciasA/{id}",method = RequestMethod.GET,headers="Accept=application/json")
	public List<User> getAllidincidenciasA(@PathVariable String id) {
		List<User> users=userService.getAllidincidenciasA(id);
		return users;
	}
	
	//ID INCIDENCIAS
	@RequestMapping(value = "/idincidencias/", method = RequestMethod.GET,headers="Accept=application/json")
	public User getidincidencias() {
		User user=userService.idincidencias();
		return user;
	}

	@RequestMapping(value = "/countserv/{id}", method = RequestMethod.GET,headers="Accept=application/json")
	public User countserv(@PathVariable String id) {
		User user=userService.countserv(id);
		return user;
	}
	
	@RequestMapping(value = "/countservall/{id}", method = RequestMethod.GET,headers="Accept=application/json")
	public User countservall(@PathVariable String id) {
		User user=userService.countservall(id);
		return user;
	}
	
	@RequestMapping(value = "/countservallA/", method = RequestMethod.GET,headers="Accept=application/json")
	public User countservallA() {
		User user=userService.countservallA();
		return user;
	}
	
	  //ID SERVICIO
	@RequestMapping(value = "/idservicio/{id}", method = RequestMethod.GET,headers="Accept=application/json")
	public User getidservicio(@PathVariable int id) {
		User user=userService.getidservicio(id);
		return user;
	}
	
	  //ID CHOFER
	@RequestMapping(value = "/getidloginusuario/{nombre}/clv/{clave}/sd/{sede}", method = RequestMethod.GET,headers="Accept=application/json")
	public User getLoginc(@PathVariable String nombre,@PathVariable String clave,@PathVariable String sede) {
		User user=userService.Login(nombre,clave,sede);
		return user;
	}
	
	  //ID CHOFER
	@RequestMapping(value = "/getidloginpassword/{dni}", method = RequestMethod.GET,headers="Accept=application/json")
	public User getLogin(@PathVariable String dni) {
		User user=userService.Login2(dni);
		return user;
	}
	
	  //ID CHOFER
	@RequestMapping(value = "/getidloginsede/{dni}/x/{clave}/xz/{id}", method = RequestMethod.GET,headers="Accept=application/json")
	public User getLogin(@PathVariable String dni,@PathVariable String clave,@PathVariable String id) {
		User user=userService.Login3(dni,clave,id);
		return user;
	}
	
	@RequestMapping(value = "/getidsedechofer/{sede}", method = RequestMethod.GET,headers="Accept=application/json")
	public User getidsedechofer(@PathVariable String sede) {
		User user=userService.getidsedechofer(sede);
		return user;
	}
	
	@RequestMapping(value = "/getperfiladmin/{id}", method = RequestMethod.GET,headers="Accept=application/json")
	public User getperfiladmin(@PathVariable String id) {
		User user=userService.getperfiladmin(id);
		return user;
	}
	
	//ID DEL TRASLADISTA
	@RequestMapping(value = "/idtrasl/{id}", method = RequestMethod.GET,headers="Accept=application/json")
	public User getidtrasl(@PathVariable int id) {
		User user=userService.getidtrasl(id);
		return user;
	}
	
	//ID TIPO INCIDENCIA
	@RequestMapping(value = "/idtipoincidencia/{id}", method = RequestMethod.GET,headers="Accept=application/json")
	public User getidtipoincidencia(@PathVariable String id) {
		User user=userService.getidtipoincidencia(id);
		return user;
	}
	
	
	//Nombre Apellido Trasladista
	@RequestMapping(value = "/nombretrasl/{id}", method = RequestMethod.GET,headers="Accept=application/json")
	public User getNombreTrasl(@PathVariable int id) {
		User user=userService.getNombreTrasl(id);
		return user;
	}
	
	
	//Nombre Apellido Trasladista
	@RequestMapping(value = "/getDetalleservicio/{id}", method = RequestMethod.GET,headers="Accept=application/json")
	public User getDetalleservicio(@PathVariable int id) {
		User user=userService.getDetalleservicio(id);
		return user;
	}
	
	//INSERTAR
	@RequestMapping(value = "/insert/{id}/as/{des}", method = RequestMethod.GET,headers="Accept=application/json")
	public User insert(@PathVariable  int id,@PathVariable String des) {
		User user=userService.Insert(id,des);
		return user;
	}
	
	//CAMBIAR ESTADO SERVICIO	
	@RequestMapping(value = "/cambiarestadoservicio/{id}", method = RequestMethod.GET,headers="Accept=application/json")
	public User cambiarestado(@PathVariable String id) {
		User user=userService.cambiarestadoservicio(id);
		return user;
	}
	
	//INSERTAR INCIDENCIA
	@RequestMapping(value = "/insertinc/{idinc}/zx/{desc}/cv/{idestadoinc}/bn/{idtipoinc}/ml/{idserv}/kj/{fecha}/ej/{fecha2}/sd/{idsede}/yin/{est}", method = RequestMethod.GET,headers="Accept=application/json")
	public User insert(@PathVariable int idinc,@PathVariable String desc,@PathVariable String idestadoinc,@PathVariable String idtipoinc,@PathVariable String idserv,@PathVariable String fecha,@PathVariable String fecha2,@PathVariable String idsede,@PathVariable String est) {
		User user=userService.Insertincidencia(idinc,desc,idestadoinc,idtipoinc,idserv,fecha,fecha2,idsede,est);
		return user;
	}
	
	@RequestMapping(value = "/getIdCliente/{id}", method = RequestMethod.GET,headers="Accept=application/json")
	public User getIdCliente(@PathVariable int id) {
		User user=userService.getIdCliente(id);
		return user;
	}
	
	@RequestMapping(value = "/getNombreCliente/{id}", method = RequestMethod.GET,headers="Accept=application/json")
	public User getNombreCliente(@PathVariable int id) {
		User user=userService.getNombreCliente(id);
		return user;
	}
	
	@RequestMapping(value = "/getCoordenadas/{id}", method = RequestMethod.GET,headers="Accept=application/json")
	public User getCoordenadas(@PathVariable String id) {
		User user=userService.getCoordenadas(id);
		return user;
	}
	
	@RequestMapping(value = "/getiddestino/{id}", method = RequestMethod.GET,headers="Accept=application/json")
	public User getiddestino(@PathVariable String id) {
		User user=userService.getiddestino(id);
		return user;
	}
	
	@RequestMapping(value="/listadescripcion",method = RequestMethod.GET,headers="Accept=application/json")
	public List<User> getDescripcion() {
		List<User> users=userService.getDescripcion();
		return users;
	}
	
	@RequestMapping(value="/listatrasl/{id}",method = RequestMethod.GET,headers="Accept=application/json")
	public List<User> getlisttrasl(@PathVariable String id) {
		List<User> users=userService.getlisttrasl(id);
		return users;
	}
	
	@RequestMapping(value="/listchofer/{id}",method = RequestMethod.GET,headers="Accept=application/json")
	public List<User> getlistchofer(@PathVariable String id) {
		List<User> users=userService.getlistchofer(id);
		return users;
	}

	@RequestMapping(value="/getdestinosxidservicio/{id}",method = RequestMethod.GET,headers="Accept=application/json")
	public List<User> getdestinosxidservicio(@PathVariable String id) {
		List<User> users=userService.getdestinosxidservicio(id);
		return users;
	}
	@RequestMapping(value="/listasede",method = RequestMethod.GET,headers="Accept=application/json")
	public List<User> listasede() {
		List<User> users=userService.listasede();
		return users;
	}
	
	@RequestMapping(value = "/getdatostrasl/{id}", method = RequestMethod.GET,headers="Accept=application/json")
	public User getdatostrasl(@PathVariable String id) {
		User user=userService.getdatostrasl(id);
		return user;
	}
	@RequestMapping(value = "/getdirecdestino/{id}", method = RequestMethod.GET,headers="Accept=application/json")
	public User getdirecdestino(@PathVariable String id) {
		User user=userService.getdirecdestino(id);
		return user;
	}
	
	@RequestMapping(value = "/getdatochofer/{id}", method = RequestMethod.GET,headers="Accept=application/json")
	public User getdatochofer(@PathVariable String id) {
		User user=userService.getdatochofer(id);
		return user;
	}
	
	@RequestMapping(value = "/getdatosincidencia/{id}", method = RequestMethod.GET,headers="Accept=application/json")
	public User getdatosincidencia(@PathVariable String id) {
		User user=userService.getdatosincidencia(id);
		return user;
	}
	
	@RequestMapping(value = "/idtypeinc/{id}", method = RequestMethod.GET,headers="Accept=application/json")
	public User idtipoincidencia(@PathVariable String id) {
		User user=userService.idtipoincidencia(id);
		return user;
	}
	
	@RequestMapping(value = "/descripcionincidencia/{id}", method = RequestMethod.GET,headers="Accept=application/json")
	public User descripcionincidencia(@PathVariable String id) {
		User user=userService.descripcionincidencia(id);
		return user;
	}
	
	@RequestMapping(value = "/dniwhereid/{id}", method = RequestMethod.GET,headers="Accept=application/json")
	public User dniwhereid(@PathVariable String id) {
		User user=userService.dniwhereid(id);
		return user;
	}
	
	@RequestMapping(value = "/getpass/{id}", method = RequestMethod.GET,headers="Accept=application/json")
	public User getpass(@PathVariable String id) {
		User user=userService.getpass(id);
		return user;
	}
	
	@RequestMapping(value = "/getpassA/{id}", method = RequestMethod.GET,headers="Accept=application/json")
	public User getpassA(@PathVariable String id) {
		User user=userService.getpassA(id);
		return user;
	}
	
	@RequestMapping(value = "/estadoid/{id}", method = RequestMethod.GET,headers="Accept=application/json")
	public User idestado(@PathVariable String id) {
		User user=userService.idestado(id);
		return user;
	}
	
	@RequestMapping(value = "/descripcionestado/{id}", method = RequestMethod.GET,headers="Accept=application/json")
	public User descripcionestado(@PathVariable String id) {
		User user=userService.descripcionestado(id);
		return user;
	}
	
	@RequestMapping(value = "/insertdatetimeorigen/{id}/in/{fecha}", method = RequestMethod.GET,headers="Accept=application/json")
	public User Insertdatetimeorigen(@PathVariable  int id,@PathVariable String fecha) {
		User user=userService.Insertdatetimeorigen(id,fecha);
		return user;
	}
	
	@RequestMapping(value = "/insertdatetimedestino/{id}/in/{fecha}", method = RequestMethod.GET,headers="Accept=application/json")
	public User Insertdatetimedestino(@PathVariable  int id,@PathVariable String fecha) {
		User user=userService.Insertdatetimedestino(id,fecha);
		return user;
	}
	
	@RequestMapping(value = "/changepass/{id}/in/{clave}", method = RequestMethod.GET,headers="Accept=application/json")
	public User Insertdatetimedestino(@PathVariable String id,@PathVariable String clave) {
		User user=userService.changepass(id,clave);
		return user;
	}
	
	@RequestMapping(value = "/changepassA/{id}/in/{clave}", method = RequestMethod.GET,headers="Accept=application/json")
	public User changepassA(@PathVariable String id,@PathVariable String clave) {
		User user=userService.changepassA(id,clave);
		return user;
	}
	
	@RequestMapping(value = "/insertcronometro/{id}/in/{tiempo}", method = RequestMethod.GET,headers="Accept=application/json")
	public User Insertcronometro(@PathVariable  int id,@PathVariable String tiempo) {
		User user=userService.Insertcronometro(id,tiempo);
		return user;
	}

	//Consultar Servicio Administrador  6/11/15 .......
	
	//Obtener todos los servicios 
	@RequestMapping(value="/listserviceadmin",method = RequestMethod.GET,headers="Accept=application/json")
	public List<User> getListAllServiceAdmin() {
		List<User> users=userService.getListAllServiceAdmin();
		return users;
	}
	
	@RequestMapping(value="/listserviceadminP",method = RequestMethod.GET,headers="Accept=application/json")
	public List<User> getListAllServiceAdminP() {
		List<User> users=userService.getListAllServiceAdminP();
		return users;
	}
	
	//Obtener NumeroPersonas pasajeros Servicio
	@RequestMapping(value = "/getNumPersonas/{id}", method = RequestMethod.GET,headers="Accept=application/json")
	public User getNumPersonas(@PathVariable  String id) {
		User user=userService.getNumPersonas(id);
		return user;
	}
	
	//Obtener Externalizado Servicio
	@RequestMapping(value = "/getExternalizado/{id}", method = RequestMethod.GET,headers="Accept=application/json")
	public User getExternalizado(@PathVariable  String id) {
		User user=userService.getExternalizado(id);
		return user;
	}
	
	//Obtener Precio Servicio
	@RequestMapping(value = "/getPrecioServicio/{id}", method = RequestMethod.GET,headers="Accept=application/json")
	public User getPrecioServicio(@PathVariable  String id) {
		User user=userService.getPrecioServicio(id);
		return user;
	}
	
	//Obtener Descuento de servicio
	@RequestMapping(value = "/getDescuento/{id}", method = RequestMethod.GET,headers="Accept=application/json")
	public User getDescuento(@PathVariable  String id) {
		User user=userService.getDescuento(id);
		return user;
	}
	
	//Obtener Adicional de servicio
	@RequestMapping(value = "/getAdicional/{id}", method = RequestMethod.GET,headers="Accept=application/json")
	public User getAdicional(@PathVariable  String id) {
		User user=userService.getAdicional(id);
		return user;
	}
	
	//Obtener Dias Viaje del servicio
		@RequestMapping(value = "/getDiasViaje/{id}", method = RequestMethod.GET,headers="Accept=application/json")
		public User getDiasViaje(@PathVariable  String id) {
			User user=userService.getDiasViaje(id);
			return user;
	}
	
	//Obtener Estado del servicio
		@RequestMapping(value = "/getEstadoServicio/{id}", method = RequestMethod.GET,headers="Accept=application/json")
		public User getEstadoServicio(@PathVariable  String id) {
			User user=userService.getEstadoServicio(id);
			return user;
	}
		
	//Obtener Fecha de Registro de servicio
		@RequestMapping(value = "/getFechaRegistro/{id}", method = RequestMethod.GET,headers="Accept=application/json")
		public User getFechaRegistro(@PathVariable  String id) {
			User user=userService.getFechaRegistro(id);
			return user;
	}
		
	//Obtener idchofer de servicio
		@RequestMapping(value = "/getidChofer/{id}", method = RequestMethod.GET,headers="Accept=application/json")
		public User getidChofer(@PathVariable  String id) {
			User user=userService.getidChofer(id);
			return user;
	}	
	
	//Obtener Nombre , Apellido de Chofer
		@RequestMapping(value = "/getNombreApellidoChofer/{id}", method = RequestMethod.GET,headers="Accept=application/json")
		public User getNombreApellidoChofer(@PathVariable  String id) {
			User user=userService.getNombreApellidoChofer(id);
			return user;
	}	
	
	//Obtener idTrasladista de Servicio
		@RequestMapping(value = "/getIdTrasladista/{id}", method = RequestMethod.GET,headers="Accept=application/json")
		public User getIdTrasladista(@PathVariable  String id) {
			User user=userService.getIdTrasladista(id);
			return user;
	}	
	
	//Obtener Nombre , Apellido de Trasladista
		@RequestMapping(value = "/getNombreApellidoTrasladista/{id}", method = RequestMethod.GET,headers="Accept=application/json")
		public User getNombreApellidoTrasladista(@PathVariable  String id) {
			User user=userService.getNombreApellidoTrasladista(id);
			return user;
	}	
	
	//Obtener idvehiculo de servicio
		@RequestMapping(value = "/getidvehiculo/{id}", method = RequestMethod.GET,headers="Accept=application/json")
		public User getidvehiculo(@PathVariable  String id) {
			User user=userService.getidvehiculo(id);
			return user;
	}	
	
	@RequestMapping(value = "/UpdateCostos/{serv}/b/{desc}/c/{ad}/d/{id}", method = RequestMethod.GET,headers="Accept=application/json")
	public User UpdateCostos(@PathVariable String serv,@PathVariable String desc,@PathVariable String ad,@PathVariable String id) {
		User user=userService.UpdateCostos(serv,desc,ad,id);
		return user;
}
	
	//Obtener Placa , Descripcion de Vehiculo
		@RequestMapping(value = "/getPlacaDescripcionVehiculo/{id}", method = RequestMethod.GET,headers="Accept=application/json")
		public User getPlacaDescripcionVehiculo(@PathVariable  String id) {
			User user=userService.getPlacaDescripcionVehiculo(id);
			return user;
	}	
		@RequestMapping(value = "/getdescripcion2/{id}", method = RequestMethod.GET,headers="Accept=application/json")
		public User getdescripcion2(@PathVariable  String id) {
			User user=userService.getdescripcion2(id);
			return user;
	}	
	
	//Obtener idVuelo de servicio_detalle
		@RequestMapping(value = "/getIdVuelo/{id}", method = RequestMethod.GET,headers="Accept=application/json")
		public User getIdVuelo(@PathVariable  String id) {
			User user=userService.getIdVuelo(id);
			return user;
	}	
	
	//Obtener descripcion del vuelo 
		@RequestMapping(value = "/getdescripcionvuelo/{id}", method = RequestMethod.GET,headers="Accept=application/json")
		public User getdescripcionvuelo(@PathVariable  String id) {
			User user=userService.getdescripcionvuelo(id);
			return user;
	}	
		@RequestMapping(value = "/getdescripcionaerolinea/{id}", method = RequestMethod.GET,headers="Accept=application/json")
		public User getdescripcionaerolinea(@PathVariable  String id) {
			User user=userService.getdescripcionaerolinea(id);
			return user;
	}	
	
	//Detalles Vehiculo
		//Obtener descripcion del vehiculo 
		@RequestMapping(value = "/getdetallesvehiculo/{id}", method = RequestMethod.GET,headers="Accept=application/json")
		public User getdetallesvehiculo(@PathVariable  String id) {
			User user=userService.getdetallesvehiculo(id);
			return user;
	}	
		//Obtener idSede del vehiculo 
		@RequestMapping(value = "/getidsede/{id}", method = RequestMethod.GET,headers="Accept=application/json")
		public User getidsede(@PathVariable  String id) {
			User user=userService.getidsede(id);
			return user;
	}	
		
	
		//Obtener descripcion sede del vehiculo 
		@RequestMapping(value = "/getdescripcionsede/{id}", method = RequestMethod.GET,headers="Accept=application/json")
		public User getdescripcionsede(@PathVariable  String id) {
			User user=userService.getdescripcionsede(id);
			return user;
	}
		//Obtener nombre tipo vehiculo 
		@RequestMapping(value = "/getnombretipovehiculo/{id}", method = RequestMethod.GET,headers="Accept=application/json")
		public User getnombretipovehiculo(@PathVariable  String id) {
			User user=userService.getnombretipovehiculo(id);
			return user;
	}
		//Obtener nombre marca 
		@RequestMapping(value = "/getnombremarca/{id}", method = RequestMethod.GET,headers="Accept=application/json")
		public User getnombremarca(@PathVariable  String id) {
			User user=userService.getnombremarca(id);
			return user;
	}
		//Obtener nombre modelo 
		@RequestMapping(value = "/getnombremodelo/{id}", method = RequestMethod.GET,headers="Accept=application/json")
		public User getnombremodelo(@PathVariable  String id) {
			User user=userService.getnombremodelo(id);
			return user;
	}
		@RequestMapping(value="/getAllincidenciasxfecha/{id}",method = RequestMethod.GET,headers="Accept=application/json")
		public List<User> getAllincidenciasxfecha(@PathVariable String id) {
			List<User> users=userService.getAllincidenciasxfecha(id);
			return users;
		}
//***********************************************************************************************************************************	
//***********************************************************************************************************************************	
//***********************************************************************************************************************************	
//***********************************************************************************************************************************	
//***********************************************************************************************************************************	
//***********************************************************************************************************************************	
//***********************************************************************************************************************************	
//***********************************************************************************************************************************	
//***********************************************************************************************************************************	
//***********************************************************************************************************************************	
//***********************************************************************************************************************************	
//***********************************************************************************************************************************	
//***********************************************************************************************************************************	
//***********************************************************************************************************************************	
//***********************************************************************************************************************************	
//***********************************************************************************************************************************	
		
	
		
}

	