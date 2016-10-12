/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author felipe
 */
@Entity
@Table(name = "cliente", catalog = "marcai", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cliente.findAll", query = "SELECT c FROM Cliente c"),
    @NamedQuery(name = "Cliente.findByClienteid", query = "SELECT c FROM Cliente c WHERE c.clienteid = :clienteid"),
    @NamedQuery(name = "Cliente.findByClientenome", query = "SELECT c FROM Cliente c WHERE c.clientenome = :clientenome"),
    @NamedQuery(name = "Cliente.findByClienteCNPJ", query = "SELECT c FROM Cliente c WHERE c.clienteCNPJ = :clienteCNPJ")})
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Cliente_id")
    private Integer clienteid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Cliente_nome")
    private String clientenome;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Cliente_CNPJ")
    private String clienteCNPJ;
    @JoinColumn(name = "Login_Login_id", referencedColumnName = "Login_id")
    @ManyToOne(optional = false)
    private Login loginLoginid;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clienteClienteid")
    private Collection<Society> societyCollection;

    public Cliente() {
    }

    public Cliente(Integer clienteid) {
        this.clienteid = clienteid;
    }

    public Cliente(Integer clienteid, String clientenome, String clienteCNPJ) {
        this.clienteid = clienteid;
        this.clientenome = clientenome;
        this.clienteCNPJ = clienteCNPJ;
    }
    
    @Override
    public Cliente clone(){
       Cliente c = new Cliente();
       c.setClienteid(clienteid);
       c.setClientenome(clientenome);
       c.setClienteCNPJ(clienteCNPJ);
       c.setLoginLoginid(loginLoginid);
       c.setSocietyCollection(new ArrayList<Society>());
       for(Society s : getSocietyCollection()){
           c.getSocietyCollection().add(s.clone());
       }
       return c;
    }

    public Integer getClienteid() {
        return clienteid;
    }

    public void setClienteid(Integer clienteid) {
        this.clienteid = clienteid;
    }

    public String getClientenome() {
        return clientenome;
    }

    public void setClientenome(String clientenome) {
        this.clientenome = clientenome;
    }

    public String getClienteCNPJ() {
        return clienteCNPJ;
    }

    public void setClienteCNPJ(String clienteCNPJ) {
        this.clienteCNPJ = clienteCNPJ;
    }

    public Login getLoginLoginid() {
        return loginLoginid;
    }

    public void setLoginLoginid(Login loginLoginid) {
        this.loginLoginid = loginLoginid;
    }

    @XmlTransient
    public Collection<Society> getSocietyCollection() {
        return societyCollection;
    }

    public void setSocietyCollection(Collection<Society> societyCollection) {
        this.societyCollection = societyCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (clienteid != null ? clienteid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
        if ((this.clienteid == null && other.clienteid != null) || (this.clienteid != null && !this.clienteid.equals(other.clienteid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Cliente[ clienteid=" + clienteid + " ]";
    }
    
}
