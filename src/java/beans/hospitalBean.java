/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import classes.Hospital;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Alejandro
 */
@Named(value = "hospitalBean")
@RequestScoped
public class hospitalBean {

    private List<Hospital> hospitales;
    private Hospital selectedHospital;

    public hospitalBean() {
        this.hospitales = new ArrayList<Hospital>();
    }

    public List<Hospital> getHospitales() {
        return hospitales;
    }

    public Hospital getSelectedHospital() {
        return selectedHospital;
    }

    public void setSelectedHospital(Hospital selectedHospital) {
        this.selectedHospital = selectedHospital;
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

