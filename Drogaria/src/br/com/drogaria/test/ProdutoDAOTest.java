package br.com.drogaria.test;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.*;

import br.com.drogaria.dao.ProdutoDAO;
import br.com.drogaria.domain.Fabricante;
import br.com.drogaria.domain.Produto;

public class ProdutoDAOTest {
	@Test
	@Ignore
	public void salvar()throws SQLException{
		Produto p = new Produto();
		p.setDescricao("NOVALGINA COM 10 COMPRIMIDOS");
		p.setPreco(2.45);
		p.setQuantidade(13L);
		
		Fabricante f = new Fabricante();
		f.setCodigo(2L);
		
		p.setFabricante(f);

		ProdutoDAO dao = new ProdutoDAO();
		dao.salvar(p);

	}
	@Test
	@Ignore
	public void Listar()throws SQLException{
		ProdutoDAO dao = new ProdutoDAO();
		ArrayList<Produto> lista = dao.listar();
		
		for(Produto p : lista){
			System.out.println("Código: " + p.getCodigo());
			System.out.println("Descrição: " + p.getPreco());
			System.out.println("Quantidade: " + p.getQuantidade());
			System.out.println("Preço: " + p.getPreco());
			System.out.println("Código do Fabricante: " + p.getFabricante().getCodigo());
			System.out.println("Descrição do fabricante: " + p.getFabricante().getDescricao());
		}
		
	}
	
	@Test
	@Ignore
	public void excluir() throws SQLException{
		Produto p = new Produto();
		
		p.setCodigo(3L);
		
		ProdutoDAO dao = new ProdutoDAO();
		dao.excluir(p);
	}
	
	@Test
	public void editar () throws SQLException{
		Produto p = new Produto();
		
		p.setCodigo(2L);
		
		p.setDescricao("CATAFLAN pomada com 30g");
		p.setPreco(12.00);
		p.setQuantidade(30L);
		
		Fabricante f = new Fabricante();
		
		f.setCodigo(1L);
		p.setFabricante(f);
	}
	

}
