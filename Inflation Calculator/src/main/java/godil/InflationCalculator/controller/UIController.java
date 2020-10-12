package godil.InflationCalculator.controller;

import godil.InflationCalculator.io.IOHelper;
import godil.InflationCalculator.model.IndexChange;
import godil.InflationCalculator.model.ItemValueChange;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class UIController {

    @FXML
    public TextField qtyFld;
    @FXML
    public TextField itmFld;
    @FXML
    public TextField p1Fld;
    @FXML
    public TextField p2Fld;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="table"
    private TableView<ItemValueChange> table; // Value injected by FXMLLoader

    @FXML // fx:id="itmCol"
    private TableColumn<ItemValueChange, String> itmCol; // Value injected by FXMLLoader

    @FXML // fx:id="qtyCol"
    private TableColumn<ItemValueChange, Double> qtyCol; // Value injected by FXMLLoader

    @FXML // fx:id="p1Col"
    private TableColumn<ItemValueChange, Double> p1Col; // Value injected by FXMLLoader

    @FXML // fx:id="p2Col"
    private TableColumn<ItemValueChange, Double> p2Col; // Value injected by FXMLLoader

    @FXML // fx:id="chgCol"
    private TableColumn<ItemValueChange, String> chgCol; // Value injected by FXMLLoader

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

    /**
     * event handler
     *
     * @param event
     */
    @FXML
    void addRowAction(ActionEvent event) {
        ic.getValueChanges().add(new ItemValueChange(itmFld.getText(), Double.valueOf(qtyFld.getText()), Double.valueOf(p1Fld.getText()), Double.valueOf(p2Fld.getText())));
        itmFld.clear();
        qtyFld.clear();
        p1Fld.clear();
        p2Fld.clear();
        updateUI();
    }

    /**
     * event handler
     *
     * @param event
     */
    @FXML
    void deleteRowsAction(ActionEvent event) {
        ic.getValueChanges().removeAll(table.getSelectionModel().getSelectedItems());
        updateUI();
    }

    /**
     * event handler
     *
     * @param event
     */
    @FXML
    void readDataAction(ActionEvent event) {
        ic = IOHelper.readData(pathField.getText().trim());
        yearsField.setText(ic.getYearsBetween() + "");
        updateUI();
    }

    /**
     * event handler
     *
     * @param event
     */
    @FXML
    void writeDataAction(ActionEvent event) {
        IOHelper.writeData(ic, pathField.getText().trim());
    }

    /**
     *
     */
    @FXML
    void initialize() {
        assert table != null : "fx:id=\"table\" was not injected: check your FXML file 'UIView.fxml'.";
        assert itmCol != null : "fx:id=\"itmCol\" was not injected: check your FXML file 'UIView.fxml'.";
        assert qtyCol != null : "fx:id=\"qtyCol\" was not injected: check your FXML file 'UIView.fxml'.";
        assert p1Col != null : "fx:id=\"p1Col\" was not injected: check your FXML file 'UIView.fxml'.";
        assert p2Col != null : "fx:id=\"p2Col\" was not injected: check your FXML file 'UIView.fxml'.";
        assert chgCol != null : "fx:id=\"chgCol\" was not injected: check your FXML file 'UIView.fxml'.";
        assert pathField != null : "fx:id=\"pathField\" was not injected: check your FXML file 'UIView.fxml'.";
        assert itmFld != null : "fx:id=\"itmFld\" was not injected: check your FXML file 'UIView.fxml'.";
        assert qtyFld != null : "fx:id=\"qtyFld\" was not injected: check your FXML file 'UIView.fxml'.";
        assert p1Fld != null : "fx:id=\"p1Fld\" was not injected: check your FXML file 'UIView.fxml'.";
        assert p2Fld != null : "fx:id=\"p2Fld\" was not injected: check your FXML file 'UIView.fxml'.";
        assert yearsField != null : "fx:id=\"yearsField\" was not injected: check your FXML file 'UIView.fxml'.";
        assert val1Txt != null : "fx:id=\"val1Txt\" was not injected: check your FXML file 'UIView.fxml'.";
        assert val2Txt != null : "fx:id=\"val2Txt\" was not injected: check your FXML file 'UIView.fxml'.";
        assert avgChgTxt != null : "fx:id=\"avgChgTxt\" was not injected: check your FXML file 'UIView.fxml'.";
        assert chgTxt != null : "fx:id=\"chgTxt\" was not injected: check your FXML file 'UIView.fxml'.";

        table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        itmCol.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        qtyCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        p1Col.setCellValueFactory(new PropertyValueFactory<>("price1"));
        p2Col.setCellValueFactory(new PropertyValueFactory<>("price2"));
        chgCol.setCellValueFactory(new PropertyValueFactory<>("formatPercentChange"));

        //add listener to field
        yearsField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.matches("^\\d+(\\.\\d+)?$")) {
                ic.setYearsBetween(Double.valueOf(newValue));
            } else {
                ic.setYearsBetween(-1);
            }
            updateUI();
        });

        ic = new IndexChange(new ArrayList<>(), 0);

        updateUI();
    }

    /**
     * update ui content
     */
    private void updateUI() {
        table.getItems().setAll(ic.getValueChanges());
        //yearsField.setText(ic.getYearsBetween() + "");
        val1Txt.setText("Value 1: " + ic.indexValue1());
        val2Txt.setText("Value 2: " + ic.indexValue2());
        if (ic.indexValue1() != 0) {
            chgTxt.setText("Change: " + String.format("%.5f", ic.getPercentChange()) + "%");
            if (ic.getYearsBetween() > 0) {
                avgChgTxt.setText("Compound Annual Change: " + String.format("%.5f", ic.getPercentAverageChange()) + "%");
            } else {
                avgChgTxt.setText("");
            }
        }
    }
}
