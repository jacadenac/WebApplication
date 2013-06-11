/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import classes.HistoriaClinica;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Alejandro
 */
@Named(value = "historiaClinicaBean")
@RequestScoped
public class historiaClinicaBean {
    private List<HistoriaClinica> historias;
    private HistoriaClinica selectedHistoria; 

    public historiaClinicaBean() {
        this.historias = new ArrayList<HistoriaClinica>();
    }

    public List<HistoriaClinica> getHistorias() {
        return historias;
    }

    public HistoriaClinica getSelectedHistoria() {
        return selectedHistoria;
    }

    public void setSelectedHistoria(HistoriaClinica selectedHistoria) {
        this.selectedHistoria = selectedHistoria;
    }
    
    public void btnCreateHistoria(ActionEvent actionEvent) {
        //implementar método que se comunica con el otro compomente
    }

    public void btnUpdateHistoria(ActionEvent actionEvent) {
        //implementar método que se comunica con el otro compomente
    }

    public void btnDeleteHistoria(ActionEvent actionEvent) {
        //implementar método que se comunica con el otro compomente
    }
}
