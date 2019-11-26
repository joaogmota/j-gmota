/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import View.Agenda;
import View.Login;
import View.MenuPrincipal;

/**
 *
 * @author igors
 */
public class MenuPrincipalController {
    
    private final MenuPrincipal view;

    public MenuPrincipalController(MenuPrincipal view) {
        this.view = view;
    }
    
    public void navegarParaAgenda(){
        Agenda agenda = new Agenda();
        agenda.setVisible(true);
        view.dispose();
    }
    
    public void logoffUsuario(){
        Login login = new Login();
        login.setVisible(true);
        this.view.dispose();
    }
    
}
