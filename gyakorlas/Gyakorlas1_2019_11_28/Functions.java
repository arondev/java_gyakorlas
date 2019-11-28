package Gyakorlas1_2019_11_28;

//import static Gyakorlas1_2019_11_28.Main.sportagLista;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class Functions {    
    static final int INFO_MSG = 0;
    static final int WARN_MSG = 1;
    static final int ERROR_MSG = 2;
    static final int DEBUG_MSG = 3;
    
    static void showMsg(int msgType, String msgTitle, String msgText) {
        JOptionPane.showMessageDialog(null, msgText, msgTitle, msgType);
    }
    
    static void systemMsg(int msgType, String msgText) {
        switch(msgType) {
            case 0: {
                System.out.printf("[I] %s\n", msgText);
                break;
            } case 1: {
                System.out.printf("[W] %s\n", msgText);
                break;
            } case 2: {
                System.out.printf("[E] %s\n", msgText);
                break;
            } case 3: {
                if(Main.debug) {
                    System.out.printf("[D] %s\n", msgText);
                    break;
                }
            } default: {
                System.out.println("[E] Invalid msgType at systemMsg function!");
            }
        }
    }
    
    static void readFile(String fileName) {
        systemMsg(INFO_MSG, String.format("Fájl beolvasása megkezdve.. (%s)", fileName));
        File f = new File(fileName);
        try(Scanner sc = new Scanner(f)) {
            String line;
            String [] elements;
            while(sc.hasNextLine()) {
                line = sc.nextLine();
                elements = line.split(";");
                Datastores.sportagLista.add(new Sportag(elements[0]));
                for(int i = 1; i <= 16; i++) {
                    Datastores.sportagLista.get(Datastores.sportagLista.size() - 1).donto[i-1] = Integer.parseInt(elements[i]);
                }
            }
            systemMsg(INFO_MSG, String.format("Fájl beolvasva! (%s)", fileName));
        } catch(FileNotFoundException fnfe) {
            systemMsg(ERROR_MSG, String.format("%s", fnfe.getMessage()));
        }
    }
}

