package cl.appdrbyte.condor_code.drbyteappv10;

public class perfil_user {

    String perfil_id;
    String perfil_nombre;
    String perfil_cel;
    String perfil_correo;
    String perfil_profe;
    String image_path;

    public perfil_user (){

    }

    public perfil_user(String perfil_id, String perfil_nombre, String perfil_cel, String perfil_correo, String perfil_profe, String image_path) {
        this.perfil_id = perfil_id;
        this.perfil_nombre = perfil_nombre;
        this.perfil_cel = perfil_cel;
        this.perfil_correo = perfil_correo;
        this.perfil_profe = perfil_profe;
        this.image_path = image_path;
    }

    public String getPerfil_id() {
        return perfil_id;
    }

    public void setPerfil_id(String perfil_id) {
        this.perfil_id = perfil_id;
    }

    public String getPerfil_nombre() {
        return perfil_nombre;
    }

    public void setPerfil_nombre(String perfil_nombre) {
        this.perfil_nombre = perfil_nombre;
    }

    public String getPerfil_cel() {
        return perfil_cel;
    }

    public void setPerfil_cel(String perfil_cel) {
        this.perfil_cel = perfil_cel;
    }

    public String getPerfil_correo() {
        return perfil_correo;
    }

    public void setPerfil_correo(String perfil_correo) {
        this.perfil_correo = perfil_correo;
    }

    public String getPerfil_profe() {
        return perfil_profe;
    }

    public void setPerfil_profe(String perfil_profe) {
        this.perfil_profe = perfil_profe;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }
}


