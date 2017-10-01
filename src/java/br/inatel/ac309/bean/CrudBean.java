/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.inatel.ac309.bean;

import br.inatel.ac309.DAO.CrudDAO;
import br.inatel.ac309.util.exception.ErroSistema;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;


public abstract class CrudBean<E, D extends CrudDAO> {
    
    private String estadoTela = "buscar";// Inserir,editar e buscar
    private E entidade;
    private List<E> entidades;
    
    public void novo(){
        entidade = criarNovaEntidade();
        mudarParaInseri();
    }
    
    public void salvar(){
        try {
            getDAO().salvar(entidade);
            entidade = criarNovaEntidade();
            adicionarMensagem("Salvo com suceso !", FacesMessage.SEVERITY_INFO);
            mudarParaBusca();
        } catch (ErroSistema ex) {
            Logger.getLogger(CrudBean.class.getName()).log(Level.SEVERE, null, ex);
            adicionarMensagem(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
        
    }
    
    public void editar(E entidade){
        this.entidade = entidade;
        mudarParaEdita();
        
    }
    
    public void deletar(E entidade){

        try {
            getDAO().deletar(entidade); 
            entidades.remove(entidade);
            adicionarMensagem("Deletado com suceso !", FacesMessage.SEVERITY_INFO);
        } catch (ErroSistema ex) {
            Logger.getLogger(CrudBean.class.getName()).log(Level.SEVERE, null, ex);
            adicionarMensagem(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }
    
    public void buscar(){
       if(isBusca() == false ){
            mudarParaBusca();
            return;
        }
        try {
            entidades = getDAO().buscar();
            if(entidades == null || entidades.size() < 1){
                adicionarMensagem("Nao temos nada cadastrado !!", FacesMessage.SEVERITY_WARN);
            }
        } catch (ErroSistema ex) {
            Logger.getLogger(CrudBean.class.getName()).log(Level.SEVERE, null, ex);
            adicionarMensagem(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }
    
    public void adicionarMensagem(String mensagem, FacesMessage.Severity tipoErro){
        FacesMessage fm = new FacesMessage(tipoErro, mensagem, null);
        FacesContext.getCurrentInstance().addMessage(null, fm);
    }
    public E getEntidade() {
        return entidade;
    }

    public void setEntidade(E entidade) {
        this.entidade = entidade;
    }

    public List<E> getEntidades() {
        return entidades;
    }

    //getters and setters para entidades
    public void setEntidades(List<E> entidades) {    
        this.entidades = entidades;
    }

    //Responsavel por criar metodos nas classes bean
    public abstract D getDAO();
    public abstract E criarNovaEntidade();
    
    
    public boolean isInseri(){
        return "inserir".equals(estadoTela);
    }
    public boolean isEdita(){
        return "editar".equals(estadoTela);
    }
    public boolean isBusca(){
        return "buscar".equals(estadoTela);
    }
    
    public void mudarParaInseri(){
        estadoTela = "inserir";
    }
    public void mudarParaEdita(){
        estadoTela = "editar";
    }
    public void mudarParaBusca(){
        estadoTela = "buscar";
    }
}
