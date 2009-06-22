import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class Generator {
	
	private final FileWriter file;
	private final VarTable varTable = VarTable.getInstance();
	
	//Variables para generar codigo
	private final String cabecera = "#\n# UC3M - PROCESADORES DEL LENGUAJE\n# Codigo generado por los alumnos:\n# Adrian Lopez Perez\n# Daniel Conde Garcia\n###################################\n";
	private final String text = "\n\t\t.text";
	private final String globl = "\n\t\t.globl __start";
	private final String data = "\n\t\t.data";
	private NodeAnalyzer main;
	
	private final String printfint="printf_int:\n\t\tli $v0, 1\n\t\tsyscall\n\t\tla $a0, ln_str\n\t\tli $v0, 4\n\t\tsyscall\n\t\tjr $ra\t# retorna al invocador\n\n";
	private final String printffloat="printf_float:\n\t\tmtc1 $a1, $f12\n\t\tli $v0, 2\n\t\tsyscall\n\t\tla $a0, ln_str\n\t\tli $v0, 4\n\t\tsyscall\n\t\tjr $ra\t# retorna al invocador\n\n";
	private final String printfstr="printf_str:\n\t\tli $v0, 4\n\t\tsyscall\n\t\tjr $ra\t# retorna al invocador\n\n";
	
	public void setMain(NodeAnalyzer main) {
		this.main = main;
	}

	public Generator(String filename) throws IOException {
		String name = filename.substring(0, filename.indexOf('.'));
		file = new FileWriter(new File(name+".s"));
	}

	public void generate() {
		System.out.println("\nMostrando el arbol");
		main.print(0);
		
		try {
			file.write(cabecera);
			file.write(data);
			file.write(getDataCode());
			file.write(text);
			file.write(getTextCode());
			file.write(globl);
			file.write(getGloblCode());
			file.write(printfint);
			file.write(printffloat);
			file.write(printfstr);
			file.flush();
			file.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("\nFICHERO GENERADO");
	}

	private String getDataCode() {
		StringBuilder sb = new StringBuilder();
		sb.append("\n");
		sb.append(varTable.getDataCode());
		return sb.toString();
	}

	private String getGloblCode() {
		StringBuilder sb = new StringBuilder();
		sb.append("\n");
		sb.append(main.getGloblCode());
		return sb.toString();
	}

	private String getTextCode() {
		return "\n";
	}
}