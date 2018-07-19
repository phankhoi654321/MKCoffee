package mkcoffee.controllers;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import mkcoffee.main.MKCoffee;
import mkcoffee.model.ProductSales;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;
import javafx.util.Duration;
import javafx.util.StringConverter;
import mkcoffee.model.Customer;
import mkcoffee.model.CustomerBuy;
import mkcoffee.model.EmployeeSale;
import mkcoffee.model.Product;
import mkcoffee.model.User;
import mkcoffee.model.WorkingUser;
import mkcoffee.model.ProductOrder;
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
public class MainViewController extends Thread {

    private MKCoffee mainView;
    //Menu
    @FXML
    private Button homeMenu;
    @FXML
    private Button employeeMenu;
    @FXML
    private Button customerMenu;
    @FXML
    private Button storeMenu;
    @FXML
    private Button statisticMenu;
    @FXML
    private Button quitMenu;
    @FXML
    private Button productSalesMenu;
    // All View
    @FXML
    private BorderPane mainBorderPane;
    @FXML
    private VBox viewHome;
    @FXML
    private VBox viewStore;
    @FXML
    private VBox viewCustomer;
    @FXML
    private VBox viewEmployee;
    @FXML
    private VBox viewProductSales;
    @FXML
    private HBox viewOrder;
    @FXML
    private HBox viewEpresso;
    @FXML
    private HBox viewTea;
    @FXML
    private HBox viewSmoothie;
    @FXML
    private HBox viewBlended;
    //Price
    @FXML
    private Label epressoSmallPrice;
    @FXML
    private Label epressoMediumPrice;
    @FXML
    private Label caramelSmallPrice;
    @FXML
    private Label caramelMediumPrice;
    @FXML
    private Label whiteCoffeeSmallPrice;
    @FXML
    private Label whiteCoffeeMediumPrice;
    @FXML
    private Label cappuccinoSmallPrice;
    @FXML
    private Label cappuccinoMediumPrice;
    @FXML
    private Label blackMilkSmallPrice;
    @FXML
    private Label blackMilkMediumPrice;
    @FXML
    private Label epressoMilkSmallPrice;
    @FXML
    private Label epressoMilkMediumPrice;
    @FXML
    private Label blackTeaSmallPrice;
    @FXML
    private Label blackTeaMediumPrice;
    @FXML
    private Label flavouredTeaSmallPrice;
    @FXML
    private Label flavouredTeaMediumPrice;
    @FXML
    private Label raspberryMacchiatoSmallPrice;
    @FXML
    private Label raspberryMacchiatoMediumPrice;
    @FXML
    private Label matchaLatteSmallPrice;
    @FXML
    private Label matchaLatteMediumPrice;
    @FXML
    private Label macchiatoTeaSmallPrice;
    @FXML
    private Label macchiatoTeaMediumPrice;
    @FXML
    private Label peachTeaManiaSmallPrice;
    @FXML
    private Label peachTeaManiaMediumPrice;
    @FXML
    private Label raspberrySodaSmallPrice;
    @FXML
    private Label raspberrySodaMediumPrice;
    @FXML
    private Label berrySmoothieSmallPrice;
    @FXML
    private Label berrySmoothieMediumPrice;
    @FXML
    private Label mangoSmoothieSmallPrice;
    @FXML
    private Label mangoSmoothieMediumPrice;
    @FXML
    private Label greenAppleSmallPrice;
    @FXML
    private Label greenAppleMediumPrice;
    @FXML
    private Label berrySodaSmallPrice;
    @FXML
    private Label berrySodaMediumPrice;
    @FXML
    private Label mojitoLemonSmallPrice;
    @FXML
    private Label mojitoLemonMediumPrice;
    @FXML
    private Label mochaIceSmallPrice;
    @FXML
    private Label mochaIceMediumPrice;
    @FXML
    private Label caramelIceSmallPrice;
    @FXML
    private Label caramelIceMediumPrice;
    @FXML
    private Label cookieIceSmallPrice;
    @FXML
    private Label cookieIceMediumPrice;
    @FXML
    private Label iceChocolateSmallPrice;
    @FXML
    private Label iceChocolateMediumPrice;
    @FXML
    private Label doubleChocolateSmallPrice;
    @FXML
    private Label doubleChocolateMediumPrice;
    @FXML
    private Label vanillaIceSmallPrice;
    @FXML
    private Label vanillaIceMediumPrice;
    //Quantity
    @FXML
    private TextField epressoSmallQuantity;
    @FXML
    private TextField epressoMediumQuantity;
    @FXML
    private TextField caramelSmallQuantity;
    @FXML
    private TextField caramelMediumQuantity;
    @FXML
    private TextField whiteCoffeeSmallQuantity;
    @FXML
    private TextField whiteCoffeeMediumQuantity;
    @FXML
    private TextField cappuccinoSmallQuantity;
    @FXML
    private TextField cappuccinoMediumQuantity;
    @FXML
    private TextField blackMilkSmallQuantity;
    @FXML
    private TextField blackMilkMediumQuantity;
    @FXML
    private TextField epressoMilkSmallQuantity;
    @FXML
    private TextField epressoMilkMediumQuantity;
    @FXML
    private TextField matchaLatteSmallQuantity;
    @FXML
    private TextField matchaLatteMediumQuantity;
    @FXML
    private TextField flavouredTeaSmallQuantity;
    @FXML
    private TextField flavouredTeaMediumQuantity;
    @FXML
    private TextField raspberryMacchiatoSmallQuantity;
    @FXML
    private TextField raspberryMacchiatoMediumQuantity;
    @FXML
    private TextField blackTeaSmallQuantity;
    @FXML
    private TextField blackTeaMediumQuantity;
    @FXML
    private TextField macchiatoTeaSmallQuantity;
    @FXML
    private TextField macchiatoTeaMediumQuantity;
    @FXML
    private TextField peachTeaManiaSmallQuantity;
    @FXML
    private TextField peachTeaManiaMediumQuantity;
    @FXML
    private TextField raspberrySodaSmallQuantity;
    @FXML
    private TextField raspberrySodaMediumQuantity;
    @FXML
    private TextField berrySmoothieSmallQuantity;
    @FXML
    private TextField berrySmoothieMediumQuantity;
    @FXML
    private TextField mangoSmoothieSmallQuantity;
    @FXML
    private TextField mangoSmoothieMediumQuantity;
    @FXML
    private TextField greenAppleSmallQuantity;
    @FXML
    private TextField greenAppleMediumQuantity;
    @FXML
    private TextField berrySodaSmallQuantity;
    @FXML
    private TextField berrySodaMediumQuantity;
    @FXML
    private TextField mojitoLemonSmallQuantity;
    @FXML
    private TextField mojitoLemonMediumQuantity;
    @FXML
    private TextField mochaIceSmallQuantity;
    @FXML
    private TextField mochaIceMediumQuantity;
    @FXML
    private TextField caramelIceSmallQuantity;
    @FXML
    private TextField caramelIceMediumQuantity;
    @FXML
    private TextField cookieIceSmallQuantity;
    @FXML
    private TextField cookieIceMediumQuantity;
    @FXML
    private TextField iceChocolateSmallQuantity;
    @FXML
    private TextField iceChocolateMediumQuantity;
    @FXML
    private TextField doubleChocolateSmallQuantity;
    @FXML
    private TextField doubleChocolateMediumQuantity;
    @FXML
    private TextField vanillaIceSmallQuantity;
    @FXML
    private TextField vanillaIceMediumQuantity;
    //Text name
    @FXML
    private Text epresso;
    @FXML
    private Text caramel;
    @FXML
    private Text whiteCoffee;
    @FXML
    private Text cappuccino;
    @FXML
    private Text blackMilk;
    @FXML
    private Text epressoMilk;
    @FXML
    private Text blackTea;
    @FXML
    private Text flavouredTea;
    @FXML
    private Text raspberryMacchiato;
    @FXML
    private Text matchaLatte;
    @FXML
    private Text macchiatoTea;
    @FXML
    private Text peachTeaMania;
    @FXML
    private Text raspberrySoda;
    @FXML
    private Text berrySmoothie;
    @FXML
    private Text mangoSmoothie;
    @FXML
    private Text greenApple;
    @FXML
    private Text berrySoda;
    @FXML
    private Text mojitoLemon;
    @FXML
    private Text mochaIce;
    @FXML
    private Text caramelIce;
    @FXML
    private Text cookieIce;
    @FXML
    private Text iceChocolate;
    @FXML
    private Text doubleChocolate;
    @FXML
    private Text vanillaIce;
    //Kind
    @FXML
    private Button epressoSmall;
    @FXML
    private Button epressoMedium;
    @FXML
    private Button caramelSmall;
    @FXML
    private Button caramelMedium;
    @FXML
    private Button whiteCoffeeSmall;
    @FXML
    private Button whiteCoffeeMedium;
    @FXML
    private Button cappuccinoSmall;
    @FXML
    private Button cappuccinoMedium;
    @FXML
    private Button blackMilkSmall;
    @FXML
    private Button blackMilkMedium;
    @FXML
    private Button epressoMilkSmall;
    @FXML
    private Button epressoMilkMedium;
    @FXML
    private Button blackTeaSmall;
    @FXML
    private Button blackTeaMedium;
    @FXML
    private Button flavouredTeaSmall;
    @FXML
    private Button flavouredTeaMedium;
    @FXML
    private Button raspberryMacchiatoSmall;
    @FXML
    private Button raspberryMacchiatoMedium;
    @FXML
    private Button matchaLatteSmall;
    @FXML
    private Button matchaLatteMedium;
    @FXML
    private Button macchiatoTeaSmall;
    @FXML
    private Button macchiatoTeaMedium;
    @FXML
    private Button peachTeaManiaSmall;
    @FXML
    private Button peachTeaManiaMedium;
    @FXML
    private Button raspberrySodaSmall;
    @FXML
    private Button raspberrySodaMedium;
    @FXML
    private Button berrySmoothieSmall;
    @FXML
    private Button berrySmoothieMedium;
    @FXML
    private Button mangoSmoothieSmall;
    @FXML
    private Button mangoSmoothieMedium;
    @FXML
    private Button greenAppleSmall;
    @FXML
    private Button greenAppleMedium;
    @FXML
    private Button berrySodaSmall;
    @FXML
    private Button berrySodaMedium;
    @FXML
    private Button mojitoLemonSmall;
    @FXML
    private Button mojitoLemonMedium;
    @FXML
    private Button mochaIceSmall;
    @FXML
    private Button mochaIceMedium;
    @FXML
    private Button caramelIceSmall;
    @FXML
    private Button caramelIceMedium;
    @FXML
    private Button cookieIceSmall;
    @FXML
    private Button cookieIceMedium;
    @FXML
    private Button iceChocolateSmall;
    @FXML
    private Button iceChocolateMedium;
    @FXML
    private Button doubleChocolateSmall;
    @FXML
    private Button doubleChocolateMedium;
    @FXML
    private Button vanillaIceSmall;
    @FXML
    private Button vanillaIceMedium;

    // ProductSalese
    @FXML
    private TextField customerMonth;
    @FXML
    private TextField employeeMonth;
    @FXML
    private Label customerTotalPayMonth;
    @FXML
    private Label employeeTotalSaleMonth;
    @FXML
    private Label totalSaleMonth;
    @FXML
    private Label customerIdMonth;
    @FXML
    private Label employeeIdMonth;
    @FXML
    private Label customerNameMonth;
    @FXML
    private Label employeeNameMonth;

    //Get name and userId from login
    @FXML
    private Label employeeId;
    @FXML
    private Label employeeName;
    @FXML
    private Label employeeNameStore;
    @FXML
    private Label employeeNameCustomer;
    @FXML
    private Label employeeNameEmployee;
    @FXML
    private Label employeeIdStore;
    @FXML
    private Label employeeIdCustomer;
    @FXML
    private Label employeeIdEmployee;
    @FXML
    private Label employeeNameProductSales;
    @FXML
    private Label employeeIdProductSales;

    private String nameEmployee;
    private String idEmployee;

    public void myEmployeeId(String name, String userId) {
        employeeId.setText(userId);
        employeeName.setText(name);
        employeeNameStore.setText(name);
        employeeIdStore.setText(userId);
        employeeIdCustomer.setText(userId);
        employeeNameCustomer.setText(name);
        employeeNameEmployee.setText(name);
        employeeIdEmployee.setText(userId);
        employeeNameProductSales.setText(name);
        employeeIdProductSales.setText(userId);

        // Check user to using program and promote user to Admin
        List<User> userPromote = Datasource.getInstance().queryUsersPromote();
        if (userPromote.isEmpty()) {
            alertWarning("Waring Admin Is Not Set", "Set Admin", "Please set Admin or Promote employee in Employee Tab");
        } else {
            for (int i = 0; i < userPromote.size(); i++) {
                if (employeeId.getText().equalsIgnoreCase(userPromote.get(i).getUserId())) {
                    employeeMenu.setDisable(false);
                    customerMenu.setDisable(false);
                    storeMenu.setDisable(false);
                    statisticMenu.setDisable(false);
                    break;
                } else {
                    employeeMenu.setDisable(true);
                    customerMenu.setDisable(true);
                    storeMenu.setDisable(true);
                    statisticMenu.setDisable(true);
                }
            }
        }

    }

    @FXML
    private ImageView imgHomeBtn;
    @FXML
    private ImageView imgSellBtn;

