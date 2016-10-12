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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author felipe
 */
@Entity
@Table(name = "local", catalog = "marcai", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Local.findAll", query = "SELECT l FROM Local l"),
    @NamedQuery(name = "Local.findByLocalid", query = "SELECT l FROM Local l WHERE l.localid = :localid"),
    @NamedQuery(name = "Local.findByLocallong", query = "SELECT l FROM Local l WHERE l.locallong = :locallong"),
    @NamedQuery(name = "Local.findByLocallati", query = "SELECT l FROM Local l WHERE l.locallati = :locallati"),
    @NamedQuery(name = "Local.findByLocalendere\u00e7o", query = "SELECT l FROM Local l WHERE l.localendere\u00e7o = :localendere\u00e7o"),
    @NamedQuery(name = "Local.findByLocalbairro", query = "SELECT l FROM Local l WHERE l.localbairro = :localbairro")})
public class Local implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Local_id")
    private Integer localid;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Local_long")
    private Float locallong;
    @Column(name = "Local_lati")
    private Float locallati;
    @Size(max = 45)
    @Column(name = "Local_endere\u00e7o")
    private String localendereço;
    @Size(max = 45)
    @Column(name = "Local_bairro")
    private String localbairro;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "localLocalid")
    private Collection<Society> societyCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "localLocalid")
    private Collection<Usuario> usuarioCollection;

    public Local() {
    }

    public Local(Integer localid) {
        this.localid = localid;
    }

    public Integer getLocalid() {
        return localid;
    }

    public void setLocalid(Integer localid) {
        this.localid = localid;
    }

    public Float getLocallong() {
        return locallong;
    }

    public void setLocallong(Float locallong) {
        this.locallong = locallong;
    }

    public Float getLocallati() {
        return locallati;
    }

    public void setLocallati(Float locallati) {
        this.locallati = locallati;
    }

    public String getLocalendereço() {
        return localendereço;
    }

    public void setLocalendereço(String localendereço) {
        this.localendereço = localendereço;
    }

    public String getLocalbairro() {
        return localbairro;
    }

    public void setLocalbairro(String localbairro) {
        this.localbairro = localbairro;
    }

    @XmlTransient
    public Collection<Society> getSocietyCollection() {
        return societyCollection;
    }

    public void setSocietyCollection(Collection<Society> societyCollection) {
        this.societyCollection = societyCollection;
    }

    @XmlTransient
    public Collection<Usuario> getUsuarioCollection() {
        return usuarioCollection;
    }

    public void setUsuarioCollection(Collection<Usuario> usuarioCollection) {
        this.usuarioCollection = usuarioCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (localid != null ? localid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Local)) {
            return false;
        }
        Local other = (Local) object;
        if ((this.localid == null && other.localid != null) || (this.localid != null && !this.localid.equals(other.localid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Local[ localid=" + localid + " ]";
    }
    
    @Override
    public Local clone(){
        Local l = new Local();
        l.setLocalid(localid);
        l.setLocalbairro(localbairro);
        l.setLocalendereço(localendereço);
        l.setLocallati(locallati);
        l.setLocallong(locallong);
        l.setSocietyCollection(new ArrayList<Society>());
        for(Society s : getSocietyCollection()){
            l.getSocietyCollection().add(s.clone());
        }
        l.setUsuarioCollection(new ArrayList<Usuario>());
        for(Usuario u : getUsuarioCollection()){
            l.getUsuarioCollection().add(u.clone());
        }
        return l;
    }
    
}
