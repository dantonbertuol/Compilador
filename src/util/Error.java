package util;

/**
 * Class - responsável por declarar os erros encontrados
 * @author Daniel, Danton e Adrian
 * @since - 21/11/2017
 */
public class Error {
    private Types.Error type;
    private String message;
    private String token;

    // Construtor padrão
    public Error() {
    }

    // Construtor parametrizado
    public Error(String token, Types.Error type, String message) {
        this.token = token;
        this.type = type;
        this.message = message;
    }

    // getters e setters
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    
    public Types.Error getType() {
        return type;
    }

    public void setType(Types.Error type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    @Override
    /**
     * Function - retonar tipo e o erro declarado
     */
    public String toString() {
        return String.format("   %s : %s : %s", this.type, this.token, this.message);
    }
}
