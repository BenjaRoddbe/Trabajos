package proyect;

import java.sql.*;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class mySQL_Proyecto {
	private final String DB_URL = "jdbc:mysql://localhost:3306/ProyectosUTUArrayanes?useSSL=false"; // URL DE LA BD
	private final String DB_USER = "root"; // USUARIO DE LA BD
	private final String DB_PASS = ""; // CONTRASEÑA DE LA BD

	private Connection getConnection() throws SQLException {
		return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
	}

	public void listarProyectos(DefaultTableModel modelo, String orientacionSeleccionada) {

		String sentenciaProyectosListar = "SELECT idProyecto, orientacion, nombreProyecto, tema, año, autores, ciAutores FROM Proyectos";

		if (!orientacionSeleccionada.equals("")) {
			sentenciaProyectosListar += " WHERE orientacion='" + orientacionSeleccionada + "'";

			System.out.println(sentenciaProyectosListar);
		}

		try {
			Connection conexion = getConnection();
			PreparedStatement declaracionProyectos = conexion.prepareStatement(sentenciaProyectosListar);
			ResultSet rsProyectos = declaracionProyectos.executeQuery();

			modelo.setRowCount(0);

			while (rsProyectos.next()) {
				int id = rsProyectos.getInt(1);
				String orientacion = rsProyectos.getString(2);
				String nombre = rsProyectos.getString(3);
				String tema = rsProyectos.getString(4);
				String anio = rsProyectos.getString(5);
				String autores = rsProyectos.getString(6);
				String ciAutores = rsProyectos.getString(7);

				Object[] fila = { id, orientacion, nombre, tema, anio, autores, ciAutores };
				modelo.addRow(fila);
			}

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
		}
	}

	// FUNCION DAR ALTA PROYECTO
	public void altaProyecto(String ori, String nom, String tema, int anio, String autores, int ciAutores) {

		String sentenciaProyectosAlta = "INSERT INTO Proyectos(orientacion, nombreProyecto, tema, año, autores, ciAutores)  VALUES ("
				+ "'" + ori + "','" + nom + "','" + tema + "'," + anio + ",'" + autores + "'," + ciAutores + ");";
		System.out.println(sentenciaProyectosAlta);

		try {
			Connection conexion = getConnection();
			PreparedStatement declaracionSQL = conexion.prepareStatement(sentenciaProyectosAlta);
			declaracionSQL.executeUpdate();

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
		}
	}

//	FUNCION DAR BAJA PROYECTO
	public void bajaProyecto(int idProyecto) {
		String sentenciaBajaProyecto = "DELETE FROM Proyectos WHERE idProyecto=" + idProyecto + ";";
		
		try {
			Connection conexion = getConnection();
			PreparedStatement declaracionSQL = conexion.prepareStatement(sentenciaBajaProyecto);
			declaracionSQL.executeUpdate();

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
		}

	}

	public void altaAutor(int ciAu, String primerNom, String primerAp) {
		String sentenciaAltaAutor = "INSERT INTO Autores(ciAutor, PrimerNombre, PrimerApellido) VALUES (" + "" + ciAu + ",'" + primerNom + "','" + primerAp + "');";
		
		try {
			Connection conexion = getConnection();
			PreparedStatement declaracionSQL = conexion.prepareStatement(sentenciaAltaAutor);
			declaracionSQL.executeUpdate();

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
		}
	}
	
//	FUNCION DAR BAJA AUTOR
	public void bajaAutor(int ciAutor) {
		String sentenciaDarBajaAutor = "DELETE FROM Autores WHERE ciAutor=" + ciAutor + ";";
		
		try {
			Connection conexion = getConnection();
			PreparedStatement declaracionSQL = conexion.prepareStatement(sentenciaDarBajaAutor);
			declaracionSQL.executeUpdate();

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
		}
	}
	

	public void altaAdmin(int ciAd, String contrasenia) {
		String sentenciaAltaAdmin = "INSERT INTO Admin(ciAdmin, contraseña) VALUES (" + "" + ciAd + ",'" + contrasenia + "');";

		try {
			Connection conexion = getConnection();
			PreparedStatement declaracionSQL = conexion.prepareStatement(sentenciaAltaAdmin);
			declaracionSQL.executeUpdate();

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
		}
	}

//	FUNCION DAR BAJA ADMIN
	public void bajaAdmin(int ciAdmin) {
		String sentenciaDarBajaAdmin = "DELETE FROM Admin WHERE ciAdmin=" + ciAdmin + ";";
		try {
			Connection conexion = getConnection();
			PreparedStatement declaracionSQL = conexion.prepareStatement(sentenciaDarBajaAdmin);
			declaracionSQL.executeUpdate();

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
		}
	}

}
