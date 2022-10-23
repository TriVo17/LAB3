/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import dao.CDDao;
import dao.CDDaoImpl;
import java.util.ArrayList;
import java.util.List;
import model.CDs;
import tool.MyTool;

public class CDManager {

    public static void main(String[] args) {
        String[] options = {"Add new CD to the catalog",
            "Search CD by CD title",
            "Display the catalog",
            "Update CD",
            "Save the catalog to file",
            "Print list of CDs from file"
        };
        Menu mnu = new Menu(options);
        int choice = 0;
        int choice2 = 0;
        String response, inputname;
        int inputID;
        CDs CD;
        boolean changed = false;
        CDDao CDDao = new CDDaoImpl();
        do {

            choice = mnu.getChoice("CD MANAGEMENT");
            switch (choice) {
                case 1:
                    CDDao.addCD();
                    changed = true;
                    break;
                case 2:
                    MyTool.sc.nextLine();
                    inputname = MyTool.readNonBlank("Enter CD Title: ");
                    inputname = inputname.toLowerCase();
                    List<CDs> list = new ArrayList<>();
                    list = CDDao.getCDByName(inputname);
                    if (list.isEmpty()) {
                        System.out.println("    No CD Found!");
                    } else {
                        for (CDs CDs : list) {
                            System.out.println(CDs.getID() + ", "
                                    + CDs.getTitle() + ", " + CDs.getName()
                                    + ", " + CDs.getType() + ", " + CDs.getPrice()
                                    + ", " + CDs.getYear());
                        }
                    }
                    break;
                case 3:
                    CDDao.getNewCDs();
                    break;
                case 4:
                    boolean quit = false;
                    do {
                        mnu.list.clear();
                        mnu.list.add("Update a CD");
                        mnu.list.add("Delete a CD");
                        choice2 = mnu.getChoice2("UPDATE CDs");
                        switch (choice2) {
                            case 1:
                                System.out.print("Enter ID: ");
                                inputID = Integer.parseInt(MyTool.sc.next());
                                CD = CDDao.updateID(inputID);
                                if (CD != null) {
                                    CDDao.updateCD(CD);
                                } else {
                                    System.out.println("");
                                    System.out.println("    No CD Found!");
                                }
                                changed = true;
                                break;
                            case 2:
                                System.out.print("Enter ID: ");
                                inputID = Integer.parseInt(MyTool.sc.next());
                                CD = CDDao.updateID(inputID);
                                if (CD != null) {
                                    CDDao.deleteCD(CD);
                                } else {
                                    System.out.println("");
                                    System.out.println("    No CD Found!");
                                }
                                changed = true;
                                break;
                            default:
                                quit = true;
                        }
                    } while (!quit);
                    break;
                case 5:
                    CDDao.saveFile();
                    changed = false;
                    break;
                case 6:
                    for (CDs items : CDDao.getAllCDs()) {
                        if (items != null) {
                            items.print();
                        }
                    }
                    break;
                default:
                    if (changed) {
                        boolean check = true;
                        do {
                            System.out.print("Save changes Y/N? ");
                            response = MyTool.sc.next().toUpperCase();
                            if (response.startsWith("Y")) {
                                CDDao.saveFile();
                                check = false;
                            } else if (response.startsWith("N")) {
                                check = false;
                            } else {
                                check = true;
                            }
                        } while (check);
                    }
            }
        } while (choice > 0 && choice < 7);
        System.out.println("Goodbye.");
    }
}
