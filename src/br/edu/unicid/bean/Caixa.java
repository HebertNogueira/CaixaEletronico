package br.edu.unicid.bean;

public class Caixa {
	private static int codigo;
	private static int cedula;
	private static int quantidade;

	//Construtor vazio
	public Caixa(){
		
	}
	
	public Caixa(int codigo,  int cedula, int quantidade){
		Caixa.codigo = codigo;
		Caixa.cedula = cedula;
		Caixa.quantidade = quantidade;				
	}

	
	public static int getCodigo() {
		return codigo;
	}

	public static void setCodigo(int codigo) {
		Caixa.codigo = codigo;
	}

	public static int getCedula() {
		return cedula;
	}

	public static void setCedula(int cedula) {
		Caixa.cedula = cedula;
	}

	public static int getQuantidade() {
		return quantidade;
	}

	public static void setQuantidade(int quantidade) {
		Caixa.quantidade = quantidade;
	}
	

	

	

	
}
