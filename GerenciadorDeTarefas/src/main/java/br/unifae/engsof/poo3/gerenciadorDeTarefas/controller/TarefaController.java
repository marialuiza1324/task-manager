package br.unifae.engsof.poo3.gerenciadorDeTarefas.controller;

import br.unifae.engsof.poo3.gerenciadorDeTarefas.model.GerenciaTarefas;
import br.unifae.engsof.poo3.gerenciadorDeTarefas.model.Tarefa;
import br.unifae.engsof.poo3.gerenciadorDeTarefas.model.TarefaComPrazo;
import br.unifae.engsof.poo3.gerenciadorDeTarefas.model.TarefaSimples;

import java.time.LocalDate;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TarefaController {
    GerenciaTarefas gerencia_tarefas = new GerenciaTarefas();
    /**
    * Tenta cadastrar os dados
    * @param descricao: descrição da tarefa
    * @param prioridade: 1 a 5
    * @param data_criacao: criação da tarefa
    * @return : true se pode cadastrar
    **/
    public boolean create(String descricao, int prioridade, LocalDate data_criacao){
        System.out.println("Tarefa simples");
        return gerencia_tarefas.cadastrar(new TarefaSimples(descricao, prioridade, data_criacao));
    }
    
    public boolean create(String descricao, int prioridade, LocalDate data_criacao, LocalDate prazo){
        System.out.println("Tarefa com prazo");
        return gerencia_tarefas.cadastrar(new TarefaComPrazo(descricao, prioridade, data_criacao, prazo));
    }
    
    public void show(JTable tabela){
        
        List<Tarefa> tarefas = GerenciaTarefas.listar();
        DefaultTableModel dtm = (DefaultTableModel) tabela.getModel();
        dtm.setRowCount(tarefas.size());
        tabela.setModel(dtm);
        
        int posicao_linha = 0;
        for(Tarefa tarefa : tarefas){
           tabela.setValueAt(tarefa.getDescricao(), posicao_linha, 0);
           tabela.setValueAt(tarefa.getPrioridade(), posicao_linha, 1);
           tabela.setValueAt(tarefa.getDataCriacao(), posicao_linha, 2);
           if(tarefa instanceof TarefaComPrazo) {
            tabela.setValueAt(((TarefaComPrazo) tarefa).getPrazo(), posicao_linha, 3);
           } else {
              tabela.setValueAt("-", posicao_linha, 3); 
            }
           tabela.setValueAt(tarefa.isConcluida(), posicao_linha, 4);
           
           posicao_linha += 1;
        }
    }
    
    
    public void buscar(int posicao){
        Tarefa tarefa = GerenciaTarefas.buscar(posicao);
        if(tarefa != null){
            if(tarefa instanceof TarefaSimples){
                JOptionPane.showMessageDialog(null, "=== Tarefa simples ===\nDescrição: " + tarefa.getDescricao() + "\nPrioridade: " + tarefa.getPrioridade() + "\nData criação: " + tarefa.getDataCriacao() + "\nConcluída: " + (tarefa.isConcluida() ? "sim" : "não"));
            }else{
                JOptionPane.showMessageDialog(null, "=== Tarefa com prazo ===\nDescrição: " + tarefa.getDescricao() + "\nPrioridade: " + tarefa.getPrioridade() + "\nData criação: " + tarefa.getDataCriacao() + "\nPrazo: " + ((TarefaComPrazo) tarefa).getPrazo() + "\nConcluída: " + (tarefa.isConcluida() ? "sim" : "não"));
            }
        }else{
            JOptionPane.showMessageDialog(null, "Não foi possível encontrar essa tarefa", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void buscar(String descricao){
        Tarefa tarefa = GerenciaTarefas.buscar(descricao);
        if(tarefa != null){
            if(tarefa instanceof TarefaSimples){
                JOptionPane.showMessageDialog(null, "=== Tarefa simples ===\nDescrição: " + tarefa.getDescricao() + "\nPrioridade: " + tarefa.getPrioridade() + "\nData criação: " + tarefa.getDataCriacao() + "\nConcluída: " + (tarefa.isConcluida() ? "sim" : "não"));
            }else{
                JOptionPane.showMessageDialog(null, "=== Tarefa com prazo ===\nDescrição: " + tarefa.getDescricao() + "\nPrioridade: " + tarefa.getPrioridade() + "\nData criação: " + tarefa.getDataCriacao() + "\nPrazo: " + ((TarefaComPrazo) tarefa).getPrazo() + "\nConcluída: " + (tarefa.isConcluida() ? "sim" : "não"));
            }
        }else{
            JOptionPane.showMessageDialog(null, "Não foi possível encontrar essa tarefa", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void delete(JTable tabela){
        
        // verificando se há uma tarefa selecionada
        if(tabela.getSelectedRow() == -1){
            JOptionPane.showMessageDialog(null, "Selecione uma tarefa para ser excluída", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        }
        // ter certeza que deseja excluir a tarefa
        else{
            int resposta = JOptionPane.showConfirmDialog(null, "Deseja realmente excluir esta tarefa?", "Excluir tarefa", JOptionPane.YES_NO_OPTION);
            if(resposta == JOptionPane.YES_OPTION){
                if(GerenciaTarefas.remover(tabela.getSelectedRow()) != null){
                    JOptionPane.showMessageDialog(null, "Tarefa excluída com sucesso!");
                    show(tabela);
                }else{
                    JOptionPane.showMessageDialog(null, "Não foi possível excluir a tarefa", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}
