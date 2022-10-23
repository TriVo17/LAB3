/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.CDs;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import tool.MyTool;
import static tool.MyTool.sc;

public class CDDaoImpl implements CDDao {

    private List<CDs> CDs;
    private CDs[] list;
    private int numOfCD;
    private final int MAX = 2;
    String filename = "CD.dat";

    public CDDaoImpl() {
        CDs = new ArrayList<>();
        list = new CDs[MAX];
        numOfCD = 0;
    }

    public boolean searchFileCDID(int ID) {
        List<CDs> list = new ArrayList<>();
        list = MyTool.loadFromFile(filename, list);
        for (CDs CD : list) {
            if (CD.getID() == ID) {
                return true;
            }
        }
        return false;
    }

    public boolean searchFileCDName(String CDTitle) {
        List<CDs> list = new ArrayList<>();
        list = MyTool.loadFromFile(filename, list);
        for (CDs CD : list) {
            if (CD.getTitle().equalsIgnoreCase(CDTitle)) {
                return true;
            }
        }
        return false;
    }

    public void getNewCDs() {
        if (numOfCD == 0) {
            System.out.println("");
            System.out.println("    Empty List.");
        }
        for (int i = 0; i < numOfCD; i++) {
            list[i].print();
        }
    }

    public List<CDs> getAllCDs() {
        CDs = MyTool.loadFromFile(filename, CDs);
        if (CDs.isEmpty()) {
            System.out.println("");
            System.out.println("    Empty File.");
        }
        Collections.sort(CDs);
        return CDs;
    }

    public List<CDs> getCDByName(String name) {
        CDs = MyTool.loadFromFile(filename, CDs);
        if (CDs.isEmpty()) {
            System.out.println("");
            System.out.println("    Empty File.");
        }
        List<CDs> list = new ArrayList<>();
        for (CDs CD : CDs) {
            if (CD != null && CD.getTitle().toLowerCase().contains(name)) {
                list.add(CD);
            }
        }
        return list;
    }

    public CDs updateID(int ID) {
        if (numOfCD == 0) {
            return null;
        }
        for (CDs CD : list) {
            if (CD.getID() == ID) {
                return CD;
            }
        }
        return null;
    }

    public int SearchListID(CDs CD) {
        for (int i = 0; i < numOfCD; i++) {
            if (list[i] == CD) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void addCD() {

        int ID;
        String name;
        String type;
        String title;
        double price;
        int year;
        boolean check = true;
        System.out.println("    Enter New CD Details");
        do {
            ID = MyTool.getInt("Enter ID(1-10000): ", 1, 10000);
            if (searchFileCDID(ID)) {
                System.out.println("    ID is Duplicated!");
            } else {
                check = false;
            }
        } while (check);
        MyTool.sc.nextLine();
        do {
            check = true;
            System.out.print("Enter CD Title: ");
            title = MyTool.sc.nextLine().trim();
            if (searchFileCDName(title)) {
                System.out.println("    CD is Duplicated!");
            } else if (title.isEmpty()) {
                check = true;
            } else {
                check = false;
            }
        } while (check);
        do {
            check = true;
            System.out.print("Enter Collection Name(Game/Movie/Music): ");
            name = MyTool.sc.nextLine().trim();
            if (name.isEmpty()) {
                check = true;
            } else if (name.equalsIgnoreCase("Game")) {
                check = false;
            } else if (name.equalsIgnoreCase("Movie")) {
                check = false;
            } else if (name.equalsIgnoreCase("Music")) {
                check = false;
            } else {
                check = true;
            }
        } while (check);
        do {
            check = true;
            System.out.print("Enter CD Type(Audio/Video): ");
            type = MyTool.sc.nextLine().trim();
            if (type.isEmpty()) {
                check = true;
            } else if (type.equalsIgnoreCase("Audio")) {
                check = false;
            } else if (type.equalsIgnoreCase("Video")) {
                check = false;
            } else {
                check = true;
            }
        } while (check);
        price = MyTool.getDouble("Enter Price(0-10000): ", 0, 10000);
        year = MyTool.getInt("Enter Year of Released(1900-2022): ", 1900, 2022);
        if (numOfCD >= MAX) {
            System.out.println("");
            System.out.println("    List is Full, Cannot Add More CD.");
        } else {
            list[numOfCD] = new CDs(ID, title, name, type, price, year);
            numOfCD++;
            System.out.println("");
            System.out.println("    New CD Has Been Add! ");
        }
    }

    @Override
    public void updateCD(CDs CD) {
        String newTitle = "";
        String newName;
        String newType = "";
        double newPrice;
        int newYear;
        boolean check = true;
        MyTool.sc.nextLine();
        System.out.print("New Title: ");
        newTitle = MyTool.sc.nextLine().trim();
        if (!newTitle.isEmpty()) {
            CD.setTitle(newTitle);
        }
        do {
            check = true;
            System.out.print("New Collection Name(Game/Movie/Music): ");
            newName = MyTool.sc.nextLine().trim();
            if (newName.isEmpty()) {
                check = false;
            } else if (newName.equalsIgnoreCase("Game")) {
                CD.setName(newName);
                check = false;
            } else if (newName.equalsIgnoreCase("Movie")) {
                CD.setName(newName);
                check = false;
            } else if (newName.equalsIgnoreCase("Music")) {
                CD.setName(newName);
                check = false;
            } else {
                check = true;
            }
        } while (check);
        do {
            check = true;
            System.out.print("New CD Type(Audio/Video): ");
            newType = MyTool.sc.nextLine().trim();
            if (newType.isEmpty()) {
                check = false;
            } else if (newType.equalsIgnoreCase("Audio")) {
                CD.setType(newType);
                check = false;
            } else if (newType.equalsIgnoreCase("Video")) {
                CD.setType(newType);
                check = false;
            } else {
                check = true;
            }
        } while (check);
        do {
            try {
                System.out.print("New Price(0-10000): ");
                String newPrice2 = MyTool.sc.nextLine().trim();
                if (!newPrice2.isEmpty()) {
                    newPrice = Double.parseDouble(newPrice2);
                    if (newPrice >=0 && newPrice<=10000){
                        CD.setPrice(newPrice);
                        check = false;
                    }
                }
                check = false;
            } catch (Exception e) {
                System.out.println("Invalid Input, Price is Unchanged!");
                check = false;
            }
        } while (check);

        do {
            try {
                System.out.print("New Year(1900-2022): ");
                String newYear2 = MyTool.sc.nextLine().trim();
                if (!newYear2.isEmpty()) {
                    newYear = Integer.parseInt(newYear2);
                    if (newYear >= 1900 && newYear <= 2022) {
                        CD.setYear(newYear);
                        check = false;
                    }
                }
                check = false;
            } catch (Exception e) {
                System.out.println("Invalid Input, Year is Unchanged!");
                check = false;
            }
        } while (check);
        System.out.println("");
        System.out.println("    Update Complete!");
    }

    @Override

    public void deleteCD(CDs CD) {
        int pos = SearchListID(CD);
        if (pos >= 0 && pos < numOfCD) {
            for (int i = pos; i < (numOfCD - 1); i++) {
                list[i] = list[i + 1];
            }
            numOfCD--;
            System.out.println("");
            System.out.println("    CD is Deleted!");
        }
    }

    @Override
    public void saveFile() {
        MyTool.saveToFile(list, numOfCD);
        System.out.println("");
        System.out.println("New CD(s) has been saved to the file.");
    }

}
