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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name = "society", catalog = "marcai", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Society.findAll", query = "SELECT s FROM Society s"),
    @NamedQuery(name = "Society.findBySocietyid", query = "SELECT s FROM Society s WHERE s.societyid = :societyid"),
    @NamedQuery(name = "Society.findBySocietynome", query = "SELECT s FROM Society s WHERE s.societynome = :societynome")})
public class Society implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Society_id")
    private Integer societyid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "Society_nome")
    private String societynome;
    @JoinTable(name = "societyfavorito", joinColumns = {
        @JoinColumn(name = "Society_Society_id", referencedColumnName = "Society_id")}, inverseJoinColumns = {
        @JoinColumn(name = "Usuario_Usuario_id", referencedColumnName = "Usuario_id")})
    @ManyToMany
    private Collection<Usuario> usuarioCollection;
    @JoinColumn(name = "Cliente_Cliente_id", referencedColumnName = "Cliente_id")
    @ManyToOne(optional = false)
    private Cliente clienteClienteid;
    @JoinColumn(name = "Local_Local_id", referencedColumnName = "Local_id")
    @ManyToOne(optional = false)
    private Local localLocalid;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "societySocietyid")
    private Collection<Campo> campoCollection;

    public Society() {
    }

    public Society(Integer societyid) {
        this.societyid = societyid;
    }

    public Society(Integer societyid, String societynome) {
        this.societyid = societyid;
        this.societynome = societynome;
    }

    public Integer getSocietyid() {
        return societyid;
    }

    public void setSocietyid(Integer societyid) {
        this.societyid = societyid;
    }

    public String getSocietynome() {
        return societynome;
    }

    public void setSocietynome(String societynome) {
        this.societynome = societynome;
    }

    @XmlTransient
    public Collection<Usuario> getUsuarioCollection() {
        return usuarioCollection;
    }

    public void setUsuarioCollection(Collection<Usuario> usuarioCollection) {
        this.usuarioCollection = usuarioCollection;
    }

    public Cliente getClienteClienteid() {
        return clienteClienteid;
    }

    public void setClienteClienteid(Cliente clienteClienteid) {
        this.clienteClienteid = clienteClienteid;
    }

    public Local getLocalLocalid() {
        return localLocalid;
    }

    public void setLocalLocalid(Local localLocalid) {
        this.localLocalid = localLocalid;
    }

    @XmlTransient
    public Collection<Campo> getCampoCollection() {
        return campoCollection;
    }

    public void setCampoCollection(Collection<Campo> campoCollection) {
        this.campoCollection = campoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (societyid != null ? societyid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Society)) {
            return false;
        }
        Society other = (Society) object;
        if ((this.societyid == null && other.societyid != null) || (this.societyid != null && !this.societyid.equals(other.societyid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Society[ societyid=" + societyid + " ]";
    }
    
    @Override
    public Society clone(){
        Society s = new Society();
        s.setCampoCollection(new ArrayList<Campo>());
        for(Campo c : getCampoCollection()){
            s.getCampoCollection().add(c.clone());
        }
        s.setClienteClienteid(clienteClienteid);
        s.setLocalLocalid(localLocalid);
        s.setSocietyid(societyid);
        s.setSocietynome(societynome);
        s.setUsuarioCollection(new ArrayList<Usuario>());
        for(Usuario u : getUsuarioCollection()){
            s.getUsuarioCollection().add(u.clone());
        }
        return s;
    }
}
