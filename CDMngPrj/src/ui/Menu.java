/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import tool.MyTool;

public class Menu extends Vector <String> {
    
    List<String> list = new ArrayList<String>();
   
    public Menu() {
        super();
    }
    
    public Menu(String[] items) {
        super();
        for (String item : items) this.add(item);
    }

    
   public int getChoice(String title) {
        int choice=0;
        System.out.println("");
        System.out.println("-----------------------------------------");
        System.out.println("             " + title);
        System.out.println("-----------------------------------------");
        for(int i=0;i<this.size();i++){
            System.out.println(i+1+"- "+this.get(i));
        }
        System.out.println("Others for quit");
        System.out.println("-----------------------------------------");
        try {
            System.out.print("Choose {1..6}: ");
            choice = Integer.parseInt(MyTool.sc.next());
        } catch (Exception e) {
        }
        return choice;
    }
    
    public int getChoice2(String title2) {
        int choice2 = 0;
        System.out.println("");
        System.out.println("-----------------------------------------");
        System.out.println("               " + title2);
        System.out.println("-----------------------------------------");
        for (int i=0; i<list.size(); i++) {
            System.out.println((i+1) + "- " + list.get(i));   
        }
        System.out.println("Others for quit");
        System.out.println("-----------------------------------------");
        try {
            System.out.print("Choose {1..2}: ");
            choice2 = Integer.parseInt(MyTool.sc.next());
        } catch (Exception e) {
        }
        return choice2;
    }
}
