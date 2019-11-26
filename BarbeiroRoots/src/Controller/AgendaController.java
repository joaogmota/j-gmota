/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Controller.Helper.AgendaHelper;
import Model.Agendamento;
import Model.Cliente;
import Model.DAO.AgendamentoDAO;
import Model.DAO.ClienteDAO;
import Model.DAO.ServicoDAO;
import Model.Servico;
import Servico.Correio;
import View.Agenda;
import View.MenuPrincipal;
import java.util.ArrayList;

/**
 *
 * @author igors
 */
public class AgendaController {
    private final Agenda view;
    private final AgendaHelper helper;

    public AgendaController(Agenda view) {
        this.view = view;
        this.helper = new AgendaHelper(view);
    }

    public void voltarParaMenu(){
        MenuPrincipal menu = new MenuPrincipal();
        menu.setVisible(true);
        this.view.dispose();
     
    }
    
    public void atualizaTabela(){
        //Buscar lista com agendamentos do BD
        AgendamentoDAO agendamentoDAO = new AgendamentoDAO();
        ArrayList<Agendamento> agendamentos = agendamentoDAO.selectAll();
        
        //Exibir lista na view
        helper.preencherTabela(agendamentos);
        
    }

    
    public void atualizaCliene(){
        //Buscar Clientes no BD
        ClienteDAO clienteDAO = new ClienteDAO();
        ArrayList<Cliente> clientes = clienteDAO.selectAll();
        
        //Exibir na view
        helper.preencherCliente(clientes);
    }
    
    public void atualizaServico(){
        ServicoDAO servicoDAO = new ServicoDAO();
        ArrayList<Servico> servicos = new ServicoDAO().selectAll();
        
        helper.preencherServico(servicos);
    }
    
    public void atualizaValor(){
        Servico servico = helper.obterSevico();
        helper.setarValor(servico.getValor());
    }
    
    public void agendar(){
        //Buscar obj agendamento da tela
        Agendamento agendamento = helper.obterModelo();
        
        //Salvar obj no BD
        new AgendamentoDAO().insert(agendamento);
        
        Correio correio = new Correio();
        correio.NotificarPorEmail(agendamento);
        
        //Inserir na tabela
        atualizaTabela();
        helper.limparTela();
        
        
    }
}