    // get image for main icon
    Image homeGray = new Image("/mkcoffee/image/home.png");
    Image homeGreen = new Image("/mkcoffee/image/homeGreen.png");
    Image sellGreen = new Image("/mkcoffee/image/sell2Green.png");

    // Set image for button Home
    @FXML
    private void changeHome() {
        imgHomeBtn.setImage(homeGreen);
    }

    // Set Home Active
    private boolean homeActiveBoolean = true;

    @FXML
    private void homeActive() throws IOException {
        if (!homeActiveBoolean) {
            changeHome();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/mkcoffee/views/MainView.fxml"));
            Parent root = (Parent) loader.load();
            mainBorderPane.getChildren().removeAll();
            mainBorderPane.getChildren().setAll(root);
            MainViewController secController = loader.getController();
            secController.myEmployeeId(nameEmployee, idEmployee);
            homeMenu.setDisable(false);
            productSalesMenu.setDisable(false);
            employeeMenu.setDisable(false);
            customerMenu.setDisable(false);
            storeMenu.setDisable(false);
//            homeActiveBoolean = true;
        }

    }

    @FXML
    private void sellActive() {
        imgSellBtn.setImage(sellGreen);

    }

    @FXML
    private JFXTextField customerPhoneProductSale;

    //product input form
    @FXML
    private JFXTreeTableView<Product> storeTable;
    @FXML
    private TextField productName;
    @FXML
    private ComboBox<String> productKind;
    @FXML
    private TextField productPrice;
    @FXML
    private ComboBox<String> productCategory;
    @FXML
    private TextField productStock;
    @FXML
    private ComboBox<String> productNameDelete;
    @FXML
    private ComboBox<String> productKindDelete;
    @FXML
    private TextField productIdUpdate;
    @FXML
    private TextField findCustomerById;
    @FXML
    private TextField findEmployeeSale;
    @FXML
    private JFXTextField invoiceIdSearch;
    @FXML
    private JFXTextField productNameSearch;
    @FXML
    private Label totalPay;
    @FXML
    private TableView<ProductSales> orderTable;
    @FXML
    private TableView<Customer> customerTable;
    @FXML
    private TableView<User> employeeTable;
    @FXML
    private TableView<WorkingUser> workingUserTable;
    @FXML
    private TableView<ProductSales> productSalesTable;

    @FXML
    private ContextMenu listContextMenu;    // make menu when right click
    @FXML
    private ContextMenu listContextMenuStore;    // make menu when right click
    @FXML
    private ContextMenu listContextPromoteUser;    // make promote user when right click
    @FXML
    private ContextMenu listContextPrintInvoice;    // make promote user when right click

    //customer input form
    @FXML
    private TextField customerName;
    @FXML
    private TextField customerAdd;
    @FXML
    private TextField customerPhone;
    @FXML
    private TextField customerEmail;
    @FXML
    private TextField customerNameDelete;
    @FXML
    private TextField customerPhoneDelete;
    @FXML
    private TextField customerIdUpdate;

    //Employee input Form
    @FXML
    private TextField employeeFullName;
    @FXML
    private TextField employeeEmail;
    @FXML
    private TextField employeePhone;
    @FXML
    private DatePicker employeeBirthday;

    @FXML
    private TextField employeeUsername;
    @FXML
    private TextField employeePassword;

    @FXML
    private TextField monthWorking;
    @FXML
    private JFXTextField employeeNameSearch;
    @FXML
    private JFXTextField employeeWorkingSearch;
    @FXML
    private JFXTextField customerNameSearch;

    @FXML
    private ComboBox<String> employeeGender;

    //Employee ID delete
    @FXML
    private TextField employeeIdDelete;
    @FXML
    private TextField employeeIdUpdate;

    private String dateStart;

    private int minute;
    private int hour;
    private int second;

    private int day;
    private int month;
    private int year;

    private int minuteLabor;
    private int hourLabor;
    private int secondLabor;

    private int dayLabor;
    private int monthLabor;
    private int yearLabor;

    @FXML
    private Label time;
    @FXML
    private Label timeStore;
    @FXML
    private Label timeCustomer;
    @FXML
    private Label timeEmployee;
    @FXML
    private Label timeProductSales;

    private Integer monthbestCustomer;

    // Set date time for invoice
    private String dateInvoice;
    private String timeInvoice;

    public void initialize() throws IOException {
        //Set product name to combobox product to search product from combobox
        setComboboxProductName();

        //format date picker
        employeeBirthday.setConverter(new StringConverter<LocalDate>() {
            String pattern = "yyyy-MM-dd";
            //https://o7planning.org/en/11085/javafx-datepicker-tutorial
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);

            //Instance block
            {
                employeeBirthday.setPromptText(pattern.toLowerCase());
            }

            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        }
        );

        // set Img for home button is active
        imgHomeBtn.setImage(homeGreen);
//        homeActiveBoolean = true;

        showCustomerTableView();

        showEmployeeTableView();

//        showWorkingEmployeeTableView();   // show working employee table without month
        showWorkingEmployeeTableViewMonth();

        showProductTreeTableView();

        showProductSalesTableView();

        //Set DateTime
        //https://docs.oracle.com/javafx/2/api/javafx/animation/Timeline.html
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

                time.setText(year + "-" + myFormatter.format(month) + "-" + myFormatter.format(day) + " " + myFormatter.format(hour) + ":" + myFormatter.format(minute) + ":" + myFormatter.format(second));
                timeStore.setText(year + "-" + myFormatter.format(month) + "-" + myFormatter.format(day) + " " + myFormatter.format(hour) + ":" + myFormatter.format(minute) + ":" + myFormatter.format(second));
                timeCustomer.setText(year + "-" + myFormatter.format(month) + "-" + myFormatter.format(day) + " " + myFormatter.format(hour) + ":" + myFormatter.format(minute) + ":" + myFormatter.format(second));
                timeEmployee.setText(year + "-" + myFormatter.format(month) + "-" + myFormatter.format(day) + " " + myFormatter.format(hour) + ":" + myFormatter.format(minute) + ":" + myFormatter.format(second));
                timeProductSales.setText(year + "-" + myFormatter.format(month) + "-" + myFormatter.format(day) + " " + myFormatter.format(hour) + ":" + myFormatter.format(minute) + ":" + myFormatter.format(second));

                monthbestCustomer = month;
                dateInvoice = year + "-" + myFormatter.format(month) + "-" + myFormatter.format(day);
                timeInvoice = myFormatter.format(hour) + ":" + myFormatter.format(minute) + ":" + myFormatter.format(second);
            }
        }),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
        //

        //Set DateTime Start
        DecimalFormat myFormatterLabor = new DecimalFormat("00");
        Calendar calLabor = Calendar.getInstance();
        secondLabor = calLabor.get(Calendar.SECOND);

        minuteLabor = calLabor.get(Calendar.MINUTE);
        hourLabor = calLabor.get(Calendar.HOUR_OF_DAY);

        dayLabor = calLabor.get(Calendar.DATE);

        monthLabor = calLabor.get(Calendar.MONTH) + 1;
        yearLabor = calLabor.get(Calendar.YEAR);
        dateStart = yearLabor + "-" + myFormatterLabor.format(monthLabor) + "-" + myFormatterLabor.format(dayLabor) + " " + myFormatterLabor.format(hourLabor) + ":" + myFormatterLabor.format(minuteLabor) + ":" + myFormatterLabor.format(secondLabor);
        //

        orderTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        homeMenu.setStyle("-fx-background-color: #33cc94");
        viewEpresso.managedProperty().bind(viewEpresso.visibleProperty());
        viewEpresso.setVisible(true);

        viewTea.managedProperty().bind(viewTea.visibleProperty());
        viewTea.setVisible(false);

        viewSmoothie.managedProperty().bind(viewSmoothie.visibleProperty());
        viewSmoothie.setVisible(false);

        viewBlended.managedProperty().bind(viewBlended.visibleProperty());
        viewBlended.setVisible(false);

        viewStore.managedProperty().bind(viewStore.visibleProperty());
        viewStore.setVisible(false);

        viewCustomer.managedProperty().bind(viewCustomer.visibleProperty());
        viewCustomer.setVisible(false);

        viewEmployee.managedProperty().bind(viewEmployee.visibleProperty());
        viewEmployee.setVisible(false);

        viewProductSales.managedProperty().bind(viewProductSales.visibleProperty());
        viewProductSales.setVisible(false);

        //edit quantity from table
//        orderTable.setEditable(true);
//        
//        tableQuantity.setCellFactory(TextFieldTableCell.<ProductSales, Integer>forTableColumn(new IntegerStringConverter()));
        //done
        // Make menu when right click to delete
        listContextMenu = new ContextMenu();
        MenuItem deleteMenuItem = new MenuItem("Delete");
        listContextMenu.getItems().add(deleteMenuItem);

        orderTable.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                if (t.getButton() == MouseButton.SECONDARY) {
                    listContextMenu.show(orderTable, t.getScreenX(), t.getScreenY());
                }
            }
        });

        deleteMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ProductSales item = orderTable.getSelectionModel().getSelectedItem();

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Delete Item");
                alert.setHeaderText("Delete item: " + item.getProductName());
                alert.setContentText("Are you sure? Press OK to confirm, or cancel to back out.");
                Optional<ButtonType> result = alert.showAndWait();

                if (result.isPresent() && (result.get() == ButtonType.OK)) {
                    orderTable.getItems().remove(item);
                    resetAddButton(item);
                }
            }
        });
        // Finish funtion

        // Make menu when right click to update
        listContextMenuStore = new ContextMenu();
        MenuItem updateStoreItem = new MenuItem("Update");
        MenuItem deleteStoreItem = new MenuItem("Delete");
        listContextMenuStore.getItems().add(updateStoreItem);
        listContextMenuStore.getItems().add(deleteStoreItem);
        storeTable.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getButton() == MouseButton.SECONDARY) {
                    listContextMenuStore.show(storeTable, event.getScreenX(), event.getScreenY());
                }
            }

        });
        updateStoreItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Product product = storeTable.getSelectionModel().getSelectedItem().getValue();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Update product");
                alert.setHeaderText("Update product: " + product.getProductName());
                alert.setContentText("Are you sure? Press OK to confirm, or cancel to back out.");
                Optional<ButtonType> result = alert.showAndWait();

                if (result.isPresent() && (result.get() == ButtonType.OK)) {
                    updateProduct(product.getProductID());
                }

            }
        });

        deleteStoreItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Product product = storeTable.getSelectionModel().getSelectedItem().getValue();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Delete Item");
                alert.setHeaderText("Delete item: " + product.getProductName());
                alert.setContentText("Are you sure? Press OK to confirm, or cancel to back out.");
                Optional<ButtonType> result = alert.showAndWait();

                if (result.isPresent() && (result.get() == ButtonType.OK)) {
                    deleteProduct(product.getProductName(), product.getProductKind());
                }
            }
        });

        // Make menu when right click to promote user
        listContextPromoteUser = new ContextMenu();
        MenuItem promoteUser = new MenuItem("Promote");
        MenuItem deletePromoteUser = new MenuItem("Demote");
        listContextPromoteUser.getItems().add(promoteUser);
        listContextPromoteUser.getItems().add(deletePromoteUser);
        employeeTable.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getButton() == MouseButton.SECONDARY) {
                    listContextPromoteUser.show(employeeTable, event.getScreenX(), event.getScreenY());
                }
            }
        });
        promoteUser.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                User userPromote = employeeTable.getSelectionModel().getSelectedItem();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Promote User");
                alert.setHeaderText("Promote User: " + userPromote.getUserName());
                alert.setContentText("Are you sure? Press OK to confirm, or cancel to back out.");
                Optional<ButtonType> result = alert.showAndWait();

                if (result.isPresent() && (result.get() == ButtonType.OK)) {
                    Datasource.getInstance().insertUsersPromote(userPromote.getUserId(), userPromote.getUserName());
                }

            }
        });
        deletePromoteUser.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                User userPromote = employeeTable.getSelectionModel().getSelectedItem();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Delete Promote User");
                alert.setHeaderText("Delete Promote User: " + userPromote.getUserName());
                alert.setContentText("Are you sure? Press OK to confirm, or cancel to back out.");
                Optional<ButtonType> result = alert.showAndWait();

                if (result.isPresent() && (result.get() == ButtonType.OK)) {
                    Datasource.getInstance().deleteUserPromote(userPromote.getUserId());
                }

            }
        });

        // Make menu when right click to print invoice
        listContextPrintInvoice = new ContextMenu();
        MenuItem printInvoice = new MenuItem("Print");
        listContextPrintInvoice.getItems().add(printInvoice);
        productSalesTable.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getButton() == MouseButton.SECONDARY) {
                    listContextPrintInvoice.show(productSalesTable, event.getScreenX(), event.getScreenY());
                }
            }
        });
        printInvoice.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ProductSales productSales = productSalesTable.getSelectionModel().getSelectedItem();
                searchInvoiceLater(productSales.getOrderId());

            }
        });

