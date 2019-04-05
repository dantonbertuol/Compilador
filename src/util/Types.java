package util;

/**
 * ENUMERADOR - responsável por armazenar os tipos de tokens que devem ser classificados
 * @author Daniel, Danton e Adrian
 * @since 20/11/2017
 */
public class Types {
    public static enum Token {
        beginCodeBlock, // {
        endCodeBlock, // }
        endLine, // ;
        comment, // // | # | /**/
        string, // "
        relationalOperator, // operadores relacionais
        arithmeticOperator, // operadores aritméticos
        increment, // ++
        decrement, // --
        attribuition, // =
        number, // números
        mainFunction, // principal
        beginFunctionIf, // se
        endFunctionIf, // fimse
        beginFunctionFor, // para
        betweenFunctionFor, // ate
        endFunctionFor, // fimpara
        doFunctionIfFor, // faca - se e para
        variableType, // tipos de variáveis
        variable, // variáveis
        text, // strings
        error // erro
    }
    
    public static enum Error {
        lexical, // léxico
        syntatic, // sintático
        danger // perigo
    }
}
