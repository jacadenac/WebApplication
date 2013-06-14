/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import classes.Cita;
import classes.Doctor;
import classes.Hospital;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;

/**
 *
 * @author Alejandro
 */
@Named(value = "ControladorCita")
@ManagedBean
@RequestScoped
public class ControladorCita {
    @EJB
    Conector conector;

    private List<Hospital> hospitales = new ArrayList<Hospital>();
    private List<Doctor> doctores;
    private List<Cita> citas;
    private Hospital selectedHospital;
    private Cita selectedCita;
    private String selectedEspecialidad;
    private Doctor selectedDoctor;

    //método editado
    public ControladorCita() {
        
        this.hospitales = new ArrayList<Hospital>();
        this.selectedHospital = new Hospital(3, "HOSPITAL TUNAL");
        this.doctores = new ArrayList<Doctor>();
        this.selectedDoctor = new Doctor();
        this.citas = new ArrayList<Cita>();
        this.selectedCita = new Cita();
    }

    public List<Hospital> getHospitales() {
        hospitales= conector.getHospitales();
        return hospitales;
    }

    public void setHospitales(List<Hospital> hospitales) {
        this.hospitales = hospitales;
    }

    public List<Doctor> getDoctores() {
        return doctores;
    }

    public void setDoctores(List<Doctor> doctores) {
        this.doctores = doctores;
    }

    public List<Cita> getCitas() {
        return citas;
    }

    public void setCitas(List<Cita> citas) {
        this.citas = citas;
    }

    public Hospital getSelectedHospital() {
        return selectedHospital;
    }

    public void setSelectedHospital(Hospital selectedHospital) {
        this.selectedHospital = selectedHospital;
    }

    public Cita getSelectedCita() {
        return selectedCita;
    }

    public void setSelectedCita(Cita selectedCita) {
        this.selectedCita = selectedCita;
    }

    public String getSelectedEspecialidad() {
        return selectedEspecialidad;
    }

    public void setSelectedEspecialidad(String selectedTipoCita) {
        this.selectedEspecialidad = selectedTipoCita;
    }

    public Doctor getSelectedDoctor() {
        return selectedDoctor;
    }

    public void setSelectedDoctor(Doctor selectedDoctor) {
        this.selectedDoctor = selectedDoctor;
    }

    public void actualizarDoctores() {
        if(selectedHospital!=null) {
            doctores = conector.getDoctores(selectedHospital.getId(),selectedEspecialidad);
        }
    }

    public void btnCreateHospital(ActionEvent actionEvent) {
        //implementar método que se comunica con el otro compomente
    }

    public void btnUpdateHospital(ActionEvent actionEvent) {
        //implementar método que se comunica con el otro compomente
    }

    public void btnDeleteHospital(ActionEvent actionEvent) {
        //implementar método que se comunica con el otro compomente
    }

}

