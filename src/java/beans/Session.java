
package beans;

import javax.ejb.Stateless;


@Stateless
public class Session {
    
    //variables del usuario
    private Integer id;
    private Integer idEntity;
    private String Role;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String Role) {
        this.Role = Role;
    }

    public Integer getIdEntity() {
        return idEntity;
    }

    public void setIdEntity(Integer idEntity) {
        this.idEntity = idEntity;
    }
    
    public boolean isSuperAdmin() {
        return Role.equals("SUPERADMIN");
    }
    
    public boolean isAdminEPS() {
        return Role.equals("ADMINEPS");
    }
    
    public boolean isAdminHospital() {
        return Role.equals("ADMINHOSPITAL");
    }
    
    public boolean isDoctor() {
        return Role.equals("DOCTOR");
    }    
    
    public boolean isUser() {
        return Role.equals("USER");
    }    
}
