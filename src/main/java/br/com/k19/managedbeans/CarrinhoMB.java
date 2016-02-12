package br.com.k19.managedbeans;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import br.com.k19.sessionbeans.Carrinho;

@ManagedBean
@SessionScoped
public class CarrinhoMB {
//	@EJB
	private Carrinho carrinhoBean;
	private String produto;
	
	@PostConstruct
	public void inicializador() throws NamingException {
		Properties props = new Properties();
		props.put(Context.INITIAL_CONTEXT_FACTORY,
				"org.jboss.naming.remote.client.InitialContextFactory");
		props.put(Context.PROVIDER_URL, "remote://127.0.0.1:4447");
		props.put(Context.SECURITY_PRINCIPAL, "k19");
		props.put(Context.SECURITY_CREDENTIALS, "1234");
		InitialContext ic = new InitialContext(props);
		carrinhoBean = (Carrinho) ic.lookup("proximagradeejb/CarrinhoBean!br.com.k19.sessionbeans.Carrinho");
	}

	public List<String> getProdutos() {
		return new ArrayList<String>(this.carrinhoBean.getProdutos());
	}

	public void adiciona() {
		this.carrinhoBean.adiciona(this.produto);
	}

	public void remove(String produto) {
		this.carrinhoBean.remove(produto);
	}

	public void setProduto(String produto) {
		this.produto = produto;
	}

	public String getProduto() {
		return produto;
	}
}