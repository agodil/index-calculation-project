package sample;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

public class UIController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="table"
    private TableView<ValueChange> table; // Value injected by FXMLLoader

    @FXML // fx:id="itmCol"
    private TableColumn<ValueChange, String> itmCol; // Value injected by FXMLLoader

    @FXML // fx:id="qtyCol"
    private TableColumn<ValueChange, Double> qtyCol; // Value injected by FXMLLoader

    @FXML // fx:id="p1Col"
    private TableColumn<ValueChange, Double> p1Col; // Value injected by FXMLLoader

    @FXML // fx:id="p2Col"
    private TableColumn<ValueChange, Double> p2Col; // Value injected by FXMLLoader

    @FXML // fx:id="chgCol"
    private TableColumn<ValueChange, Double> chgCol; // Value injected by FXMLLoader

    @FXML // fx:id="pathField"
    private TextField pathField; // Value injected by FXMLLoader

    @FXML // fx:id="yearsField"
    private TextField yearsField; // Value injected by FXMLLoader

    @FXML // fx:id="val1Txt"
    private Text val1Txt; // Value injected by FXMLLoader

    @FXML // fx:id="val2Txt"
    private Text val2Txt; // Value injected by FXMLLoader

    @FXML // fx:id="avgChgTxt"
    private Text avgChgTxt; // Value injected by FXMLLoader

    @FXML // fx:id="chgTxt"
    private Text chgTxt; // Value injected by FXMLLoader

    private IndexChange ic;

    @FXML
    void addRowAction(ActionEvent event) {
        table.getItems().add(new ValueChange("", 0, 0, 0));
    }

    @FXML
    void deleteRowsAction(ActionEvent event) {
        table.getItems().removeAll(table.getSelectionModel().getSelectedItems());
        updateUI();
    }

    @FXML
    void readDataAction(ActionEvent event) {
        ic = IOHelper.readData(pathField.getText().trim());
        updateUI();
    }

    @FXML
    void writeDataAction(ActionEvent event) {
        IOHelper.writeData(ic, pathField.getText().trim());
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert table != null : "fx:id=\"table\" was not injected: check your FXML file 'UIView.fxml'.";
        assert itmCol != null : "fx:id=\"itmCol\" was not injected: check your FXML file 'UIView.fxml'.";
        assert qtyCol != null : "fx:id=\"qtyCol\" was not injected: check your FXML file 'UIView.fxml'.";
        assert p1Col != null : "fx:id=\"p1Col\" was not injected: check your FXML file 'UIView.fxml'.";
        assert p2Col != null : "fx:id=\"p2Col\" was not injected: check your FXML file 'UIView.fxml'.";
        assert chgCol != null : "fx:id=\"chgCol\" was not injected: check your FXML file 'UIView.fxml'.";
        assert pathField != null : "fx:id=\"pathField\" was not injected: check your FXML file 'UIView.fxml'.";
        assert yearsField != null : "fx:id=\"yearsField\" was not injected: check your FXML file 'UIView.fxml'.";
        assert val1Txt != null : "fx:id=\"val1Txt\" was not injected: check your FXML file 'UIView.fxml'.";
        assert val2Txt != null : "fx:id=\"val2Txt\" was not injected: check your FXML file 'UIView.fxml'.";
        assert avgChgTxt != null : "fx:id=\"avgChgTxt\" was not injected: check your FXML file 'UIView.fxml'.";
        assert chgTxt != null : "fx:id=\"chgTxt\" was not injected: check your FXML file 'UIView.fxml'.";

        itmCol.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        qtyCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        p1Col.setCellValueFactory(new PropertyValueFactory<>("price1"));
        p2Col.setCellValueFactory(new PropertyValueFactory<>("price2"));
        chgCol.setCellValueFactory(new PropertyValueFactory<>("percentChange"));

        ic = new IndexChange(table.getItems(), 0);

        updateUI();
    }

    private void updateUI() {
        ObservableList list = FXCollections.observableArrayList();
        list.addAll(ic.getValueChanges());
        table.setItems(list);
        yearsField.setText(ic.getYearsBetween() + "");
        val1Txt.setText(ic.indexValue1() + "");
        val2Txt.setText(ic.indexValue2() + "");
        if(ic.indexValue1() != 0) {
            chgTxt.setText(ic.getPercentChange() + "");
            if(ic.getYearsBetween() > 0) {
                avgChgTxt.setText(ic.getPercentAverageChange() + "");
            }
        }
    }
}
