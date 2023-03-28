package consultasSQL;

public class Constantes_SQL {

	//usuarios
	public final static String REGISTRA_USUARIO =  " insert into usuarios (nombre,email,pass,telefono,dni) values (?,?,?,?,?) ; "; 
	public final static String OBTENER_USUARIOS=" select * from usuarios";
	public final static String BUSCA_USUARIO=" select * from usuarioss  where id = ?";
	public final static String ELIMINA_USUARIO=" delete  from usuarios  where id = ?";
	public static final String SQL_BORRAR_USUARIO = 	"delete from usuarios where id = ? ";
	public static final String OBTENER_USUARIO_POR_ID = " select * from usuarios  where id = ?";;
	public static final String GUARDAR_CAMBIOS_USUARIO = "update usuarios set nombre = ?, email = ?, pass = ? , telefono = ? , dni = ? where id = ? ; ";;
	
	//fin de usuarios
	
	//camisetas
	public final static String REGISTRA_CAMISETA=  " insert into camisetas (modelo,temporada,talla,descripcion,precio,color) values (?,?,?,?,?,?) ; "; 
	public final static String BUSCA_CAMISETA=" select * from camisetas  where id = ?";
	public final static String OBTENER_CAMISETAS=" select * from camisetas";
	public final static String ELIMINA_CAMISETA=" delete  from camisetas  where id = ?";
	public static final String BORRAR_CAMISETA_ID = " delete  from camisetas  where id = ?";
	public static final String BUSCAR_CAMISETA_POR_ID = " select * from camisetas  where id = ?";
	public static final String GUARDAR_CAMBIOS_CAMISETA = "update camisetas set modelo = ?, temporada = ?, talla = ? , descripcion = ? , precio = ? , color = ?  where id = ? ; ";;

	
	//fin de camisetas
	
}
