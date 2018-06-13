package aula1.exercicio1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClienteAula1 {
	
	String host;
	int porta;
	boolean running = true;
	public ClienteAula1(String hostname, int portNumber)
	{
		this.host = hostname; this.porta = portNumber;
	}
	
	public void send()
	{
		if(running)
			sendMessage(host, porta);
	}
	
	private void sendMessage(String hostName, int portNumber)
	{
		try (
			Socket clientSocket = new Socket(hostName, portNumber);
			PrintWriter saida = new PrintWriter(clientSocket.getOutputStream(), true);
			BufferedReader entrada = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));	
			){
				String mensagemCliente;
				if(stdIn.ready())
				{
					while((mensagemCliente = stdIn.readLine()) != null){
						if(isQuittingMessage(mensagemCliente))
						{
							quit();
							break;
						}
						saida.println(mensagemCliente);
					}
				}
			}
			catch (UnknownHostException e)		{
					System.err.println("Don't know about host "+hostName);
					System.err.println(e.getMessage());
				}
			catch (IOException e) {
				System.err.println("Não foi possível exibir receber"
								+ " a entrada. \n"+e.getMessage());
			} 
	}
	
	public boolean isRunning()
	{
		return running;
	}
	
	private boolean isQuittingMessage(String msg)
	{
		return msg.toLowerCase().equals("sair");
	}
	

	private void quit()
	{
		running = false;
		System.out.println("Cliente saindo");
	}
	
	
}
