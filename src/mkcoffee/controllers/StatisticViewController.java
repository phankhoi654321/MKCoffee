/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mkcoffee.controllers;

import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import mkcoffee.main.MKCoffee;
import mkcoffee.model.InvoicePrint;
import mkcoffee.model.IssuePrint;
import mkcoffee.model.IssueProduct;
import mkcoffee.model.OrderList;
import mkcoffee.model.Product;
import mkcoffee.model.ProductOrder;
import mkcoffee.model.ProductSales;
import mkcoffee.model.ReceiptPrint;
import mkcoffee.model.ReceiptProduct;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author maikhanh
 */
public class StatisticViewController implements Initializable {

    @FXML
    private TableView issueTableView;
    @FXML
    private TableView receiptTableView;
    @FXML
    private TableView<OrderList> invoiceTableView;

    @FXML
    private Label date;
    @FXML
    private Label employeeId;
    @FXML
    private Label employeeName;
    @FXML
    private DatePicker dateInvoice;
    @FXML
    private DatePicker issueDate;
    @FXML
    private DatePicker receiptDate;
    @FXML
    private DatePicker receiptDate1;
    @FXML
    private DatePicker receiptDate2;
    @FXML
    private TextField receiptMonth;
    @FXML
    private Label receiptProductName;
    @FXML
    private Label receiptKind;
    @FXML
    private Label receiptPrice;
    @FXML
    private Label receiptInput;

    @FXML
    private TextField issueMonth;
    @FXML
    private Label issueProductName;
    @FXML
    private Label issueKind;
    @FXML
    private Label issuePrice;
    @FXML
    private Label issueOutput;
    @FXML 
    private Label totalReceipt;
    @FXML 
    private Label totalIssue;
    @FXML 
    private Label totalInvoice;

    @FXML
    private DatePicker issueDate1;
    @FXML
    private DatePicker issueDate2;

    @FXML
    private JFXTextField receiptEmployeeID;
    @FXML
    private JFXTextField issueEmployeeID;

    @FXML
    private HBox receiptIssueView;
    @FXML
    private HBox orderView;
    @FXML
    private Button receiptIssueBtn;
    @FXML
    private Button orderBtn;
    @FXML
    private LineChart<String, Integer> lineChart;
    
    @FXML
    private ContextMenu listContextMenuInvoice;    // make menu when right click

    @FXML
    private ComboBox<String> kindComboBox;
    @FXML
    private ComboBox<String> nameComboBox;
    @FXML
    private ComboBox<String> kindComboBoxIssue;
    @FXML
    private ComboBox<String> nameComboBoxIssue;

    @FXML
    private CategoryAxis x;

    @FXML
    private NumberAxis y;
    
    private int minute;
    private int hour;
    private int second;

    private int day;
    private int month;
    private int year;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        receiptIssueBtn.setStyle("-fx-background-color: white");
        orderBtn.setStyle("-fx-background-color: #bdc3c7");

        y.setLabel("Money");
        x.setLabel("Month");
        XYChart.Series series = new XYChart.Series();
        series.setName("Year 2018");

        List<OrderList> orderLists = Datasource.getInstance().queryOrderListMonth();
        List<Integer> monthSale = new ArrayList<>();
        monthSale.add(1);
        monthSale.add(2);
        monthSale.add(3);
        monthSale.add(4);
        monthSale.add(5);
        monthSale.add(6);
        monthSale.add(7);
        monthSale.add(8);
        monthSale.add(9);
        monthSale.add(10);
        monthSale.add(11);
        monthSale.add(12);

        List<Integer> monthHave = new ArrayList<>();

        Map<Integer, Integer> totalPrice = new HashMap<Integer, Integer>();

