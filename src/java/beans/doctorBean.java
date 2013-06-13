/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import classes.Doctor;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Alejandro
 */
@Named(value = "doctorBean")
@RequestScoped
public class doctorBean {
    private List<Doctor> doctores;
    private Doctor selectedDoctor;

    public doctorBean() {
        this.doctores = new ArrayList<Doctor>();
    }

    public List<Doctor> getDoctores() {
        return doctores;
    }

    public Doctor getSelectedDoctor() {
        return selectedDoctor;
    }

    public void setSelectedDoctor(Doctor selectedDoctor) {
        this.selectedDoctor = selectedDoctor;
    }    
    
    public void btnCreateDoctor(ActionEvent actionEvent) {
        //implementar método que se comunica con el otro compomente
    }

    public void btnUpdateDoctor(ActionEvent actionEvent) {
        //implementar método que se comunica con el otro compomente
    }

    public void btnDeleteDoctor(ActionEvent actionEvent) {
        //implementar método que se comunica con el otro compomente
    }
}
