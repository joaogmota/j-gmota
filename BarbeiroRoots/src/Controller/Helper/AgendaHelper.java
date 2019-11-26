/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Helper;

import Model.Agendamento;
import Model.Cliente;
import Model.Servico;
import View.Agenda;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author igors
 */
public class AgendaHelper implements IHelper{
    
    private final Agenda view;
    

    public AgendaHelper(Agenda view) {
        this.view = view;
    }

    public void preencherTabela(ArrayList<Agendamento> agendamentos) {
        DefaultTableModel tableModel = (DefaultTableModel) view.getTableAgendamentos().getModel();
        tableModel.setNumRows(0);
        
        //percorrer a lista preenchendo
        for (Agendamento agendamento : agendamentos) {
            tableModel.addRow(new Object[]{
                
                agendamento.getId(),
                agendamento.getCliente().getNome(),
                agendamento.getServico().getDescricao(),
                agendamento.getValor(),
                agendamento.getDataFormatada(),
                agendamento.getHoraFormatada(),
                agendamento.getObservacao()
            });
        }
        
    }

    public void preencherCliente(ArrayList<Cliente> clientes) {

        DefaultComboBoxModel comboBoxModel = (DefaultComboBoxModel) view.getJComboboxCliente().getModel();
        for (Cliente cliente : clientes) {
            comboBoxModel.addElement(cliente);
        }
    }

    public void preencherServico(ArrayList<Servico> servicos) {
          DefaultComboBoxModel comboBoxModel = (DefaultComboBoxModel) view.getJComboboxServico().getModel();
          for (Servico servico : servicos) {
            comboBoxModel.addElement(servico);
        }
    
    }

    public Servico obterSevico() {

        return (Servico)view.getJComboboxServico().getSelectedItem();
        
    }
    public Cliente obterCliente() {

        return (Cliente)view.getJComboboxCliente().getSelectedItem();
        
    }

    public void setarValor(float valor) {
       
        view.getTextValor().setText(valor+"");
    
    }

    @Override
    public Agendamento obterModelo() {
         //new Agendamento(Integer.SIZE, null, null, valor, null)
        String idString = view.getTextId().getText();
        Integer id = Integer.parseInt(idString);
        Cliente cliente = obterCliente();
        Servico servico = obterSevico();
        String valorString = view.getTextValor().getText();
        float valor = Float.parseFloat(valorString);
        String data = view.getTextFormatedData().getText();
        String hora = view.getTextFormatedHora().getText();
        String dataHora = data + " " + hora;
        String observacao = view.getTextObservacao().getText();
        
        Agendamento agendamento = new Agendamento(id, cliente, servico, valor, dataHora, observacao);
        return agendamento;
    }

    @Override
    public void limparTela() {

        view.getTextId().setText("0");
        view.getTextFormatedData().setText("");
        view.getTextFormatedHora().setText("");
        view.getTextObservacao().setText("");
    
    }
    
    
}
