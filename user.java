import java.io.Serializable;

public class user implements Serializable {
    private String name;
    private int securityLevel;

    user(String name, int securityLevel){
        this.name = name;
        this.securityLevel = securityLevel;
    }

    public String getName() {
        return this.name;
    }

    public int getSecurityLevel(){
        return this.securityLevel;
    }

    public void setSecurityLevel(int securityLevel) {
        this.securityLevel = securityLevel;
    }

    @Override
    public String toString(){
        return this.name + " Security level: " + this.securityLevel;
    }
}


