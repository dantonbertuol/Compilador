package lexical;

import util.Error;
import util.PrivateLanguage;
import util.RegularExpressions;
import util.Token;
import util.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class - responsável por separar o código de entrada em tokens e identificar os erros
 * @author Daniel, Danton e Adrian
 * @since 26/11/2017
 */
public class LexicalAnalyzer {
    /**
     * Analisador léxico
     * @param input - código fonte
     * @return lista de tokens e erros encontrados na análise léxica
     */

    public static Object[] analyze(String input) {
        // listas de tokens identificados 
	ArrayList<Token> tokens = new ArrayList<>();
        // lista de erros encontrados
	ArrayList<Error> errors = new ArrayList<>();		
	// lista de variáveis 
	Map <String, Types.Token> variables = new HashMap<>();		
	// quebra o código em linhas
	String[] lines = input.split("\\n");
        // tipos de tokens
	Types.Token TokenIds;
        // expressões regulares
	PrivateLanguage privateLanguage = new PrivateLanguage();
        // padrões de tokens que devem se encontrados
	Pattern regularExpressions = RegularExpressions.stringPatern();

        // itera entre as linhas do código de entrada
        for (String line : lines) {
            // busca tokens na linha iterada através das expressões regulares
            Matcher matcher = regularExpressions.matcher(line);
            // varre a sequencia de caracteres encontrados um a um
            while (matcher.find()) {
                // armazena a sequencia encontrada
                String sequenceFound = matcher.group();

                if (sequenceFound.matches(RegularExpressions.BARCOMMENT)) {
                    tokens.add(new Token(sequenceFound, Types.Token.comment));
                }

                else if (sequenceFound.matches(RegularExpressions.SHARPCOMMENT)) {
                    tokens.add(new Token(sequenceFound, Types.Token.comment));
                }

                else if (sequenceFound.matches(RegularExpressions.BLOCKCOMMENT)) {
                    tokens.add(new Token(sequenceFound, Types.Token.comment));
                }
                
                else if (privateLanguage.privateLanguageList.containsKey(sequenceFound)) {
                    tokens.add(new Token(sequenceFound, privateLanguage.privateLanguageList.get(sequenceFound)));
                } 
                
                else if (variables.containsKey(sequenceFound.trim())){
                    // trim() remove os espaços antes e depois do identificador
                    TokenIds = variables.get(sequenceFound.trim());
                    tokens.add(new Token(sequenceFound, TokenIds));
                }
                
                else {
                    if (sequenceFound.matches(RegularExpressions.NUMBER)) {
                        tokens.add(new Token(sequenceFound, Types.Token.number));
                    }
                    
                    else if (sequenceFound.matches(RegularExpressions.VARIABLE)) {
                        sequenceFound = sequenceFound.replace("=", " ");
                        tokens.add(new Token(sequenceFound, Types.Token.variable));
                    } 
                    
                    else if (sequenceFound.matches(RegularExpressions.TEXT)) {
                        sequenceFound = sequenceFound.replace('"', ' ');
                        TokenIds = Types.Token.text;
                        tokens.add(new Token(sequenceFound, TokenIds));
                    } 
                    
                    else if (sequenceFound.matches(RegularExpressions.SPACE)) {
                        // espaços em branco, apenas para validar a aferição
                    }
                    
                    else {
                        //tokens.add(new Token(sequenceFound, Types.Token.error));
                        errors.add(new Error(sequenceFound, Types.Error.lexical, "identificador inválido."));
                    }
                }
            }
        }

        return (new Object[] { tokens, errors });
    }
}
