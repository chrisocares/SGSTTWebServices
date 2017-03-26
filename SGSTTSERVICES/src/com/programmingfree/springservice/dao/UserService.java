package com.programmingfree.springservice.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.programmingfree.springservice.domain.User;
import com.programmingfree.springservice.utility.DBUtility;


public class UserService {
	
	private Connection connection;

	public UserService() {
		connection = DBUtility.getConnection();
	}
	
	public int getUserCount(){
		int count=0;
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select count(*) as count from tblUser");		
			while (rs.next()) {
				count=rs.getInt("count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
		
	
	}

	public void addUser(User user) {
		try {
			
			PreparedStatement preparedStatement = connection
					.prepareStatement("insert into estado_incidencia(idestado_incidencia,descripcion) values (?,?)");
			// Parameters start with 1
			preparedStatement.setInt(1, user.getUserid());
			preparedStatement.setString(2, user.getFirstName());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteUser(int userId) {
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("delete from tblUser where userid=?");
			// Parameters start with 1
			preparedStatement.setInt(1, userId);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateUser(User user) throws ParseException {
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("update tblUser set lastname=?,email=?" +
							"where userid=?");
			// Parameters start with 1			
			preparedStatement.setString(1, user.getLastName());
			preparedStatement.setString(2, user.getEmail());			
			preparedStatement.setInt(3, user.getUserid());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<User> getAllUsers(int userId) {
		List<User> users = new ArrayList<User>();
		try {
			PreparedStatement preparedStatement = connection.
					prepareStatement("SELECT idservicio_detalle,descripcion from servicio_detalle where idchofer=? and estado_servicio='PENDIENTE' ORDER BY fecha ASC ");
			preparedStatement.setInt(1, userId);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				User user = new User();

				user.setFirstName(rs.getString("idservicio_detalle"));
				user.setLastName(rs.getString("descripcion"));
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return users;
	}
	
	public List<User> getAllUsersadmin() {
		List<User> users = new ArrayList<User>();
		try {
			PreparedStatement preparedStatement = connection.
					prepareStatement("SELECT idservicio_detalle from servicio_detalle limit 15");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				User user = new User();

				user.setFirstName(rs.getString("idservicio_detalle"));
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return users;
	}
	
	public List<User> getdestinosxidservicio(String id) {
		List<User> users = new ArrayList<User>();
		try {
			PreparedStatement preparedStatement = connection.
					prepareStatement("select a.iddestinos ,destinos_iddestinos ,a.tipo_destino,a.razonsocial,a.direccion from servicio_destinos inner join destinos a on destinos_iddestinos=a.iddestinos where servicio_idservicio=?");
			preparedStatement.setString(1,id);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				User user = new User();

				user.setFirstName(rs.getString("tipo_destino"));
				user.setLastName(rs.getString("razonsocial"));
				user.setdirecc(rs.getString("direccion"));
				user.setText(rs.getString("iddestinos"));
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return users;
	}
	
	public List<User> getAllTipoServicio() {
		List<User> users = new ArrayList<User>();
		try {
			PreparedStatement preparedStatement = connection.
					prepareStatement("SELECT descripcion from tipo_servicio limit 15");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				User user = new User();

				user.setFirstName(rs.getString("descripcion"));
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return users;
	}
	
	public List<User> getAllincidencias() {
		List<User> users = new ArrayList<User>();
		try {
			PreparedStatement preparedStatement = connection.
					prepareStatement("SELECT descripcion,idtipo_incidencia from tipo_incidencia limit 45");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				User user = new User();

				user.setFirstName(rs.getString("descripcion"));
				user.setLastName(rs.getString("idtipo_incidencia"));
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return users;
	}
	
	public List<User> getAllidincidenciasA(String id) {
		List<User> users = new ArrayList<User>();
		try {
			PreparedStatement preparedStatement = connection.
					prepareStatement("SELECT n.idincidencia ,x.descripcion from incidencia n inner join servicio_detalle x on n.idservicio_detalle=x.idservicio_detalle where idsede=?");
			preparedStatement.setString(1,id);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				User user = new User();

				user.setFirstName(rs.getString("idincidencia"));
				user.setLastName(rs.getString("descripcion"));
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return users;
	}
	
	public User getUserById(int userId) {
		User user = new User();
		try {
			PreparedStatement preparedStatement = connection.
					prepareStatement("select * from tblUser where userid=?");
			preparedStatement.setInt(1, userId);
			ResultSet rs = preparedStatement.executeQuery();
			
			if (rs.next()) {
				user.setUserid(rs.getInt("userid"));
				user.setFirstName(rs.getString("firstname"));
				user.setLastName(rs.getString("lastname"));
				
				user.setEmail(rs.getString("email"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	public User getNombretrasladista(int userId) {
		User user = new User();
		try {
			PreparedStatement preparedStatement = connection.
					prepareStatement("SELECT nombre , apellido FROM chofer WHERE idchofer=?");
			preparedStatement.setInt(1, userId);
			ResultSet rs = preparedStatement.executeQuery();
			
			if (rs.next()) {

				user.setFirstName(rs.getString("nombre"));
				user.setLastName(rs.getString("apellido"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	
	public User getnickadmin(String obj,String obj2,String obj3) {
			User user = new User();
			try{
				PreparedStatement preparedStatement = connection.
						prepareStatement("SELECT idusuario FROM usuario WHERE idPERFIL IN ('1','2','3') and nick=? and idsede=? and clave=?");
				preparedStatement.setString(1, obj);
				preparedStatement.setString(2, obj2);
				preparedStatement.setString(3, obj3);
				ResultSet rs = preparedStatement.executeQuery();
				
				if(rs.next()){
					
					user.setUserid(rs.getInt("idusuario"));
					
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	

	
	public User getclaveadmin(String obj,String obj2) {
		User user = new User();
		try{
			PreparedStatement preparedStatement = connection.
					prepareStatement("SELECT idusuario FROM usuario WHERE idPERFIL=1 and clave=? and idsede=?");
			preparedStatement.setString(1, obj);
			preparedStatement.setString(2, obj2);
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next()){
				
				user.setFirstName(rs.getString("idusuario"));
				
			}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return user;
	}

	
	//Obtener Fecha de servicio_detalle
	
	public User getHoraFecha(int userId){
		User user = new User();
		try{
			PreparedStatement preparedStatement = connection.
					prepareStatement("select FECHA from servicio_detalle where idservicio_detalle=?");
			preparedStatement.setInt(1, userId);
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next()){
				
				user.setFirstName(rs.getString("FECHA"));
				
				
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		return user;
	}
	
	public User getHoraFechaSistema(){
		User user = new User();
		try{
			PreparedStatement preparedStatement = connection.
					prepareStatement("SELECT sysdate() as Datetime_sistema");
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next()){
				
				user.setFirstName(rs.getString("Datetime_sistema"));
				
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		return user;
	}
	
	public User getCurdate(){
		User user = new User();
		try{
			PreparedStatement preparedStatement = connection.
					prepareStatement("SELECT CURDATE() as Datetime_sistema");
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next()){
				
				user.setFirstName(rs.getString("Datetime_sistema"));
				
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		return user;
	}

	//Obtiene descripcion del servicio (idservicio)
	
	public User getOrigenDestino(int userId){
		User user = new User();
		try{
			PreparedStatement preparedStatement = connection.
					prepareStatement("SELECT descripcion FROM servicio WHERE idservicio=?");
			preparedStatement.setInt(1, userId);
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next()){
				
				user.setFirstName(rs.getString("descripcion"));
				
				
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		return user;
	}
	
	
	public User getidDetalleservicio(int userId){
		User user = new User();
		try{
			PreparedStatement preparedStatement = connection.
					prepareStatement("SELECT idservicio_detalle from servicio_detalle where idservicio_detalle=?");
			preparedStatement.setInt(1, userId);
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next()){
				
				user.setFirstName(rs.getString("idservicio_detalle"));
				
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		return user;
	}
	
	public User getcuenta(int userId){
		User user = new User();
		try{
			PreparedStatement preparedStatement = connection.
					prepareStatement("SELECT comentario,cuenta from servicio_detalle where idservicio_detalle=?");
			preparedStatement.setInt(1, userId);
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next()){
				
				user.setFirstName(rs.getString("cuenta"));
				user.setLastName(rs.getString("comentario"));
				
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		return user;
	}
	
	public User getDetalleservicio(int userId){
		User user = new User();
		try{
			PreparedStatement preparedStatement = connection.
					prepareStatement("SELECT idservicio_detalle from Servicio where idservicio=?");
			preparedStatement.setInt(1, userId);
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next()){
				
				user.setFirstName(rs.getString("idservicio_detalle"));
				
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		return user;
	}
	
	public User getIdDescripcionservicio(int userId){
		User user = new User();
		try{
			PreparedStatement preparedStatement = connection.
					prepareStatement("SELECT idtipo_servicio from servicio where idservicio=?");
			preparedStatement.setInt(1, userId);
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next()){
				
				user.setFirstName(rs.getString("idtipo_servicio"));
				
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		return user;
	}
	
	public User getDescripcionservicio(int userId){
		User user = new User();
		try{
			PreparedStatement preparedStatement = connection.
					prepareStatement("SELECT descripcion from tipo_servicio where idtipo_servicio=?");
			preparedStatement.setInt(1, userId);
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next()){
				
				user.setFirstName(rs.getString("descripcion"));
				
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		return user;
	}

	public User getNombrepax(int userId){
		User user = new User();
		try{
			PreparedStatement preparedStatement = connection.
					prepareStatement("SELECT PAX , cuenta from file where idFile=?");
			preparedStatement.setInt(1, userId);
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next()){
				
				user.setFirstName(rs.getString("Pax"));
				user.setLastName(rs.getString("cuenta"));
		
				
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		return user;
	}
	
	public User getpaxsd(int userId){
		User user = new User();
		try{
			PreparedStatement preparedStatement = connection.
					prepareStatement("SELECT pax from servicio_detalle where idservicio_detalle=?");
			preparedStatement.setInt(1, userId);
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next()){
				
				user.setFirstName(rs.getString("pax"));
		
				
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		return user;
	}
	
	public User getidPax(int userId){
		User user = new User();
		try{	
			PreparedStatement preparedStatement = connection.
					prepareStatement("SELECT idFile from servicio_detalle where idservicio_detalle=?");
			preparedStatement.setInt(1, userId);
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next()){
				
				user.setFirstName(rs.getString("idFile"));
				
				
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		return user;
	}
	
	
	public User getidservicio(int userId){
		User user = new User();
		try{
			PreparedStatement preparedStatement = connection.
					prepareStatement("SELECT idservicio from servicio_detalle where idservicio_detalle=?");
			preparedStatement.setInt(1, userId);
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next()){
				
				user.setFirstName(rs.getString("idservicio"));
				
				
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		return user;
	}
	
	
	public User getidtrasl(int userId){
		User user = new User();
		try{
			PreparedStatement preparedStatement = connection.
					prepareStatement("SELECT idtrasladista from servicio_detalle where idservicio_detalle=?");
			preparedStatement.setInt(1, userId);
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next()){
				
				user.setFirstName(rs.getString("idtrasladista"));
				
				
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		return user;
	}
	
	public User getidtipoincidencia(String id){
		User user = new User();
		try{
			PreparedStatement preparedStatement = connection.
					prepareStatement("SELECT idtipo_incidencia from tipo_incidencia where descripcion=?");
			preparedStatement.setString(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next()){
				
				user.setFirstName(rs.getString("idtipo_incidencia"));
				
				
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		return user;
	}
	public User getNombreTrasl(int userId){
		User user = new User();
		try{
			PreparedStatement preparedStatement = connection.
					prepareStatement("SELECT nombre,apellido from trasladista where idtrasladista=?");
			preparedStatement.setInt(1, userId);
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next()){
				
				user.setFirstName(rs.getString("nombre"));
				user.setLastName(rs.getString("apellido"));
				
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		return user;
	}
	
	public User Insert(int id,String des){
		User user = new User();
		try{
					PreparedStatement preparedStatement = connection
					.prepareStatement("insert into estado_incidencia(idestado_incidencia,descripcion) values (?,?)");
					
					
					preparedStatement.setInt(1, id);
					preparedStatement.setString(2, des);
					 preparedStatement.executeUpdate();
					
					} catch (SQLException e) {
					e.printStackTrace();
					}
		return user;
	}
	
	public User UpdateCostos(String serv,String desc,String ad,String id){
		
			User user = new User();
			try{
				PreparedStatement preparedStatement = connection.
						prepareStatement("UPDATE servicio_detalle SET precio_servicio=? , descuento=?,adicional=? WHERE idservicio_detalle=?");
				preparedStatement.setString(1, serv);
				preparedStatement.setString(2, desc);
				preparedStatement.setString(3,ad);
				preparedStatement.setString(4, id);
				 preparedStatement.executeUpdate();
			} catch(SQLException e){
				e.printStackTrace();
			}
			return user;
		
	}
//FALTA COMPLETAR LOGIN------------------------------------------------------------------------------------------
	
	public User Login(String nombre,String clave,String sede){
		User user = new User();
		try{
					PreparedStatement preparedStatement = connection
					.prepareStatement("select idchofer from chofer where dni=? and clave=? and idsede=?");
					preparedStatement.setString(1,nombre);
					preparedStatement.setString(2,clave);
					preparedStatement.setString(3,sede);
					
					ResultSet rs = preparedStatement.executeQuery();
					
					if(rs.next()){
						
						user.setUserid(rs.getInt("idchofer"));		
					}
				} catch(SQLException e){
					e.printStackTrace();
				}
		return user;
	}
	
	public User Login2(String dni){
		User user = new User();
		try{
					PreparedStatement preparedStatement = connection
					.prepareStatement("select idchofer from chofer where clave=?");
					preparedStatement.setString(1,dni);
					ResultSet rs = preparedStatement.executeQuery();
					
					if(rs.next()){
						
						user.setUserid(rs.getInt("idchofer"));		
					}
				} catch(SQLException e){
					e.printStackTrace();
				}
		return user;
	}
	
	public User Login3(String dni ,String clave ,String id){
		User user = new User();
		try{
					PreparedStatement preparedStatement = connection
					.prepareStatement("select idchofer from chofer where dni=? and clave=? and idsede=?");
					preparedStatement.setString(1,dni);
					preparedStatement.setString(2,clave);
					preparedStatement.setString(3,id);
					ResultSet rs = preparedStatement.executeQuery();
					
					if(rs.next()){
						
						user.setUserid(rs.getInt("idchofer"));		
					}
				} catch(SQLException e){
					e.printStackTrace();
				}
		return user;
	}

//INSERTAR INCIDENCIAS-----------------------------------------------------------------------------------------------
	
	public User Insertincidencia(int idinc,String desc,String idestadoinc,String idtipoinc,String idserv,String fecha,String fecha2,String idsede,String est){
		User user = new User();
		try{
					PreparedStatement preparedStatement = connection
					.prepareStatement("INSERT into incidencia(idincidencia,descripcion,estado_incidencia,idtipo_incidencia,idservicio_detalle,fecha_registro,fecha_modificacion,idsede,estado) value (?,?,?,?,?,?,?,?,?)");		
					preparedStatement.setInt(1, idinc);
					preparedStatement.setString(2, desc);
					preparedStatement.setString(3,idestadoinc);
					preparedStatement.setString(4,idtipoinc);
					preparedStatement.setString(5,idserv);
					preparedStatement.setString(6,fecha);
					preparedStatement.setString(7,fecha2);
					preparedStatement.setString(8,idsede);
					preparedStatement.setString(9,est);
					 preparedStatement.executeUpdate();
					
					} catch (SQLException e) {
					e.printStackTrace();
					}
		return user;
	}
	

	
	public User idincidencias(){
		User user = new User();
		try{
			PreparedStatement preparedStatement = connection.
					prepareStatement("select count(*) from incidencia;");
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next()){
				
				user.setUserid(rs.getInt("count(*)"));
				
				
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		return user;
	}
	
	public User countserv(String id){
		User user = new User();
		try{
			PreparedStatement preparedStatement = connection.
					prepareStatement("select count(*) from servicio_detalle where idchofer=? and estado_servicio='PENDIENTE';");
			preparedStatement.setString(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next()){
				
				user.setUserid(rs.getInt("count(*)"));
				
				
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		return user;
	}
	
	public User countservall(String id){
		User user = new User();
		try{
			PreparedStatement preparedStatement = connection.
					prepareStatement("select count(*) from servicio_detalle where estado_servicio='PENDIENTE' and fecha=?;");
			preparedStatement.setString(1,id);
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next()){
				
				user.setUserid(rs.getInt("count(*)"));
				
				
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		return user;
	}
	
	public User countservallA(){
		User user = new User();
		try{
			PreparedStatement preparedStatement = connection.
					prepareStatement("select count(*) from servicio_detalle where estado_servicio='PENDIENTE';");

			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next()){
				
				user.setUserid(rs.getInt("count(*)"));
				
				
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		return user;
	}
	
	public User cambiarestadoservicio(String id){
		User user = new User();
		try{
			PreparedStatement preparedStatement = connection.
					prepareStatement("UPDATE servicio_detalle SET estado_servicio='REALIZADO' WHERE idservicio_detalle=?");
			preparedStatement.setString(1, id);
			 preparedStatement.executeUpdate();
		} catch(SQLException e){
			e.printStackTrace();
		}
		return user;
	}
	
	public User getIdCliente(int userId){
		User user = new User();
		try{
			PreparedStatement preparedStatement = connection.
					prepareStatement("SELECT idCliente from File where idFile=?");
			preparedStatement.setInt(1, userId);
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next()){
				
				user.setFirstName(rs.getString("idCliente"));		
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		return user;
	}
		
	public User getNombreCliente(int userId){
		User user = new User();
		try{
			PreparedStatement preparedStatement = connection.
					prepareStatement("SELECT Nombre from Cliente where idCliente=?");
			preparedStatement.setInt(1, userId);
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next()){
				
				user.setFirstName(rs.getString("Nombre"));		
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		return user;
	}
	
	//Obtener COORDENADAS del destino del servicio
	public User getCoordenadas(String id){
		User user = new User();
		try{
			PreparedStatement preparedStatement = connection.
					prepareStatement("SELECT LATITUD , LONGITUD from Destinos where idDestinos=?");
			preparedStatement.setString(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next()){
				
				user.setFirstName(rs.getString("LATITUD"));
				user.setLastName(rs.getString("LONGITUD"));		
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		return user;
	}
	
	//Obtener iddestino de destinos 
	public User getiddestino(String id){
		User user = new User();
		try{
			PreparedStatement preparedStatement = connection.
					prepareStatement("SELECT Destinos_idDestinos from servicio_destinos where servicio_idservicio=?");
			preparedStatement.setString(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next()){
				
				user.setFirstName(rs.getString("Destinos_idDestinos"));
				
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		return user;
	}
	
	public List<User> getDescripcion() {
		List<User> users = new ArrayList<User>();
		try {
			PreparedStatement preparedStatement = connection.
					prepareStatement("SELECT DESCRIPCION FROM servicio limit 15");
			
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				User user = new User();

				user.setFirstName(rs.getString("DESCRIPCION"));
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return users;
	}

	public List<User> getlisttrasl(String id) {
		List<User> users = new ArrayList<User>();
		try {
			PreparedStatement preparedStatement = connection.
					prepareStatement("SELECT idtrasladista ,nombre,apellido FROM trasladista where idsede=?");
			preparedStatement.setString(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				User user = new User();

				user.setFirstName(rs.getString("idtrasladista"));
				user.setLastName(rs.getString("nombre"));
				user.setdirecc(rs.getString("apellido"));
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return users;
	}
	
	public List<User> getlistchofer(String id) {
		List<User> users = new ArrayList<User>();
		try {
			PreparedStatement preparedStatement = connection.
					prepareStatement("SELECT idchofer ,nombre,apellido FROM chofer where idsede=?");
			preparedStatement.setString(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				User user = new User();

				user.setFirstName(rs.getString("idchofer"));
				user.setLastName(rs.getString("nombre"));
				user.setdirecc(rs.getString("apellido"));
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return users;
	}
	public List<User> listasede() {
		List<User> users = new ArrayList<User>();
		try {
			PreparedStatement preparedStatement = connection.
					prepareStatement("SELECT descripcion FROM sede ");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				User user = new User();

				user.setFirstName(rs.getString("descripcion"));
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return users;
	}
	public User getdatostrasl(String id){
		User user = new User();
		try{
			PreparedStatement preparedStatement = connection.
					prepareStatement("SELECT nombre,apellido,direccion,correo,telefono,celular from trasladista where idtrasladista=?");
			preparedStatement.setString(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next()){
				
				user.setFirstName(rs.getString("nombre"));
				user.setLastName(rs.getString("apellido"));
				user.setEmail(rs.getString("direccion"));
				user.settelf(rs.getString("correo"));
				user.setcell(rs.getString("telefono"));
				user.setdirecc(rs.getString("celular"));
				
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		return user;
	}
	public User getdirecdestino(String id){
		User user = new User();
		try{
			PreparedStatement preparedStatement = connection.
					prepareStatement("select direccion,latitud,longitud from destinos where iddestinos=?");
			preparedStatement.setString(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next()){
				
				user.setFirstName(rs.getString("direccion"));
				user.setLastName(rs.getString("latitud"));
				user.setText(rs.getString("longitud"));
				
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		return user;
	}
	public User getdatochofer(String id){
		User user = new User();
		try{
			PreparedStatement preparedStatement = connection.
					prepareStatement("SELECT nombre,apellido,a.celular,e.razon_social,categoria,S.descripcion as sedec from chofer a inner join sede S on S.idsede=a.idsede inner join empresa e on a.idempresa=e.idempresa where idchofer=?");
			preparedStatement.setString(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next()){
				
				user.setFirstName(rs.getString("nombre"));
				user.setLastName(rs.getString("apellido"));
				user.setEmail(rs.getString("categoria"));
				user.settelf(rs.getString("sedec"));
				user.setdirecc(rs.getString("celular"));
				user.setText(rs.getString("razon_social"));
				
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		return user;
	}
	
	public User getdatosincidencia(String id){
		User user = new User();
		try{
			PreparedStatement preparedStatement = connection.
					prepareStatement("SELECT descripcion,idservicio_detalle,FECHA_REGISTRO,estado_INCIDENCIA from incidencia where idincidencia=?");
			preparedStatement.setString(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next()){
				
				user.setFirstName(rs.getString("descripcion"));
				user.setLastName(rs.getString("idservicio_detalle"));
				user.setEmail(rs.getString("FECHA_REGISTRO"));
				user.setText(rs.getString("estado_INCIDENCIA"));
				
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		return user;
	}
	
	public User idtipoincidencia(String id){
		User user = new User();
		try{
			PreparedStatement preparedStatement = connection.
					prepareStatement("SELECT idtipo_incidencia from incidencia where idincidencia=?");
			preparedStatement.setString(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next()){
				
				user.setFirstName(rs.getString("idtipo_incidencia"));

				
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		return user;
	}
	
	public User descripcionincidencia(String id){
		User user = new User();
		try{
			PreparedStatement preparedStatement = connection.
					prepareStatement("SELECT descripcion from tipo_incidencia where idtipo_incidencia=?");
			preparedStatement.setString(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next()){
				
				user.setFirstName(rs.getString("descripcion"));

				
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		return user;
	}
	
	
	public User idestado(String id){
		User user = new User();
		try{
			PreparedStatement preparedStatement = connection.
					prepareStatement("SELECT idestado_incidencia from incidencia where idincidencia=?");
			preparedStatement.setString(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next()){
				
				user.setFirstName(rs.getString("idestado_incidencia"));

				
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		return user;
	}
	
	public User descripcionestado(String id){
		User user = new User();
		try{
			PreparedStatement preparedStatement = connection.
					prepareStatement("SELECT descripcion from estado_incidencia where idestado_incidencia=?");
			preparedStatement.setString(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next()){
				
				user.setFirstName(rs.getString("descripcion"));

				
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		return user;
	}
	public User getpass(String id){
		User user = new User();
		try{
			PreparedStatement preparedStatement = connection.
					prepareStatement("SELECT clave from chofer where idchofer=?");
			preparedStatement.setString(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next()){
				
				user.setFirstName(rs.getString("clave"));

				
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		return user;
	}
	public User getpassA(String id){
		User user = new User();
		try{
			PreparedStatement preparedStatement = connection.
					prepareStatement("SELECT clave from usuario where idusuario=?");
			preparedStatement.setString(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next()){
				
				user.setFirstName(rs.getString("clave"));

				
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		return user;
	}
	public User Insertdatetimeorigen(int id,String fecha) {
		
		User user = new User();
		try{
					PreparedStatement preparedStatement = connection
					.prepareStatement("UPDATE servicio SET DateTimeOrigen=? WHERE idservicio=?");
					
					
					preparedStatement.setString(1, fecha);
					preparedStatement.setInt(2, id);
					 preparedStatement.executeUpdate();
					
					} catch (SQLException e) {
					e.printStackTrace();
					}
		return user;
	}
	
	public User Insertdatetimedestino(int id,String fecha) {
		
		User user = new User();
		try{
					PreparedStatement preparedStatement = connection
					.prepareStatement("UPDATE servicio SET DateTimeDestino=? WHERE idservicio=?");
					
					
					preparedStatement.setString(1, fecha);
					preparedStatement.setInt(2, id);
					 preparedStatement.executeUpdate();
					
					} catch (SQLException e) {
					e.printStackTrace();
					}
		return user;
	}
	
	public User changepass(String id,String clave) {
		
		User user = new User();
		try{
					PreparedStatement preparedStatement = connection
					.prepareStatement("UPDATE chofer SET clave=? WHERE idchofer=?");
					
					
					preparedStatement.setString(1, clave);
					preparedStatement.setString(2, id);
					 preparedStatement.executeUpdate();
					
					} catch (SQLException e) {
					e.printStackTrace();
					}
		return user;
	}
	
	public User changepassA(String id,String clave) {
		
		User user = new User();
		try{
					PreparedStatement preparedStatement = connection
					.prepareStatement("UPDATE usuario SET clave=? WHERE idusuario=?");
					
					
					preparedStatement.setString(1, clave);
					preparedStatement.setString(2, id);
					 preparedStatement.executeUpdate();
					
					} catch (SQLException e) {
					e.printStackTrace();
					}
		return user;
	}
	
	public User Insertcronometro(int id,String tiempo) {
		
		User user = new User();
		try{
					PreparedStatement preparedStatement = connection
					.prepareStatement("UPDATE servicio SET TiempoServicio=? WHERE idservicio=?");
					
					
					preparedStatement.setString(1, tiempo);
					preparedStatement.setInt(2, id);
					 preparedStatement.executeUpdate();
					
					} catch (SQLException e) {
					e.printStackTrace();
					}
		return user;
	}
	
	public User getHora(){
		User user = new User();
		try{
			PreparedStatement preparedStatement = connection.
					prepareStatement("SELECT curtime() as Time_sistema");
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next()){
				
				user.setFirstName(rs.getString("Time_sistema"));
				
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		return user;
	}
	
	public User dniwhereid(String id){
		User user = new User();
		try{
			PreparedStatement preparedStatement = connection.
					prepareStatement("SELECT dni from chofer where idchofer=?");
			preparedStatement.setString(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next()){
				
				user.setFirstName(rs.getString("dni"));

				
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		return user;
	}
	
	//Consultar Servicio Administrador  6/11/15 .......
	
	//Obtener todos los servicios  
	
	public List<User> getListAllServiceAdminP() {
		List<User> users = new ArrayList<User>();
		try {
			PreparedStatement preparedStatement = connection.
					prepareStatement("SELECT idservicio_detalle,descripcion FROM servicio_detalle where estado_SERVICIO='Pendiente' ORDER BY fecha ASC");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				User user = new User();

				user.setFirstName(rs.getString("idservicio_detalle"));
				user.setLastName(rs.getString("descripcion"));
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return users;
	}
	
	public List<User> getListAllServiceAdmin() {
		List<User> users = new ArrayList<User>();
		try {
			PreparedStatement preparedStatement = connection.
					prepareStatement("SELECT idservicio_detalle,descripcion FROM servicio_detalle where estado_SERVICIO='Finalizado' ORDER BY fecha ASC");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				User user = new User();

				user.setFirstName(rs.getString("idservicio_detalle"));
				user.setLastName(rs.getString("descripcion"));
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return users;
	}
	
	//Obtener NumeroPersonas de servicio_detalle (idservicio_detalle)
	
	public User getNumPersonas(String id){
		User user = new User();
		try{
			PreparedStatement preparedStatement = connection.
					prepareStatement("SELECT num_personas from servicio_detalle where idservicio_detalle=?");
			preparedStatement.setString(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next()){
				
				user.setFirstName(rs.getString("num_personas"));

				
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		return user;
	}
	
	//Obtener si es externalizado de servicio_detalle(idservicio_detalle)
	
	public User getExternalizado(String id){
		User user = new User();
		try{
			PreparedStatement preparedStatement = connection.
					prepareStatement("SELECT externalizado from servicio_detalle where idservicio_detalle=?");
			preparedStatement.setString(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next()){
				
				user.setFirstName(rs.getString("externalizado"));

				
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		return user;
	}
	
	//Obtener Precio Servicio de servicio_detalle(idservicio_detalle)
	
	public User getPrecioServicio(String id){
		User user = new User();
		try{
			PreparedStatement preparedStatement = connection.
					prepareStatement("SELECT PRECIO_SERVICIO from servicio_detalle where idservicio_detalle=?");
			preparedStatement.setString(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next()){
				
				user.setFirstName(rs.getString("PRECIO_SERVICIO"));

				
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		return user;
	}

	//Obtener Descuento de servicio_detalle(idservicio_detalle)
	
	public User getDescuento(String id){
		User user = new User();
		try{
			PreparedStatement preparedStatement = connection.
					prepareStatement("SELECT DESCUENTO from servicio_detalle where idservicio_detalle=?");
			preparedStatement.setString(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next()){
				
				user.setFirstName(rs.getString("DESCUENTO"));

				
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		return user;
	}
	
	//Obtener Adicional de servicio_detalle(idservicio_Detalle)
	
	public User getAdicional(String id){
		User user = new User();
		try{
			PreparedStatement preparedStatement = connection.
					prepareStatement("SELECT ADICIONAL from servicio_detalle where idservicio_detalle=?");
			preparedStatement.setString(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next()){
				
				user.setFirstName(rs.getString("ADICIONAL"));

				
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		return user;
	}
	
	//Obtener Dias Viaje de servicio_detalle(idservicio_detalle)
	
	public User getDiasViaje(String id){
		User user = new User();
		try{
			PreparedStatement preparedStatement = connection.
					prepareStatement("SELECT DIAS_VIAJE from servicio_detalle where idservicio_detalle=?");
			preparedStatement.setString(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next()){
				
				user.setFirstName(rs.getString("DIAS_VIAJE"));

				
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		return user;
	}
	
	//Obtener Estado del servicio de servicio_detalle(idservicio_detalle)
	
	public User getEstadoServicio(String id){
		User user = new User();
		try{
			PreparedStatement preparedStatement = connection.
					prepareStatement("SELECT ESTADO_SERVICIO from servicio_detalle where idservicio_detalle=?");
			preparedStatement.setString(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next()){
				
				user.setFirstName(rs.getString("ESTADO_SERVICIO"));

				
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		return user;
	}
	
	//Obtener Fecha de Registro de servicio_detalle(idServicio_detalle)
	
	public User getFechaRegistro(String id){
		User user = new User();
		try{
			PreparedStatement preparedStatement = connection.
					prepareStatement("SELECT FECHA_REGISTRO from servicio_detalle where idservicio_detalle=?");
			preparedStatement.setString(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next()){
				
				user.setFirstName(rs.getString("FECHA_REGISTRO"));

				
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		return user;
	}
	
	//Obtener idchofer de servicio_detalle(idServicio_detalle)
	
	public User getidChofer(String id){
		User user = new User();
		try{
			PreparedStatement preparedStatement = connection.
					prepareStatement("SELECT idchofer from servicio_detalle where idservicio_detalle=?");
			preparedStatement.setString(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next()){
				
				user.setFirstName(rs.getString("idchofer"));

				
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		return user;
	}
	
	//Obtener Nombre , Apellido de Chofer(idchofer)
	
	public User getNombreApellidoChofer(String id){
		User user = new User();
		try{
			PreparedStatement preparedStatement = connection.
					prepareStatement("SELECT nombre , apellido from chofer where idchofer=?");
			preparedStatement.setString(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next()){
				
				user.setFirstName(rs.getString("nombre"));
				user.setLastName(rs.getString("apellido"));

					
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		return user;
	}
	
	//Obtener idTrasladista de Servicio_detalle(idservicio_detalle)
	
	public User getIdTrasladista(String id){
		User user = new User();
		try{
			PreparedStatement preparedStatement = connection.
					prepareStatement("SELECT idtrasladista from servicio_detalle where idservicio_detalle=?");
			preparedStatement.setString(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next()){
				
				user.setFirstName(rs.getString("idtrasladista"));
			
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		return user;
	}
	
	//Obtener Nombre , Apellido de Trasladista(idtrasladista)
	
	public User getNombreApellidoTrasladista(String id){
		User user = new User();
		try{
			PreparedStatement preparedStatement = connection.
					prepareStatement("SELECT nombre , apellido from trasladista where idtrasladista=?");
			preparedStatement.setString(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next()){
				
				user.setFirstName(rs.getString("nombre"));
				user.setLastName(rs.getString("apellido"));

				
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		return user;
	}
	
	//Obtener idvehiculo de servicio_Detalle(idservicio_detalle)
	
	public User getidvehiculo(String id){
		User user = new User();
		try{
			PreparedStatement preparedStatement = connection.
					prepareStatement("SELECT idvehiculo from servicio_detalle where idservicio_detalle=?");
			preparedStatement.setString(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next()){
				
				user.setFirstName(rs.getString("idvehiculo"));

				
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		return user;
	}
	
	//Obtener Placa , Descripcion de Vehiculo(idVehiculo)
	
	public User getPlacaDescripcionVehiculo(String id){
		User user = new User();
		try{
			PreparedStatement preparedStatement = connection.
					prepareStatement("SELECT descripcion , placa from vehiculo where idvehiculo=?");
			preparedStatement.setString(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next()){
				
				user.setFirstName(rs.getString("descripcion"));
				user.setLastName(rs.getString("placa"));

				
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		return user;
	}
	
	public User getdescripcion2(String id){
		User user = new User();
		try{
			PreparedStatement preparedStatement = connection.
					prepareStatement("SELECT descripcion2 from vehiculo where idvehiculo=?");
			preparedStatement.setString(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next()){
				
				user.setFirstName(rs.getString("descripcion2"));

				
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		return user;
	}
	
	//Obtener idVuelo de servicio_detalle(idservicio)
	
	public User getIdVuelo(String id){
		User user = new User();
		try{
			PreparedStatement preparedStatement = connection.
					prepareStatement("SELECT idvuelo from servicio_detalle where idservicio_detalle=?");
			preparedStatement.setString(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next()){
				
				user.setFirstName(rs.getString("idvuelo"));

				
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		return user;
	}
	
	//Obtener descripcion del vuelo 
	
	public User getdescripcionvuelo(String id){
		User user = new User();
		try{
			PreparedStatement preparedStatement = connection.
					prepareStatement("SELECT descripcion,origen,destino,horario,idaerolinea from vuelo where idvuelo=?");
			preparedStatement.setString(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next()){
				
				user.setFirstName(rs.getString("descripcion"));
				user.setdirecc(rs.getString("origen"));
				user.setText(rs.getString("destino"));
				user.setLastName(rs.getString("horario"));
				user.setEmail(rs.getString("idaerolinea"));
				
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		return user;
	}
	
	public User getdescripcionaerolinea(String id){
		User user = new User();
		try{
			PreparedStatement preparedStatement = connection.
					prepareStatement("SELECT descripcion from aerolinea where idaerolinea=?");
			preparedStatement.setString(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next()){
				
				user.setFirstName(rs.getString("descripcion"));
				
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		return user;
	}
	
	
	//DETALLES DEL VEHICULO
	
	//Obtener descripcion vehiculo
	
	public User getdetallesvehiculo(String id){
		User user = new User();
		try{
			PreparedStatement preparedStatement = connection.
					prepareStatement("SELECT marca_idmarca,idtipo_vehiculo,descripcion , placa , año_fabricacion ,capacidad_max , color  from vehiculo where idvehiculo=?");
			preparedStatement.setString(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next()){
				
				user.setFirstName(rs.getString("descripcion"));
				user.setLastName(rs.getString("placa"));
				user.setcell(rs.getString("año_fabricacion"));
				user.setdirecc(rs.getString("capacidad_max"));
				user.setEmail(rs.getString("color"));
				user.settelf(rs.getString("idtipo_vehiculo"));
				user.setText(rs.getString("marca_idmarca"));
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		return user;
	}
	
	//Obtener IDSEDE vehiculo
	public User getidsede(String id){
		User user = new User();
		try{
			PreparedStatement preparedStatement = connection.
					prepareStatement("SELECT idSede  from vehiculo where idvehiculo=?");
			preparedStatement.setString(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next()){
				
				user.setFirstName(rs.getString("idSede"));
				
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		return user;
	}
	
	public User getidsedechofer(String sede){
		User user = new User();
		try{
			PreparedStatement preparedStatement = connection.
					prepareStatement("SELECT idSede  from sede where descripcion=?");
			preparedStatement.setString(1, sede);
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next()){
				
				user.setFirstName(rs.getString("idSede"));
				
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		return user;
	}
	
	public User getperfiladmin(String id){
		User user = new User();
		try{
			PreparedStatement preparedStatement = connection.
					prepareStatement("select b.apellidos , a.descripcion from usuario inner join empleado b on usuario.idempleado=b.idempleado inner join sede a on usuario.idsede=a.idsede where idusuario=?");
			preparedStatement.setString(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next()){
				
				user.setFirstName(rs.getString("apellidos"));
				user.setLastName(rs.getString("descripcion"));
				
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		return user;
	}
	
	//Obtener descripcion sede
	public User getdescripcionsede(String id){
		User user = new User();
		try{
			PreparedStatement preparedStatement = connection.
					prepareStatement("SELECT descripcion from Sede where idSede=?");
			preparedStatement.setString(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next()){
				
				user.setFirstName(rs.getString("descripcion"));
				
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		return user;
	}
	
	//Obtener nombre tipovehiculo
	public User getnombretipovehiculo(String id){
		User user = new User();
		try{
			PreparedStatement preparedStatement = connection.
					prepareStatement("SELECT nombre from tipo_vehiculo where idtipo_vehiculo=?");
			preparedStatement.setString(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next()){
				
				user.setFirstName(rs.getString("nombre"));
				
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		return user;
	}
	
	//Obtener nombre marca 
	public User getnombremarca(String id){
		User user = new User();
		try{
			PreparedStatement preparedStatement = connection.
					prepareStatement("SELECT nombreMarca from marca where idmarca=?");
			preparedStatement.setString(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next()){
				
				user.setFirstName(rs.getString("nombreMarca"));
				
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		return user;
	}
	
	//Obtener nombre modelo
	public User getnombremodelo(String id){
		User user = new User();
		try{
			PreparedStatement preparedStatement = connection.
					prepareStatement("SELECT nombreModelo from modelo where marca_idmarca=?");
			preparedStatement.setString(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next()){
				
				user.setFirstName(rs.getString("nombreModelo"));
				
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		return user;
	}
	
	//Obtener idincidencia x fecha de incidencia
	public List<User> getAllincidenciasxfecha(String userId) {
		List<User> users = new ArrayList<User>();
		try {
			PreparedStatement preparedStatement = connection.
					prepareStatement("select idincidencia from incidencia where FECHA_REGISTRO=?");
			preparedStatement.setString(1,userId);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				User user = new User();

				user.setFirstName(rs.getString("idincidencia"));
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

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