        for (int i = 0; i < monthSale.size(); i++) {
            Integer test = monthSale.get(i);
            Integer totalPriceTemp = 0;
            for (int j = 0; j < orderLists.size(); j++) {
                if (test.equals(orderLists.get(j).getMonthOfTransaction())) {
                    totalPriceTemp += orderLists.get(j).getTotalInvoice();
                    monthHave.add(test);
                }
            }
            totalPrice.put(i, totalPriceTemp);
        }
        Integer k = 0;
        for (Integer key : totalPrice.keySet()) {
//            System.out.println(key);
//            System.out.println(totalPrice.get(key));

            if (totalPrice.get(key) > 0) {
                k = key + 1;
                series.getData().add(new XYChart.Data(k.toString(), totalPrice.get(key)));
            }

        }
        lineChart.getData().addAll(series);

        ObservableList<String> kindChoose = FXCollections.observableArrayList("Small", "Medium");
        kindComboBox.setItems(kindChoose);
        kindComboBox.setEditable(true);
        TextFields.bindAutoCompletion(kindComboBox.getEditor(), kindComboBox.getItems());

        ObservableList<String> kindChooseIssue = FXCollections.observableArrayList("Small", "Medium");
        kindComboBoxIssue.setItems(kindChoose);
        kindComboBoxIssue.setEditable(true);
        TextFields.bindAutoCompletion(kindComboBoxIssue.getEditor(), kindComboBoxIssue.getItems());

        ObservableList<String> nameChoose = FXCollections.observableArrayList(Datasource.getInstance().queryReceiptProductNameDB());
        nameComboBox.setItems(nameChoose);
        nameComboBox.setEditable(true);
        TextFields.bindAutoCompletion(nameComboBox.getEditor(), nameComboBox.getItems());

        ObservableList<String> nameChooseIssue = FXCollections.observableArrayList(Datasource.getInstance().queryIssueProductNameDB());
        nameComboBoxIssue.setItems(nameChooseIssue);
        nameComboBoxIssue.setEditable(true);
        TextFields.bindAutoCompletion(nameComboBoxIssue.getEditor(), nameComboBoxIssue.getItems());

        showIssueTableView();

        showReceiptTableView();

        showOrderListTableView();
        
