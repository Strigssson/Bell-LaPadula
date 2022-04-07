import java.io.Serializable;

public class file implements Serializable {
    private String name;
    private String type;
    private int securityLevel;

    file(String name, String type, int securityLevel){
        this.name = name;
        this.type = type;
        this.securityLevel = securityLevel;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getSecurityLevel() {
        return securityLevel;
    }

    public void setSecurityLevel(int securityLevel) {
        this.securityLevel = securityLevel;
    }

    @Override
    public String toString(){
        return this.type + " " + this.name + " Security level: " + this.securityLevel;
    }
}
