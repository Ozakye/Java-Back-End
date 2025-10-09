package singleton;

public class ConfigManager {
    private static ConfigManager instance;
    private String ambiente;

    private ConfigManager() {
        this.ambiente = "Produção";
    }

    public static ConfigManager getInstance() {
        if (instance == null) {
            instance = new ConfigManager();
        }
        return instance;
    }

    public String getAmbiente() {
        return ambiente;
    }

    public void setAmbiente(String ambiente) {
        this.ambiente = ambiente;
    }
}