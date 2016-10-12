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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author felipe
 */
@Entity
@Table(name = "campo", catalog = "marcai", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Campo.findAll", query = "SELECT c FROM Campo c"),
    @NamedQuery(name = "Campo.findByCampoid", query = "SELECT c FROM Campo c WHERE c.campoid = :campoid"),
    @NamedQuery(name = "Campo.findByCampoidentificador", query = "SELECT c FROM Campo c WHERE c.campoidentificador = :campoidentificador"),
    @NamedQuery(name = "Campo.findByCampopreco", query = "SELECT c FROM Campo c WHERE c.campopreco = :campopreco")})
public class Campo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Campo_id")
    private Integer campoid;
    @Size(max = 45)
    @Column(name = "Campo_identificador")
    private String campoidentificador;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Campo_preco")
    private Float campopreco;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "campoCampoid")
    private Collection<Racha> rachaCollection;
    @JoinColumn(name = "Society_Society_id", referencedColumnName = "Society_id")
    @ManyToOne(optional = false)
    private Society societySocietyid;

    public Campo() {
    }

    public Campo(Integer campoid) {
        this.campoid = campoid;
    }

    public Integer getCampoid() {
        return campoid;
    }

    public void setCampoid(Integer campoid) {
        this.campoid = campoid;
    }

    public String getCampoidentificador() {
        return campoidentificador;
    }

    public void setCampoidentificador(String campoidentificador) {
        this.campoidentificador = campoidentificador;
    }

    public Float getCampopreco() {
        return campopreco;
    }

    public void setCampopreco(Float campopreco) {
        this.campopreco = campopreco;
    }

    @XmlTransient
    public Collection<Racha> getRachaCollection() {
        return rachaCollection;
    }

    public void setRachaCollection(Collection<Racha> rachaCollection) {
        this.rachaCollection = rachaCollection;
    }

    public Society getSocietySocietyid() {
        return societySocietyid;
    }

    public void setSocietySocietyid(Society societySocietyid) {
        this.societySocietyid = societySocietyid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (campoid != null ? campoid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Campo)) {
            return false;
        }
        Campo other = (Campo) object;
        if ((this.campoid == null && other.campoid != null) || (this.campoid != null && !this.campoid.equals(other.campoid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Campo[ campoid=" + campoid + " ]";
    }
    
    @Override
    public Campo clone(){
        Campo c = new Campo();
        c.setCampoid(campoid);
        c.setCampoidentificador(campoidentificador);
        c.setCampopreco(campopreco);
        c.setRachaCollection(new ArrayList<Racha>());
        c.setSocietySocietyid(societySocietyid);
        for(Racha r : getRachaCollection()){
            c.getRachaCollection().add(r.clone());
        }
        
        
        return c;
    }
    
}
