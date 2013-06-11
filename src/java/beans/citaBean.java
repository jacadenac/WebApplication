/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import classes.Cita;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Alejandro
 */
@Named(value = "citaBean")
@RequestScoped
public class citaBean {
    private List<Cita> citas;
    private Cita selectedCita; 

    public citaBean() {
        this.citas = new ArrayList<Cita>();
    }

    public List<Cita> getCitas() {
        return citas;
    }

    public Cita getSelectedCita() {
        return selectedCita;
    }

    public void setSelectedCita(Cita selectedCita) {
        this.selectedCita = selectedCita;
    }
    
    public void btnCreateCita(ActionEvent actionEvent) {
        //implementar método que se comunica con el otro compomente
    }

    public void btnUpdateCita(ActionEvent actionEvent) {
        //implementar método que se comunica con el otro compomente
    }

    public void btnDeleteCita(ActionEvent actionEvent) {
        //implementar método que se comunica con el otro compomente
    }
    
}
