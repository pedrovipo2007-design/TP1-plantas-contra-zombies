package pvz.view;

public interface GameView { // incluye los metodos que implementa GamePrinter (no que usa)

	// show methods ->
	void showGame();

	void showEndMessage();

	void showError(String message);

	void showMessage(String message);

	// get data from view ->
	String[] getPrompt();

	void printHelpMessage();

	// esto no se como lo vas a querer implementar si simplemente llamarlo con el
	// show message normal o hacer uno especifico
	// creo que tenemos que llamar a cada comando en concreto para que sea
	// independiente
}
