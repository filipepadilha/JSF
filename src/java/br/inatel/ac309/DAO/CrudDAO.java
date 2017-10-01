/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.inatel.ac309.DAO;

import br.inatel.ac309.util.exception.ErroSistema;
import java.util.List;

/**
 *
 * @author Filipe
 */
public interface CrudDAO<E> {//E --> Representa a Entidade
    
    public void salvar(E entidade) throws ErroSistema;
    public void deletar(E entidade) throws ErroSistema;
    public List<E> buscar() throws ErroSistema;
       
    
    
    
}