        // Make menu when right click to update
        listContextMenuInvoice = new ContextMenu();
        MenuItem printInvoice = new MenuItem("print");
        listContextMenuInvoice.getItems().add(printInvoice);
        invoiceTableView.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getButton() == MouseButton.SECONDARY) {
                    listContextMenuInvoice.show(invoiceTableView, event.getScreenX(), event.getScreenY());
                }
            }

        });
        printInvoice.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                OrderList orderList = invoiceTableView.getSelectionModel().getSelectedItem();
                searchInvoiceLater(orderList.getOrderId());
            }
        });
        
        DecimalFormat myFormatter = new DecimalFormat("00");
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                Calendar cal = Calendar.getInstance();
                second = cal.get(Calendar.SECOND);

                minute = cal.get(Calendar.MINUTE);
                hour = cal.get(Calendar.HOUR_OF_DAY);

                day = cal.get(Calendar.DATE);

                month = cal.get(Calendar.MONTH) + 1;
                year = cal.get(Calendar.YEAR);

                date.setText(year + "-" + myFormatter.format(month) + "-" + myFormatter.format(day) + " " + myFormatter.format(hour) + ":" + myFormatter.format(minute) + ":" + myFormatter.format(second));
            }
        }),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();

    }
    
    @FXML
    private VBox mainVBox;
    

    private void searchInvoiceLater(String invoiceIdSearch) {
        if (Datasource.getInstance().searchOrderInvoice(invoiceIdSearch) != null) {
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.initOwner(mainVBox.getScene().getWindow());
            ObservableList<ProductSales> productSaleses = FXCollections.observableArrayList(Datasource.getInstance().searchOrderInvoice(invoiceIdSearch));
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/mkcoffee/views/OrderInvoiceDialog.fxml"));
            try {
                dialog.getDialogPane().setContent(fxmlLoader.load());
                OrderInvoiceDialogController dialogController = fxmlLoader.getController();
                dialogController.myOrderInvoice(productSaleses);
            } catch (IOException e) {
//                System.out.println("Couldn't load the dialog");

                e.printStackTrace();
                return;
            }
            ButtonType Print = new ButtonType("Print", ButtonBar.ButtonData.OK_DONE);
            ButtonType Cancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
            dialog.getDialogPane().getButtonTypes().setAll(Print, Cancel);

            Optional<ButtonType> result = dialog.showAndWait();
            if (result.isPresent() && result.get() == Print) {
                printInvoiceLater(productSaleses);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Update Product");
            alert.setHeaderText("Product Update form");
            alert.setContentText("Please fill in product ID!");
            Optional<ButtonType> choose = alert.showAndWait();
        }
    }
    
    private void printInvoiceLater(ObservableList<ProductSales> productSaleses) {
        String srcFile = "/Users/maikhanh/NetBeansProjects/MKCoffee/src/mkcoffee/invoice/Invoice.jrxml";
        Integer total = 0;

        for (int i = 0; i < productSaleses.size(); i++) {
            total += productSaleses.get(i).getTotalPrice();
        }
        try {
            JasperReport jr = JasperCompileManager.compileReport(srcFile);
            HashMap<String, Object> para = new HashMap<>();
            para.put("employee", productSaleses.get(0).getEmployeeId());
            para.put("date", productSaleses.get(0).getDateOfTransaction());
            para.put("totalInvoice", priceWithDecimal(total));
            para.put("invoiceId", productSaleses.get(0).getOrderId());

            List<ProductOrder> productSalesesList = new ArrayList<>();

            for (int i = 0; i < productSaleses.size(); i++) {
                ProductOrder productOrder = new ProductOrder();
                productOrder.setProductName(productSaleses.get(i).getProductName());
                productOrder.setKind(productSaleses.get(i).getKind());
                productOrder.setUnit(productSaleses.get(i).getUnit());
                productOrder.setQuantity(productSaleses.get(i).getQuantity().toString());
                productOrder.setPrice(productSaleses.get(i).getPrice().toString());
                productOrder.setAmount(priceWithDecimal(productSaleses.get(i).getTotalPrice()));

                productSalesesList.add(productOrder);
            }

            JRBeanCollectionDataSource jcds = new JRBeanCollectionDataSource(productSalesesList);
            JasperPrint jp = JasperFillManager.fillReport(jr, para, jcds);
            JasperViewer jv = new JasperViewer(jp, false);
            jv.setVisible(true);

        } catch (JRException ex) {
            Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void showReceiptTableView() {
        ObservableList<ReceiptProduct> receiptProducts = FXCollections.observableArrayList(Datasource.getInstance().queryReceiptProductDB());
        receiptTableView.setItems(receiptProducts);
        totalReceiptValue();
    }

    public void totalReceiptValue() {
        ObservableList<ReceiptProduct> receiptProducts = receiptTableView.getItems();
        Integer total = 0;
        for (int i = 0; i < receiptProducts.size(); i++) {
            total += (receiptProducts.get(i).getReceiptProductPrice() * receiptProducts.get(i).getReceiptProductStock());
        }
        totalReceipt.setText(priceWithDecimal(total));
    }
    
    public void totalIssueValue() {
        ObservableList<IssueProduct> issueProducts = issueTableView.getItems();
        Integer total = 0;
        for (int i = 0; i < issueProducts.size(); i++) {
            total += (issueProducts.get(i).getPrice()* issueProducts.get(i).getQuantity());
        }
        totalIssue.setText(priceWithDecimal(total));
    }
    
    public void totalInvoiceValue() {
        ObservableList<OrderList> orderLists = invoiceTableView.getItems();
        Integer total = 0;
        for (int i = 0; i < orderLists.size(); i++) {
            total += orderLists.get(i).getTotalInvoice();
        }
        totalInvoice.setText(priceWithDecimal(total));
    }

    public void showIssueTableView() {
        ObservableList<IssueProduct> issueProducts = FXCollections.observableArrayList(Datasource.getInstance().queryIssueProductDB());
        issueTableView.setItems(issueProducts);
        totalIssueValue();
        
    }

    public void showOrderListTableView() {
        ObservableList<OrderList> orderLists = FXCollections.observableArrayList(Datasource.getInstance().queryOrderListECName());
        invoiceTableView.setItems(orderLists);
        totalInvoiceValue();
    }

    MKCoffee statisticView;
    Stage stage;

    public void main(MKCoffee statisticView) {
//        this.stage = stage;
        this.statisticView = statisticView;
    }

    public void myEmployeeId(String name, String userId, String datetime, String month) {
        employeeId.setText(userId);
        employeeName.setText(name);
//        date.setText(datetime);

    }

    @FXML
    private void searchIssueByBetweenDate() {
        if (issueDate1.getValue() == null || issueDate2.getValue() == null) {
            showIssueTableView();
        } else {
            ObservableList<IssueProduct> issueProducts = FXCollections.observableArrayList(Datasource.getInstance().searchIssueProductByBetweenDate(issueDate1.getValue().toString(), issueDate2.getValue().toString()));
            issueTableView.setItems(issueProducts);
        }
        totalIssueValue();
        issueEmployeeID.clear();
        nameComboBoxIssue.setValue(null);
        kindComboBoxIssue.setValue(null);
    }

    @FXML
    private void searchIssueByNameKind() {
        if (nameComboBoxIssue.getValue().isEmpty() || kindComboBoxIssue.getValue().isEmpty()) {
            showIssueTableView();
        } else {
            ObservableList<IssueProduct> issueProducts = FXCollections.observableArrayList(Datasource.getInstance().queryIssueByProductNameKind(nameComboBoxIssue.getValue(), kindComboBoxIssue.getValue()));
            issueTableView.setItems(issueProducts);
        }
        totalIssueValue();
        issueEmployeeID.clear();
        issueDate1.setValue(null);
        issueDate2.setValue(null);
    }

    @FXML
    private void searchReceiptByBetweenDate() {
        if (receiptDate1.getValue() == null || receiptDate2.getValue() == null) {
            showReceiptTableView();
        } else {
            ObservableList<ReceiptProduct> receiptProducts = FXCollections.observableArrayList(Datasource.getInstance().searchReceiptProductByBetweenDate(receiptDate1.getValue().toString(), receiptDate2.getValue().toString()));
            receiptTableView.setItems(receiptProducts);
        }
        
        totalReceiptValue();
        receiptEmployeeID.clear();
        nameComboBox.setValue(null);
        kindComboBox.setValue(null);
    }

    @FXML
    private void searchIssueByEmployeeID() {
        if (issueEmployeeID.getText().isEmpty()) {
            showIssueTableView();
        } else {
            ObservableList<IssueProduct> issueProducts = FXCollections.observableArrayList(Datasource.getInstance().searchIssueProductByEmployeeId(issueEmployeeID.getText()));
            issueTableView.setItems(issueProducts);
        }
        totalIssueValue();
        issueDate1.setValue(null);
        issueDate2.setValue(null);
        nameComboBoxIssue.setValue(null);
        kindComboBoxIssue.setValue(null);
    }

    @FXML
    private void searchReceiptByEmployeeID() {
        if (receiptEmployeeID.getText().isEmpty()) {
            showReceiptTableView();
        } else {
            ObservableList<ReceiptProduct> receiptProducts = FXCollections.observableArrayList(Datasource.getInstance().searchReceiptProductByEmployeeId(receiptEmployeeID.getText()));
            receiptTableView.setItems(receiptProducts);
        }
        
        totalReceiptValue();
        nameComboBox.setValue(null);
        kindComboBox.setValue(null);
        receiptDate1.setValue(null);
        receiptDate2.setValue(null);
    }

    @FXML
    private void goodsReceiptMonth() {

        List<Product> products = Datasource.getInstance().queryProducts();

        List<Integer> total = new ArrayList<>();
        for (int i = 0; i < products.size(); i++) {
            int totalinput = 0;
            List<ReceiptProduct> rp = Datasource.getInstance().queryReceiptByProductName(products.get(i).getProductName(), products.get(i).getProductKind(), Integer.parseInt(receiptMonth.getText()));
            for (int j = 0; j < rp.size(); j++) {
                totalinput += rp.get(j).getReceiptProductStock();
            }
            total.add(totalinput);
        }

        Integer totalMax = 0;
        String bestProductName = "";
        String bestProductKind = "";
        Integer price = 0;
        Integer input = 0;
        for (int i = 0; i < total.size(); i++) {
            if (totalMax < total.get(i)) {
                totalMax = total.get(i);
                bestProductName = products.get(i).getProductName();
                bestProductKind = products.get(i).getProductKind();
                price = products.get(i).getProductPrice();
                input = totalMax;
            }
        }

        receiptProductName.setText(bestProductName);
        receiptKind.setText(bestProductKind);
        receiptPrice.setText(priceWithDecimal(price));
        receiptInput.setText(input.toString());
    }

    @FXML
    private void goodsIssueMonth() {

        List<Product> products = Datasource.getInstance().queryProducts();

        List<Integer> total = new ArrayList<>();
        for (int i = 0; i < products.size(); i++) {
            int totalinput = 0;
            List<IssueProduct> rp = Datasource.getInstance().queryIssueByProductName(products.get(i).getProductName(), products.get(i).getProductKind(), Integer.parseInt(issueMonth.getText()));
            for (int j = 0; j < rp.size(); j++) {
                totalinput += rp.get(j).getQuantity();
            }
            total.add(totalinput);
        }

        Integer totalMax = 0;
        String bestProductName = "";
        String bestProductKind = "";
        Integer price = 0;
        Integer output = 0;
        for (int i = 0; i < total.size(); i++) {
            if (totalMax < total.get(i)) {
                totalMax = total.get(i);
                bestProductName = products.get(i).getProductName();
                bestProductKind = products.get(i).getProductKind();
                price = products.get(i).getProductPrice();
            }
        }

        issueProductName.setText(bestProductName);
        issueKind.setText(bestProductKind);
        issuePrice.setText(priceWithDecimal(price));
        issueOutput.setText(totalMax.toString());
    }

    @FXML
    private void searchReceiptByNameKind() {
        if (nameComboBox.getValue().isEmpty() || kindComboBox.getValue().isEmpty()) {
            showReceiptTableView();
        } else {
            ObservableList<ReceiptProduct> receiptProducts = FXCollections.observableArrayList(Datasource.getInstance().queryReceiptByProductName(nameComboBox.getValue(), kindComboBox.getValue()));
            receiptTableView.setItems(receiptProducts);
        }
        
        totalReceiptValue();
        receiptEmployeeID.clear();
        receiptDate1.setValue(null);
        receiptDate2.setValue(null);

    }

    @FXML
    private void openReceiptIssue() throws IOException {
        makeFadeIn(receiptIssueView);
        orderView.managedProperty().bind(orderView.visibleProperty());
        orderView.setVisible(false);
        orderBtn.setStyle("-fx-background-color: #bdc3c7");
        receiptIssueBtn.setStyle("-fx-background-color: white");
        receiptIssueView.managedProperty().bind(receiptIssueView.visibleProperty());
        receiptIssueView.setVisible(true);

    }

    @FXML
    private Label totalSale;

    @FXML
    private void totalInvoiceByDate() {
        Integer total = 0;
        if (!(dateInvoice.getValue().toString().isEmpty())) {
            ObservableList<OrderList> orderLists = FXCollections.observableArrayList(Datasource.getInstance().searchOrderListByDate(dateInvoice.getValue().toString()));
            for (int i = 0; i < orderLists.size(); i++) {
                total += orderLists.get(i).getTotalInvoice();
            }
//            totalSale.setText(total.toString());
            totalSale.setText(priceWithDecimal(total));
//            totalSale.setText(String.format("%,.2f", total));
        } else {
            alertWarning("Warning", "Check Input", "Please fill in the date");

//            totalSale.setText(total.toString());
            totalSale.setText(String.format("%,.2f", total.toString()));

        }

    }

    

    @FXML
    private JFXTextField InvoiceEmployeeID;

    @FXML
    private void searchInvoiceByEmployeeID() {
        if (InvoiceEmployeeID.getText().isEmpty()) {
            showOrderListTableView();
        } else {
            ObservableList<OrderList> orderLists = FXCollections.observableArrayList(Datasource.getInstance().searchOrderListByEmployeeID(InvoiceEmployeeID.getText()));
            invoiceTableView.setItems(orderLists);
        }
        totalInvoiceValue();

    }

    @FXML
    private DatePicker orderDate1;
    @FXML
    private DatePicker orderDate2;

    @FXML
    private void searchInvoiceByBetweenDate() {
        if (orderDate1.getValue() == null || orderDate2.getValue() == null) {
            showOrderListTableView();
        } else {
            ObservableList<OrderList> orderLists = FXCollections.observableArrayList(Datasource.getInstance().searchOrderListByBetweenDate(orderDate1.getValue().toString(), orderDate2.getValue().toString()));
            invoiceTableView.setItems(orderLists);
        }
        totalInvoiceValue();
    }

    @FXML
    private void openOrder() throws IOException, ParseException {
        makeFadeIn(orderView);
        receiptIssueView.managedProperty().bind(receiptIssueView.visibleProperty());
        receiptIssueView.setVisible(false);
        receiptIssueBtn.setStyle("-fx-background-color: #bdc3c7");
        orderBtn.setStyle("-fx-background-color: white");
        orderView.managedProperty().bind(orderView.visibleProperty());
        orderView.setVisible(true);
        LocalDate localDate = LocalDate.now();
        dateInvoice.setValue(localDate);

    }

    private void makeFadeIn(HBox view) {
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(500));
        fadeTransition.setNode(view);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();
    }

    private void alertWarning(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        Optional<ButtonType> result = alert.showAndWait();
    }
    
    private static String priceWithDecimal(Integer price) {
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        return formatter.format(price);
    }
    
    
    @FXML
    private void printReceipt() {
        String srcFile = "/Users/maikhanh/NetBeansProjects/MKCoffee/src/mkcoffee/invoice/ReceiptReport.jrxml";
        try {
            JasperReport jr = JasperCompileManager.compileReport(srcFile);
            HashMap<String, Object> para = new HashMap<>();
            para.put("totalPrice", totalReceipt.getText());
            para.put("date", date.getText());
        
            ObservableList<ReceiptProduct> receiptProducts = receiptTableView.getItems();
            List<ReceiptPrint> receiptProductsList = new ArrayList<>();
            
            for (int i = 0; i < receiptProducts.size(); i++) {
                ReceiptPrint receiptPrint = new ReceiptPrint();
                receiptPrint.setReceiptProductID(receiptProducts.get(i).getReceiptEmployeeId());
                receiptPrint.setProductId(receiptProducts.get(i).getProductId());
                receiptPrint.setReceiptEmployeeId(receiptProducts.get(i).getReceiptEmployeeId());
                receiptPrint.setReceiptProductName(receiptProducts.get(i).getReceiptProductName());
                receiptPrint.setReceiptProductKind(receiptProducts.get(i).getReceiptProductKind());
                receiptPrint.setReceiptProductUnit(receiptProducts.get(i).getReceiptProductUnit());
                receiptPrint.setReceiptProductPrice(receiptProducts.get(i).getReceiptProductPrice().toString());
                receiptPrint.setReceiptProductStock(receiptProducts.get(i).getReceiptProductStock().toString());
                receiptPrint.setReceiptProductdateOfTransaction(receiptProducts.get(i).getReceiptProductdateOfTransaction());

                receiptProductsList.add(receiptPrint);

            }
            JRBeanCollectionDataSource jcds = new JRBeanCollectionDataSource(receiptProductsList);
            JasperPrint jp = JasperFillManager.fillReport(jr, para, jcds);
            JasperViewer jv = new JasperViewer(jp, false);  // set false to cannot close the program
            jv.setVisible(true);
        } catch (JRException ex) {
            Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void printInvoice() {
        String srcFile = "/Users/maikhanh/NetBeansProjects/MKCoffee/src/mkcoffee/invoice/InvoiceReport.jrxml";
        try {
            JasperReport jr = JasperCompileManager.compileReport(srcFile);
            HashMap<String, Object> para = new HashMap<>();
            para.put("totalPrice", totalReceipt.getText());
            para.put("date", date.getText());
        
            ObservableList<OrderList> orderLists = invoiceTableView.getItems();
            List<InvoicePrint> invoicePrintsList = new ArrayList<>();
            
            for (int i = 0; i < orderLists.size(); i++) {
                InvoicePrint invoicePrint = new InvoicePrint();
                invoicePrint.setInvoiceId(orderLists.get(i).getOrderId());
                invoicePrint.setEmployeeId(orderLists.get(i).getEmployeeId());
                invoicePrint.setEmployeeName(orderLists.get(i).getEmployeeName());
                invoicePrint.setCustomerId(orderLists.get(i).getCustomerId());
                invoicePrint.setCustomerName(orderLists.get(i).getCustomerName());
                invoicePrint.setDate(orderLists.get(i).getDateOrder());
                invoicePrint.setTime(orderLists.get(i).getTimeOrder());
                invoicePrint.setTotalInvoice(orderLists.get(i).getTotalInvoice().toString());
                

                invoicePrintsList.add(invoicePrint);

            }
            JRBeanCollectionDataSource jcds = new JRBeanCollectionDataSource(invoicePrintsList);
            JasperPrint jp = JasperFillManager.fillReport(jr, para, jcds);
            JasperViewer jv = new JasperViewer(jp, false);  // set false to cannot close the program
            jv.setVisible(true);
        } catch (JRException ex) {
            Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void printIssue() {
        String srcFile = "/Users/maikhanh/NetBeansProjects/MKCoffee/src/mkcoffee/invoice/IssueReport.jrxml";
        try {
            JasperReport jr = JasperCompileManager.compileReport(srcFile);
            HashMap<String, Object> para = new HashMap<>();
            para.put("totalPrice", totalIssue.getText());
            para.put("date", date.getText());
            
            ObservableList<IssueProduct> issueProducts = issueTableView.getItems();
            List<IssuePrint> issuePrintList = new ArrayList<>();

            for (int i = 0; i < issueProducts.size(); i++) {
                IssuePrint issuePrint = new IssuePrint();
                issuePrint.setIssueId(issueProducts.get(i).getTransactionId());
                issuePrint.setEmployeeId(issueProducts.get(i).getEmployeeId());
                issuePrint.setProductName(issueProducts.get(i).getProductName());
                issuePrint.setKind(issueProducts.get(i).getKind());
                issuePrint.setUnit(issueProducts.get(i).getUnit());
                issuePrint.setPrice(issueProducts.get(i).getPrice().toString());
                issuePrint.setOutputStock(issueProducts.get(i).getQuantity().toString());
                issuePrint.setDate(issueProducts.get(i).getDateOfTransaction());
                
                issuePrintList.add(issuePrint);

            }

            JRBeanCollectionDataSource jcds = new JRBeanCollectionDataSource(issuePrintList);
            JasperPrint jp = JasperFillManager.fillReport(jr, para, jcds);
            JasperViewer jv = new JasperViewer(jp, false);
            jv.setVisible(true);

        } catch (JRException ex) {
            Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String setDateParsing(String date) throws ParseException {

    // This is the format date we want
    DateFormat mSDF = new SimpleDateFormat("yyyy-mm-dd");

    // This format date is actually present
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
    return mSDF.format(formatter.parse(date));
}

}
