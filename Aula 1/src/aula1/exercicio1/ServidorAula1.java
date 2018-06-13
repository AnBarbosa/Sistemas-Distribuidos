package aula1.exercicio1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorAula1 {

	String username;
	int porta;
	boolean running = true;
	
	public ServidorAula1(String user, int porta)
	{
		this.username = user;
		this.porta = porta;
	}
	
	public void receive()
	{
		Runnable task  = () -> System.out.print("Recebendo");
		
		if(running)		{
			System.out.println("Dentro do loop");
			receiveMessage(porta);
			task.run();
		}
	}
	
	private void receiveMessage(int portNumber)
	{
		try (
			ServerSocket serverSocket = new ServerSocket(portNumber);
			Socket clientSocket = serverSocket.accept();
			PrintWriter saida = new PrintWriter(clientSocket.getOutputStream(), true);
			BufferedReader entrada = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				
			)
		{
			System.out.println("Rodando antes do if");
				String mensagemCliente;
				if(entrada.ready())
				{
					System.out.println("Recebido in");
					while((mensagemCliente = entrada.readLine()) != null){
						if(isQuittingMessage(mensagemCliente))
						{		
							quit();
							break;
						
						}
						
						saida.println(username+": "+mensagemCliente);
						System.out.println(username+": "+mensagemCliente);
					}
				}
			}
			catch (IOException e) {
				System.err.println("Porta "+porta+"Não foi possível exibir receber"
								+ " a entrada. \n"+e.getMessage());
				e.printStackTrace();
			} 
		
	}
	
	private boolean isQuittingMessage(String msg)
	{
		return msg.toLowerCase().equals("sair");
	}
	

	private void quit()
	{
		running = false;
		System.out.println("Servidor saindo");
	}
}
