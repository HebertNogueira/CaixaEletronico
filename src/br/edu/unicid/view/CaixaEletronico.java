package br.edu.unicid.view;
import javax.swing.JOptionPane;

import br.edu.unicid.bean.Caixa;
import br.edu.unicid.dao.CaixaDAO;
import br.edu.unicid.util.ConnectionFactory;

public class CaixaEletronico extends Caixa implements ICaixaEletronico {

	private static int saldoNota2=0;
	private static int saldoNota5=0;
	private static int saldoNota10=0;
	private static int saldoNota20=0;
	private static int saldoNota50=0;
	private static int saldoNota100=0;
	private static int nota2=0;
	private static int nota5=0;
	private static int nota10=0;
	private static int nota20=0;
	private static int nota50=0;
	private static int nota100=0;
	private static int saldoEmUso=0;
	private static int valorRecebido=0;
	private static int totalDisponivel=0;
	private static int cotaMinima = 0;
	private static String compResp = "";
	private static boolean verificaCotaMinima = false;
	private static boolean verificaSaldoNotas = false;
	private static String relatorio = "";
	private static int saqueEfetuado;
	private static boolean saque = false;
	
	
	
	public static void atualizaSaldo(){
		CaixaDAO dao = new CaixaDAO();

		dao.pegaCedulas100();
		saldoNota100 = getQuantidade();
		dao.pegaCedulas50();
		saldoNota50 = getQuantidade();
		dao.pegaCedulas20();
		saldoNota20 = getQuantidade();
		dao.pegaCedulas10();
		saldoNota10 = getQuantidade();
		dao.pegaCedulas5();
		saldoNota5 = getQuantidade();
		dao.pegaCedulas2();
		saldoNota2 = getQuantidade();
		
		totalDisponivel = (saldoNota2*2)+(saldoNota5*5)+(saldoNota10*10)+(saldoNota20*20)+(saldoNota50*50)+(saldoNota100*100);
	}
	
//Gera relatório de cédulas disponíveis
public String pegaRelatorioCedulas() {

String resposta = "Relatório de Cédulas\n\n"+ "Cédulas de 2 Reais: " + saldoNota2 + 
											"\nCédulas de 5 Reais: " + saldoNota5 +
											"\nCédulas de 10 Reais: " + saldoNota10 +
											"\nCédulas de 20 Reais: " + saldoNota20 +
											"\nCédulas de 50 Reais: " + saldoNota50 +
											"\nCédulas de 100 Reais: " + saldoNota100 +
											"\n\nQuantidade total de cédulas: " + 
											(saldoNota2+saldoNota5+saldoNota10+saldoNota20+saldoNota50+saldoNota100);

return resposta;
}

//Gera relatório de valor total disponível
public String pegaValorTotalDisponivel() {
	
totalDisponivel = (saldoNota2*2)+(saldoNota5*5)+(saldoNota10*10)+(saldoNota20*20)+(saldoNota50*50)+(saldoNota100*100);
	
String resposta = "Valor total disponível no caixa: R$" + totalDisponivel + ",00\n\nSendo: "
					+ "\n R$: " + (saldoNota2*2) + ",00 em notas de 2 Reais"
					+ "\n R$: " + (saldoNota5*5) + ",00  em notas de 5 Reais"
					+ "\n R$: " + (saldoNota10*10) + ",00  em notas de 10 Reais"
					+ "\n R$: " + (saldoNota20*20) + ",00  em notas de 20 Reais"
					+ "\n R$: " + (saldoNota50*50) + ",00  em notas de 50 Reais"
					+ "\n R$: " + (saldoNota100*100) + ",00  em notas de 100 Reais";

return resposta;

}


//Verifica cédula inserida e adiciona o valor ao saldo de cédulas
//Atualiza o Total Disponível

public String reposicaoCedulas(int cedula, int quantidade) {

	if (cedula == 2){
		saldoNota2+=quantidade;
		saldoEmUso=saldoNota2;
	}
	if (cedula == 5){
		saldoNota5+=quantidade;
		saldoEmUso = saldoNota5;
	}
	if (cedula == 10){
		saldoNota10+=quantidade;
		saldoEmUso = saldoNota10;
	}
	if (cedula == 20){
		saldoNota20+=quantidade;
		saldoEmUso = saldoNota20;
	}
	if (cedula == 50){
		saldoNota50+=quantidade;
		saldoEmUso = saldoNota50;
	}
	if (cedula == 100){
		saldoNota100+=quantidade;
		saldoEmUso = saldoNota100;
	}
	
totalDisponivel = (saldoNota2*2)+(saldoNota5*5)+(saldoNota10*10)+(saldoNota20*20)+(saldoNota50*50)+(saldoNota100*100);
if (totalDisponivel>cotaMinima) verificaCotaMinima = false;
String resposta = "Notas inseridas com sucesso, " + quantidade + " cédulas de " + cedula + " Reais.\nSaldo Atual: " + saldoEmUso;


try {
	CaixaDAO.atualizaSaldoNoBanco(cedula,saldoEmUso);
} catch (Exception e) {
	e.printStackTrace();
}

return resposta;

}

public String sacar(int valor) {	
	
	
	String resposta="";
	valorRecebido = valor;
	verificaSaldoNotas=false;
	
	
		//Verifica se a ultima operação deixou o caixa abaixo da cota mínima.
		if (verificaCotaMinima == false){
			

			if( valorRecebido==9 ){
				if ((saldoNota5 != 0) && (saldoNota2 != 0)){
					nota5+=1;
					saldoNota5-=1;
					nota2+=2;
					saldoNota2-=2;
					valorRecebido=0;
				} else {
					System.out.println("9");
					verificaSaldoNotas = true;
				}
			}
			
			if( valorRecebido==8 ){
				if (saldoNota2 != 0){
					nota2+=4;
					saldoNota2-=4;
					valorRecebido=0;
				} else {
					System.out.println("8");
					verificaSaldoNotas = true;
				}
			}
			
			if( valorRecebido==7 ){
				if ((saldoNota5 != 0) && (saldoNota2 != 0)){
					nota5+=1;
					saldoNota5-=1;
					nota2+=1;
					saldoNota2-=1;
					valorRecebido=0;
				} else {
					System.out.println("7");
					verificaSaldoNotas = true;
				}
			}
			
			if( valorRecebido==6 ){
				if (saldoNota2 != 0){
					nota2+=3;
					saldoNota2-=3;
					valorRecebido=0;
				} else {
					System.out.println("6");
					verificaSaldoNotas = true;
				}
			}
			
			if( valorRecebido==5 ){
				if (saldoNota5 != 0){
					nota5+=1;
					saldoNota5-=1;
					valorRecebido=0;
				} else {
					System.out.println("5");
					verificaSaldoNotas = true;
				}
			}
			

			if( valorRecebido==4 ){
				if (saldoNota2 != 0){
					nota2+=2;
					saldoNota2-=2;
					valorRecebido=0;
				} else {
					System.out.println("4");
					verificaSaldoNotas = true;
				}
			}
			
			if( valorRecebido==2 ){
				if (saldoNota2 != 0){
					nota2+=1;
					saldoNota2-=1;
					valorRecebido=0;
				} else {
					System.out.println("2");
					verificaSaldoNotas = true;
				}
			}
			

			if( (valorRecebido%10)==1 ){
				System.out.println(saldoNota5 + " " + saldoNota2);
				if ((saldoNota5 != 0) && (saldoNota2 != 0)){
					nota5+=1;
					saldoNota5-=1;
					nota2+=3;
					saldoNota5-=3;
					valorRecebido-=11;
				} else {
					System.out.println("%1");
					verificaSaldoNotas = true;
				}
			}
			
			
			if( (valorRecebido%10)==3 ){
				if ((saldoNota5 != 0) || (saldoNota2 != 0)){
					nota5+=1;
					saldoNota5-=1;
					nota2+=4;
					saldoNota2-=4;
					valorRecebido-=13;
				} else {
					System.out.println("%3");
					verificaSaldoNotas = true;
				}
			}
			
			if((valorRecebido%10)==5 ){
				if (saldoNota5 != 0){
					nota5+=1;
					saldoNota5-=1;
					valorRecebido-=5;
				} else {
					System.out.println("%5");
					verificaSaldoNotas = true;
				}
			}
			
			if( (valorRecebido%10)==7){
				if ((saldoNota5 != 0) && (saldoNota2 != 0)){
					nota5+=1;
					saldoNota5-=1;
					nota2+=1;
					saldoNota2-=1;
					valorRecebido-=7;
					
				} else {
					System.out.println("%7");
					verificaSaldoNotas = true;
				}
				
			}
			
			if( (valorRecebido%10)==9){
				if ((saldoNota5 != 0) && (saldoNota2 != 0)){
					nota5+=1;
					saldoNota5-=1;
					nota2+=2;
					saldoNota2-=2;
					valorRecebido-=9;
				} else {
					System.out.println("%9");
					verificaSaldoNotas = true;
				}
			}

		//Checa se há cédulas de 100 suficientes para a operação.			
		if(saldoNota100>=((valorRecebido-(valorRecebido%100))/100)){
			
			//Guarda o valor que será usado com esta cédula.
			nota100 += (valorRecebido-(valorRecebido%100))/100;
			
			//Salva o valor que irá para a próxima cédula.
			valorRecebido = valorRecebido%100;

			//retira a quantidade de cédulas usadas do saldo do caixa.
			saldoNota100-=nota100;
			
		//Caso não Tenha o montante de cédulas suficientes.
		} else { 
			
			//Retira o total disponível.
			nota100 += saldoNota100;
			
			//Subtrai o valor Retirado do saldo do caixa.
			valorRecebido =  valorRecebido-(nota100*100);
			
			//Zera o saldo da cédula.
			saldoNota100 = 0;
		}
			
			if(saldoNota50>=((valorRecebido-(valorRecebido%50))/50)){
				nota50 += (valorRecebido-(valorRecebido%50))/50;
				valorRecebido = valorRecebido%50;
				saldoNota50-=nota50;
			} else { 
				nota50 += saldoNota50;
				valorRecebido =  valorRecebido-(nota50*50);
				saldoNota50 = 0;
			}	
			
			
			if(saldoNota20>=((valorRecebido-(valorRecebido%20))/20)){		
				nota20 += (valorRecebido-(valorRecebido%20))/20;
				valorRecebido = valorRecebido%20;
				saldoNota20-=nota20;
			} else { 
				nota20 += saldoNota20;
				valorRecebido =  valorRecebido-(nota20*20);
				saldoNota20 = 0;
			}
			
			
			if(saldoNota10>=((valorRecebido-(valorRecebido%10))/10)){		
				nota10 += (valorRecebido-(valorRecebido%10))/10;
				valorRecebido = valorRecebido%10;
				saldoNota10-=nota10;
			} else { 
				nota10 += saldoNota10;
				valorRecebido =  valorRecebido-(nota10*10);
				saldoNota10 = 0;
			}	
					
			if(saldoNota5>=((valorRecebido-(valorRecebido%10))/5)){		
				nota5 += (valorRecebido-(valorRecebido%10))/5;
				valorRecebido = valorRecebido%10;
				saldoNota5-=nota5;
			} else {
				
				if((saldoNota5%2)==0){
					nota5 += saldoNota5;
					valorRecebido =  valorRecebido-(nota5*5);
					saldoNota5 = 0;					
				} else {
					nota5 += (saldoNota5)-1;
					valorRecebido =  valorRecebido-(nota5*5);
					saldoNota5 = 1;	
				}
					
			}		
			
			if(saldoNota2>=((valorRecebido-(valorRecebido%2))/2)){		
				nota2 += (valorRecebido-(valorRecebido%2))/2;
				valorRecebido = valorRecebido%2;
				saldoNota2-=nota2;
			} else { 
				verificaSaldoNotas = true;
			}
			
				
		if(verificaSaldoNotas == false){
			
		//Atualiza o total disponível de cédulas
				
		//Compôe a resposta com as cédulas utilizadas.
		if(nota100 > 0 ) compResp = nota100 + " Cédulas de 100 Reais\n";
		if(nota50 > 0 ) compResp+=nota50 + " Cédulas de 50 Reais\n";
		if(nota20 > 0 ) compResp+=nota20 + " Cédulas de 20 Reais\n";
		if(nota10 > 0 ) compResp+=nota10 + " Cédulas de 10 Reais\n";
		if(nota5 > 0 )	compResp+=nota5 + " Cédulas de 5 Reais\n";
		if(nota2 > 0 ) compResp+=nota2 + " Cédulas de 2 Reais\n";
		
		compResp = "Retire suas notas: \n\n" + compResp;
		
		} else {
			compResp = "Não há cédulas suficientes para compor seu saque, escolha outro valor.";
			verificaSaldoNotas = false;
	
		}
		
	//Avisa ao usuário que o caixa está vazio.
		} else {
			compResp = "Caixa vazio, chame o operador";
		}
		
		System.out.println(nota100+nota50+nota20+nota10+nota5+nota2);
		
		if((nota100+nota50+nota20+nota10+nota5+nota2) <= 30){
			
			saque = true;
			
			//COMPÕE RELATORIO DE SAQUES
			saqueEfetuado = (nota100*100)+(nota50*50)+(nota20*20)+(nota10*10)+(nota5*5)+(nota2*2);		
			relatorio = String.valueOf(relatorio) + "R$: " + String.valueOf(saqueEfetuado) + ",00 \n";
			
		
				//Atribui a resposta na variável passada no código.
				resposta = compResp;
				
				//Limpa a composição
				compResp ="";
				
				//Zera as notas do saque para o próximo.
				nota100=0;nota50=0;nota20=0;nota10=0;nota5=0;nota2=0;
				
				//Se a cota mínima for atingida, o próximo saque será barrado.
				if (totalDisponivel<cotaMinima) verificaCotaMinima = true;
			
				//Atualiza no BD
				try {
					CaixaDAO.atualizaSaldoNoBanco(100,saldoNota100);
					CaixaDAO.atualizaSaldoNoBanco(50,saldoNota50);
					CaixaDAO.atualizaSaldoNoBanco(20,saldoNota20);
					CaixaDAO.atualizaSaldoNoBanco(10,saldoNota10);
					CaixaDAO.atualizaSaldoNoBanco(5,saldoNota5);
					CaixaDAO.atualizaSaldoNoBanco(2,saldoNota2);
				} catch (Exception e) {
					e.printStackTrace();
				}
		} else {
			resposta = "Saque ultrapassa máximo permitido de 30 cédulas";
			nota100=0;nota50=0;nota20=0;nota10=0;nota5=0;nota2=0;
			compResp="";
			atualizaSaldo();
		}
			
		return resposta;
	
	
}

//cota mínima a ser mantida, se menor bloqueia o saque.
public String armazenaCotaMinima(int minimo) {
	
	cotaMinima = minimo;
	
	if (totalDisponivel>cotaMinima) verificaCotaMinima = false;
	else  verificaCotaMinima = true;
	
String resposta = "Valor mínimo de saldo no caixa foi alterado para: R$" + minimo + ",00";
return resposta;

}



public static void main(String arg[]){

	GUI janela = new GUI();
	janela.setVisible(true);

	}

public static int getSaldoNota2() {
	return saldoNota2;
}

public static void setSaldoNota2(int saldoNota2) {
	CaixaEletronico.saldoNota2 = saldoNota2;
}

public static int getSaldoNota5() {
	return saldoNota5;
}

public static void setSaldoNota5(int saldoNota5) {
	CaixaEletronico.saldoNota5 = saldoNota5;
}

public static int getSaldoNota10() {
	return saldoNota10;
}

public static void setSaldoNota10(int saldoNota10) {
	CaixaEletronico.saldoNota10 = saldoNota10;
}

public static int getSaldoNota20() {
	return saldoNota20;
}

public static void setSaldoNota20(int saldoNota20) {
	CaixaEletronico.saldoNota20 = saldoNota20;
}

public static int getSaldoNota50() {
	return saldoNota50;
}

public static void setSaldoNota50(int saldoNota50) {
	CaixaEletronico.saldoNota50 = saldoNota50;
}

public static int getSaldoNota100() {
	return saldoNota100;
}

public static void setSaldoNota100(int saldoNota100) {
	CaixaEletronico.saldoNota100 = saldoNota100;
}

public static int getNota2() {
	return nota2;
}

public static void setNota2(int nota2) {
	CaixaEletronico.nota2 = nota2;
}

public static int getNota5() {
	return nota5;
}

public static void setNota5(int nota5) {
	CaixaEletronico.nota5 = nota5;
}

public static int getNota10() {
	return nota10;
}

public static void setNota10(int nota10) {
	CaixaEletronico.nota10 = nota10;
}

public static int getNota20() {
	return nota20;
}

public static void setNota20(int nota20) {
	CaixaEletronico.nota20 = nota20;
}

public static int getNota50() {
	return nota50;
}

public static void setNota50(int nota50) {
	CaixaEletronico.nota50 = nota50;
}

public static int getNota100() {
	return nota100;
}

public static void setNota100(int nota100) {
	CaixaEletronico.nota100 = nota100;
}

public static int getSaldoEmUso() {
	return saldoEmUso;
}

public static void setSaldoEmUso(int saldoEmUso) {
	CaixaEletronico.saldoEmUso = saldoEmUso;
}

public static int getValorRecebido() {
	return valorRecebido;
}

public static void setValorRecebido(int valorRecebido) {
	CaixaEletronico.valorRecebido = valorRecebido;
}

public static int getTotalDisponivel() {
	return totalDisponivel;
}

public static void setTotalDisponivel(int totalDisponivel) {
	CaixaEletronico.totalDisponivel = totalDisponivel;
}

public static int getCotaMinima() {
	return cotaMinima;
}

public static void setCotaMinima(int cotaMinima) {
	CaixaEletronico.cotaMinima = cotaMinima;
}

public static String getComporResposta() {
	return compResp;
}

public static void setComporResposta(String compResp) {
	CaixaEletronico.compResp = compResp;
}

public static String getRelatorio() {
	return relatorio;
}

public static void setRelatorio(String relatorio) {
	CaixaEletronico.relatorio = relatorio;
}

public static boolean isSaque() {
	return saque;
}

public static void setSaque(boolean saque) {
	CaixaEletronico.saque = saque;
}

}