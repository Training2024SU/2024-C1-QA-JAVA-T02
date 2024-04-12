package co.com.sofka;


import co.com.sofka.database.mysql.MySqlOperation;
import co.com.sofka.utils.menu.Menu;

public class Main {


    private static final MySqlOperation mySqlOperation = new MySqlOperation();
    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.displayMenu();
    }

}