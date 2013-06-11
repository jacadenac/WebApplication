/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import classes.Eps;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Alejandro
 */
@Named(value = "epsBean")
@RequestScoped
public class epsBean {
    private List<Eps> epss;
    private Eps selectedEps; 

    public epsBean() {
        this.epss = new ArrayList<Eps>();
    }

    public List<Eps> getEpss() {
        return epss;
    }

    public Eps getSelectedEps() {
        return selectedEps;
    }

    public void setSelectedEps(Eps selectedEps) {
        this.selectedEps = selectedEps;
    }
    
    public void btnCreateEps(ActionEvent actionEvent) {
        //implementar método que se comunica con el otro compomente
    }

    public void btnUpdateEps(ActionEvent actionEvent) {
        //implementar método que se comunica con el otro compomente
    }

    public void btnDeleteEps(ActionEvent actionEvent) {
        //implementar método que se comunica con el otro compomente
    }

}
