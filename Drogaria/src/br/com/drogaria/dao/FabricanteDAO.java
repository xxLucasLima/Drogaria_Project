package br.com.drogaria.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

import br.com.drogaria.domain.Fabricante;
import br.com.drogaria.factory.ConexaoFactory;

public class FabricanteDAO {
	public void salvar(Fabricante f) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO fabricante ");
		sql.append("(descricao)");
		sql.append("VALUES (?)");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = (PreparedStatement) conexao.prepareStatement(sql.toString());
		comando.setString(1, f.getDescricao());

		comando.executeUpdate();
		comando.close();

	}

	public void excluir(Fabricante f) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM fabricante ");
		sql.append("WHERE codigo = ? ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = (PreparedStatement) conexao.prepareStatement(sql.toString());
		comando.setLong(1, f.getCodigo());

		comando.executeUpdate();
		comando.close();

	}

	public void editar(Fabricante f) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE fabricante ");
		sql.append("SET descricao = ? ");
		sql.append("WHERE codigo = ?");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = (PreparedStatement) conexao.prepareStatement(sql.toString());
		comando.setString(1, f.getDescricao());
		comando.setLong(2, f.getCodigo());

		comando.executeUpdate();
		comando.close();
	}

	public Fabricante buscarPorCodigo(Fabricante f) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, descricao  ");
		sql.append("FROM fabricante ");
		sql.append("WHERE codigo = ?");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = (PreparedStatement) conexao.prepareStatement(sql.toString());
		comando.setString(1, f.getDescricao());

		ResultSet resultado = comando.executeQuery();

		Fabricante retorno = null;

		if (resultado.next()) {
			retorno = new Fabricante();
			retorno.setCodigo(resultado.getLong("codigo"));
			retorno.setDescricao(resultado.getString("descricao"));

		}
		return retorno;

	}

	public ArrayList<Fabricante> listar() throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, descricao ");
		sql.append("FROM fabricante ");
		sql.append("ORDER BY descricao ASC ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = (PreparedStatement) conexao.prepareStatement(sql.toString());

		ResultSet resultado = comando.executeQuery();

		ArrayList<Fabricante> lista = new ArrayList<Fabricante>();

		while (resultado.next()) {
			Fabricante f = new Fabricante();

			f.setCodigo(resultado.getLong("codigo"));
			f.setDescricao(resultado.getString("descricao"));

			lista.add(f);
		}
		return lista;

	}

	public ArrayList<Fabricante> buscarPorDescricao(Fabricante f) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, descricao ");
		sql.append("FROM fabricante ");
		sql.append("WHERE descricao LIKE = ? ");
		sql.append("ORDER BY descricao ASC ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = (PreparedStatement) conexao.prepareStatement(sql.toString());
		comando.setString(1, "%" + f.getDescricao() + "%");

		ResultSet resultado = comando.executeQuery();

		ArrayList<Fabricante> lista = new ArrayList<Fabricante>();

		while (resultado.next()) {
			Fabricante item = new Fabricante();

			item.setCodigo(resultado.getLong("codigo"));
			item.setDescricao(resultado.getString("descricao"));

			lista.add(f);
		}
		return lista;
	}

	public static void main(String[] args) {
		/*
		 * Fabricante f1 = new Fabricante(); f1.setDescricao("DESCRICAO 1");
		 * 
		 * Fabricante f2 = new Fabricante(); f2.setDescricao("DESCRICAO 2");
		 * 
		 * FabricanteDAO fdao = new FabricanteDAO();
		 * 
		 * try { fdao.salvar(f2); fdao.salvar(f1); System.out.println(
		 * "Os FAbricantes foram salvos com sucesso"); } catch (SQLException e)
		 * { System.out.println(
		 * "Ocorreu um erro ao tentar salvar um dos fabricantes");
		 * e.printStackTrace(); }
		 * 
		 * Fabricante f1 = new Fabricante(); f1.setCodigo(2L);
		 * 
		 * FabricanteDAO fdao = new FabricanteDAO(); try{ fdao.excluir(f1);
		 * System.out.println("Removido com sucesso"); }catch(SQLException e){
		 * e.printStackTrace(); System.out.println(
		 * "Ocorreu um erro ao tentar remover fabricante"); }
		 */

		Fabricante f1 = new Fabricante();
		f1.setCodigo(1L);
		f1.setDescricao("Descri��o 3");

		FabricanteDAO fdao = new FabricanteDAO();

		try {
			fdao.editar(f1);
			System.out.println("O fabricante foi editado com sucesso");
		} catch (SQLException e) {
			System.out.println("Erro ao rodar edi��o");
			e.printStackTrace();
		}
	}
}
