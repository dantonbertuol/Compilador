package util;

import util.Types;
import java.util.HashMap;
import java.util.Map;

/**
 * Class - responsável por definir quais os tokens reservados da linguagem
 * @author Daniel, Danton e Adrian
 * @since 20/11/2017
 */
public class PrivateLanguage {
    public Map <String, Types.Token> privateLanguageList;

    public PrivateLanguage() {
	this.privateLanguageList = new HashMap<>();
		
        // DELIMITADORES
        // 'início de código' - {
        this.privateLanguageList.put("{", Types.Token.beginCodeBlock);
        // 'fim de código' - }
        this.privateLanguageList.put("}", Types.Token.endCodeBlock);
        // 'fim de linha' - ;
        this.privateLanguageList.put(";", Types.Token.endLine);
        // 'comentário de linha' - //
        this.privateLanguageList.put("//", Types.Token.comment);
        // 'comentário de linha' - #
        this.privateLanguageList.put("#", Types.Token.comment);
        // ´início de bloco de comentário' - /*
        this.privateLanguageList.put("/*", Types.Token.comment);
        // 'fim de bloco de comentário' - */
        this.privateLanguageList.put("*/", Types.Token.comment);
        // 'string' - "
        this.privateLanguageList.put("\"", Types.Token.string);
        
        // OPERADORES RELACIONAIS
        // 'menor que ou igual' - <=
        this.privateLanguageList.put("<=", Types.Token.relationalOperator);
        // 'maior que ou igual' - >=
        this.privateLanguageList.put(">=", Types.Token.relationalOperator);
        // 'diferente de' - <>
        this.privateLanguageList.put("<>", Types.Token.relationalOperator);
        // 'equivalente a' - ==
        this.privateLanguageList.put("==", Types.Token.relationalOperator);
        // 'menor que' - <
        this.privateLanguageList.put("<", Types.Token.relationalOperator);
        // 'maior que' - >
        this.privateLanguageList.put(">", Types.Token.relationalOperator);
        
        // OPERADORES ARITMÉTICOS
        // 'soma' - +
        this.privateLanguageList.put("+", Types.Token.arithmeticOperator);
        // 'subtração' - -
        this.privateLanguageList.put("-", Types.Token.arithmeticOperator);
        // 'multiplicação' - *
        this.privateLanguageList.put("*", Types.Token.arithmeticOperator);
        // 'divisão' - /
        this.privateLanguageList.put("/", Types.Token.arithmeticOperator);
        
        // ATRIBUIDORES
        // 'atribuição' - =
        this.privateLanguageList.put("=", Types.Token.attribuition);
        // 'incremento' - ++
        this.privateLanguageList.put("++", Types.Token.increment);
        // 'decremento' - --
        this.privateLanguageList.put("--", Types.Token.decrement);
        
        // FUNÇÕES
        // 'função principal do programa' - principal
        this.privateLanguageList.put("principal", Types.Token.mainFunction);
        // 'inicio da operação condicional' - se
        this.privateLanguageList.put("se", Types.Token.beginFunctionIf);
        // 'fim da operação condicional' - fimse
        this.privateLanguageList.put("fimse", Types.Token.endFunctionIf);
        // 'inicio da operacao para' - para
        this.privateLanguageList.put("para", Types.Token.beginFunctionFor);
        // 'meio da operacao para' - ate
        this.privateLanguageList.put("ate", Types.Token.betweenFunctionFor);
        // 'fim da operacao para' - fimpara
        this.privateLanguageList.put("fimpara", Types.Token.endFunctionFor);
        // 'faca da operacao para e se' - faca
        this.privateLanguageList.put("faca", Types.Token.doFunctionIfFor);
        
        // TIPOS DE VARIÁVEIS
        // 'inteio' - inteiro
        //this.privateLanguageList.put("inteiro", Types.Token.variableType);
        // 'real' - real
        //this.privateLanguageList.put("real", Types.Token.variableType);
        // 'literal' - literal
        //this.privateLanguageList.put("literal", Types.Token.variableType);
        // 'booleano' - booleano
        //this.privateLanguageList.put("booleano", Types.Token.variableType);
    }
}
