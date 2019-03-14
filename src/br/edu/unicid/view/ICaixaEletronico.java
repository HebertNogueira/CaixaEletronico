package br.edu.unicid.view;

/**
* Interface (contrato) para utilizacao da interface grafica.
*  Nesse  contrato  e  definido  as  operacoes  de  entrada  e  saida  de 
dinheiro do
caixa eletronico
*/
public interface ICaixaEletronico{
/**
* Pega o valor total disponivel no caixa e
letronico
* 
@retorna 
uma string formatada com o valor total disponivel
*/
public String pegaValorTotalDisponivel();
/**
* Efetua o saque
* 
@param 
valor a ser sacado
* 
@retorna 
uma string formatada informando o resultado da operacao
*/
public String sacar(int valor);
/**
*  Pega  um  relatorio  informando  as  celulas  e  a  quantidade  de  celula 
disponivel
* 
@retorna 
uma string formatada com as celula e suas quantidades
*/
public String pegaRelatorioCedulas();
/**
* Efetua a reposicao de cedulas
* 
@param 
cedula d
e reposicao
* 
@param 
quantidade de cedulas para reposicao
* 
@retorna 
uma string formatada informando o resultado da operacao
*/
public String reposicaoCedulas(int cedula, int quantidade);
/**
* Efetua a leitura da cota minima de atendimento
* 
@para
m 
minimo
* 
@retorna 
uma string formatada informando o resultado da operacao
*/
public String armazenaCotaMinima(int minimo);
}