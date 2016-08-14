package br.com.drogaria.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import br.com.drogaria.domain.Fabricante;
import br.com.drogaria.domain.Produto;
import br.com.drogaria.factory.ConexaoFactory;

public class ProdutoDAO {
	public void salvar(Produto p)throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO produto ");
		sql.append("(descricao, preco, quantidade, fabricante_codigo) ");
		sql.append("VALUES (?, ?, ?, ?) ");
		
		Connection conexao = (Connection) ConexaoFactory.conectar();
	
		java.sql.PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		comando.setString(1, p.getDescricao() );
		comando.setDouble(2, p.getPreco());
		comando.setLong(3, p.getQuantidade());
		comando.setLong(4, p.getFabricante().getCodigo());
		
		comando.executeUpdate();
	}
	
	public ArrayList<Produto> listar()throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT p.codigo, p.descricao, p.preco, p.quantidade, f.codigo, f.descricao ");
		sql.append("FROM produto p ");
		sql.append("INNER JOIN fabricante f ON f.codigo = p.fabricante_codigo ");
		
		Connection conexao = (Connection) ConexaoFactory.conectar();
		
		PreparedStatement comando = (PreparedStatement) conexao.prepareStatement(sql.toString());
		
		ResultSet resultado = comando.executeQuery();
		
		ArrayList<Produto> itens = new ArrayList<Produto>();
		
		while(resultado.next()){
			Fabricante f = new Fabricante();
			f.setCodigo(resultado.getLong("f.codigo"));
			f.setDescricao(resultado.getString("f.descricao"));
			
			Produto p = new Produto();
			
			p.setCodigo(resultado.getLong("p.codigo"));
			p.setDescricao(resultado.getString("p.descricao"));
			p.setPreco(resultado.getDouble("p.preco"));
			p.setQuantidade(resultado.getLong("p.quantidade"));
			p.setFabricante(f);
			
			itens.add(p);
		}
		return itens;	
	}
	
	public void excluir(Produto p)throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM produto ");
		sql.append("WHERE codigo = ? ");
		
		Connection conexao = (Connection) ConexaoFactory.conectar();
		PreparedStatement comando = (PreparedStatement) conexao.prepareStatement(sql.toString());
		comando.setLong(1, p.getCodigo());
		comando.executeUpdate();		
	}
	
	public void editar(Produto p) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE produto ");
		sql.append("SET descricao = ?, preco = ?, quantidade = ?, fabricante_codigo = ? ");
		sql.append("WHERE codigo = ? " );
		
		Connection conexao = (Connection) ConexaoFactory.conectar();
		PreparedStatement comando = (PreparedStatement) conexao.prepareStatement(sql.toString());
		
		comando.setString(1, p.getDescricao());
		comando.setDouble(2, p.getPreco());
		comando.setLong(3, p.getQuantidade());
		comando.setLong(4, p.getFabricante().getCodigo());
		comando.setLong(5, p.getCodigo());
		
		comando.executeUpdate();
		
	}
}
