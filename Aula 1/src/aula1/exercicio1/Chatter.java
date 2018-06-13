package aula1.exercicio1;

public class Chatter {
	
	String username;
	ClienteAula1 cliente;
	ServidorAula1 servidor;
	
	public Chatter(String username, int porta, String hostAddress)
	{
		servidor = new ServidorAula1(username, porta);
		cliente = new ClienteAula1(hostAddress, porta);
		
	}
	
	public void update()
	{
		System.out.println("Chamando servidor.");
		servidor.receive();
		System.out.println("Chamando cliente.");
		cliente.send();
		System.out.println("Fim do update.");
	}
	
	public boolean isRunning()
	{
		return cliente.isRunning();
	}
	
}
