package sio.bulletin;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import sio.bulletin.Model.Etudiant;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.TreeMap;

public class BulletinController implements Initializable
{
    DecimalFormat df;
    HashMap<String,HashMap<String, HashMap<String,ArrayList<Etudiant>>>> lesBulletins;
    @FXML
    private AnchorPane apBulletin;
    @FXML
    private ListView lvMatieres;
    @FXML
    private ListView lvDevoirs;
    @FXML
    private ComboBox cboTrimestres;
    @FXML
    private TextField txtNomEtudiant;
    @FXML
    private TextField txtNote;
    @FXML
    private Button btnValider;
    @FXML
    private AnchorPane apMoyenne;
    @FXML
    private Button btnMenuBulletin;
    @FXML
    private Button btnMenuMoyenne;
    @FXML
    private ListView lvMatieresMoyenne;
    @FXML
    private TreeView tvMoyennesParDevoirs;
    @FXML
    private TextField txtMajor;
    @FXML
    private TextField txtNoteMaxi;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        apBulletin.toFront();
        df = new DecimalFormat("#.##");
        lesBulletins = new HashMap<>();
        lvMatieres.getItems().addAll("Maths","Anglais","Economie");
        lvDevoirs.getItems().addAll("Devoir n°1","Devoir n°2","Devoir n°3","Devoir n°4");
        cboTrimestres.getItems().addAll("Trim 1","Trim 2","Trim 3");
        cboTrimestres.getSelectionModel().selectFirst();
        lvMatieres.getSelectionModel().selectionModeProperty().addListener((observable, oldValue, newValue) ->{
            if (newValue != null){
                lvMatieresMoyenne.getItems().clear();
                lvMatieresMoyenne.getItems().add(newValue);
            }
        });
    }

    @FXML
    public void btnMenuClicked(Event event)
    {
        if(event.getSource()==btnMenuBulletin)
        {
            apBulletin.toFront();
        }
        else if(event.getSource()==btnMenuMoyenne)
        {
            apMoyenne.toFront();
        }
    }

    @FXML
    public void btnValiderClicked(Event event) {
        // A vous de jouer
        if (lvMatieres.getSelectionModel().getSelectedItem().toString() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Choix de la matiere");
            alert.setHeaderText("");
            alert.setContentText("Sélectionner une matiere");
            alert.showAndWait();

        } else if (lvDevoirs.getSelectionModel().getSelectedItem().toString() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Choix du devoir");
            alert.setHeaderText("");
            alert.setContentText("Sélectionner un devoir");
            alert.showAndWait();

        } else if (txtNomEtudiant.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Choix de l'etudiant");
            alert.setHeaderText("");
            alert.setContentText("Saisir un etudiant");
            alert.showAndWait();

        } else if (txtNote.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Choix du la note");
            alert.setHeaderText("");
            alert.setContentText("Saisir une note");
            alert.showAndWait();
        } else {

                String matiere = lvMatieres.getSelectionModel().getSelectedItem().toString();
                String devoir = lvDevoirs.getSelectionModel().getSelectedItem().toString();
                String trimestre = cboTrimestres.getSelectionModel().getSelectedItem().toString();

                Etudiant etudiant = new Etudiant(txtNomEtudiant.getText(), txtNote.getPrefColumnCount());
                if (!lesBulletins.containsKey(trimestre)) {
                    lesBulletins.put(trimestre, new HashMap<>());
                }
                if (!lesBulletins.get(trimestre).containsKey(matiere)) {
                    lesBulletins.get(trimestre).put(matiere, new HashMap<>());
                }
                if (!lesBulletins.get(trimestre).get(matiere).containsKey(devoir)) {
                    lesBulletins.get(trimestre).get(matiere).put(devoir, new ArrayList<>());
                }
                lesBulletins.get(trimestre).get(matiere).get(devoir).add(etudiant);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Note affectée");
                alert.setHeaderText("");
                alert.setContentText("Note enregistrée");
                alert.showAndWait();
        }
    }


    @FXML
    public void lvMatieresMoyenneClicked(Event event)
    {
    String noeudS = String.valueOf((TreeItem)tvMoyennesParDevoirs.getSelectionModel().getSelectedItem());
    if (noeudS != null){

    }




    }
}