package model;

import java.util.regex.*;

public class Validacoes {
	
	public boolean validaTelefone() {
	    Pattern pattern = pattern.compile("^\((?:[14689][1-9]|2[12478]|3[1234578]|5[1345]|7[134579])\) (?:[2-8]|9[1-9])[0-9]{3}\-[0-9]{4}$");
	    Matcher matcher = pattern.matcher("(202) 555-0125");
	    return (matcher.matches());
	}
}
