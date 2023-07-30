/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.lutfi.ptjalintestapplication;

import id.co.lutfi.ptjalintestapplication.alert.Alert;
import id.co.lutfi.ptjalintestapplication.apitester.Tester;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Lutfi
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("******* Jalin Appication Test ********");
        run();
    }

    public static void run() {
        System.out.println("1. Read File And Send Alert");
        System.out.println("2: Api Tester");
        System.out.println("Pilih program: ");

        Scanner scan = new Scanner(System.in);
        int menu = scan.nextInt();
        switch (menu) {
            case 1:
                runAlert();
                break;
            case 2:
                runAPITester();
                break;
            default:
                break;
        }
        
        run();
    }

    private static void runAlert() {
        new Alert().execute();
    }

    private static void runAPITester() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        new Tester().setVisible(true);
    }

}
