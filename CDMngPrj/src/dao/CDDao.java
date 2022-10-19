/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.CDs;
import java.util.List;

public interface CDDao {
    
    void getNewCDs();
    List<CDs> getAllCDs();
    List<CDs> getCDByName(String name);
    CDs updateID(int ID);
    int SearchListID(CDs CD);
    boolean searchFileCDID(int ID);
    boolean searchFileCDName(String CDTitle);
    
    
    void addCD();
    void updateCD(CDs CD);
    void deleteCD(CDs CD);
    void saveFile();
    
}
