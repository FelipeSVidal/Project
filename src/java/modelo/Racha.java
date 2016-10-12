/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author felipe
 */
@Entity
@Table(name = "racha", catalog = "marcai", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Racha.findAll", query = "SELECT r FROM Racha r"),
    @NamedQuery(name = "Racha.findByRachaid", query = "SELECT r FROM Racha r WHERE r.rachaid = :rachaid"),
    @NamedQuery(name = "Racha.findByRachanome", query = "SELECT r FROM Racha r WHERE r.rachanome = :rachanome"),
    @NamedQuery(name = "Racha.findByRachadata", query = "SELECT r FROM Racha r WHERE r.rachadata = :rachadata"),
    @NamedQuery(name = "Racha.findByRachapMax", query = "SELECT r FROM Racha r WHERE r.rachapMax = :rachapMax"),
    @NamedQuery(name = "Racha.findByRachapAtual", query = "SELECT r FROM Racha r WHERE r.rachapAtual = :rachapAtual")})
public class Racha implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Racha_id")
    private Integer rachaid;
    @Size(max = 45)
    @Column(name = "Racha_nome")
    private String rachanome;
    @Column(name = "Racha_data")
    @Temporal(TemporalType.TIMESTAMP)
    private Date rachadata;
    @Column(name = "Racha_pMax")
    private Integer rachapMax;
    @Column(name = "Racha_pAtual")
    private Integer rachapAtual;
    @JoinTable(name = "usuarios_no_racha", joinColumns = {
        @JoinColumn(name = "Racha_Racha_id", referencedColumnName = "Racha_id")}, inverseJoinColumns = {
        @JoinColumn(name = "Usuario_Usuario_id", referencedColumnName = "Usuario_id")})
    @ManyToMany
    private Collection<Usuario> usuarioCollection;
    @JoinColumn(name = "Campo_Campo_id", referencedColumnName = "Campo_id")
    @ManyToOne(optional = false)
    private Campo campoCampoid;
    @JoinColumn(name = "Usuario_lider", referencedColumnName = "Usuario_id")
    @ManyToOne(optional = false)
    private Usuario usuariolider;

    public Racha() {
    }

    public Racha(Integer rachaid) {
        this.rachaid = rachaid;
    }

    public Integer getRachaid() {
        return rachaid;
    }

    public void setRachaid(Integer rachaid) {
        this.rachaid = rachaid;
    }

    public String getRachanome() {
        return rachanome;
    }

    public void setRachanome(String rachanome) {
        this.rachanome = rachanome;
    }

    public Date getRachadata() {
        return rachadata;
    }

    public void setRachadata(Date rachadata) {
        this.rachadata = rachadata;
    }

    public Integer getRachapMax() {
        return rachapMax;
    }

    public void setRachapMax(Integer rachapMax) {
        this.rachapMax = rachapMax;
    }

    public Integer getRachapAtual() {
        return rachapAtual;
    }

    public void setRachapAtual(Integer rachapAtual) {
        this.rachapAtual = rachapAtual;
    }

    @XmlTransient
    public Collection<Usuario> getUsuarioCollection() {
        return usuarioCollection;
    }

    public void setUsuarioCollection(Collection<Usuario> usuarioCollection) {
        this.usuarioCollection = usuarioCollection;
    }

    public Campo getCampoCampoid() {
        return campoCampoid;
    }

    public void setCampoCampoid(Campo campoCampoid) {
        this.campoCampoid = campoCampoid;
    }

    public Usuario getUsuariolider() {
        return usuariolider;
    }

    public void setUsuariolider(Usuario usuariolider) {
        this.usuariolider = usuariolider;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rachaid != null ? rachaid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Racha)) {
            return false;
        }
        Racha other = (Racha) object;
        if ((this.rachaid == null && other.rachaid != null) || (this.rachaid != null && !this.rachaid.equals(other.rachaid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Racha[ rachaid=" + rachaid + " ]";
    }
    
    @Override
    public Racha clone(){
        Racha r = new Racha();
        r.setCampoCampoid(campoCampoid);
        r.setRachadata(rachadata);
        r.setRachaid(rachaid);
        r.setRachanome(rachanome);
        r.setRachapAtual(rachapAtual);
        r.setRachapMax(rachapMax);
        r.setUsuarioCollection(new ArrayList<Usuario>());
        for(Usuario u : getUsuarioCollection()){
            r.getUsuarioCollection().add(u.clone());
        }
        r.setUsuariolider(usuariolider);
        return r;
    }
}
