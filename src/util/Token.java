package util;

/**
 * Class - responsável por declarar os tokens identificados
 * @author Daniel, Danton e Adrian
 * @since 23/10/2017
 */
public class Token {
    /**
     * token - token delimitado pelo analisador léxico
     * description - descrição do tipo do token
     */
    private String token;
    private Types.Token type;
    private boolean sytantic;

    // Construtor padrão
    public Token() {
    }
    
    // Construtor parametrizado
    public Token(String token, Types.Token description) {
        this.token = token;
        this.type = description;
    }

    // getters e setters
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Types.Token getType() {
        return type;
    }

    public void setType(Types.Token type) {
        this.type = type;
    }

    public boolean getSytantic() {
        return sytantic;
    }

    public void setSytantic(boolean sytantic) {
        this.sytantic = sytantic;
    }
    
    
    
    @Override
    /**
     * Function - retornar o tipo e o token declarado
     */
    public String toString() {
        return String.format("   %s : %s", this.token, this.type.name());
    }
}
