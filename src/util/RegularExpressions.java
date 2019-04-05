package util;

import java.util.regex.Pattern;

/**
 * Class - responsável por quebrar a entrada em tokens pré-definidos
 * @author Daniel, Danton e Adrian
 * @since 22/11/2017
 */
public class RegularExpressions {
    /**
     * NUMBER
     *      '-' deve aparecer uma ou nenhuma vez
     *      '[0-9]+' seguido por um mais números de 0 a 9
     */
    public static final String NUMBER = "-?[0-9]+";
    /**
     * OPERASION
     *      ''
     */
    public static final String OPERASION = "[-{1}|\\+{1}|*{1}|/{1}]";
    /**
     * SPACE
     *      ' '  espaço
     *      '\t' tab
     *      '\f' quebra de página
     *      '\r' início de lina
     *      '\n' nova linha
     */
    public static final String SPACE = "[ \t\f\r\n]+";
    /**
     * STRING
     *      '[a-zA-Z]+' uma ou mais letras
     */
    public static final String STRING = "[a-zA-Z]+";
    /**
     * TEXT
     *      '()'  exige este grupo de captura
     *      '\"'  inicia com uma abertura de aspas
     *      '\w+' contém um ou mais cacteres alfanuméricos
     *      '\"'  termina com um fechamento de aspas
     */
    public static final String TEXT = "(\"\\w+\")";
    /**
     * INCREMENT
     *      '()'    exige este grupo de captura
     *      '\+{2}' obriga a ter exatamente dois operadores de soma
     */
    public static final String INCREMENT = "(\\+{2})";
    /**
     * DECREMENT
     *      '()'    exige este grupo de captura
     *      '\-{2}' obriga a ter exatamente dois operadores de subtração
     */
    public static final String DECREMENT = "(\\-{2})";
    /**
     * BEGINCODEBLOCK
     *      '{' abertura de chaves
     * CLOSEBRASE
     *      '}' fechamento de chaves
     * OPENPARENTHESES
     *      '(' abertura de parênteses
     * CLOSEPARENTHESES
     *      ')' fechamento de parênteses
     * ENDLINE
     *      ';' fim de linha
     */
    public static final String BEGINCODEBLOCK       = "[{]";
    public static final String ENDCODEBLOCK      = "[}]";
    public static final String OPENPARENTHESES  = "[(]";
    public static final String CLOSEPARENTHESES = "[)]";
    public static final String ENDLINE          = "[;]";
    /**
     * BARCOMMENT
     *      '//'                                deve iniciar obrigatoriamente com um //
     *      '[a-zA-Z0-9á-úÁ-Ú-<>+/*:;,.?=)( ]+' seguido por um ou mais de qualquer caractere
     */
    public static final String BARCOMMENT = "//(\\w*^\\w*)*"; // //[a-zA-Z0-9á-úÁ-Ú-<>+/*:;,.?=)( ]+
    /**
     * SHARPCOMMENT
     *      '#'                                deve iniciar obrigatoriamente com um sustenido
     *      '[a-zA-Z0-9á-úÁ-Ú-<>+/*:;,.?=)( ]+' seguido por um ou mais de qualquer caractere
     */
    public static final String SHARPCOMMENT = "#[a-zA-Z0-9á-úÁ-Ú-<>+/*:;,.?=)( ]+";
    /**
     * SHARPCOMMENT
     *      '/\\*'                                deve iniciar obrigatoriamente com um /*
     *      '[a-zA-Z0-9á-úÁ-Ú-<>+/*:;,.?=)( ]+' seguido por um ou mais de qualquer caractere
     *      '\\*\/'                               deve terminar obrigatoriamente com um *\/
     */
    public static final String BLOCKCOMMENT = "(/\\*)[a-zA-Z0-9á-úÁ-Ú-<>+/*:;,.?=)( ]+(\\*/)";
    /**
     * VARIABLE
     *      '[a-zA-Z]+' um ou mais caracteres
     *      '\\s*?'     seguido de um ou mais espaços opcionalmente
     *      '='         seguido de um operador de atribuição
     */
    public static final String VARIABLE = "(\\$[a-zA-Z]+[a-zA-Z0-9]*\\s*?)"; //((inteiro)|(real)|(literal)|(booleano))?\\s+([a-zA-Z]+\\s*?=) ([a-zA-Z]+\\s*?=)
    /**
     * COMPARISION
     *      '' deve possuir qualquer um dos grupos de captura informados entre parênteses
     */
    public static final String COMPARISION = "(>=)|(<=)|(==)|(=)|(<>)|(>)|(<)";

    public static Pattern stringPatern(){

        StringBuilder tokenPatternsBuffer = new StringBuilder();

        tokenPatternsBuffer.append(String.format("(?<%s>%s)" , "BARCOMMENT"       , BARCOMMENT));
        tokenPatternsBuffer.append(String.format("(?<%s>%s)" , "SHARPCOMMENT"     , SHARPCOMMENT));
        tokenPatternsBuffer.append(String.format("(?<%s>%s)" , "BLOCKCOMMENT"     , BLOCKCOMMENT));        
        tokenPatternsBuffer.append(String.format("|(?<%s>%s)", "VARIABLE"         , VARIABLE));
        tokenPatternsBuffer.append(String.format("|(?<%s>%s)", "INCREMENT"        , INCREMENT));
        tokenPatternsBuffer.append(String.format("|(?<%s>%s)", "DECREMENT"        , DECREMENT));
        tokenPatternsBuffer.append(String.format("|(?<%s>%s)", "NUMBER"           , NUMBER));
        tokenPatternsBuffer.append(String.format("|(?<%s>%s)", "OPERASION"         , OPERASION));
        tokenPatternsBuffer.append(String.format("|(?<%s>%s)", "COMPARISION"      , COMPARISION));
        tokenPatternsBuffer.append(String.format("|(?<%s>%s)", "SPACE"       , SPACE));
        tokenPatternsBuffer.append(String.format("|(?<%s>%s)", "STRING"           , STRING));
        tokenPatternsBuffer.append(String.format("|(?<%s>%s)", "TEXT"       , TEXT));
        tokenPatternsBuffer.append(String.format("|(?<%s>%s)", "BEGINCODEBLOCK"       , BEGINCODEBLOCK));
        tokenPatternsBuffer.append(String.format("|(?<%s>%s)", "ENDCODEBLOCK"      , ENDCODEBLOCK));
        tokenPatternsBuffer.append(String.format("|(?<%s>%s)", "OPENPARENTHESES"  , OPENPARENTHESES));
        tokenPatternsBuffer.append(String.format("|(?<%s>%s)", "CLOSEPARENTHESES" , CLOSEPARENTHESES));
        tokenPatternsBuffer.append(String.format("|(?<%s>%s)", "ENDLINE"          , ENDLINE));
                
        Pattern tokenPatterns = Pattern.compile(tokenPatternsBuffer.toString());
        return tokenPatterns;
    }
}
