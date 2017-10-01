/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.inatel.ac309.bean;

import br.inatel.ac309.DAO.CarroDAO;
import br.inatel.ac309.DAO.CrudDAO;
import br.inatel.ac309.entity.Carro;
import br.inatel.ac309.util.exception.ErroSistema;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
/**
 *
 * @author Filipe
 */
@ManagedBean
@SessionScoped
public class CarroBean extends CrudBean<Carro,CarroDAO>{
    
    private CarroDAO carroDAO;
    

    @Override
    public CarroDAO getDAO() {
        if(carroDAO == null){
            carroDAO = new CarroDAO();
            
        }
        return carroDAO;
    }

    @Override
    public Carro criarNovaEntidade() {
        return new Carro();
        
    }

    
    

}
