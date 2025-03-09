package br.unifae.engsof.poo3.gerenciadorDeTarefas.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author android
 */
public class GerenciaTarefas {
    //alguém no banco que recebe as tarefas: arrayList
    private static List<Tarefa> tarefas = null;
    
    public GerenciaTarefas(){
        if(tarefas == null){
           tarefas = new ArrayList<>();
        }
    }
    
    public boolean cadastrar(Tarefa tarefa){
        if(tarefa != null && !(tarefas.contains(tarefa))){
            return tarefas.add(tarefa);
        }else{
            return false;
        }
    }
    
    public static List<Tarefa> listar(){
        return tarefas;
    }
    
    public static Tarefa buscar(int posicao){
        if(posicao < tarefas.size()) return tarefas.get(posicao);
  
        return null;
    }
    
    public static Tarefa buscar(String descricao){
        if(descricao != null){
            for(Tarefa tarefa : tarefas){
               if(tarefa.getDescricao().equals(descricao)) return tarefa;
            }
        }
        return null;
    }
    
    public static Tarefa remover(int posicao){
        if(posicao < tarefas.size()){
            Tarefa tarefa_excluida = tarefas.get(posicao);
            tarefas.remove(posicao);
            return tarefa_excluida;
        }
        
        return null;
    }
}
