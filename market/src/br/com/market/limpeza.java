package br.com.market;

public class limpeza extends produto {

	public limpeza( String nome, String fornecedor, String categoria,int codigo) {
		super( nome, fornecedor, "LIMPEZA", codigo);
	}

	@Override
	public String setor(String setor) {
		return super.setor("limpeza");
	}
}
