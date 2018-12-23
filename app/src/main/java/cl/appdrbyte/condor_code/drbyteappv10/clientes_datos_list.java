package cl.appdrbyte.condor_code.drbyteappv10;

public class clientes_datos_list {
    String nom_cli;
    String nom_equipo;
    String num_serie;
    String estado;
    String fecha;

    public clientes_datos_list() {
    }

    public clientes_datos_list(String nom_cli, String nom_equipo, String num_serie, String estado, String fecha) {
        this.nom_cli = nom_cli;
        this.nom_equipo = nom_equipo;
        this.num_serie = num_serie;
        this.estado = estado;
        this.fecha = fecha;
    }

    public String getNom_cli() {
        return nom_cli;
    }

    public void setNom_cli(String nom_cli) {
        this.nom_cli = nom_cli;
    }

    public String getNom_equipo() {
        return nom_equipo;
    }

    public void setNom_equipo(String nom_equipo) {
        this.nom_equipo = nom_equipo;
    }

    public String getNum_serie() {
        return num_serie;
    }

    public void setNum_serie(String num_serie) {
        this.num_serie = num_serie;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
