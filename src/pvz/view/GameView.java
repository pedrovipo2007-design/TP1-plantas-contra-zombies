package pvz.view;

public interface GameView {

	// show methods ->
	void showGame();

	void showEndMessage();

	void showError(String message);

	void showMessage(String message);

	// get data from view ->
	String[] getPrompt();

	void printHelpMessage();//esto no se como lo vas a querer implementar si simplemente llamarlo con el show message normalo hacer uno especifico
}
