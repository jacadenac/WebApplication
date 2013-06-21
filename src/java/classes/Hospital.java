/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

/**
 *
 * @author Alejandro
 */
public class Hospital {

    private Integer id;
    private String nombre;
    private String url;

    public Hospital() {
        this.id=0;
        this.nombre = "";
    }

    public Hospital(Integer id, String nombre, String url) {
        this.id = id;
        this.nombre = nombre;
        this.url = url;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
}
