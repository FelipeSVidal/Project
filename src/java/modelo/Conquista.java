/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author felipe
 */
@Entity
@Table(name = "conquista", catalog = "marcai", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Conquista.findAll", query = "SELECT c FROM Conquista c"),
    @NamedQuery(name = "Conquista.findByConquistaid", query = "SELECT c FROM Conquista c WHERE c.conquistaid = :conquistaid"),
    @NamedQuery(name = "Conquista.findByConquistatitulo", query = "SELECT c FROM Conquista c WHERE c.conquistatitulo = :conquistatitulo"),
    @NamedQuery(name = "Conquista.findByConquistapremio", query = "SELECT c FROM Conquista c WHERE c.conquistapremio = :conquistapremio"),
    @NamedQuery(name = "Conquista.findByConquistaconcluida", query = "SELECT c FROM Conquista c WHERE c.conquistaconcluida = :conquistaconcluida")})
public class Conquista implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Conquista_id")
    private Integer conquistaid;
    @Size(max = 45)
    @Column(name = "Conquista_titulo")
    private String conquistatitulo;
    @Size(max = 45)
    @Column(name = "Conquista_premio")
    private String conquistapremio;
    @Column(name = "Conquista_concluida")
    private Boolean conquistaconcluida;
    @JoinColumn(name = "Usuario_Usuario_id", referencedColumnName = "Usuario_id")
    @ManyToOne(optional = false)
    private Usuario usuarioUsuarioid;

    public Conquista() {
    }

    public Conquista(Integer conquistaid) {
        this.conquistaid = conquistaid;
    }

    public Integer getConquistaid() {
        return conquistaid;
    }

    public void setConquistaid(Integer conquistaid) {
        this.conquistaid = conquistaid;
    }

    public String getConquistatitulo() {
        return conquistatitulo;
    }

    public void setConquistatitulo(String conquistatitulo) {
        this.conquistatitulo = conquistatitulo;
    }

    public String getConquistapremio() {
        return conquistapremio;
    }

    public void setConquistapremio(String conquistapremio) {
        this.conquistapremio = conquistapremio;
    }

    public Boolean getConquistaconcluida() {
        return conquistaconcluida;
    }

    public void setConquistaconcluida(Boolean conquistaconcluida) {
        this.conquistaconcluida = conquistaconcluida;
    }

    public Usuario getUsuarioUsuarioid() {
        return usuarioUsuarioid;
    }

    public void setUsuarioUsuarioid(Usuario usuarioUsuarioid) {
        this.usuarioUsuarioid = usuarioUsuarioid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (conquistaid != null ? conquistaid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Conquista)) {
            return false;
        }
        Conquista other = (Conquista) object;
        if ((this.conquistaid == null && other.conquistaid != null) || (this.conquistaid != null && !this.conquistaid.equals(other.conquistaid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Conquista[ conquistaid=" + conquistaid + " ]";
    }
    
    @Override
    public Conquista clone(){
        Conquista c = new Conquista();
        c.setConquistaid(conquistaid);
        c.setConquistapremio(conquistapremio);
        c.setConquistaconcluida(conquistaconcluida);
        c.setConquistatitulo(conquistatitulo);
        c.setUsuarioUsuarioid(usuarioUsuarioid);
        
        return c;
    }
}
