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
import javax.inject.Named;

/**
 *
 * @author Alejandro
 */
@Named(value = "hospitalBean")
@RequestScoped
public class hospitalBean {
    @EJB
    Conector conector;

    private List<Hospital> hospitales;
    private Hospital selectedHospital;
    private Cita selectedCita;
    private String selectedTipoCita;
    private Doctor selectedDoctor;

    //método editado
    public hospitalBean() {
        this.hospitales = new ArrayList<Hospital>();
        selectedHospital = new Hospital(3, "HOSPITAL TUNAL");
        selectedCita = new Cita();
        selectedDoctor = new Doctor();
    }

    public List<Hospital> getHospitales() {
        return conector.getHospitales();
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

    public Doctor getSelectedDoctor() {
        return selectedDoctor;
    }

    public void setSelectedDoctor(Doctor selectedDoctor) {
        this.selectedDoctor = selectedDoctor;
    }

    public String getSelectedTipoCita() {
        return selectedTipoCita;
    }

    public void setSelectedTipoCita(String selectedTipoCita) {
        this.selectedTipoCita = selectedTipoCita;
    }   
    
    //métodos para personalizar la búsqueda
    public List<Doctor> getDoctores() {
        return conector.getDoctores(selectedHospital.getId(),selectedTipoCita);
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

