/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

/**
 *
 * @author Alejandro
 */
public class Doctor {
    private Integer id;
    private String nombre;
    private String especialidad;
    //provisional
    private Integer hospital;

    public Doctor() {}

    public Doctor(Integer id, String nombre, String especialidad, Integer hospital) {
        this.id = id;
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.hospital = hospital;
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
    
    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public Integer getHospital() {
        return hospital;
    }

    public void setHospital(Integer hospital) {
        this.hospital = hospital;
    }

}
