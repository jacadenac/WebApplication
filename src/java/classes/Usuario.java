/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

/**
 *
 * @author Alejandro
 */
public class Usuario {
    private Integer id;
    private String rol;
    private Integer idEntity;

    public Usuario() {
    }

    public Usuario(Integer id, String rol, Integer idEntity) {
        this.id = id;
        this.rol = rol;
        this.idEntity = idEntity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Integer getIdEntity() {
        return idEntity;
    }

    public void setIdEntity(Integer idEntity) {
        this.idEntity = idEntity;
    }
}
