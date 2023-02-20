package br.com.market;


public class bebida extends produto {
	String tipo;

	public bebida( String nome, String fornecedor, String categoria,int codigo) {
		super(nome, fornecedor, "Bebida",codigo);

	}
	@Override
	public String setor(String setor) {
		return super.setor("bebida");
	}
	
	

}
