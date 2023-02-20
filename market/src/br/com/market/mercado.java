package br.com.market;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class mercado {
	


	    private ArrayList<produto> produtos;

	    public mercado() {
	        this.produtos = new ArrayList<produto>();
	    }
	    public String[] leValores (String [] dadosIn){
	        String [] dadosOut = new String [dadosIn.length];

	        for (int i = 0; i < dadosIn.length; i++)
	           dadosOut[i] = JOptionPane.showInputDialog("Entre com "+ dadosIn[i] +": ");

	        return dadosOut;
	    }
	    public bebida leBebida(){

	        String [] valores = new String [4];
	        String [] nomeVal = {"nome", "Categoria", "Codigo", "Fornecedor"};
	        valores = leValores (nomeVal);

	        int codigo = this.retornaInteiro(valores[1]);

	        bebida b = new bebida(valores[0], valores[3], valores[1],codigo);
	        return b;
	    }

	    public limpeza leLimpeza(){
	        String [] valores = new String [4];
	        String [] nomeVal = {"nome", "Categoria", "Codigo", "Fornecedor"};
	        valores = leValores (nomeVal);

	        int codigo = this.retornaInteiro(valores[1]);

	
	        limpeza l = new limpeza(valores[0], valores[3], valores[1],codigo);
	        return l;
	    }
	    private boolean  intValido(String s) {
	        try {
	          Integer.parseInt(s); 
	          return true;
	           
	        } catch (NumberFormatException e) { 
	            return false;
	        }
	    }
	    public int retornaInteiro(String entrada) { 
	        int numInt;

	        while (!this.intValido(entrada)) {
	            entrada = JOptionPane.showInputDialog(null, "Valor incorreto!\n\nDigite um número inteiro.");
	        }
	        return Integer.parseInt(entrada);
	    }

	    public void salvaProdutos (ArrayList<produto> produtos){
	        ObjectOutputStream outputStream = null;
	        try {
	            outputStream = new ObjectOutputStream 
	                    (new FileOutputStream("c:\\temp\\mercado.dados"));
	            for (int i=0; i < produtos.size(); i++)
	                outputStream.writeObject(produtos.get(i));
	        } catch (FileNotFoundException ex) {
	            JOptionPane.showMessageDialog(null, "Impossível criar arquivo!");
	            ex.printStackTrace();
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        } finally { 
	            try {
	                if (outputStream != null) {
	                    outputStream.flush();
	                    outputStream.close();
	                }
	            } catch (IOException ex) {
	                ex.printStackTrace();
	            }
	        }
	    }
	    public ArrayList<produto> recuperaprodutos (){
	        ArrayList<produto> produtosTemp = new ArrayList<produto>();

	        ObjectInputStream inputStream = null;

	        try {    
	            inputStream = new ObjectInputStream
	                    (new FileInputStream("c:\\temp\\mercado.dados"));
	            Object obj = null;
	            while ((obj = inputStream.readObject()) != null) {
	                if (obj instanceof produto) {
	                    produtosTemp.add((produto) obj);
	                }   
	            }          
	        } catch (EOFException ex) { 
	            System.out.println("Fim de arquivo.");
	        } catch (ClassNotFoundException ex) {
	            ex.printStackTrace();
	        } catch (FileNotFoundException ex) {
	            JOptionPane.showMessageDialog(null, "Arquivo com produtos NÃO existe!");
	            ex.printStackTrace();
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        } finally { 
	            try {
	                if (inputStream != null) {
	                    inputStream.close();
	                }
	            } catch (final IOException ex) {
	                ex.printStackTrace();
	            }
	            return produtosTemp;
	        }
	    }
	    public void menuMercado (){
	        String menu = "";
	        String entrada;
	        int    opc1, opc2;
	        do {
	            menu = "Controle Mercado\n" +
	                    "Opções:\n" + 
	                    "1. Entrar Produtos \n" +
	                    "2. Exibir produtos\n" +
	                    "3. Limpar produtos\n" +
	                    "4. Gravar produtos\n" +
	                    "5. Recuperar produtos\n" +
	                    "9. Sair";
	            entrada = JOptionPane.showInputDialog (menu + "\n\n");
	            opc1 = this.retornaInteiro(entrada);

	            switch (opc1) {
	            case 1:
	                menu = "Entrada de produtos\n" +
	                        "Opções:\n" + 
	                        "1. Bebida\n" +
	                        "2. limpeza\n";

	                entrada = JOptionPane.showInputDialog (menu + "\n\n");
	                opc2 = this.retornaInteiro(entrada);

	                switch (opc2){
	                case 1: produtos.add((produto)leBebida());
	                break;
	                case 2: produtos.add((produto) leLimpeza());
	                break;
	                default: 
	                    JOptionPane.showMessageDialog(null, "Entrada não é válida!");
	                }

	                break;
	            case 2: // Exibir dados
	                if (produtos.size() == 0) {
	                    JOptionPane.showMessageDialog(null, "Entre com produtos ");
	                    break;
	                }
	                String dados = "";
	                for (int i=0; i < produtos.size(); i++)    {
	                    dados +=   produtos.get(i).toString() + "---------------\n";
	                }
	                JOptionPane.showMessageDialog(null,dados);
	                break;
	            case 3: 
	                if ( produtos.size() == 0) {
	                    JOptionPane.showMessageDialog(null, "Adicione produtos");
	                    break;
	                }
	                 produtos.clear();
	                JOptionPane.showMessageDialog(null, "Dados LIMPOS com sucesso!");
	                break;
	            case 4: 
	                if (produtos.size() == 0) {
	                    JOptionPane.showMessageDialog(null, "Adicione produtos");
	                    break;
	                }
	                salvaProdutos(produtos);
	                JOptionPane.showMessageDialog(null, "Dados salvos com sucesso!");
	                break;
	            case 5:
	                 produtos = recuperaprodutos();
	                if ( produtos.size() == 0) {
	                    JOptionPane.showMessageDialog(null,"Sem dados para apresentar.");
	                    break;
	                }
	                JOptionPane.showMessageDialog(null,"Dados RECUPERADOS com sucesso!");
	                break;
	            case 9:
	                JOptionPane.showMessageDialog(null, "Fim do aplicativo Mercado");
	                break;
	            }
	        } while (opc1 != 9);
	    }
	    public static void main (String [] args){
	        mercado m = new mercado ();
	       m.menuMercado();
	    }
	}

