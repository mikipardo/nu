package utilsFile;
import modelo.Camiseta;
import modelo.Usuario;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class GestorDeFotos {


	public static void guardarFotoUsuario(Usuario u, CommonsMultipartFile foto, String rutaRealDelProyecto) {
		String nombreArchivo = "u" + u.getId() + ".jpg";
		String rutaSubidas = rutaRealDelProyecto + "/subidas";
		//si rutaSubidas no existe, crearla:
		File fileRutaSubidas = new File(rutaSubidas);
		if( ! fileRutaSubidas.exists() ) {
			fileRutaSubidas.mkdirs();
		}
		//si existe el archivo subido
		if(foto.getSize() > 0) {
			try {
				foto.transferTo(new File(rutaSubidas,nombreArchivo));
				System.out.println("archivo movido a: " + rutaSubidas);
			} catch (IllegalStateException | IOException e) {
				System.out.println("no pude mover el archivo a la ruta de subidas");
				e.printStackTrace();
			}
		}else {
			System.out.println("se subio un usurio sin avatar, no hay problema,"
					+ "de momento la imagen es opcional");
		}
	}//end guardarFotoUsuario
	
		
		public static void guardarFoto(Camiseta camiseta, String rutaReal) {
			
			camiseta.getFotoDelante();
			String nombreArchivo = camiseta.getId() + ".jpg";
		
			String rutaSubidas = rutaReal + "/subidas";
			
			//si rutaSubidas no existe, crearla:
			File fileRutaSubidas = new File(rutaSubidas);
			if( ! fileRutaSubidas.exists() ) {
				// mkdirs establece un su-directorio especifico, en este caso si la ruta de
				// subdirectorio no se ha creado la crea, solo la primera vez.
				fileRutaSubidas.mkdirs();
			}
			//si existe el archivo subido
			if(camiseta.getFotoDelante().getSize() > 0 ) {
				try {
					camiseta.getFotoDelante().transferTo(new File(rutaSubidas,nombreArchivo));

					System.out.println("archivo movido a: " + rutaSubidas);
				} catch (IllegalStateException | IOException e) {
					System.out.println("no pude mover el archivo a la ruta de subidas");
					e.printStackTrace();
				}
			}
	}
		
		public static void guardarFotoAtras(Camiseta c, String rutaReal) {
			
			String nombreArchivoAtras= c.getId() + "B.jpg";
			String rutaSubidas = rutaReal + "/subidas";
			//si rutaSubidas no existe, crearla:
			File fileRutaSubidas = new File(rutaSubidas);
			if( ! fileRutaSubidas.exists() ) {
				// mkdirs establece un su-directorio especifico, en este caso si la ruta de
				// subdirectorio no se ha creado la crea, solo la primera vez.
				fileRutaSubidas.mkdirs();
			}
			//si existe el archivo subido
			if(c.getFotoAtras().getSize()>0) {
				try {
			
					c.getFotoAtras().transferTo(new File(rutaSubidas,nombreArchivoAtras));
					
					System.out.println("archivo movido a: " + rutaSubidas+"Zona B");
				} catch (IllegalStateException | IOException e) {
					System.out.println("no pude mover el archivo a la ruta de subidas foto B");
					e.printStackTrace();
				}
			}else {
				System.out.println("se subio una Camiseta parte atras B sin foto, no hay problema,"
						+ "de momento la imagen es opcional");
			}
	}
		public static void guardarFotoOpcional(Camiseta c, String rutaReal) {
			
			String nombreArchivoOpcional= c.getId() + "X.jpg";
			String rutaSubidas = rutaReal + "/subidas";
			//si rutaSubidas no existe, crearla:
			File fileRutaSubidas = new File(rutaSubidas);
			if( ! fileRutaSubidas.exists() ) {
				// mkdirs establece un su-directorio especifico, en este caso si la ruta de
				// subdirectorio no se ha creado la crea, solo la primera vez.
				fileRutaSubidas.mkdirs();
			}
			//si existe el archivo subido
			if(c.getFotoOpcional().getSize()>0) {
				try {
			
					c.getFotoOpcional().transferTo(new File(rutaSubidas,nombreArchivoOpcional));
					
					System.out.println("archivo movido a: " + rutaSubidas+"Zona X");
				} catch (IllegalStateException | IOException e) {
					System.out.println("no pude mover el archivo a la ruta de subidas foto X");
					e.printStackTrace();
				}
			}else {
				System.out.println("se subio una Camiseta parte atras X sin foto 3, no hay problema,"
						+ "de momento la imagen es opcional");
			}
	}
		
		public static void reductorImagenes(String rutaReal,Camiseta c) {
			int ancho = 2000;
			 int largo = 2200;
			
			 String rutaFotoReducir = rutaReal+ "/subidas/"+ c.getId() +".jpg";
			 String rutaBack = rutaReal + "/subidas/"+c.getId()+"B.jpg";
			 String rutaFotoDestinoOpcional = rutaReal + "/subidas/"+c.getId()+"X.jpg";
			
			File url = new File (rutaFotoReducir);
			File url2 = new File (rutaBack);
			File url3 = new File (rutaFotoDestinoOpcional);
			  try {
				  //Leemos la imagen por un argumento file que antes le hemos metido por parametro la ruta
				BufferedImage imagenRead = ImageIO.read(url);
				BufferedImage imagenRead2 = ImageIO.read(url2);
				BufferedImage imagenRead3 = ImageIO.read(url3);
				//creamos otro objeto BufferedImage para establecer el marco tipo que queremos (una especie de molde)
				//primer argumento ancho y segundo alto
				
				  BufferedImage imagenModelo = new BufferedImage(ancho,
						  largo, imagenRead.getType());
				  
				  BufferedImage imagenModelo2 = new BufferedImage(ancho,
						  largo, imagenRead2.getType());
				  
				  BufferedImage imagenModelo3 = new BufferedImage(ancho,
						  largo, imagenRead3.getType());
				  
				  //objeto para luego usar el dibujarImagen
				  Graphics2D g2d = imagenModelo.createGraphics();
				  Graphics2D g2d2 = imagenModelo2.createGraphics();
				  Graphics2D g2d3 = imagenModelo3.createGraphics();
				  
				  //imagen-X-Y-ancho-largo-ImageObserver
				  g2d.drawImage(imagenRead, 0, 0, ancho, largo, null);
				  g2d2.drawImage(imagenRead2, 0, 0, ancho, largo, null);
				  g2d3.drawImage(imagenRead3, 0, 0, ancho, largo, null);
				  //liberamos recursos
				  g2d.dispose();
				  g2d2.dispose();
				  g2d3.dispose();
				  //Terminacion JPG
	
				  	ImageIO.write(imagenModelo, "jpg", new File(rutaFotoReducir));
			        ImageIO.write(imagenModelo2, "jpg", new File(rutaBack));
			       ImageIO.write(imagenModelo3, "jpg", new File(rutaFotoDestinoOpcional));
			       
			System.out.println("subiendo fotos.....");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Error al leer la imagen de fotografia en la clase: "+rutaFotoReducir+" en el utilsFile/Gestor de fotos/reductorImagenes");
				e.printStackTrace();
			}
		}
		
		public static boolean comprobarJPG(String nombreArchivo) {
			boolean puntoJPG=false;
			int posicion = 0;
			String terminacion = "";
			char[] array = nombreArchivo.toCharArray();
			for (int i = nombreArchivo.length() - 1; i > 0; i--) {
				if (String.valueOf(array[i]).equals(".")) {
					posicion = i;
				}
			}
			terminacion = nombreArchivo.substring(posicion, nombreArchivo.length());
			if(terminacion.equalsIgnoreCase(".jpg")) {
				puntoJPG=true;
			}
			return puntoJPG;
		}

		public static boolean borrarPortadaLibro(String idFoto, String rutaReal) {
			//metemos la ruta en el File y le aplicamos metodo delete ->boolean
			File f = new File(rutaReal+"/subidas/"+idFoto+".jpg");
			if(f.delete()) {
				System.out.println("archivo borrado correctamente");
				return true;
			}else {
				System.out.println("no se pudo borrar el archivo");
				return false;
			}
		}

}