package validacoes;

import java.util.regex.*;

public class Validacoes {
	
	public boolean validaTelefone(String telefone) {
		
		String regex = "^\\((?:[14689][1-9]|2[12478]|3[1234578]|5[1345]|7[134579])\\) (?:[2-8]|9[1-9])[0-9]{3}\\-[0-9]{4}$";
		Pattern pattern = Pattern.compile(regex);
	    Matcher matcher = pattern.matcher(telefone);
	    return (matcher.matches());
	}
}
