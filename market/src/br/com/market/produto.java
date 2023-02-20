package br.com.market;

public abstract class produto {
	int codigo;
	String nome;
	String fornecedor;
	String categoria;

	public produto( String nome, String fornecedor, String categoria, int codigo) {
		
		this.categoria = categoria;
		this.codigo = codigo;
		this.nome = nome;
		this.fornecedor = fornecedor;

	}
	public  String setor(String setor) {
		return setor;
	};
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(nome);
		builder.append("\n");
		builder.append(categoria);
		builder.append("\n");
		builder.append(codigo);
		builder.append("\n");
		builder.append(fornecedor);
		return builder.toString();



	}
}