//        totalPay.setText(this.totalPay().toString());
        totalPay.setText(priceWithDecimal(this.totalPay()));

    }

    //Get product name to combobox
    public void setComboboxProductName() {
        //set combobox productNameDelete
        ObservableList<String> nameChoose = FXCollections.observableArrayList(Datasource.getInstance().queryProductsName());
        productNameDelete.setItems(nameChoose);
        productNameDelete.setEditable(true);
        TextFields.bindAutoCompletion(productNameDelete.getEditor(), productNameDelete.getItems());
    }

    // edit quantity from order Table
    @FXML
    public void changeQuantity(CellEditEvent edittedCell) {
        ProductSales productSelected = orderTable.getSelectionModel().getSelectedItem();
        productSelected.setQuantity(Integer.parseInt(edittedCell.getNewValue().toString()));
    }

    private void makeFadeIn(VBox view) {
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(500));
        fadeTransition.setNode(view);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();
    }

    private void makeFadeIn(HBox view) {
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(500));
        fadeTransition.setNode(view);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();
    }

    public void showCustomerTableView() {
        ObservableList<Customer> customers = FXCollections.observableArrayList(Datasource.getInstance().queryCustomers());
        customerTable.setItems(customers);
    }

    public void showEmployeeTableView() {
        ObservableList<User> users = FXCollections.observableArrayList(Datasource.getInstance().queryUsers());
        employeeTable.setItems(users);
    }

    public void showWorkingEmployeeTableView() {
        ObservableList<WorkingUser> workingUsers = FXCollections.observableArrayList(Datasource.getInstance().queryWorkingUser());
        workingUserTable.setItems(workingUsers);
    }

    public void showWorkingEmployeeTableViewMonth() {
        ObservableList<WorkingUser> workingUsers = FXCollections.observableArrayList(Datasource.getInstance().queryWorkingUserMonth(monthWorking.getText()));
        workingUserTable.setItems(workingUsers);
    }

    public void showProductSalesTableView() {
        ObservableList<ProductSales> productSaleses = FXCollections.observableArrayList(Datasource.getInstance().queryProductSales());
        productSalesTable.setItems(productSaleses);
    }

    public void showProductTreeTableView() {
        JFXTreeTableColumn<Product, String> productId = new JFXTreeTableColumn<>("Product ID");
        productId.setPrefWidth(140);
        productId.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Product, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Product, String> param) {
                return param.getValue().getValue().productID;
            }
        });

        JFXTreeTableColumn<Product, String> productName = new JFXTreeTableColumn<>("Name");
        productName.setPrefWidth(140);
        productName.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Product, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Product, String> param) {
                return param.getValue().getValue().productName;
            }
        });
        JFXTreeTableColumn<Product, String> productKind = new JFXTreeTableColumn<>("Kind");
        productKind.setPrefWidth(100);
        productKind.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Product, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Product, String> param) {
                return param.getValue().getValue().productKind;
            }
        });

        JFXTreeTableColumn<Product, String> productUnit = new JFXTreeTableColumn<>("Unit");
        productUnit.setPrefWidth(70);
        productUnit.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Product, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Product, String> param) {
                return param.getValue().getValue().productUnit;
            }
        });

        JFXTreeTableColumn<Product, Integer> productPrice = new JFXTreeTableColumn<>("Price");
        productPrice.setPrefWidth(150);
        productPrice.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Product, Integer>, ObservableValue<Integer>>() {
            @Override
            public ObservableValue<Integer> call(TreeTableColumn.CellDataFeatures<Product, Integer> param) {
                return param.getValue().getValue().productPrice.asObject();
            }
        });

        JFXTreeTableColumn<Product, String> productCategory = new JFXTreeTableColumn<>("Category");
        productCategory.setPrefWidth(150);
        productCategory.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Product, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Product, String> param) {
                return param.getValue().getValue().productCategory;
            }
        });

        JFXTreeTableColumn<Product, Integer> productStock = new JFXTreeTableColumn<>("Stock");
        productStock.setPrefWidth(150);
        productStock.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Product, Integer>, ObservableValue<Integer>>() {
            @Override
            public ObservableValue<Integer> call(TreeTableColumn.CellDataFeatures<Product, Integer> param) {
                return param.getValue().getValue().productStock.asObject();
            }
        });

        ObservableList<Product> products = FXCollections.observableArrayList(Datasource.getInstance().queryProducts());

        final TreeItem<Product> root = new RecursiveTreeItem<Product>(products, RecursiveTreeObject::getChildren);

        storeTable.getColumns().setAll(productId, productName, productKind, productUnit, productPrice, productCategory, productStock);
        storeTable.setRoot(root);

        storeTable.setShowRoot(false);
    }

    public void main(MKCoffee su) {
        this.mainView = su;
    }

    //kiem tra ban dau ko du so luong trong kho tra ve null
    public ProductSales getProduct(Text productName, Button productKind, Label productPrice, TextField productQuantity) throws NumberFormatException {
        int checkStock = Datasource.getInstance().checkStock(productName.getText(), productKind.getText());
        try {
            ProductSales product = new ProductSales();
            product.setProductName(productName.getText());
            product.setKind(productKind.getText());
            product.setUnit("pcs");
            product.setPrice(Integer.parseInt(productPrice.getText()));

            if (Integer.parseInt(productQuantity.getText()) > checkStock) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Item in store");
                alert.setHeaderText("Quantity check");
                alert.setContentText("Please check your store, this quantity is not enough \nYour Store is only have: " + checkStock + " pcs");
                productQuantity.clear();
                Optional<ButtonType> result = alert.showAndWait();
                return null;
            } else if (Integer.parseInt(productQuantity.getText()) <= 0) {
                alertWarning("Quantity check", "Quantity check", "Quantity must be more than 0");
                productQuantity.clear();
                return null;
            } else {
                product.setQuantity(Integer.parseInt(productQuantity.getText()));
                product.setTotalPrice(Integer.parseInt(productPrice.getText()), product.getQuantity());
                return product;
            }
        } catch (NumberFormatException ex) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Quantity Input");
            alert.setHeaderText("Check Quantity Input");
            alert.setContentText("Please check quantity input, it must be a number");
            productQuantity.clear();
            Optional<ButtonType> result = alert.showAndWait();
            return null;
        }

    }

    public void addButton(ProductSales product, boolean epressoSmallBoolean, Text epresso, Button epressoSmall, TextField epressoSmallQuantity) {
        int checkStock = Datasource.getInstance().checkStock(epresso.getText(), epressoSmall.getText());
        ObservableList<ProductSales> allProducts = orderTable.getItems();
        if (allProducts.isEmpty() || !epressoSmallBoolean) {
            allProducts.add(product);
            epressoSmallQuantity.clear();
            orderTable.setItems(allProducts);
            orderTable.refresh();
            totalPay.setText(priceWithDecimal(this.totalPay()));
            return;
        }
        for (int i = 0; i < allProducts.size(); i++) {
            if (allProducts.get(i).getProductName().equalsIgnoreCase(epresso.getText()) && allProducts.get(i).getKind().equalsIgnoreCase(epressoSmall.getText())) {
                if ((allProducts.get(i).getQuantity() + Integer.parseInt(epressoSmallQuantity.getText())) > checkStock) {
                    int rs = checkStock - allProducts.get(i).getQuantity();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Item in store");
                    alert.setHeaderText("Quantity check");
                    alert.setContentText("Please check your store, this quantity is not enough \nYour Store is only have: " + rs + " pcs");
                    epressoSmallQuantity.clear();
                    Optional<ButtonType> result = alert.showAndWait();
                    return;
                } else {
                    if ((product.getProductName().equalsIgnoreCase(allProducts.get(i).getProductName()) && product.getKind().equalsIgnoreCase(allProducts.get(i).getKind())) && epressoSmallBoolean) {
                        allProducts.get(i).setQuantity(allProducts.get(i).getQuantity() + product.getQuantity());
                        allProducts.get(i).setTotalPrice(product.getPrice(), allProducts.get(i).getQuantity());
                        orderTable.setItems(allProducts);
                        epressoSmallQuantity.clear();
                        orderTable.refresh();
                        totalPay.setText(priceWithDecimal(this.totalPay()));
                        return;
                    }
                }
            }
        }
    }

    //EpressoSmall
    boolean epressoSmallBoolean = false;

    @FXML
    public void addButtonEpressoSmall() {
        ProductSales product = getProduct(epresso, epressoSmall, epressoSmallPrice, epressoSmallQuantity);
        if (product != null) {
            addButton(product, epressoSmallBoolean, epresso, epressoSmall, epressoSmallQuantity);
            epressoSmallBoolean = true;
        }
    }

    //EpressoMedium
    boolean epressoMediumBoolean = false;

    @FXML
    public void addButtonEpressoMedium() {
        ProductSales product = getProduct(epresso, epressoMedium, epressoMediumPrice, epressoMediumQuantity);
        if (product != null) {
            addButton(product, epressoMediumBoolean, epresso, epressoMedium, epressoMediumQuantity);
            epressoMediumBoolean = true;
        }
    }

    //CramelSmall
    boolean caramelSmallBoolean = false;

    @FXML
    public void addButtonCaramelSmall() {
        ProductSales product = getProduct(caramel, caramelSmall, caramelSmallPrice, caramelSmallQuantity);

        if (product != null) {
            addButton(product, caramelSmallBoolean, caramel, caramelSmall, caramelSmallQuantity);
            caramelSmallBoolean = true;
        }
    }

    //CramelMedium
    boolean caramelMediumBoolean = false;

    @FXML
    public void addButtonCaramelMedium() {
        ProductSales product = getProduct(caramel, caramelMedium, caramelMediumPrice, caramelMediumQuantity);
        if (product != null) {
            addButton(product, caramelMediumBoolean, caramel, caramelMedium, caramelMediumQuantity);
            caramelMediumBoolean = true;
        }
    }

    //White Coffee small
    boolean whiteCoffeeSmallBoolean = false;

    @FXML
    public void addButtonWhiteCoffeeSmall() {
        ProductSales product = getProduct(whiteCoffee, whiteCoffeeSmall, whiteCoffeeSmallPrice, whiteCoffeeSmallQuantity);
        if (product != null) {
            addButton(product, whiteCoffeeSmallBoolean, whiteCoffee, whiteCoffeeSmall, whiteCoffeeSmallQuantity);
            whiteCoffeeSmallBoolean = true;
        }
    }

    //White Coffee Medium
    boolean whiteCoffeeMediumBoolean = false;

    @FXML
    public void addButtonWhiteCoffeeMedium() {
        ProductSales product = getProduct(whiteCoffee, whiteCoffeeMedium, whiteCoffeeMediumPrice, whiteCoffeeMediumQuantity);
        if (product != null) {
            addButton(product, whiteCoffeeMediumBoolean, whiteCoffee, whiteCoffeeMedium, whiteCoffeeMediumQuantity);
            whiteCoffeeMediumBoolean = true;
        }
    }

    //Cappuccino Small
    boolean cappuccinoSmallBoolean = false;

    @FXML
    public void addButtonCappuccinoSmall() {
//        orderTable.refresh();
        ProductSales product = getProduct(cappuccino, cappuccinoSmall, cappuccinoSmallPrice, cappuccinoSmallQuantity);
        if (product != null) {
            addButton(product, cappuccinoSmallBoolean, cappuccino, cappuccinoSmall, cappuccinoSmallQuantity);
            cappuccinoSmallBoolean = true;
        }
    }

    //Cappuccino Medium
    boolean cappuccinoMediumBoolean = false;

    @FXML
    public void addButtonCappuccinoMedium() {
//        orderTable.refresh();
        ProductSales product = getProduct(cappuccino, cappuccinoMedium, cappuccinoMediumPrice, cappuccinoMediumQuantity);
        if (product != null) {
            addButton(product, cappuccinoMediumBoolean, cappuccino, cappuccinoMedium, cappuccinoMediumQuantity);
            cappuccinoMediumBoolean = true;
        }
    }

    //Black Milk Small
    boolean blackMilkSmallBoolean = false;

    @FXML
    public void addButtonBlackMilkSmall() {
        ProductSales product = getProduct(blackMilk, blackMilkSmall, blackMilkSmallPrice, blackMilkSmallQuantity);
        if (product != null) {
            addButton(product, blackMilkSmallBoolean, blackMilk, blackMilkSmall, blackMilkSmallQuantity);
            blackMilkSmallBoolean = true;
        }
    }

    //Black Milk Medium
    boolean blackMilkMediumBoolean = false;

    @FXML
    public void addButtonBlackMilkMedium() {
        ProductSales product = getProduct(blackMilk, blackMilkMedium, blackMilkMediumPrice, blackMilkMediumQuantity);
        if (product != null) {
            addButton(product, blackMilkMediumBoolean, blackMilk, blackMilkMedium, blackMilkMediumQuantity);
            blackMilkMediumBoolean = true;
        }
    }

    //Epresso Milk Small
    boolean epressoMilkSmallBoolean = false;

    @FXML
    public void addButtonEpressoMilkSmall() {
        ProductSales product = getProduct(epressoMilk, epressoMilkSmall, epressoMilkSmallPrice, epressoMilkSmallQuantity);
        if (product != null) {
            addButton(product, epressoMilkSmallBoolean, epressoMilk, epressoMilkSmall, epressoMilkSmallQuantity);
            epressoMilkSmallBoolean = true;
        }
    }

    //Epresso Milk Small
    boolean epressoMilkMediumBoolean = false;

    @FXML
    public void addButtonEpressoMilkMedium() {
        ProductSales product = getProduct(epressoMilk, epressoMilkMedium, epressoMilkMediumPrice, epressoMilkMediumQuantity);
        if (product != null) {
            addButton(product, epressoMilkMediumBoolean, epressoMilk, epressoMilkMedium, epressoMilkMediumQuantity);
            epressoMilkMediumBoolean = true;
        }
    }

    //Black Tea Small
    boolean blackTeaSmallBoolean = false;

    @FXML
    public void addButtonBlackTeaSmall() {
        ProductSales product = getProduct(blackTea, blackTeaSmall, blackTeaSmallPrice, blackTeaSmallQuantity);
        if (product != null) {
            addButton(product, blackTeaSmallBoolean, blackTea, blackTeaSmall, blackTeaSmallQuantity);
            blackTeaSmallBoolean = true;
        }
    }

    //Black Tea Medium
    boolean blackTeaMediumBoolean = false;

    @FXML
    public void addButtonBlackTeaMedium() {
        ProductSales product = getProduct(blackTea, blackTeaMedium, blackTeaMediumPrice, blackTeaMediumQuantity);
        if (product != null) {
            addButton(product, blackTeaMediumBoolean, blackTea, blackTeaMedium, blackTeaMediumQuantity);
            blackTeaMediumBoolean = true;
        }
    }

    //flavouredTea small
    boolean flavouredTeaSmallBoolean = false;

    @FXML
    public void addButtonFlavouredTeaSmall() {
        ProductSales product = getProduct(flavouredTea, flavouredTeaSmall, flavouredTeaSmallPrice, flavouredTeaSmallQuantity);
        if (product != null) {
            addButton(product, flavouredTeaSmallBoolean, flavouredTea, flavouredTeaSmall, flavouredTeaSmallQuantity);
            flavouredTeaSmallBoolean = true;
        }
    }

    //flavouredTea medium
    boolean flavouredTeaMediumBoolean = false;

    @FXML
    public void addButtonFlavouredTeaMedium() {
        ProductSales product = getProduct(flavouredTea, flavouredTeaMedium, flavouredTeaMediumPrice, flavouredTeaMediumQuantity);
        if (product != null) {
            addButton(product, flavouredTeaMediumBoolean, flavouredTea, flavouredTeaMedium, flavouredTeaMediumQuantity);
            flavouredTeaMediumBoolean = true;
        }
    }

    //raspberryMacchiato small
    boolean raspberryMacchiatoSmallBoolean = false;

    @FXML
    public void addButtonRaspberryMacchiatoSmall() {
        ProductSales product = getProduct(raspberryMacchiato, raspberryMacchiatoSmall, raspberryMacchiatoSmallPrice, raspberryMacchiatoSmallQuantity);
        if (product != null) {
            addButton(product, raspberryMacchiatoSmallBoolean, raspberryMacchiato, raspberryMacchiatoSmall, raspberryMacchiatoSmallQuantity);
            flavouredTeaMediumBoolean = true;
        }
    }

    //raspberryMacchiato medium
    boolean raspberryMacchiatoMediumBoolean = false;

    @FXML
    public void addButtonRaspberryMacchiatoMedium() {
        ProductSales product = getProduct(raspberryMacchiato, raspberryMacchiatoMedium, raspberryMacchiatoMediumPrice, raspberryMacchiatoMediumQuantity);
        if (product != null) {
            addButton(product, raspberryMacchiatoMediumBoolean, raspberryMacchiato, raspberryMacchiatoMedium, raspberryMacchiatoMediumQuantity);
            flavouredTeaMediumBoolean = true;
        }
    }

    //matchaLatte Small
    boolean matchaLatteSmallBoolean = false;

    @FXML
    public void addButtonMatchaLatteSmall() {
        ProductSales product = getProduct(matchaLatte, matchaLatteSmall, matchaLatteSmallPrice, matchaLatteSmallQuantity);
        if (product != null) {
            addButton(product, matchaLatteSmallBoolean, matchaLatte, matchaLatteSmall, matchaLatteSmallQuantity);
            matchaLatteSmallBoolean = true;
        }
    }
    //matchaLatte Medium
    boolean matchaLatteMediumBoolean = false;

    @FXML
    public void addButtonMatchaLatteMedium() {
        ProductSales product = getProduct(matchaLatte, matchaLatteMedium, matchaLatteMediumPrice, matchaLatteMediumQuantity);
        if (product != null) {
            addButton(product, matchaLatteMediumBoolean, matchaLatte, matchaLatteMedium, matchaLatteMediumQuantity);
            matchaLatteMediumBoolean = true;
        }
    }

    //macchiatoTea Small
    boolean macchiatoTeaSmallBoolean = false;

    @FXML
    public void addButtonMacchiatoTeaSmall() {
        ProductSales product = getProduct(macchiatoTea, macchiatoTeaSmall, macchiatoTeaSmallPrice, macchiatoTeaSmallQuantity);
        if (product != null) {
            addButton(product, macchiatoTeaSmallBoolean, matchaLatte, macchiatoTeaSmall, macchiatoTeaSmallQuantity);
            macchiatoTeaSmallBoolean = true;
        }
    }

    //macchiatoTea Medium
    boolean macchiatoTeaMediumBoolean = false;

    @FXML
    public void addButtonMacchiatoTeaMedium() {
        ProductSales product = getProduct(macchiatoTea, macchiatoTeaMedium, macchiatoTeaMediumPrice, macchiatoTeaMediumQuantity);
        if (product != null) {
            addButton(product, macchiatoTeaMediumBoolean, macchiatoTea, macchiatoTeaMedium, macchiatoTeaMediumQuantity);
            macchiatoTeaMediumBoolean = true;
        }
    }

    //peachTeaMania Small
    boolean peachTeaManiaSmallBoolean = false;

    @FXML
    public void addButtonPeachTeaManiaSmall() {
//        orderTable.refresh();
        ProductSales product = getProduct(peachTeaMania, peachTeaManiaSmall, peachTeaManiaSmallPrice, peachTeaManiaSmallQuantity);
        if (product != null) {
            addButton(product, peachTeaManiaSmallBoolean, peachTeaMania, peachTeaManiaSmall, peachTeaManiaSmallQuantity);
            peachTeaManiaSmallBoolean = true;
        }
    }

    //peachTeaMania Medium
    boolean peachTeaManiaMediumBoolean = false;

    @FXML
    public void addButtonPeachTeaManiaMedium() {
        ProductSales product = getProduct(peachTeaMania, peachTeaManiaMedium, peachTeaManiaMediumPrice, peachTeaManiaMediumQuantity);
        if (product != null) {
            addButton(product, peachTeaManiaMediumBoolean, peachTeaMania, peachTeaManiaMedium, peachTeaManiaMediumQuantity);
            peachTeaManiaMediumBoolean = true;
        }
    }

    //raspberrySoda Small
    boolean raspberrySodaSmallBoolean = false;

    @FXML
    public void addButtonRaspberrySodaSmall() {
        ProductSales product = getProduct(raspberrySoda, raspberrySodaSmall, raspberrySodaSmallPrice, raspberrySodaSmallQuantity);
        if (product != null) {
            addButton(product, raspberrySodaSmallBoolean, raspberrySoda, raspberrySodaSmall, raspberrySodaSmallQuantity);
            raspberrySodaSmallBoolean = true;
        }
    }

    //raspberrySoda Mediums
    boolean raspberrySodaMediumBoolean = false;

    @FXML
    public void addButtonRaspberrySodaMedium() {
        ProductSales product = getProduct(raspberrySoda, raspberrySodaMedium, raspberrySodaMediumPrice, raspberrySodaMediumQuantity);
        if (product != null) {
            addButton(product, raspberrySodaMediumBoolean, raspberrySoda, raspberrySodaMedium, raspberrySodaMediumQuantity);
            raspberrySodaMediumBoolean = true;
        }
    }

    //berrySmoothie Small
    boolean berrySmoothieSmallBoolean = false;

    @FXML
    public void addButtonBerrySmoothieSmall() {
        ProductSales product = getProduct(berrySmoothie, berrySmoothieSmall, berrySmoothieSmallPrice, berrySmoothieSmallQuantity);
        if (product != null) {
            addButton(product, berrySmoothieSmallBoolean, berrySmoothie, berrySmoothieSmall, berrySmoothieSmallQuantity);
            berrySmoothieSmallBoolean = true;
        }
    }

    //berrySmoothie Medium
    boolean berrySmoothieMediumBoolean = false;

    @FXML
    public void addButtonBerrySmoothieMedium() {
        ProductSales product = getProduct(berrySmoothie, berrySmoothieMedium, berrySmoothieMediumPrice, berrySmoothieMediumQuantity);
        if (product != null) {
            addButton(product, berrySmoothieMediumBoolean, berrySmoothie, berrySmoothieMedium, berrySmoothieMediumQuantity);
            berrySmoothieMediumBoolean = true;
        }
    }

    //mangoSmoothie Small
    boolean mangoSmoothieSmallBoolean = false;

    @FXML
    public void addButtonMangoSmoothieSmall() {
//        orderTable.refresh();
        ProductSales product = getProduct(mangoSmoothie, mangoSmoothieSmall, mangoSmoothieSmallPrice, mangoSmoothieSmallQuantity);
        if (product != null) {
            addButton(product, mangoSmoothieSmallBoolean, mangoSmoothie, mangoSmoothieSmall, mangoSmoothieSmallQuantity);
            mangoSmoothieSmallBoolean = true;
        }
    }

    //mangoSmoothie Medium
    boolean mangoSmoothieMediumBoolean = false;

    @FXML
    public void addButtonMangoSmoothieMedium() {
        ProductSales product = getProduct(mangoSmoothie, mangoSmoothieMedium, mangoSmoothieMediumPrice, mangoSmoothieMediumQuantity);
        if (product != null) {
            addButton(product, mangoSmoothieMediumBoolean, mangoSmoothie, mangoSmoothieMedium, mangoSmoothieMediumQuantity);
            mangoSmoothieMediumBoolean = true;
        }
    }

    //greenApple Small
    boolean greenAppleSmallBoolean = false;

    @FXML
    public void addButtonGreenAppleSmall() {
        ProductSales product = getProduct(greenApple, greenAppleSmall, greenAppleSmallPrice, greenAppleSmallQuantity);
        if (product != null) {
            addButton(product, greenAppleSmallBoolean, greenApple, greenAppleSmall, greenAppleSmallQuantity);
            greenAppleSmallBoolean = true;
        }
    }

    //greenApple Medium
    boolean greenAppleMediumBoolean = false;

    @FXML
    public void addButtonGreenAppleMedium() {
        ProductSales product = getProduct(greenApple, greenAppleMedium, greenAppleMediumPrice, greenAppleMediumQuantity);
        if (product != null) {
            addButton(product, greenAppleMediumBoolean, greenApple, greenAppleMedium, greenAppleMediumQuantity);
            greenAppleMediumBoolean = true;
        }
    }

    //berrySoda Small
    boolean berrySodaSmallBoolean = false;

    @FXML
    public void addButtonBerrySodaSmall() {
        ProductSales product = getProduct(berrySoda, berrySodaSmall, berrySodaSmallPrice, berrySodaSmallQuantity);
        if (product != null) {
            addButton(product, berrySodaSmallBoolean, berrySoda, berrySodaSmall, berrySodaSmallQuantity);
            berrySodaSmallBoolean = true;
        }
    }

    //berrySoda Medium
    boolean berrySodaMediumBoolean = false;

    @FXML
    public void addButtonBerrySodaMedium() {
        ProductSales product = getProduct(berrySoda, berrySodaMedium, berrySodaMediumPrice, berrySodaMediumQuantity);
        if (product != null) {
            addButton(product, berrySodaMediumBoolean, berrySoda, berrySodaMedium, berrySodaMediumQuantity);
            berrySodaMediumBoolean = true;
        }
    }

    //mojitoLemon Small
    boolean mojitoLemonSmallBoolean = false;

    @FXML
    public void addButtonMojitoLemonSmall() {
        ProductSales product = getProduct(mojitoLemon, mojitoLemonSmall, mojitoLemonSmallPrice, mojitoLemonSmallQuantity);
        if (product != null) {
            addButton(product, mojitoLemonSmallBoolean, mojitoLemon, mojitoLemonSmall, mojitoLemonSmallQuantity);
            mojitoLemonSmallBoolean = true;
        }
    }

    //mojitoLemon Medium
    boolean mojitoLemonMediumBoolean = false;

    @FXML
    public void addButtonMojitoLemonMedium() {
        ProductSales product = getProduct(mojitoLemon, mojitoLemonMedium, mojitoLemonMediumPrice, mojitoLemonMediumQuantity);
        if (product != null) {
            addButton(product, mojitoLemonMediumBoolean, mojitoLemon, mojitoLemonSmall, mojitoLemonMediumQuantity);
            mojitoLemonMediumBoolean = true;
        }
    }

    //mochaIce Small
    boolean mochaIceSmallBoolean = false;

    @FXML
    public void addButtonMochaIceSmall() {
        ProductSales product = getProduct(mochaIce, mochaIceSmall, mochaIceSmallPrice, mochaIceSmallQuantity);
        if (product != null) {
            addButton(product, mochaIceSmallBoolean, mochaIce, mochaIceSmall, mochaIceSmallQuantity);
            mochaIceSmallBoolean = true;
        }
    }

    //mochaIce Medium
    boolean mochaIceMediumBoolean = false;

    @FXML
    public void addButtonMochaIceMedium() {
        ProductSales product = getProduct(mochaIce, mochaIceMedium, mochaIceMediumPrice, mochaIceMediumQuantity);
        if (product != null) {
            addButton(product, mochaIceMediumBoolean, mochaIce, mochaIceMedium, mochaIceMediumQuantity);
            mochaIceMediumBoolean = true;
        }
    }

    //caramelIce Small
    boolean caramelIceSmallBoolean = false;

    @FXML
    public void addButtonCaramelIceSmall() {
        ProductSales product = getProduct(caramelIce, caramelIceSmall, caramelIceSmallPrice, caramelIceSmallQuantity);
        if (product != null) {
            addButton(product, caramelIceSmallBoolean, caramelIce, caramelIceSmall, caramelIceSmallQuantity);
            caramelIceSmallBoolean = true;
        }
    }

    //caramelIce Medium
    boolean caramelIceMediumBoolean = false;

    @FXML
    public void addButtonCaramelIceMedium() {
        ProductSales product = getProduct(caramelIce, caramelIceMedium, caramelIceMediumPrice, caramelIceMediumQuantity);
        if (product != null) {
            addButton(product, caramelIceMediumBoolean, caramelIce, caramelIceMedium, caramelIceMediumQuantity);
            caramelIceMediumBoolean = true;
        }
    }

    //cookieIce Small
    boolean cookieIceSmallBoolean = false;

    @FXML
    public void addButtonCookieIceSmall() {
        ProductSales product = getProduct(cookieIce, cookieIceSmall, cookieIceSmallPrice, cookieIceSmallQuantity);
        if (product != null) {
            addButton(product, cookieIceSmallBoolean, cookieIce, cookieIceSmall, cookieIceSmallQuantity);
            cookieIceSmallBoolean = true;
        }
    }

    //cookieIce Medium
    boolean cookieIceMediumBoolean = false;

    @FXML
    public void addButtonCookieIceMedium() {
        ProductSales product = getProduct(cookieIce, cookieIceMedium, cookieIceMediumPrice, cookieIceMediumQuantity);
        if (product != null) {
            addButton(product, cookieIceMediumBoolean, cookieIce, cookieIceMedium, cookieIceMediumQuantity);
            cookieIceMediumBoolean = true;
        }
    }

    //iceChocolate Small
    boolean iceChocolateSmallBoolean = false;

    @FXML
    public void addButtonIceChocolateSmall() {
        ProductSales product = getProduct(iceChocolate, iceChocolateSmall, iceChocolateSmallPrice, iceChocolateSmallQuantity);
        if (product != null) {
            addButton(product, iceChocolateSmallBoolean, iceChocolate, iceChocolateSmall, iceChocolateSmallQuantity);
            iceChocolateSmallBoolean = true;
        }
    }

    //iceChocolate Medium
    boolean iceChocolateMediumBoolean = false;

    @FXML
    public void addButtonIceChocolateMedium() {
        ProductSales product = getProduct(iceChocolate, iceChocolateMedium, iceChocolateMediumPrice, iceChocolateMediumQuantity);
        if (product != null) {
            addButton(product, iceChocolateMediumBoolean, iceChocolate, iceChocolateMedium, iceChocolateMediumQuantity);
            iceChocolateMediumBoolean = true;
        }
    }

    //doubleChocolate Small
    boolean doubleChocolateSmallBoolean = false;

    @FXML
    public void addButtonDoubleChocolateSmall() {
        ProductSales product = getProduct(doubleChocolate, doubleChocolateSmall, doubleChocolateSmallPrice, doubleChocolateSmallQuantity);
        if (product != null) {
            addButton(product, doubleChocolateSmallBoolean, doubleChocolate, doubleChocolateSmall, doubleChocolateSmallQuantity);
            doubleChocolateSmallBoolean = true;
        }
    }

    //doubleChocolate Medium
    boolean doubleChocolateMediumBoolean = false;

    @FXML
    public void addButtonDoubleChocolateMedium() {
        ProductSales product = getProduct(doubleChocolate, doubleChocolateMedium, doubleChocolateMediumPrice, doubleChocolateMediumQuantity);
        if (product != null) {
            addButton(product, doubleChocolateMediumBoolean, doubleChocolate, doubleChocolateMedium, doubleChocolateMediumQuantity);
            doubleChocolateMediumBoolean = true;
        }
    }

    //vanillaIce Small
    boolean vanillaIceSmallBoolean = false;

    @FXML
    public void addButtonVanillaIceSmall() {
        ProductSales product = getProduct(vanillaIce, vanillaIceSmall, vanillaIceSmallPrice, vanillaIceSmallQuantity);
        if (product != null) {
            addButton(product, vanillaIceSmallBoolean, vanillaIce, vanillaIceSmall, vanillaIceSmallQuantity);
            vanillaIceSmallBoolean = true;
        }
    }

    //vanillaIce Medium
    boolean vanillaIceMediumBoolean = false;

    @FXML
    public void addButtonVanillaIceMedium() {
        ProductSales product = getProduct(vanillaIce, vanillaIceMedium, vanillaIceMediumPrice, vanillaIceMediumQuantity);
        if (product != null) {
            addButton(product, vanillaIceMediumBoolean, vanillaIce, vanillaIceMedium, vanillaIceMediumQuantity);
            vanillaIceMediumBoolean = true;
        }
    }
    String productUnit = "pcs";

    @FXML
    public void insertProduct() {
        if (productName.getText().isEmpty() || productKind.getValue() == null || productPrice.getText().isEmpty() || productCategory.getValue() == null || productStock.getText().isEmpty()) {
            alertWarning("Input Form Warning", "Input Form Warning", "Please fill-in all the form");
        } else {
            Datasource.getInstance().insertProductDB(productName.getText(), productKind.getValue(), productUnit, productPrice.getText(), productCategory.getValue(), productStock.getText());
            Datasource.getInstance().insertReceiptProductDB(employeeId.getText(), productName.getText(), productKind.getValue(), "pcs", Integer.parseInt(productPrice.getText()), Integer.parseInt(productStock.getText()), timeStore.getText());
            //clear text field
            productName.clear();
            productKind.setValue(null);
            productKind.setPromptText("Choose Size");
            productPrice.clear();
            productCategory.setValue(null);
            productCategory.setPromptText("Choose Category");
            productStock.clear();

            showProductTreeTableView();
            setComboboxProductName();
        }

    }

    @FXML
    public void cancleInsertProduct() {

        //clear text field
        productName.clear();
        productKind.setValue(null);
        productKind.setPromptText("Choose Size");
        productPrice.clear();
        productCategory.setValue(null);
        productCategory.setPromptText("Choose Category");
        productStock.clear();

    }

    @FXML
    public void updateProduct() {
        Product product = Datasource.getInstance().searchProductById(productIdUpdate.getText());
        if (product != null) {
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.initOwner(mainBorderPane.getScene().getWindow());
//            dialog.setTitle("Update Product");
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/mkcoffee/views/UpdateProductDialog.fxml"));
            try {
                dialog.getDialogPane().setContent(fxmlLoader.load());
                UpdateProductDialogController dialogController = fxmlLoader.getController();
                dialogController.getProduct(product.getProductName(), product.getProductKind(), product.getProductPrice(), product.getProductCategory(), product.getProductStock());

            } catch (IOException e) {
                e.printStackTrace();
                return;
            }

            dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
            dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

            Optional<ButtonType> result = dialog.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                UpdateProductDialogController dialogController = fxmlLoader.getController();
                Product updateProduct = dialogController.getUpdateProduct();
                Datasource.getInstance().updateProductDb(updateProduct, productIdUpdate.getText());
                Datasource.getInstance().insertReceiptProductDB(employeeId.getText(), updateProduct.getProductName(), updateProduct.getProductKind(), updateProduct.getProductUnit(), updateProduct.getProductPrice(), updateProduct.getProductStockReceipt(), timeStore.getText());
                showProductTreeTableView();
                productIdUpdate.clear();
            }
        } else {
            alertWarning("Update Product", "Product Update form", "Please fill in product ID!");

        }
    }

    @FXML
    public void cancleUpdateProduct() {
        productIdUpdate.clear();
    }

    @FXML
    public void updateProduct(String productIdUpdate) {
        Product product = Datasource.getInstance().searchProductById(productIdUpdate);
        if (product != null) {
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.initOwner(mainBorderPane.getScene().getWindow());
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/mkcoffee/views/UpdateProductDialog.fxml"));
            try {
                dialog.getDialogPane().setContent(fxmlLoader.load());
                UpdateProductDialogController dialogController = fxmlLoader.getController();
                dialogController.getProduct(product.getProductName(), product.getProductKind(), product.getProductPrice(), product.getProductCategory(), product.getProductStock());

            } catch (IOException e) {
                e.printStackTrace();
                return;
            }

            dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
            dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

            Optional<ButtonType> result = dialog.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                UpdateProductDialogController dialogController = fxmlLoader.getController();
                Product updateProduct = dialogController.getUpdateProduct();

                Datasource.getInstance().updateProductDb(updateProduct, productIdUpdate);
                Datasource.getInstance().insertReceiptProductDB(employeeId.getText(), updateProduct.getProductName(), updateProduct.getProductKind(), updateProduct.getProductUnit(), updateProduct.getProductPrice(), updateProduct.getProductStockReceipt(), timeStore.getText());
                showProductTreeTableView();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Update Product");
            alert.setHeaderText("Product Update form");
            alert.setContentText("Please fill in product ID!");
            Optional<ButtonType> choose = alert.showAndWait();
        }
    }

    @FXML
    public void updateCustomer() {
        Customer customer = Datasource.getInstance().searchCutomerById(customerIdUpdate.getText());
        if (customer != null) {
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.initOwner(mainBorderPane.getScene().getWindow());
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/mkcoffee/views/UpdateCustomerDialog.fxml"));
            try {
                dialog.getDialogPane().setContent(fxmlLoader.load());
                UpdateCustomerDialogController dialogController = fxmlLoader.getController();
                dialogController.getCustomer(customer.getCustomerName(), customer.getCustomerAdd(), customer.getCustomerPhone(), customer.getCustomerEmail());

            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
            dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
            dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
            Optional<ButtonType> result = dialog.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                UpdateCustomerDialogController dialogController = fxmlLoader.getController();
                Customer updateCustomer = dialogController.getNewCustomer();

                Datasource.getInstance().updateCustomerDb(updateCustomer, customerIdUpdate.getText());
                showCustomerTableView();
                customerIdUpdate.clear();
            }
        } else {
            alertWarning("Update Customer Information", "Customer Update form", "Please fill in Customer ID!");
        }
    }

    @FXML
    public void cancleUpdateCustomer() {
        customerIdUpdate.clear();
    }

    @FXML
    public void registerCustomer() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainBorderPane.getScene().getWindow());
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/mkcoffee/views/UpdateCustomerDialog.fxml"));
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
            UpdateCustomerDialogController dialogController = fxmlLoader.getController();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            UpdateCustomerDialogController dialogController = fxmlLoader.getController();
            Customer newCustomer = dialogController.getNewCustomer();
            if (newCustomer != null) {
                Datasource.getInstance().insertCustomerBb(newCustomer.getCustomerName(), newCustomer.getCustomerAdd(), newCustomer.getCustomerPhone(), newCustomer.getCustomerEmail());
            }
        }

    }

    @FXML
    public void updateEmployee() throws ParseException {
        User user = Datasource.getInstance().searchEmployeeById(employeeIdUpdate.getText());
        if (user != null) {
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.initOwner(mainBorderPane.getScene().getWindow());
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/mkcoffee/views/UpdateEmployeeDialog.fxml"));
            try {
                dialog.getDialogPane().setContent(fxmlLoader.load());
                UpdateEmployeeDialogController dialogController = fxmlLoader.getController();
                String string = user.getBirthday();
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
                java.util.Date birthday = format.parse(string);
                java.sql.Date sqlDate = new java.sql.Date(birthday.getTime());
                dialogController.getEmployee(user.getFullName(), user.getEmail(), user.getPhoneNumber(), sqlDate, user.getGender(), user.getUserName(), user.getUserPass());

            } catch (IOException e) {
                e.printStackTrace();
                return;
            }

            dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
            dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

            Optional<ButtonType> result = dialog.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                UpdateEmployeeDialogController dialogController = fxmlLoader.getController();
                User updateUser = dialogController.getUpdateEmployee();
                Datasource.getInstance().updateEmployeeDb(updateUser, employeeIdUpdate.getText());
                showEmployeeTableView();
                employeeIdUpdate.clear();
            }
        } else {
            alertWarning("Update Employee Information", "Employee Update form", "Please fill in Employee ID!");
        }
    }

    @FXML
    public void cancleUpdateEmployee() {
        employeeIdUpdate.clear();
    }

    @FXML
    public void deleteProduct() {
        if (productNameDelete.getValue() == null || productKindDelete.getValue() == null) {
            alertWarning("Delete Product", "Delete Product", "Please fill in product name and kind for delete!");
        } else {
            Datasource.getInstance().deleteProductDB(productNameDelete.getValue(), productKindDelete.getValue());

            showProductTreeTableView();
            productNameDelete.setValue(null);
            productKindDelete.setValue(null);
            productNameDelete.setPromptText("Choose Name");
            productKindDelete.setPromptText("Choose Size");

        }
    }

    public void deleteProduct(String productNameDelete, String productKindDelete) {
        if (productNameDelete.isEmpty() && productKindDelete.isEmpty()) {
            alertWarning("Delete Product", "Delete Product", "Please fill in product name and kind for delete!");
        } else {
            Datasource.getInstance().deleteProductDB(productNameDelete, productKindDelete);
            showProductTreeTableView();
        }
    }

    @FXML
    public void cancleDeleteProduct() {
        productNameDelete.setPromptText("Choose Name");
        productKindDelete.setPromptText("Choose Size");
    }

    @FXML
    public void deleteCustomer() {
        if (customerNameDelete.getText().isEmpty() || customerPhoneDelete.getText().isEmpty()) {
            alertWarning("Input Error", "Customer Input check:", "Please fill in all the form.");
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Customer");
            alert.setHeaderText("Delete Customer");
            alert.setContentText("Are you sure? Press OK to confirm, or cancel to back out.");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && (result.get() == ButtonType.OK)) {
                Datasource.getInstance().deleteCustomerDb(customerNameDelete.getText(), customerPhoneDelete.getText());
                showCustomerTableView();
            }
            customerNameDelete.clear();
            customerPhoneDelete.clear();
        }
    }

    @FXML
    public void cancleDeleteCustomer() {
        customerNameDelete.clear();
        customerPhoneDelete.clear();
    }

    @FXML
    public void deleteEmployee() {
        if (employeeIdDelete.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Input Error");
            alert.setHeaderText("Employee Input check:");
            alert.setContentText("Please fill in the Employee Id.");
            Optional<ButtonType> result = alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Employee");
            alert.setHeaderText("Delete Employee");
            alert.setContentText("Are you sure? Press OK to confirm, or cancel to back out.");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && (result.get() == ButtonType.OK)) {
                Datasource.getInstance().deleteEmployeeDb(employeeIdDelete.getText());
                showEmployeeTableView();
            }
            employeeIdDelete.clear();
        }
    }

    @FXML
    public void cancleDeleteEmployee() {
        employeeIdDelete.clear();
    }

    @FXML
    public void insertProductSale() {
        if (orderTable.getItems().isEmpty()) {
            alertWarning("Order List", "Order List is Empty", "Can not payment if order list is empty.");
        } else {
            if (!(customerPhoneProductSale.getText().isEmpty())) {
                Datasource.getInstance().insertProductSalesDB(employeeId.getText(), customerPhoneProductSale.getText(), time.getText(), orderTable, totalPay());
                orderTable.refresh();
                //clear all field 
                clearField();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Invoice Priting");
                alert.setHeaderText("Invoice Priting:");
                alert.setContentText("Are you want to print your invoice? .");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && (result.get() == ButtonType.OK)) {
                    printInvoice();
                }
                //clear table
                orderTable.getItems().clear();
                //clear totalMax pay
                totalPay.setText("0");
            } else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Payment Warning");
                alert.setHeaderText("Customer ID check:");
                alert.setContentText("Please enter the customer ID, if not Customer Phone will be 0.");
                Optional<ButtonType> resultTemp = alert.showAndWait();
                if (resultTemp.isPresent() && (resultTemp.get() == ButtonType.OK)) {
                    Datasource.getInstance().insertProductSalesDB(employeeId.getText(), "0", time.getText(), orderTable, totalPay());
                    orderTable.refresh();
                    //clear text field
                    clearField();
                    //clear boolean
                    Alert alertPrint = new Alert(Alert.AlertType.CONFIRMATION);
                    alertPrint.setTitle("Invoice Priting");
                    alertPrint.setHeaderText("Invoice Priting:");
                    alertPrint.setContentText("Are you want to print your invoice? .");
                    Optional<ButtonType> result = alertPrint.showAndWait();
                    if (result.isPresent() && (result.get() == ButtonType.OK)) {
                        printInvoice();
                    }
                    //clear table
                    orderTable.getItems().clear();
                    //clear totalMax pay
                    totalPay.setText("0");
                }
            }
        }
    }

    public void alertWarning(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        Optional<ButtonType> result = alert.showAndWait();
    }

    @FXML
    public void insertCustomerTest() {
        if (!(customerName.getText().isEmpty() && customerAdd.getText().isEmpty() && customerPhone.getText().isEmpty() && customerEmail.getText().isEmpty())) {
            Datasource.getInstance().insertCustomerBb(customerName.getText(), customerAdd.getText(), customerPhone.getText(), customerEmail.getText());
            customerName.clear();
            customerAdd.clear();
            customerPhone.clear();
            customerEmail.clear();
            showCustomerTableView();
        } else {
            alertWarning("Input Error", "Customer Input check:", "Please fill in all the form.");
        }
    }

    @FXML
    public void cancleInsertCustomerTest() {
        customerName.clear();
        customerAdd.clear();
        customerPhone.clear();
        customerEmail.clear();
    }

    @FXML
    public void insertEmployee() {
        if (!(employeeFullName.getText().isEmpty() && employeeEmail.getText().isEmpty() && employeePhone.getText().isEmpty() && employeeBirthday.getValue().toString().isEmpty() && employeeGender.getValue().isEmpty() && employeeUsername.getText().isEmpty() && employeePassword.getText().isEmpty())) {
            SignUpController.insertInside(employeeFullName.getText(), employeeEmail.getText(), employeePhone.getText(), employeeBirthday.getValue().toString(), employeeGender.getValue(), employeeUsername.getText(), employeePassword.getText());
            showEmployeeTableView();
            employeeFullName.clear();
            employeeEmail.clear();
            employeePhone.clear();
            employeeBirthday.getEditor().clear();
            employeeGender.setValue(null);
            employeeGender.setPromptText("Gender");
            employeeUsername.clear();
            employeePassword.clear();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Input Error");
            alert.setHeaderText("Customer Input check:");
            alert.setContentText("Please fill in all the form.");
            Optional<ButtonType> result = alert.showAndWait();
        }
    }

    @FXML
    public void cancleInsertEmployee() {
        employeeFullName.clear();
        employeeEmail.clear();
        employeePhone.clear();
        employeeBirthday.getEditor().clear();
        employeeGender.setValue(null);
        employeeGender.setPromptText("Gender");
        employeeUsername.clear();
        employeePassword.clear();
    }

    @FXML
    private void openHome(ActionEvent event) throws IOException {
        makeFadeIn(viewHome);
        viewHome.managedProperty().bind(viewHome.visibleProperty());
        viewHome.setVisible(true);
        viewOrder.managedProperty().bind(viewOrder.visibleProperty());
        viewOrder.setVisible(true);
        viewStore.managedProperty().bind(viewStore.visibleProperty());
        viewStore.setVisible(false);
        viewCustomer.managedProperty().bind(viewCustomer.visibleProperty());
        viewCustomer.setVisible(false);
        viewEmployee.managedProperty().bind(viewEmployee.visibleProperty());
        viewEmployee.setVisible(false);
        viewProductSales.managedProperty().bind(viewProductSales.visibleProperty());
        viewProductSales.setVisible(false);
        homeMenu.setStyle("-fx-background-color: #33cc94");
        employeeMenu.setStyle("-fx-background-color: none");
        customerMenu.setStyle("-fx-background-color: none");
        storeMenu.setStyle("-fx-background-color: none");
        productSalesMenu.setStyle("-fx-background-color: none");
        quitMenu.setStyle("-fx-background-color: none");
    }

    @FXML
    private void openEpresso(ActionEvent event) throws IOException {
        makeFadeIn(viewEpresso);
        viewEpresso.managedProperty().bind(viewEpresso.visibleProperty());
        viewEpresso.setVisible(true);
        viewTea.managedProperty().bind(viewTea.visibleProperty());
        viewTea.setVisible(false);
        viewSmoothie.managedProperty().bind(viewSmoothie.visibleProperty());
        viewSmoothie.setVisible(false);
        viewBlended.managedProperty().bind(viewBlended.visibleProperty());
        viewBlended.setVisible(false);
        homeMenu.setStyle("-fx-background-color: #33cc94");
        employeeMenu.setStyle("-fx-background-color: none");
        customerMenu.setStyle("-fx-background-color: none");
        storeMenu.setStyle("-fx-background-color: none");
        quitMenu.setStyle("-fx-background-color: none");
    }

    @FXML
    private void openTea(ActionEvent event) throws IOException {
        makeFadeIn(viewTea);
        viewTea.managedProperty().bind(viewTea.visibleProperty());
        viewTea.setVisible(true);
        viewEpresso.managedProperty().bind(viewEpresso.visibleProperty());
        viewEpresso.setVisible(false);
        viewSmoothie.managedProperty().bind(viewSmoothie.visibleProperty());
        viewSmoothie.setVisible(false);
        viewBlended.managedProperty().bind(viewBlended.visibleProperty());
        viewBlended.setVisible(false);
        homeMenu.setStyle("-fx-background-color: #33cc94");
        employeeMenu.setStyle("-fx-background-color: none");
        customerMenu.setStyle("-fx-background-color: none");
        storeMenu.setStyle("-fx-background-color: none");
        quitMenu.setStyle("-fx-background-color: none");
    }

    @FXML
    private void openSmoothie(ActionEvent event) throws IOException {
        makeFadeIn(viewSmoothie);
        viewSmoothie.managedProperty().bind(viewSmoothie.visibleProperty());
        viewSmoothie.setVisible(true);
        viewEpresso.managedProperty().bind(viewEpresso.visibleProperty());
        viewEpresso.setVisible(false);
        viewTea.managedProperty().bind(viewTea.visibleProperty());
        viewTea.setVisible(false);
        viewBlended.managedProperty().bind(viewBlended.visibleProperty());
        viewBlended.setVisible(false);
        homeMenu.setStyle("-fx-background-color: #33cc94");
        employeeMenu.setStyle("-fx-background-color: none");
        customerMenu.setStyle("-fx-background-color: none");
        storeMenu.setStyle("-fx-background-color: none");
        quitMenu.setStyle("-fx-background-color: none");
    }

    @FXML
    private void openBlended(ActionEvent event) throws IOException {
        makeFadeIn(viewBlended);
        viewBlended.managedProperty().bind(viewBlended.visibleProperty());
        viewBlended.setVisible(true);
        viewEpresso.managedProperty().bind(viewEpresso.visibleProperty());
        viewEpresso.setVisible(false);
        viewTea.managedProperty().bind(viewTea.visibleProperty());
        viewTea.setVisible(false);
        viewSmoothie.managedProperty().bind(viewSmoothie.visibleProperty());
        viewSmoothie.setVisible(false);
        homeMenu.setStyle("-fx-background-color: #33cc94");
        employeeMenu.setStyle("-fx-background-color: none");
        customerMenu.setStyle("-fx-background-color: none");
        storeMenu.setStyle("-fx-background-color: none");
        quitMenu.setStyle("-fx-background-color: none");
    }

    @FXML
    private void openEmployee(ActionEvent event) throws IOException {
        makeFadeIn(viewEmployee);
        viewHome.managedProperty().bind(viewHome.visibleProperty());
        viewHome.setVisible(false);
        viewOrder.managedProperty().bind(viewOrder.visibleProperty());
        viewOrder.setVisible(false);
        viewCustomer.managedProperty().bind(viewCustomer.visibleProperty());
        viewCustomer.setVisible(false);
        viewStore.managedProperty().bind(viewStore.visibleProperty());
        viewStore.setVisible(false);
        viewProductSales.managedProperty().bind(viewProductSales.visibleProperty());
        viewProductSales.setVisible(false);
        viewEmployee.managedProperty().bind(viewEmployee.visibleProperty());
        viewEmployee.setVisible(true);
        homeMenu.setStyle("-fx-background-color: none");
        employeeMenu.setStyle("-fx-background-color: #33cc94");
        customerMenu.setStyle("-fx-background-color: none");
        storeMenu.setStyle("-fx-background-color: none");
        productSalesMenu.setStyle("-fx-background-color: none");
        quitMenu.setStyle("-fx-background-color: none");
        monthWorking.setText(monthbestCustomer.toString());
        ObservableList<WorkingUser> workingUsers = FXCollections.observableArrayList(Datasource.getInstance().queryWorkingUserMonth(monthbestCustomer.toString()));
        workingUserTable.setItems(workingUsers);
    }

    @FXML
    private void openStore(ActionEvent event) throws IOException {
        makeFadeIn(viewStore);
        viewHome.managedProperty().bind(viewHome.visibleProperty());
        viewHome.setVisible(false);
        viewOrder.managedProperty().bind(viewOrder.visibleProperty());
        viewOrder.setVisible(false);
        viewCustomer.managedProperty().bind(viewCustomer.visibleProperty());
        viewCustomer.setVisible(false);
        viewEmployee.managedProperty().bind(viewEmployee.visibleProperty());
        viewEmployee.setVisible(false);
        viewProductSales.managedProperty().bind(viewProductSales.visibleProperty());
        viewProductSales.setVisible(false);
        homeMenu.setStyle("-fx-background-color: none");
        employeeMenu.setStyle("-fx-background-color: none");
        customerMenu.setStyle("-fx-background-color: none");
        storeMenu.setStyle("-fx-background-color: #33cc94");
        productSalesMenu.setStyle("-fx-background-color: none");
        quitMenu.setStyle("-fx-background-color: none");
        viewStore.managedProperty().bind(viewStore.visibleProperty());
        viewStore.setVisible(true);
    }

    @FXML
    private void openOrderList(ActionEvent event) throws IOException {
        makeFadeIn(viewStore);
        viewHome.managedProperty().bind(viewHome.visibleProperty());
        viewHome.setVisible(false);
        viewOrder.managedProperty().bind(viewOrder.visibleProperty());
        viewOrder.setVisible(false);
        viewCustomer.managedProperty().bind(viewCustomer.visibleProperty());
        viewCustomer.setVisible(false);
        viewEmployee.managedProperty().bind(viewEmployee.visibleProperty());
        viewEmployee.setVisible(false);
        viewProductSales.managedProperty().bind(viewProductSales.visibleProperty());
        viewProductSales.setVisible(false);
        homeMenu.setStyle("-fx-background-color: none");
        employeeMenu.setStyle("-fx-background-color: none");
        customerMenu.setStyle("-fx-background-color: none");
        storeMenu.setStyle("-fx-background-color: #33cc94");
        productSalesMenu.setStyle("-fx-background-color: none");
        quitMenu.setStyle("-fx-background-color: none");
        viewStore.managedProperty().bind(viewStore.visibleProperty());
        viewStore.setVisible(true);
    }

    @FXML
    private void openCustomer(ActionEvent event) throws IOException {
        showCustomerTableView();
        makeFadeIn(viewCustomer);
        viewHome.managedProperty().bind(viewHome.visibleProperty());
        viewHome.setVisible(false);
        viewOrder.managedProperty().bind(viewOrder.visibleProperty());
        viewOrder.setVisible(false);
        viewStore.managedProperty().bind(viewStore.visibleProperty());
        viewStore.setVisible(false);
        viewEmployee.managedProperty().bind(viewEmployee.visibleProperty());
        viewEmployee.setVisible(false);
        viewProductSales.managedProperty().bind(viewProductSales.visibleProperty());
        viewProductSales.setVisible(false);
        homeMenu.setStyle("-fx-background-color: none");
        employeeMenu.setStyle("-fx-background-color: none");
        customerMenu.setStyle("-fx-background-color: #33cc94");
        storeMenu.setStyle("-fx-background-color: none");
        productSalesMenu.setStyle("-fx-background-color: none");
        quitMenu.setStyle("-fx-background-color: none");
        viewCustomer.managedProperty().bind(viewCustomer.visibleProperty());
        viewCustomer.setVisible(true);

    }

    @FXML
    private void openProductSales(ActionEvent event) throws IOException {
        showProductSalesTableView();
        makeFadeIn(viewProductSales);
        viewHome.managedProperty().bind(viewHome.visibleProperty());
        viewHome.setVisible(false);
        viewOrder.managedProperty().bind(viewOrder.visibleProperty());
        viewOrder.setVisible(false);
        viewStore.managedProperty().bind(viewStore.visibleProperty());
        viewStore.setVisible(false);
        viewEmployee.managedProperty().bind(viewEmployee.visibleProperty());
        viewEmployee.setVisible(false);
        viewCustomer.managedProperty().bind(viewCustomer.visibleProperty());
        viewCustomer.setVisible(false);
        homeMenu.setStyle("-fx-background-color: none");
        employeeMenu.setStyle("-fx-background-color: none");
        customerMenu.setStyle("-fx-background-color: none");
        productSalesMenu.setStyle("-fx-background-color: #33cc94");
        storeMenu.setStyle("-fx-background-color: none");
        quitMenu.setStyle("-fx-background-color: none");

        viewProductSales.managedProperty().bind(viewProductSales.visibleProperty());
        viewProductSales.setVisible(true);
        customerMonth.setText(monthbestCustomer.toString());
        employeeMonth.setText(monthbestCustomer.toString());
        searchBestCustomer();
        searchBestEmployee();
        searchTotalSaleMonth();
    }

    @FXML
    public void searchBestCustomer() {
        ObservableList<Customer> customers = FXCollections.observableArrayList(Datasource.getInstance().queryCustomers());
        List<Integer> total = new ArrayList<>();
        for (int i = 0; i < customers.size(); i++) {
            int totalTemp = 0;
            ObservableList<CustomerBuy> customerBestBuysTotalMonth = FXCollections.observableArrayList(Datasource.getInstance().queryBestCustomerBuyMonth(customers.get(i).getCustomerId(), Integer.parseInt(customerMonth.getText())));
            for (int j = 0; j < customerBestBuysTotalMonth.size(); j++) {
                totalTemp += customerBestBuysTotalMonth.get(j).getTotalPrice();
            }
            total.add(totalTemp);
        }
        Integer totalMax = 0;
        String bestCustomerId = "";
        String bestCustomerName = "";
        for (int i = 0; i < total.size(); i++) {
            if (totalMax < total.get(i)) {
                totalMax = total.get(i);
                bestCustomerId = customers.get(i).getCustomerId();
                bestCustomerName = customers.get(i).getCustomerName();
            }
        }
        customerIdMonth.setText(bestCustomerId);
        customerNameMonth.setText(bestCustomerName);
        customerTotalPayMonth.setText(priceWithDecimal(totalMax));
    }

    @FXML
    public void searchBestEmployee() {
        ObservableList<User> employees = FXCollections.observableArrayList(Datasource.getInstance().queryUsers());
        List<Integer> total = new ArrayList<>();

        for (int i = 0; i < employees.size(); i++) {
            int totalTemp = 0;
            ObservableList<EmployeeSale> customerBestEmployeeTotalMonth = FXCollections.observableArrayList(Datasource.getInstance().queryBestEmployeeSaleMonth(employees.get(i).getUserId(), Integer.parseInt(employeeMonth.getText())));
            for (int j = 0; j < customerBestEmployeeTotalMonth.size(); j++) {
                totalTemp += customerBestEmployeeTotalMonth.get(j).getTotalPrice();
            }
            total.add(totalTemp);
        }
        Integer totalMax = 0;
        String bestEmployeeId = "";
        String bestEmployeeName = "";
        for (int i = 0; i < total.size(); i++) {
            if (totalMax < total.get(i)) {
                totalMax = total.get(i);
                bestEmployeeId = employees.get(i).getUserId();
                bestEmployeeName = employees.get(i).getFullName();
            }
        }
        employeeIdMonth.setText(bestEmployeeId);
        employeeNameMonth.setText(bestEmployeeName);
        employeeTotalSaleMonth.setText(priceWithDecimal(totalMax));
        searchTotalSaleMonth();
    }

    @FXML
    public void searchEmployeeWorking() {
        if (Datasource.getInstance().searchEmployeeById(employeeWorkingSearch.getText()) != null) {
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.initOwner(mainBorderPane.getScene().getWindow());
            ObservableList<WorkingUser> workingUsers = FXCollections.observableArrayList(Datasource.getInstance().queryWorkingUserById(employeeWorkingSearch.getText()));
            ObservableList<User> users = FXCollections.observableArrayList(Datasource.getInstance().queryUsersById(employeeWorkingSearch.getText()));
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/mkcoffee/views/EmployeeWorkingDialog.fxml"));
            try {
                dialog.getDialogPane().setContent(fxmlLoader.load());
                EmployeeWorkingController dialogController = fxmlLoader.getController();
                dialogController.myEmployeeSearch(employeeWorkingSearch.getText(), workingUsers, users);
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
            employeeWorkingSearch.clear();
            dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
            dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
            Optional<ButtonType> result = dialog.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {

            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Update Product");
            alert.setHeaderText("Product Update form");
            alert.setContentText("Please fill in product ID!");
            Optional<ButtonType> choose = alert.showAndWait();
        }
    }

    @FXML
    public void searchTotalSaleMonth() {
        ObservableList<ProductSales> bestProductSaleses = FXCollections.observableArrayList(Datasource.getInstance().queryBestSaleMonth(Integer.parseInt(employeeMonth.getText())));
        Integer total = 0;
        for (int i = 0; i < bestProductSaleses.size(); i++) {
            total += bestProductSaleses.get(i).getTotalPrice();
        }
        totalSaleMonth.setText(priceWithDecimal(total));
    }

    @FXML
    public void searchCustomerBuy() {
        if (Datasource.getInstance().searchCutomerById(findCustomerById.getText()) != null) {
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.initOwner(mainBorderPane.getScene().getWindow());
            ObservableList<CustomerBuy> customerBuys = FXCollections.observableArrayList(Datasource.getInstance().queryCustomerBuy(findCustomerById.getText()));
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/mkcoffee/views/SearchCustomerProductSales.fxml"));
            try {
                dialog.getDialogPane().setContent(fxmlLoader.load());
                SearchCustomerProductSalesController dialogController = fxmlLoader.getController();
                dialogController.myCustomerId(findCustomerById.getText(), customerBuys);
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
            findCustomerById.clear();
            dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
            dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
            Optional<ButtonType> result = dialog.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {

            }
        } else {
            alertWarning("Update Product", "Product Update form", "Please fill in product ID!");
        }
    }

    @FXML
    public void cancleFindCustomerBuy() {
        findCustomerById.clear();
    }

    @FXML
    public void searchEmployeeSale() {
        if (Datasource.getInstance().searchEmployeeById(findEmployeeSale.getText()) != null) {
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.initOwner(mainBorderPane.getScene().getWindow());
            ObservableList<EmployeeSale> employeeSale = FXCollections.observableArrayList(Datasource.getInstance().queryEmployeeSale(findEmployeeSale.getText()));
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/mkcoffee/views/SearchEmployeeProductSales.fxml"));
            try {
                dialog.getDialogPane().setContent(fxmlLoader.load());
                SearchEmployeeProductSalesController dialogController = fxmlLoader.getController();
                dialogController.myEmployeeId(findEmployeeSale.getText(), employeeSale);
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
            findEmployeeSale.clear();
            dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
            dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
            Optional<ButtonType> result = dialog.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {

            }
        } else {
            alertWarning("Update Product", "Product Update form", "Please fill in product ID!");
        }
    }

    @FXML
    public void cancleFindEmployeeSale() {
        findEmployeeSale.clear();
    }

    private Integer totalPay() {
        ObservableList<ProductSales> allProducts = orderTable.getItems();
//        allProducts = orderTable.getItems();
        Integer total = 0;
        for (int i = 0; i < allProducts.size(); i++) {
            total += allProducts.get(i).getTotalPrice();
        }
        return total;
    }

    @FXML
    private void searchInvoiceLater() {
        if (Datasource.getInstance().searchOrderInvoice(invoiceIdSearch.getText()) != null) {
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.initOwner(mainBorderPane.getScene().getWindow());
            ObservableList<ProductSales> productSaleses = FXCollections.observableArrayList(Datasource.getInstance().searchOrderInvoice(invoiceIdSearch.getText()));
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/mkcoffee/views/OrderInvoiceDialog.fxml"));
            try {
                dialog.getDialogPane().setContent(fxmlLoader.load());
                OrderInvoiceDialogController dialogController = fxmlLoader.getController();
                dialogController.myOrderInvoice(productSaleses);
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }

            invoiceIdSearch.clear();

            ButtonType Print = new ButtonType("Print", ButtonData.OK_DONE);
            ButtonType Cancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
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

    private void searchInvoiceLater(String invoiceIdSearch) {
        if (Datasource.getInstance().searchOrderInvoice(invoiceIdSearch) != null) {
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.initOwner(mainBorderPane.getScene().getWindow());
            ObservableList<ProductSales> productSaleses = FXCollections.observableArrayList(Datasource.getInstance().searchOrderInvoice(invoiceIdSearch));
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/mkcoffee/views/OrderInvoiceDialog.fxml"));
            try {
                dialog.getDialogPane().setContent(fxmlLoader.load());
                OrderInvoiceDialogController dialogController = fxmlLoader.getController();
                dialogController.myOrderInvoice(productSaleses);
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }

//            invoiceIdSearch.clear();
            ButtonType Print = new ButtonType("Print", ButtonData.OK_DONE);
            ButtonType Cancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
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

    private void printInvoice() {
        String srcFile = "/Users/maikhanh/NetBeansProjects/MKCoffee/src/mkcoffee/invoice/Invoice.jrxml";
        String datetime;
        try {
            JasperReport jr = JasperCompileManager.compileReport(srcFile);
            HashMap<String, Object> para = new HashMap<>();
            para.put("employee", employeeId.getText());
            para.put("date", time.getText());
            datetime = time.getText();
            para.put("totalInvoice", totalPay.getText());
            para.put("invoiceId", Datasource.getInstance().searchOrderIDByDateTime());

            ObservableList<ProductSales> productSaleses = orderTable.getItems();
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
            Logger.getLogger(MainViewController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }
    boolean checkCustomerPhone = false;

    @FXML
    public void checkCustomerPhone() {
        if (customerPhoneProductSale.getText().isEmpty()) {
            alertWarning("Customer Check", "Customer Check", "Phone number is empty");
        } else {
            List<Customer> customers = Datasource.getInstance().queryCustomers();
            String checkCustomerName = "";
            for (Customer customer : customers) {
                if ((customerPhoneProductSale.getText().equalsIgnoreCase(customer.getCustomerPhone()))) {
                    checkCustomerPhone = false;
                    checkCustomerName = customer.getCustomerName();
                    break;
                } else {
                    checkCustomerPhone = true;
                }
            }

            if (checkCustomerPhone) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Customer Information");
                alert.setHeaderText("Customer Check");
                alert.setContentText("Customer not make register yet!\nDo you want to register?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && (result.get() == ButtonType.OK)) {
                    registerCustomer();
                    checkCustomerPhone = false;
                } else {
                    customerPhoneProductSale.clear();
                }

            } else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Customer Information");
                alert.setHeaderText("Customer Check");
                alert.setContentText("Welcome " + checkCustomerName);
                Optional<ButtonType> result = alert.showAndWait();
            }

        }
    }

    @FXML
    private void searchEmployee() {
        if (employeeNameSearch.getText().isEmpty()) {
            showEmployeeTableView();
        } else {
            ObservableList<User> users = FXCollections.observableArrayList(Datasource.getInstance().searchEmployeeByName(employeeNameSearch.getText()));
            employeeTable.setItems(users);
        }
    }

    @FXML
    private void searchCustomer() {
        if (customerNameSearch.getText().isEmpty()) {
            showCustomerTableView();
        } else {
            ObservableList<Customer> customers = FXCollections.observableArrayList(Datasource.getInstance().searchCustomerByName(customerNameSearch.getText()));
            customerTable.setItems(customers);
        }
    }

    @FXML
    private void searchProduct() {
        if (productNameSearch.getText().isEmpty()) {
            showProductTreeTableView();
        } else {
            ObservableList<Product> products = FXCollections.observableArrayList(Datasource.getInstance().searchProductByName(productNameSearch.getText()));
            final TreeItem<Product> root = new RecursiveTreeItem<>(products, RecursiveTreeObject::getChildren);
            storeTable.setRoot(root);

        }
    }
    @FXML
    private VBox contentArea;

    @FXML
    private void openStatistic() throws IOException {
        sellActive();
        imgHomeBtn.setImage(homeGray);
        homeMenu.setDisable(true);
        productSalesMenu.setDisable(true);
        employeeMenu.setDisable(true);
        customerMenu.setDisable(true);
        storeMenu.setDisable(true);
        homeActiveBoolean = false;
        nameEmployee = employeeName.getText();
        idEmployee = employeeId.getText();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/mkcoffee/views/StatisticView.fxml"));
        Parent root = (Parent) loader.load();
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(root);
        StatisticViewController secController = loader.getController();
        secController.myEmployeeId(nameEmployee, idEmployee, time.getText(), monthbestCustomer.toString());

    }

    @FXML
    public void quitProgram() {
        Datasource.getInstance().insertUserWorking(employeeId.getText(), dateStart, time.getText());
        Platform.exit();
    }

    public void clearField() {
        //clear text field
        customerPhoneProductSale.clear();
        epressoSmallQuantity.clear();
        epressoMediumQuantity.clear();
        caramelSmallQuantity.clear();
        caramelMediumQuantity.clear();
        whiteCoffeeSmallQuantity.clear();
        whiteCoffeeMediumQuantity.clear();
        cappuccinoSmallQuantity.clear();
        cappuccinoMediumQuantity.clear();
        blackMilkSmallQuantity.clear();
        blackMilkMediumQuantity.clear();
        //clear boolean
        epressoSmallBoolean = false;
        epressoMediumBoolean = false;
        caramelSmallBoolean = false;
        caramelMediumBoolean = false;
        whiteCoffeeSmallBoolean = false;
        whiteCoffeeMediumBoolean = false;
        cappuccinoSmallBoolean = false;
        cappuccinoMediumBoolean = false;
        blackMilkSmallBoolean = false;
        blackMilkMediumBoolean = false;
        epressoMilkSmallBoolean = false;
        epressoMilkMediumBoolean = false;

        blackTeaSmallBoolean = false;
        blackTeaMediumBoolean = false;
        flavouredTeaSmallBoolean = false;
        flavouredTeaMediumBoolean = false;
        raspberryMacchiatoSmallBoolean = false;
        raspberryMacchiatoMediumBoolean = false;
        matchaLatteSmallBoolean = false;
        matchaLatteMediumBoolean = false;
        macchiatoTeaSmallBoolean = false;
        macchiatoTeaMediumBoolean = false;
        peachTeaManiaSmallBoolean = false;
        peachTeaManiaMediumBoolean = false;

        raspberrySodaSmallBoolean = false;
        raspberrySodaMediumBoolean = false;
        berrySmoothieSmallBoolean = false;
        berrySmoothieMediumBoolean = false;
        mangoSmoothieSmallBoolean = false;
        mangoSmoothieMediumBoolean = false;
        greenAppleSmallBoolean = false;
        greenAppleMediumBoolean = false;
        berrySodaMediumBoolean = false;
        berrySodaSmallBoolean = false;
        mojitoLemonSmallBoolean = false;
        mojitoLemonMediumBoolean = false;

        mochaIceSmallBoolean = false;
        mochaIceMediumBoolean = false;
        caramelIceSmallBoolean = false;
        caramelIceMediumBoolean = false;
        cookieIceSmallBoolean = false;
        cookieIceMediumBoolean = false;
        iceChocolateSmallBoolean = false;
        iceChocolateMediumBoolean = false;
        doubleChocolateSmallBoolean = false;
        doubleChocolateMediumBoolean = false;
        vanillaIceSmallBoolean = false;
        vanillaIceMediumBoolean = false;
    }

    public void resetAddButton(ProductSales item) {
        if (item.getProductName().equalsIgnoreCase("Coffee Epresso") && item.getKind().equalsIgnoreCase("Small")) {
            epressoSmallBoolean = false;
        }
        if (item.getProductName().equalsIgnoreCase("Coffee Epresso") && item.getKind().equalsIgnoreCase("Medium")) {
            epressoMediumBoolean = false;
        }
        if (item.getProductName().equalsIgnoreCase("Caramel Macchiato") && item.getKind().equalsIgnoreCase("Small")) {
            caramelSmallBoolean = false;
        }
        if (item.getProductName().equalsIgnoreCase("Caramel Macchiato") && item.getKind().equalsIgnoreCase("Medium")) {
            caramelMediumBoolean = false;
        }
        if (item.getProductName().equalsIgnoreCase("White Coffee") && item.getKind().equalsIgnoreCase("Small")) {
            whiteCoffeeSmallBoolean = false;
        }
        if (item.getProductName().equalsIgnoreCase("White Coffee") && item.getKind().equalsIgnoreCase("Medium")) {
            whiteCoffeeMediumBoolean = false;
        }
        if (item.getProductName().equalsIgnoreCase("Cappuccino Coffee") && item.getKind().equalsIgnoreCase("Small")) {
            cappuccinoSmallBoolean = false;
        }
        if (item.getProductName().equalsIgnoreCase("Cappuccino Coffee") && item.getKind().equalsIgnoreCase("Medium")) {
            cappuccinoMediumBoolean = false;
        }
        if (item.getProductName().equalsIgnoreCase("Black Milk Coffee") && item.getKind().equalsIgnoreCase("Small")) {
            blackMilkSmallBoolean = false;
        }
        if (item.getProductName().equalsIgnoreCase("Black Milk Coffee") && item.getKind().equalsIgnoreCase("Medium")) {
            blackMilkMediumBoolean = false;
        }
        if (item.getProductName().equalsIgnoreCase("Epresso Milk") && item.getKind().equalsIgnoreCase("Small")) {
            epressoMilkSmallBoolean = false;
        }
        if (item.getProductName().equalsIgnoreCase("Epresso Milk") && item.getKind().equalsIgnoreCase("Medium")) {
            epressoMilkMediumBoolean = false;
        }
        if (item.getProductName().equalsIgnoreCase("Black Tea") && item.getKind().equalsIgnoreCase("Small")) {
            blackTeaSmallBoolean = false;
        }
        if (item.getProductName().equalsIgnoreCase("Black Tea") && item.getKind().equalsIgnoreCase("Medium")) {
            blackTeaMediumBoolean = false;
        }
        if (item.getProductName().equalsIgnoreCase("Flavoured Tea") && item.getKind().equalsIgnoreCase("Small")) {
            flavouredTeaSmallBoolean = false;
        }
        if (item.getProductName().equalsIgnoreCase("Flavoured Tea") && item.getKind().equalsIgnoreCase("Medium")) {
            flavouredTeaMediumBoolean = false;
        }
        if (item.getProductName().equalsIgnoreCase("Raspberry Macchiato") && item.getKind().equalsIgnoreCase("Small")) {
            raspberryMacchiatoSmallBoolean = false;
        }
        if (item.getProductName().equalsIgnoreCase("Raspberry Macchiato") && item.getKind().equalsIgnoreCase("Medium")) {
            raspberryMacchiatoMediumBoolean = false;
        }
        if (item.getProductName().equalsIgnoreCase("Matcha Latte") && item.getKind().equalsIgnoreCase("Small")) {
            matchaLatteSmallBoolean = false;
        }
        if (item.getProductName().equalsIgnoreCase("Matcha Latte") && item.getKind().equalsIgnoreCase("Medium")) {
            matchaLatteMediumBoolean = false;
        }
        if (item.getProductName().equalsIgnoreCase("Macchiato Tea") && item.getKind().equalsIgnoreCase("Small")) {
            macchiatoTeaSmallBoolean = false;
        }
        if (item.getProductName().equalsIgnoreCase("Macchiato Tea") && item.getKind().equalsIgnoreCase("Medium")) {
            macchiatoTeaMediumBoolean = false;
        }
        if (item.getProductName().equalsIgnoreCase("Peach Tea Mania") && item.getKind().equalsIgnoreCase("Small")) {
            peachTeaManiaSmallBoolean = false;
        }
        if (item.getProductName().equalsIgnoreCase("Peach Tea Mania") && item.getKind().equalsIgnoreCase("Medium")) {
            peachTeaManiaMediumBoolean = false;
        }
        if (item.getProductName().equalsIgnoreCase("Raspberry Soda") && item.getKind().equalsIgnoreCase("Small")) {
            raspberrySodaSmallBoolean = false;
        }
        if (item.getProductName().equalsIgnoreCase("Raspberry Soda") && item.getKind().equalsIgnoreCase("Medium")) {
            raspberrySodaMediumBoolean = false;
        }
        if (item.getProductName().equalsIgnoreCase("Berry Smoothie") && item.getKind().equalsIgnoreCase("Small")) {
            berrySmoothieSmallBoolean = false;
        }
        if (item.getProductName().equalsIgnoreCase("Berry Smoothie") && item.getKind().equalsIgnoreCase("Medium")) {
            berrySmoothieMediumBoolean = false;
        }
        if (item.getProductName().equalsIgnoreCase("Mango Smoothie") && item.getKind().equalsIgnoreCase("Small")) {
            mangoSmoothieSmallBoolean = false;
        }
        if (item.getProductName().equalsIgnoreCase("Mango Smoothie") && item.getKind().equalsIgnoreCase("Medium")) {
            mangoSmoothieMediumBoolean = false;
        }
        if (item.getProductName().equalsIgnoreCase("Green Apple") && item.getKind().equalsIgnoreCase("Small")) {
            greenAppleSmallBoolean = false;
        }
        if (item.getProductName().equalsIgnoreCase("Green Apple") && item.getKind().equalsIgnoreCase("Medium")) {
            greenAppleMediumBoolean = false;
        }
        if (item.getProductName().equalsIgnoreCase("Berry Soda") && item.getKind().equalsIgnoreCase("Small")) {
            berrySodaSmallBoolean = false;
        }
        if (item.getProductName().equalsIgnoreCase("Berry Soda") && item.getKind().equalsIgnoreCase("Medium")) {
            berrySodaMediumBoolean = false;
        }
        if (item.getProductName().equalsIgnoreCase("Mojito Lemon") && item.getKind().equalsIgnoreCase("Small")) {
            mojitoLemonSmallBoolean = false;
        }
        if (item.getProductName().equalsIgnoreCase("Mojito Lemon") && item.getKind().equalsIgnoreCase("Medium")) {
            mojitoLemonMediumBoolean = false;
        }
        if (item.getProductName().equalsIgnoreCase("Mocha Ice Blended") && item.getKind().equalsIgnoreCase("Small")) {
            mochaIceSmallBoolean = false;
        }
        if (item.getProductName().equalsIgnoreCase("Mocha Ice Blended") && item.getKind().equalsIgnoreCase("Medium")) {
            mochaIceMediumBoolean = false;
        }
        if (item.getProductName().equalsIgnoreCase("Caramel Ice Blended") && item.getKind().equalsIgnoreCase("Small")) {
            caramelIceSmallBoolean = false;
        }
        if (item.getProductName().equalsIgnoreCase("Caramel Ice Blended") && item.getKind().equalsIgnoreCase("Medium")) {
            caramelIceMediumBoolean = false;
        }
        if (item.getProductName().equalsIgnoreCase("Cookie Ice Blend") && item.getKind().equalsIgnoreCase("Small")) {
            cookieIceSmallBoolean = false;
        }
        if (item.getProductName().equalsIgnoreCase("Cookie Ice Blend") && item.getKind().equalsIgnoreCase("Medium")) {
            cookieIceMediumBoolean = false;
        }
        if (item.getProductName().equalsIgnoreCase("Ice Chocotale Mocha") && item.getKind().equalsIgnoreCase("Small")) {
            iceChocolateSmallBoolean = false;
        }
        if (item.getProductName().equalsIgnoreCase("Ice Chocotale Mocha") && item.getKind().equalsIgnoreCase("Medium")) {
            iceChocolateMediumBoolean = false;
        }
        if (item.getProductName().equalsIgnoreCase("Double Chocolate Ice Blended") && item.getKind().equalsIgnoreCase("Small")) {
            doubleChocolateSmallBoolean = false;
        }
        if (item.getProductName().equalsIgnoreCase("Double Chocolate Ice Blended") && item.getKind().equalsIgnoreCase("Medium")) {
            doubleChocolateMediumBoolean = false;
        }
        if (item.getProductName().equalsIgnoreCase("Vanilla Ice Blended ") && item.getKind().equalsIgnoreCase("Small")) {
            vanillaIceSmallBoolean = false;
        }
        if (item.getProductName().equalsIgnoreCase("Vanilla Ice Blended ") && item.getKind().equalsIgnoreCase("Medium")) {
            vanillaIceMediumBoolean = false;
        }
        totalPay.setText(priceWithDecimal(this.totalPay()));
    }

    private static String priceWithDecimal(Integer price) {
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        return formatter.format(price);
    }

}
