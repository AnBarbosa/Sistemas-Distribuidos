package aula1.exercicio1;

public class Main {

	static final int PORTA = 10000;
	public static void main(String...strings)
	{
		String nome = "TESTE";
		if(strings.length > 0)
			nome = strings[0];
		
		chatter(nome);
		//ServidorAula1 servidor = new ServidorAula1("Andre", 10000);
//		while(true)
//		{
//			servidor.receive();
//		}
		
	}

	private static void chatter(String nome)
	{
		Chatter chatter = new Chatter(nome, PORTA, "localhost");
		while(chatter.isRunning())
		
		{
			chatter.update();
		}
	}
}
