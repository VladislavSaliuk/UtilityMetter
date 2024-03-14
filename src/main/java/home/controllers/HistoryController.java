package home.controllers;

import database.DAO.history.HistoryDAO;
import database.entity.History;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class HistoryController implements Initializable {


    @FXML
    private TableView<History> historyTableView;

    @FXML
    private TableColumn<History, String> counterNumberTableColumn;

    @FXML
    private TableColumn<History, Double> dayTariffTableColumn;

    @FXML
    private TableColumn<History, Double> nightTariffTableColumn;

    @FXML
    private TableColumn<History, Integer> markupTableColumn;

    @FXML
    private TableColumn<History, Double> billTableColumn;

    @FXML
    private TextField historySearchFilter;

    @FXML
    private TableColumn<History, String> payDateTableColumn;

    private HistoryDAO historyDAO;
    private ObservableList<History> observableList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        historyDAO = new HistoryDAO();
        observableList = FXCollections.observableArrayList(historyDAO.getItems());
        counterNumberTableColumn.setCellValueFactory(new PropertyValueFactory<>("counterNumber"));
        billTableColumn.setCellValueFactory(new PropertyValueFactory<>("totalBill"));
        dayTariffTableColumn.setCellValueFactory(new PropertyValueFactory<>("dayTariff"));
        nightTariffTableColumn.setCellValueFactory(new PropertyValueFactory<>("nightTariff"));
        markupTableColumn.setCellValueFactory(new PropertyValueFactory<>("markup"));
        payDateTableColumn.setCellValueFactory(new PropertyValueFactory<>("payDate"));
        historyTableView.setItems(observableList);

        FilteredList<History> filteredList = new FilteredList<>(observableList, b -> true);
        historySearchFilter.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(history -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                return history.getPayDate().toLowerCase().contains(lowerCaseFilter);
            });
        });

        SortedList<History> sortedData = new SortedList<>(filteredList);
        sortedData.comparatorProperty().bind(historyTableView.comparatorProperty());
        historyTableView.setItems(sortedData);
    }

    @FXML
    public void refreshTable(ActionEvent event){
        observableList.setAll(historyDAO.getItems());
    }

    @FXML
    public void delete(ActionEvent event){
        History selectedHistory = historyTableView.getSelectionModel().getSelectedItem();
        if(selectedHistory != null) {
            ObservableList<History> historyList = FXCollections.observableArrayList(historyTableView.getItems());
            historyList.remove(selectedHistory);
            historyTableView.setItems(historyList);
            historyDAO.delete(selectedHistory.getHistoryID());
        }
    }


    @FXML
    public void clearHistory(ActionEvent event){
        ObservableList<History> historyList = FXCollections.observableArrayList(historyTableView.getItems());
        historyTableView.getItems().clear();
        historyDAO.clear();
    }

    @FXML
    public void makeReport(ActionEvent event) {
        String path = "";
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF files", "*.pdf"));
        File selectedFile = fileChooser.showSaveDialog(null);
        if (selectedFile != null) {
            path = selectedFile.getAbsolutePath();
            try {
                historyTableView.layout();
                createPDF(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private void createPDF(String filePath) throws IOException {
        PDDocument pdDocument = new PDDocument();
        int cellHeight = 15;

        int colCount = 6;
        int rowCount = historyTableView.getItems().size();

        int currentPage = 1;

        PDPage pdPage = new PDPage();
        pdDocument.addPage(pdPage);
        int pageHeight = (int) pdPage.getTrimBox().getHeight();
        int pageWidth = (int) pdPage.getTrimBox().getWidth();
        PDPageContentStream pdPageContentStream = new PDPageContentStream(pdDocument, pdPage);
        pdPageContentStream.setStrokingColor(Color.BLACK);
        pdPageContentStream.setLineWidth(1);

        int initX = 3;
        int initY = pageHeight - 3;
        initY -= cellHeight + 5;

        for (int i = 0; i < colCount; i++) {
            int cellWidth = (int) (((int) historyTableView.getColumns().get(i).getWidth()) * 0.9);
            pdPageContentStream.addRect(initX, initY, cellWidth, -cellHeight);
            pdPageContentStream.beginText();
            pdPageContentStream.newLineAtOffset(initX + 5, initY - cellHeight + 2);
            pdPageContentStream.setFont(PDType1Font.TIMES_ROMAN, 10);
            pdPageContentStream.showText(historyTableView.getColumns().get(i).getText());
            pdPageContentStream.endText();
            initX += cellWidth;
        }


        for (int i = 0; i < rowCount; i++) {
            History currentOrderReport = historyTableView.getItems().get(i);
            if (initY - (cellHeight * 2) < 0) {
                pdPageContentStream.stroke();
                pdPageContentStream.close();

                pdPage = new PDPage();
                pdDocument.addPage(pdPage);
                pdPageContentStream = new PDPageContentStream(pdDocument, pdPage);
                pdPageContentStream.setStrokingColor(Color.BLACK);
                pdPageContentStream.setLineWidth(1);
                initY = pageHeight - 3;

                currentPage++;
            }
            initX = 3;
            initY -= cellHeight;
            for (int j = 0; j < colCount; j++) {
                int cellWidth = (int) (((int) historyTableView.getColumns().get(j).getWidth()) * 0.9);
                pdPageContentStream.addRect(initX, initY, cellWidth, -cellHeight);
                pdPageContentStream.beginText();
                pdPageContentStream.newLineAtOffset(initX + 5, initY - cellHeight + 2);
                pdPageContentStream.setFont(PDType1Font.TIMES_ROMAN, 8);
                pdPageContentStream.showText(getCellValue(currentOrderReport, j));
                pdPageContentStream.endText();
                initX += cellWidth;
            }
        }

        pdPageContentStream.stroke();
        pdPageContentStream.close();
        pdDocument.save(filePath);
        pdDocument.close();
    }

    private String getCellValue(History history, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return history.getCounterNumber();
            case 1:
                return String.valueOf(history.getDayTariff());
            case 2:
                return String.valueOf(history.getNightTariff());
            case 3:
                return String.valueOf(history.getMarkup());
            case 4:
                return String.valueOf(history.getTotalBill());
            case 5:
                return String.valueOf(history.getPayDate());
            default:
                return "";
        }
    }

}
