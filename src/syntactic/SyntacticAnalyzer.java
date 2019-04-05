package syntactic;

import java.util.ArrayList;
import util.Token;
import util.Error;
import util.Types;

/**
 * Class responsável pelo analisador sintático
 * @author Daniel, Danton e Adrian
 * @since 27/11/2017
 */
public class SyntacticAnalyzer {
    ArrayList<Types.Token> stack = new ArrayList<>(); // pilha de tokens esperados no fim
    private ArrayList<Token> tokens = new ArrayList<>(); // lista de tokens
    private ArrayList<Error> errors = new ArrayList<>(); // lista de erros
    boolean mainFunctionFound = false; // função principal encontrada

    // getters
    public ArrayList<Token> getTokens() {
        return tokens;
    }

    public ArrayList<Error> getErrors() {
        return errors;
    }
    
    /**
     * Construtor parametrizado
     * @param tokens tokens identificados provindos do analisador léxico
     * @param errors erros econtrados pelo analisador léxico 
     */
    public SyntacticAnalyzer(ArrayList<Token> tokens, ArrayList<Error> errors){
        this.tokens = tokens;
        this.errors = errors;
    }  
    
    /**
     * Function responsável por fazer a análise sintática
     */
    public void syntatic(){
        // procura a expressão principal
        while(!mainFunctionFound && !tokens.isEmpty()){  
            switch(tokens.get(0).getType()){
                case comment:
                    tokens.remove(0);
                break;
                case mainFunction:
                    mainFunctionFound = true;
                    tokens.remove(0);
                    stack.add(Types.Token.beginCodeBlock);
                break;
                default:
                    errors.add(new Error(tokens.get(0).getToken(), Types.Error.syntatic, "expressão inesperada."));
                    tokens.remove(0);
                break;
            }
        }
        
        // procura o inicio do bloco de código
        for(int i = 0; i < tokens.size(); i++){
            if(tokens.get(i).getType().equals(Types.Token.beginCodeBlock)){
                tokens.get(i).setSytantic(true);
                stack.remove(0);
                stack.add(Types.Token.endCodeBlock);
                break;
            }
            else{
                errors.add(new Error(tokens.get(i).getToken(), Types.Error.syntatic, "expressão inesperada."));
                tokens.get(i).setType(Types.Token.error);
            }
        }
        
        // procura o fim do bloco do código
        for(int i = 0; i < tokens.size(); i++){
            if(tokens.get(i).getType().equals(Types.Token.endCodeBlock)){
                tokens.get(i).setSytantic(true);
                stack.remove(0);
                
                for(int j = i+1; j < tokens.size(); j++){
                    errors.add(new Error(tokens.get(j).getToken(), Types.Error.syntatic, "expressão inesperada."));
                    tokens.get(j).setType(Types.Token.error);
                }
                
                break;
            }
        }
        
        // valida função se - fimse
        for(int i = 0; i < tokens.size(); i++){
            if(!tokens.get(i).getSytantic() && tokens.get(i).getType().equals(Types.Token.beginFunctionIf)){
                tokens.get(i).setSytantic(true);
                stack.add(Types.Token.beginFunctionIf);
            }
            else if(!tokens.get(i).getSytantic() && tokens.get(i).getType().equals(Types.Token.doFunctionIfFor) && !tokens.get(i-1).getType().equals(Types.Token.beginFunctionIf)){
                for(int j = 0; j < stack.size(); j++){
                    if(stack.get(j).equals(Types.Token.beginFunctionIf)){
                        tokens.get(j).setSytantic(true);
                        stack.remove(j);
                        stack.add(Types.Token.doFunctionIfFor);
                    }
                }   
            }
            else if(!tokens.get(i).getSytantic() && tokens.get(i).getType().equals(Types.Token.endFunctionIf)){
                for(int j = 0; j < stack.size(); j++){
                    if(stack.get(j).equals(Types.Token.doFunctionIfFor)){
                        tokens.get(j).setSytantic(true);
                        stack.remove(j);
                    }
                }   
            }
        }
        
        // valida função para - ate - faca - fimpara
        for(int i = 0; i < tokens.size(); i++){
            if(!tokens.get(i).getSytantic() && tokens.get(i).getType().equals(Types.Token.beginFunctionFor)){
                tokens.get(i).setSytantic(true);
                stack.add(Types.Token.beginFunctionFor);
            }
            else if(!tokens.get(i).getSytantic() && tokens.get(i).getType().equals(Types.Token.betweenFunctionFor) && !tokens.get(i-1).getType().equals(Types.Token.beginFunctionFor)){
                for(int j = 0; j < stack.size(); j++){
                    if(stack.get(j).equals(Types.Token.beginFunctionFor)){
                        tokens.get(j).setSytantic(true);
                        stack.remove(j);
                        stack.add(Types.Token.betweenFunctionFor);
                    }
                }   
            }
            else if(!tokens.get(i).getSytantic() && tokens.get(i).getType().equals(Types.Token.doFunctionIfFor) && !tokens.get(i-1).getType().equals(Types.Token.betweenFunctionFor)){
                for(int j = 0; j < stack.size(); j++){
                    if(stack.get(j).equals(Types.Token.betweenFunctionFor)){
                        tokens.get(j).setSytantic(true);
                        stack.remove(j);
                        stack.add(Types.Token.doFunctionIfFor);
                    }
                }   
            }
            else if(!tokens.get(i).getSytantic() && tokens.get(i).getType().equals(Types.Token.endFunctionFor)){
                for(int j = 0; j < stack.size(); j++){
                    if(stack.get(j).equals(Types.Token.doFunctionIfFor)){
                        tokens.get(j).setSytantic(true);
                        stack.remove(j);
                    }
                }   
            }
        }
        
        // analisa os demais tokens
        for(int i = 0; i < tokens.size(); i++){
            switch(tokens.get(i).getType()){
                case endLine:
                    if(i != 0 && i < tokens.size()-1 
                              && ((tokens.get(i-1).getType().equals(Types.Token.variable))
                              || (tokens.get(i-1).getType().equals(Types.Token.text))
                              || (tokens.get(i-1).getType().equals(Types.Token.increment))
                              || (tokens.get(i-1).getType().equals(Types.Token.decrement))
                              || (tokens.get(i-1).getType().equals(Types.Token.number)))){
                        tokens.get(i).setSytantic(true);
                    }
                    else{
                        errors.add(new Error(tokens.get(i).getToken(), Types.Error.syntatic, "expressão espera operando."));
                        tokens.get(i).setSytantic(true);
                    }
                break;
                case variable:
                    if(i != 0 && i < tokens.size()-1 
                              && ((tokens.get(i+1).getType().equals(Types.Token.attribuition))
                              || (tokens.get(i+1).getType().equals(Types.Token.arithmeticOperator))
                              || (tokens.get(i+1).getType().equals(Types.Token.relationalOperator))
                              || (tokens.get(i+1).getType().equals(Types.Token.endLine))
                              || (tokens.get(i+1).getType().equals(Types.Token.doFunctionIfFor))
                              || (tokens.get(i+1).getType().equals(Types.Token.betweenFunctionFor))
                              || (tokens.get(i+1).getType().equals(Types.Token.decrement))
                              || (tokens.get(i+1).getType().equals(Types.Token.increment)))){
                        tokens.get(i).setSytantic(true);
                    }
                    else{
                        errors.add(new Error(tokens.get(i).getToken(), Types.Error.syntatic, "expressão espera operando ou fim de linha."));
                        tokens.get(i).setSytantic(true);
                    }
                break;
                case relationalOperator:
                    if(!tokens.get(i).getSytantic() && i != 0 && i < tokens.size()-1 
                              && ((tokens.get(i-1).getType().equals(Types.Token.variable))
                              || (tokens.get(i-1).getType().equals(Types.Token.text))
                              || (tokens.get(i-1).getType().equals(Types.Token.number)))
                              && ((tokens.get(i+1).getType().equals(Types.Token.variable))
                              || (tokens.get(i+1).getType().equals(Types.Token.text))
                              || (tokens.get(i+1).getType().equals(Types.Token.number)))){
                        tokens.get(i).setSytantic(true);
                        tokens.get(i-1).setSytantic(true);
                        tokens.get(i+1).setSytantic(true);
                    }
                    else{
                        errors.add(new Error(tokens.get(i).getToken(), Types.Error.syntatic, "expressão espera operando."));
                        tokens.get(i).setSytantic(true);
                    }
                break;
                case arithmeticOperator:
                    if(!tokens.get(i).getSytantic() && i != 0 && i < tokens.size()-1 
                              && ((tokens.get(i-1).getType().equals(Types.Token.variable))
                              || (tokens.get(i-1).getType().equals(Types.Token.text))
                              || (tokens.get(i-1).getType().equals(Types.Token.number)))
                              && ((tokens.get(i+1).getType().equals(Types.Token.variable))
                              || (tokens.get(i+1).getType().equals(Types.Token.text))
                              || (tokens.get(i+1).getType().equals(Types.Token.number)))){
                        tokens.get(i).setSytantic(true);
                        tokens.get(i-1).setSytantic(true);
                        tokens.get(i+1).setSytantic(true);
                    }
                    else{
                        errors.add(new Error(tokens.get(i).getToken(), Types.Error.syntatic, "expressão espera operando."));
                        tokens.get(i).setSytantic(true);
                    }
                break;
                case attribuition:
                    if(!tokens.get(i).getSytantic() && i != 0 && i < tokens.size()-1 
                              && (tokens.get(i-1).getType().equals(Types.Token.variable))
                              && ((tokens.get(i+1).getType().equals(Types.Token.variable))
                              || (tokens.get(i+1).getType().equals(Types.Token.text))
                              || (tokens.get(i+1).getType().equals(Types.Token.number)))){
                        tokens.get(i).setSytantic(true);
                        tokens.get(i-1).setSytantic(true);
                        tokens.get(i+1).setSytantic(true);
                    }
                    else{
                        errors.add(new Error(tokens.get(i).getToken(), Types.Error.syntatic, "expressão espera operando."));
                        tokens.get(i).setSytantic(true);
                    }
                break;
                case increment:
                    if(!tokens.get(i).getSytantic() && i != 0 && i < tokens.size()-1 
                              && (tokens.get(i+1).getType().equals(Types.Token.endLine))
                              && ((tokens.get(i-1).getType().equals(Types.Token.variable))
                              || (tokens.get(i-1).getType().equals(Types.Token.number)))){
                        tokens.get(i).setSytantic(true);
                        tokens.get(i-1).setSytantic(true);
                        tokens.get(i+1).setSytantic(true);
                    }
                    else{
                        errors.add(new Error(tokens.get(i).getToken(), Types.Error.syntatic, "expressão espera operando ou fim de linha."));
                        tokens.get(i).setSytantic(true);;
                    }
                break;
                case decrement:
                    if(!tokens.get(i).getSytantic() && i != 0 && i < tokens.size()-1 
                              && (tokens.get(i+1).getType().equals(Types.Token.endLine))
                              && ((tokens.get(i-1).getType().equals(Types.Token.variable))
                              || (tokens.get(i-1).getType().equals(Types.Token.number)))){
                        tokens.get(i).setSytantic(true);
                        tokens.get(i-1).setSytantic(true);
                        tokens.get(i+1).setSytantic(true);
                    }
                    else{
                        errors.add(new Error(tokens.get(i).getToken(), Types.Error.syntatic, "expressão espera operando ou fim de linha."));
                        tokens.get(i).setSytantic(true);
                    }
                break;
                case number:
                    if(i != 0 && i < tokens.size()-1 
                              && ((tokens.get(i-1).getType().equals(Types.Token.arithmeticOperator))
                              || (tokens.get(i-1).getType().equals(Types.Token.relationalOperator))
                              || (tokens.get(i-1).getType().equals(Types.Token.attribuition))
                              || (tokens.get(i-1).getType().equals(Types.Token.betweenFunctionFor)))
                              && ((tokens.get(i+1).getType().equals(Types.Token.endLine))
                              || (tokens.get(i+1).getType().equals(Types.Token.arithmeticOperator))
                              || (tokens.get(i+1).getType().equals(Types.Token.increment))
                              || (tokens.get(i+1).getType().equals(Types.Token.decrement))
                              || (tokens.get(i+1).getType().equals(Types.Token.relationalOperator))
                              || (tokens.get(i+1).getType().equals(Types.Token.doFunctionIfFor))
                              || (tokens.get(i+1).getType().equals(Types.Token.betweenFunctionFor)))){
                        tokens.get(i).setSytantic(true);
                        tokens.get(i-1).setSytantic(true);
                        tokens.get(i+1).setSytantic(true);
                    }
                    else{
                        errors.add(new Error(tokens.get(i).getToken(), Types.Error.syntatic, "expressão espera operador ou fim de linha."));
                        tokens.get(i).setSytantic(true);
                    }
                break;
                case text:
                    if(i != 0 && i < tokens.size()-1 
                              && ((tokens.get(i-1).getType().equals(Types.Token.arithmeticOperator))
                              || (tokens.get(i-1).getType().equals(Types.Token.relationalOperator))
                              || (tokens.get(i-1).getType().equals(Types.Token.attribuition))
                              || (tokens.get(i-1).getType().equals(Types.Token.betweenFunctionFor)))
                              && ((tokens.get(i+1).getType().equals(Types.Token.endLine))
                              || (tokens.get(i+1).getType().equals(Types.Token.arithmeticOperator))
                              || (tokens.get(i+1).getType().equals(Types.Token.increment))
                              || (tokens.get(i+1).getType().equals(Types.Token.decrement))
                              || (tokens.get(i+1).getType().equals(Types.Token.relationalOperator))
                              || (tokens.get(i+1).getType().equals(Types.Token.doFunctionIfFor))
                              || (tokens.get(i+1).getType().equals(Types.Token.betweenFunctionFor)))){
                        tokens.get(i).setSytantic(true);
                        tokens.get(i-1).setSytantic(true);
                        tokens.get(i+1).setSytantic(true);
                    }
                    else{
                        errors.add(new Error(tokens.get(i).getToken(), Types.Error.syntatic, "expressão espera operador ou fim de linha."));
                        tokens.get(i).setSytantic(true);
                    }
                break;
            }
        }
        
        for(int i = 0; i < stack.size(); i++){
            errors.add(new Error(new Token("", stack.get(i)).toString(), Types.Error.syntatic, "função inválida."));
        }
    }
}
