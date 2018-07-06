package wumpus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Joystick {
	private BufferedReader br;
	private static final Set<String> ACTIONS = new HashSet<String>(Arrays.asList(new String[] {"avanzar", "disparar", "girar izquierda", "girar derecha"}));
	
	public Joystick() {
		br = new BufferedReader(new InputStreamReader(System.in));
	}

	public String actionLine() {
		this.writeLine("Siguiente accion: (avanzar, disparar, girar izquierda, girar derecha)");
		String action = this.readLine();
		return checkAction(action);
	}
	
	public String checkAction(String action) {
		if (Joystick.ACTIONS.contains(action.trim())) return action.trim();
		return "error";
	}
	
	public String readLine() {
		String line = "";
		try {
			line = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return line;
	}
	
	public void writeLine(String line) {
		if (!line.isEmpty()) System.out.println(line);
	}
}
