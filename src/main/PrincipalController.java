package main;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.*;

public class PrincipalController {

    @FXML
    private MenuBar menuBar;

    @FXML
    private Button btnFileChooser;

    @FXML
    private TextArea textArea;

    @FXML
    private Label lblStatus;


    //
    // Functions
    //
    @FXML
    public void closeApp() {
        System.exit(0);
    }


    /**
     * This function opens and reads a text file,
     * after that show its content inside text area.
     *
     * This function is called from JavaFX components.
     */
    @FXML
    public void selectFile() {
        Response response;

        //
        // Opens the file chooser dialog and select one text file
        response = this.openFileChooser();
        if (!response.ok) {
            Message.setLabelInfo(this.lblStatus, response.message);
            return;
        }

        //
        // Reads the selected file content
        response = this.outputTextArea(this.textArea, (File) response.data);
        if (!response.ok) {
            Message.showError("Error al leer el documento", "", response.message);
            Message.setLabelError(this.lblStatus, response.message);

            return;
        }

        Message.showInfo("Lectura de archivo", response.message, "");
        Message.setLabelSuccess(this.lblStatus, response.message);
    }


    /**
     * This function only shows information about the program.
     * @throws IOException
     */
    @FXML
    public void showInformation() throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("AboutView.fxml"));
        stage.setTitle("Información");
        stage.setScene(new Scene(root, 320, 490));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.setMaximized(false);
        stage.show();
    }


    private Response openFileChooser() {
        FileChooser fileChooser = new FileChooser();

        //
        // Config file chooser
        fileChooser.setInitialFileName("selectedFile.txt");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text files", "*.txt")
        );

        File selectedFile = fileChooser.showOpenDialog(this.menuBar.getScene().getWindow());

        if (selectedFile == null)
            return new Response(false, null, "No se seleccionó ningún archivo");
        else
            return new Response(true, selectedFile, "Archivo cargado correctamente");
    }


    private Response outputTextArea(TextArea textArea, File pFile) {
        textArea.clear();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(pFile));
            String str;

            while ((str = reader.readLine()) != null) {
                textArea.appendText(str);
                textArea.appendText(System.getProperty("line.separator"));
            }
            return new Response(true, null, "Texto analizado correctamente");
        } catch (FileNotFoundException e) {
            return new Response(false, null, "Archivo no encontrado: " + e.getMessage());
        } catch (IOException e) {
            return new Response(false, null, "Error al leer el archivo: " + e.getMessage());
        }
    }
}
