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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HistoryController implements Initializable {

    @FXML
    private TableView<History> historyTableView;

    @FXML
    private TableColumn<History, String> counterNumberTableColumn;

    @FXML
    private TableColumn<History, Double> currentDayConsumptionTableColumn;

    @FXML
    private TableColumn<History, Double> currentNightConsumptionTableColumn;

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

        initializeTableView();
        setupFiltering();
    }

    private void initializeTableView() {
        counterNumberTableColumn.setCellValueFactory(new PropertyValueFactory<>("counterNumber"));
        billTableColumn.setCellValueFactory(new PropertyValueFactory<>("totalBill"));
        currentDayConsumptionTableColumn.setCellValueFactory(new PropertyValueFactory<>("currentDayConsumingValue"));
        currentNightConsumptionTableColumn.setCellValueFactory(new PropertyValueFactory<>("currentNightConsumingValue"));
        payDateTableColumn.setCellValueFactory(new PropertyValueFactory<>("payDate"));

        historyTableView.setItems(observableList);
    }

    private void setupFiltering() {
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
    public void handleRefreshTableView(ActionEvent event) {
        observableList.setAll(historyDAO.getItems());
    }

    @FXML
    public void handleDeleteCounter(ActionEvent event) {
        History selectedHistory = historyTableView.getSelectionModel().getSelectedItem();
        if (selectedHistory != null) {
            observableList.remove(selectedHistory);
            historyDAO.delete(selectedHistory.getHistoryID());
        }
    }

    @FXML
    public void handleClearTableView(ActionEvent event) {
        historyTableView.getItems().clear();
        historyDAO.clear();
    }

    @FXML
    public void handleMakeReport(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF files", "*.pdf"));
        File selectedFile = fileChooser.showSaveDialog(null);
        if (selectedFile != null) {
            String filePath = selectedFile.getAbsolutePath();
            try {
                createPDF(filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void createPDF(String filePath) throws IOException {
        PDDocument pdDocument = new PDDocument();
        PDPage pdPage = new PDPage();
        pdDocument.addPage(pdPage);

        int cellHeight = 15;
        int colCount = 5;
        int rowCount = historyTableView.getItems().size();
        int currentPage = 1;

        try (PDPageContentStream contentStream = new PDPageContentStream(pdDocument, pdPage)) {
            int initX = 3;
            int initY = (int) pdPage.getMediaBox().getHeight() - 3;
            initY -= cellHeight + 5;

            for (int i = 0; i < colCount; i++) {
                int cellWidth = (int) (((int) historyTableView.getColumns().get(i).getWidth()) * 0.9);
                contentStream.addRect(initX, initY, cellWidth, -cellHeight);
                contentStream.beginText();
                contentStream.newLineAtOffset(initX + 5, initY - cellHeight + 2);
                contentStream.setFont(PDType1Font.TIMES_ROMAN, 10);
                contentStream.showText(historyTableView.getColumns().get(i).getText());
                contentStream.endText();
                initX += cellWidth;
            }

            for (int i = 0; i < rowCount; i++) {
                History currentHistory = historyTableView.getItems().get(i);
                if (initY - (cellHeight * 2) < 0) {
                    contentStream.stroke();
                    contentStream.close();

                    pdPage = new PDPage();
                    pdDocument.addPage(pdPage);
                    contentStream.addRect(0, 0, (int) pdPage.getMediaBox().getWidth(), (int) pdPage.getMediaBox().getHeight());
                    contentStream.stroke();
                    initY = (int) pdPage.getMediaBox().getHeight() - 3;

                    currentPage++;
                }
                initX = 3;
                initY -= cellHeight;
                for (int j = 0; j < colCount; j++) {
                    int cellWidth = (int) (((int) historyTableView.getColumns().get(j).getWidth()) * 0.9);
                    contentStream.addRect(initX, initY, cellWidth, -cellHeight);
                    contentStream.beginText();
                    contentStream.newLineAtOffset(initX + 5, initY - cellHeight + 2);
                    contentStream.setFont(PDType1Font.TIMES_ROMAN, 8);
                    contentStream.showText(getCellValue(currentHistory, j));
                    contentStream.endText();
                    initX += cellWidth;
                }
            }
            contentStream.stroke();
        }
        pdDocument.save(filePath);
        pdDocument.close();
    }

    private String getCellValue(History history, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return history.getCounterNumber();
            case 1:
                return String.valueOf(history.getCurrentDayConsumingValue());
            case 2:
                return String.valueOf(history.getCurrentNightConsumingValue());
            case 3:
                return String.valueOf(history.getTotalBill());
            case 4:
                return String.valueOf(history.getPayDate());
            default:
                return "";
        }
    }
}
