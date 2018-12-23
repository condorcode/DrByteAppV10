package cl.appdrbyte.condor_code.drbyteappv10;

public class CotizaDatos {

    String serv_item;
    String serv_valor;

    public CotizaDatos() {
    }

    public CotizaDatos(String serv_item, String serv_valor) {
        this.serv_item = serv_item;
        this.serv_valor = serv_valor;
    }

    public String getServ_item() {
        return serv_item;
    }

    public void setServ_item(String serv_item) {
        this.serv_item = serv_item;
    }

    public String getServ_valor() {
        return serv_valor;
    }

    public void setServ_valor(String serv_valor) {
        this.serv_valor = serv_valor;
    }
}
