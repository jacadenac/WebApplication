/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import servicios.Eps;

/**
 *
 * @author Alejandro
 */
@ManagedBean
@RequestScoped
@Named(value = "epsBean")
public class epsBean {
    
    @EJB
    private Conector conector;
    
    private List<Eps> epss;
    private Eps selectedEps;
    private Long accountNumber;
    private String password;

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public epsBean() {
        this.selectedEps = new Eps();
        this.epss = new ArrayList<Eps>();
    }

    public List<Eps> getEpss() {
        this.epss =  conector.readAllEPS();
        return epss;
    }

    public Eps getSelectedEps() {
        return selectedEps;
    }

    public void setSelectedEps(Eps selectedEps) {
        this.selectedEps = selectedEps;
    }

    public void btnCreateEps(ActionEvent actionEvent) {
        String msg;
        conector.createEPS(   this.selectedEps.getId(), 
                                    this.selectedEps.getName(), 
                                    this.selectedEps.getUrlRecords(),
                                    this.password);
    }

    
    
    public void btnRegisterEps(ActionEvent ae)
    {
        conector.registerEPS(this.selectedEps.getName(), accountNumber);
    }
    

    public void btnUpdateEps(ActionEvent actionEvent) {
        conector.updateEPS(this.selectedEps.getId(), this.selectedEps.getName(), selectedEps.getUrlRecords(), selectedEps.getUrlAppointments(), selectedEps.getUrlFinancial());
    }


    public void btnDeleteEps(ActionEvent actionEvent) {
        conector.deleteEPS(this.selectedEps.getId());
    }

}
