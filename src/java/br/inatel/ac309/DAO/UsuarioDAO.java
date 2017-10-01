/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.inatel.ac309.DAO;

import br.inatel.ac309.entity.Carro;
import br.inatel.ac309.entity.Usuario;
import br.inatel.ac309.util.FabricaConexao;
import br.inatel.ac309.util.exception.ErroSistema;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Filipe
 */
public class UsuarioDAO implements CrudDAO<Usuario>{

    @Override
    public void salvar(Usuario entidade) throws ErroSistema {
        try {
            Connection conexao = FabricaConexao.getConexao();
            PreparedStatement ps;
            if(entidade.getId()== null){
            ps = conexao.prepareStatement("INSERT INTO `usuario`(`login`,`senha`)VALUES(?,?)");
                
            }else{
                ps = conexao.prepareStatement(" UPDATE `usuario`SET`login` = ?,`senha` = ? WHERE `id` = ?");
                ps.setInt(3, entidade.getId());
            }
            ps.setString(1, entidade.getLogin());
            ps.setString(2, entidade.getSenha());
            ps.execute();
            FabricaConexao.fecharConexao();
        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao adicionar o tentar salvar o usuario !! ", ex);
        }
    }

    @Override
    public void deletar(Usuario entidade) throws ErroSistema {
        try {
            Connection conexao = FabricaConexao.getConexao();
            PreparedStatement ps = conexao.prepareStatement("DELETE FROM `usuario` WHERE id=?");
            ps.setInt(1, entidade.getId());
            ps.execute();
        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao deletar o usuario !!", ex);
        }
    }

    @Override
    public List<Usuario> buscar() throws ErroSistema {
        try {
            Connection conexao = FabricaConexao.getConexao();
            PreparedStatement ps = conexao.prepareStatement("select*from usuario");
            ResultSet resultSet = ps.executeQuery();
            List<Usuario> usuarios = new ArrayList<>();
            while(resultSet.next()){
            
                Usuario usuario = new Usuario();
                usuario.setId(resultSet.getInt("id"));
                usuario.setLogin(resultSet.getString("login"));
                usuario.setSenha(resultSet.getString("senha"));
                usuarios.add(usuario);
                
            }
            FabricaConexao.fecharConexao();
            return usuarios;
            
        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao buscar o usuarios !!", ex);
            
        }
    }
    
}
