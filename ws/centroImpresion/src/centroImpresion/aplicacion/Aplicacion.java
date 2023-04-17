package centroImpresion.aplicacion;

import java.io.IOException;

import centroImpresion.controller.CentroImpresionController;
import centroImpresion.exception.DocumentoException;
import centroImpresion.exception.ImpresoraException;
import centroImpresion.model.CentroImpresion;
import centroImpresion.model.Documento;
import centroImpresion.model.Impresora;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Aplicacion extends Application{

	private Stage primaryStage;
	private CentroImpresion centroImpresion;

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		this.centroImpresion = new CentroImpresion("3023");

		mostrarVentanaPrincipal();
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	public CentroImpresion getCentroImpresion() {
		return centroImpresion;
	}

	public void setCentroImpresion(CentroImpresion centroImpresion) {
		this.centroImpresion = centroImpresion;
	}

	private void mostrarVentanaPrincipal() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Aplicacion.class.getResource("/centroImpresion/view/CentroImpresionView.fxml"));
			AnchorPane anchorPane = (AnchorPane)loader.load();
			CentroImpresionController centroImpresionController = loader.getController();
			centroImpresionController.setAplicacion(this);

			Scene scene = new Scene(anchorPane);
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		launch(args);
	}
	//----------------Funciones que conectan los controllers con la logica

	public Impresora crearImpresora(String nombre, String marca) throws ImpresoraException {
		Impresora impresora = centroImpresion.crearImpresora(nombre, marca);
		return impresora;
	}

	public void eliminarImpresora(Impresora impresoraSeleccionada) throws ImpresoraException {
		centroImpresion.eliminarImpresora(impresoraSeleccionada.getNombre(), impresoraSeleccionada.getMarca());
	}

	public void activarImpresora(Impresora impresoraSeleccionada) {
		centroImpresion.conectarImpresora(impresoraSeleccionada);

	}

	public void desconectarImpresora(Impresora impresoraSeleccionada) {
		centroImpresion.desconectarImpresora(impresoraSeleccionada);
	}

	public String imprimirDocumento(Impresora impresoraSeleccionada, Documento documento) throws ImpresoraException {
		return centroImpresion.imprimirDocumento(impresoraSeleccionada, documento);
	}

	public void eliminarDocumento(Documento documentoSeleccionado) throws DocumentoException {
		centroImpresion.eliminarDocumento(documentoSeleccionado.getTitulo());
	}

	public String cargarDocumentos() throws DocumentoException {
		return centroImpresion.seleccionarArchivosTxt();
	}

	public String extraerMensaje(String text) throws DocumentoException {
		return centroImpresion.extraerMensajeTxt(text);
	}

	public Documento crearDocumento(String titulo, String texto, Integer prioridad) throws DocumentoException {

		return centroImpresion.crearDocumento(texto, titulo, prioridad);
	}

	public boolean verificarExistenciaDocumento(String titulo) {
		return centroImpresion.verificarExistenciaDocumento(titulo);
	}

}