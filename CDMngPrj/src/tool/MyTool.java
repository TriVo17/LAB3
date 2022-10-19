/*
 * Class MyTool: class for validating input and inputing data using a condition
 * Date format: y: year, M: month in year, d: day in month
 *              Separators: - / : but they are not mixed
 *              Example: yyyy/MM/dd   dd:MM:yyyy   MM/dd/yyyy:
 * Regular expression for pattern
 *    Phone no 10 or 11 digits: "\\d{10}|\\d{11}"
 *    Phone no 10 to 11 digits: "\\d{10,11}"
 *    ID format X0000 : "X\\d{4}"
 *    ID format X0000 or M000: "X\\d{4}|M\\d{3}"
 */

package tool;


import model.CDs;
import java.sql.Date; //containing year, month, day only
import java.text.SimpleDateFormat; //for converting string <--> Date
import java.util.Scanner; //for input data
import java.io.File; //for checking a file
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter; //classes for writing data to a text file
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList; //class for a list
import java.util.List; //Interface for a list
import java.text.ParseException; //exception when parsing data from a string


public class MyTool {
    
    public static final String filename = "CD.dat";
    public static final Scanner sc = new Scanner(System.in);
    
    public static int getInt(String welcome, int min, int max) {
        boolean check = true;
        int number = 0;
        do {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.print(welcome);
                number = Integer.parseInt(sc.nextLine());
                check = false;
            } catch (Exception e) {
                
            }
        } while (check || number > max || number < min);
        return number;
    }
    
    public static double getDouble(String welcome, int min, int max) {
        boolean check = true;
        double number = 0;
        do {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.print(welcome);
                number = Double.parseDouble(sc.nextLine());
                check = false;
            } catch (Exception e) {
            }
        } while (check || number > max || number < min);
        return number;
    }
    
    public static String readNonBlank(String message) {
        String input = "";
        do {            
            System.out.print(message);
            input = sc.nextLine().trim();
        } while (input.isEmpty());
        return input;
    }
    
    public static List<CDs> loadFromFile(String fName, List<CDs> list) {
        if(list.size()>0) list.clear();
        FileInputStream fi = null;
        ObjectInputStream fo = null;
        try {
            fi = new FileInputStream(fName);
            fo = new ObjectInputStream(fi);
            CDs obj;
            while (true) {
                obj = (CDs)(fo.readObject());
                list.add(obj);
            }
        } catch (Exception e) {
//            System.out.println(e);
        } finally {
            if (fi != null) {
                try {
                    fi.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fo != null) {
                try {
                    fo.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }
    
    //check if the file has the object saved in or not
    public static boolean hasObject(String filePath) {
        FileInputStream fi;
        boolean check = true;
        try {
            fi = new FileInputStream(filePath);
            ObjectInputStream fo = new ObjectInputStream(fi);
            if (fo.readObject() == null) {
                    check = false;
            }
            fo.close();
        } catch (FileNotFoundException e) {
            check = false;
        } catch (IOException e) {
            check = false;
        } catch (ClassNotFoundException e) {
            check = false;
            e.printStackTrace();
        }
        return check;
    }
    
    public static void saveToFile(CDs[] list, int numOfItem) {
        FileOutputStream fi = null;
        ObjectOutputStream fo = null;
        try {
            if (!hasObject(filename)) {
                fi = new FileOutputStream(filename);
                fo = new ObjectOutputStream(fi);
            } else {
                fi = new FileOutputStream(filename, true);
                fo = new ObjectOutputStream(fi) {
                    @Override
                    protected void writeStreamHeader() throws IOException {
                        reset();
                    }
                };
            }
            for (int i=0; i<numOfItem; i++) {
                fo.writeObject(list[i]);             
            }
        } catch(Exception e) {
            System.out.println(e);
        } finally {
            if (fi != null) {
                try {
                    fi.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fo != null) {
                try {
                    fo.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    } 
    
} 
