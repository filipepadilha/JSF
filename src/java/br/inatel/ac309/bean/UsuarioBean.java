/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.inatel.ac309.bean;

import br.inatel.ac309.DAO.CrudDAO;
import br.inatel.ac309.DAO.UsuarioDAO;
import br.inatel.ac309.entity.Usuario;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Filipe
 */
@ManagedBean
@SessionScoped
public class UsuarioBean extends CrudBean< Usuario ,  UsuarioDAO>{
    
    private UsuarioDAO usuarioDAO;
    

    @Override
    
    public UsuarioDAO getDAO() {
        if(usuarioDAO == null){
            usuarioDAO = new UsuarioDAO();
            
        }
        return usuarioDAO;
    }

    @Override
    public Usuario criarNovaEntidade() {
        return new Usuario();
    }
    
}
