package program;

import entities.Usuario;
import ui.CMS;

public class Main {
	
    public static void main(String[] args) {
    	
        Usuario currentUser = null;
        CMS ui = new CMS();
        
        while (true) {
            if (currentUser == null) {
                currentUser = ui.iniciarMenu();
            } else {
                currentUser = ui.menulogado(currentUser);
            }
        }
    }
}