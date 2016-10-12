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
@Table(name = "usuario", catalog = "marcai", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findByUsuarioid", query = "SELECT u FROM Usuario u WHERE u.usuarioid = :usuarioid"),
    @NamedQuery(name = "Usuario.findByUsuarionome", query = "SELECT u FROM Usuario u WHERE u.usuarionome = :usuarionome"),
    @NamedQuery(name = "Usuario.findByUsuariolv", query = "SELECT u FROM Usuario u WHERE u.usuariolv = :usuariolv"),
    @NamedQuery(name = "Usuario.findByUsuarioexp", query = "SELECT u FROM Usuario u WHERE u.usuarioexp = :usuarioexp"),
    @NamedQuery(name = "Usuario.findByUsuariofoto", query = "SELECT u FROM Usuario u WHERE u.usuariofoto = :usuariofoto")})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Usuario_id")
    private Integer usuarioid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Usuario_nome")
    private String usuarionome;
    @Size(max = 45)
    @Column(name = "Usuario_lv")
    private String usuariolv;
    @Size(max = 45)
    @Column(name = "Usuario_exp")
    private String usuarioexp;
    @Size(max = 120)
    @Column(name = "Usuario_foto")
    private String usuariofoto;
    @ManyToMany(mappedBy = "usuarioCollection")
    private Collection<Society> societyCollection;
    @ManyToMany(mappedBy = "usuarioCollection")
    private Collection<Racha> rachaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioUsuarioid")
    private Collection<Conquista> conquistaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuariolider")
    private Collection<Racha> rachaCollection1;
    @JoinColumn(name = "Local_Local_id", referencedColumnName = "Local_id")
    @ManyToOne(optional = false)
    private Local localLocalid;
    @JoinColumn(name = "Login_Login_id", referencedColumnName = "Login_id")
    @ManyToOne(optional = false)
    private Login loginLoginid;

    public Usuario() {
    }

    public Usuario(Integer usuarioid) {
        this.usuarioid = usuarioid;
    }

    public Usuario(Integer usuarioid, String usuarionome) {
        this.usuarioid = usuarioid;
        this.usuarionome = usuarionome;
    }
    
    @Override
    public Usuario clone(){
        Usuario u = new Usuario();
        u.setUsuarioid(usuarioid);
        u.setUsuarionome(usuarionome);
        u.setUsuariolv(usuariolv);
        u.setUsuarioexp(usuarioexp);
        u.setUsuariofoto(usuariofoto);
        u.setLocalLocalid(localLocalid);
        u.setLoginLoginid(loginLoginid);
        u.setConquistaCollection(new ArrayList<Conquista>());
        for(Conquista c : getConquistaCollection()){
            u.getConquistaCollection().add(c.clone());
        }
        u.setRachaCollection(new ArrayList<Racha>());
        for(Racha r : getRachaCollection()){
            u.getRachaCollection().add(r.clone());
        }
        u.setRachaCollection1(new ArrayList<Racha>()); //Array de Rachas que ele é Lider
        for(Racha r: getRachaCollection1()){
            u.getRachaCollection1().add(r.clone());
        }
        u.setSocietyCollection(new ArrayList<Society>());
        for(Society s : getSocietyCollection()){
            u.getSocietyCollection().add(s.clone());
        }
        return u;
    }

    public Integer getUsuarioid() {
        return usuarioid;
    }

    public void setUsuarioid(Integer usuarioid) {
        this.usuarioid = usuarioid;
    }

    public String getUsuarionome() {
        return usuarionome;
    }

    public void setUsuarionome(String usuarionome) {
        this.usuarionome = usuarionome;
    }

    public String getUsuariolv() {
        return usuariolv;
    }

    public void setUsuariolv(String usuariolv) {
        this.usuariolv = usuariolv;
    }

    public String getUsuarioexp() {
        return usuarioexp;
    }

    public void setUsuarioexp(String usuarioexp) {
        this.usuarioexp = usuarioexp;
    }

    public String getUsuariofoto() {
        return usuariofoto;
    }

    public void setUsuariofoto(String usuariofoto) {
        this.usuariofoto = usuariofoto;
    }

    @XmlTransient
    public Collection<Society> getSocietyCollection() {
        return societyCollection;
    }

    public void setSocietyCollection(Collection<Society> societyCollection) {
        this.societyCollection = societyCollection;
    }

    @XmlTransient
    public Collection<Racha> getRachaCollection() {
        return rachaCollection;
    }

    public void setRachaCollection(Collection<Racha> rachaCollection) {
        this.rachaCollection = rachaCollection;
    }

    @XmlTransient
    public Collection<Conquista> getConquistaCollection() {
        return conquistaCollection;
    }

    public void setConquistaCollection(Collection<Conquista> conquistaCollection) {
        this.conquistaCollection = conquistaCollection;
    }

    @XmlTransient
    public Collection<Racha> getRachaCollection1() {
        return rachaCollection1;
    }

    public void setRachaCollection1(Collection<Racha> rachaCollection1) {
        this.rachaCollection1 = rachaCollection1;
    }

    public Local getLocalLocalid() {
        return localLocalid;
    }

    public void setLocalLocalid(Local localLocalid) {
        this.localLocalid = localLocalid;
    }

    public Login getLoginLoginid() {
        return loginLoginid;
    }

    public void setLoginLoginid(Login loginLoginid) {
        this.loginLoginid = loginLoginid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuarioid != null ? usuarioid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.usuarioid == null && other.usuarioid != null) || (this.usuarioid != null && !this.usuarioid.equals(other.usuarioid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Usuario[ usuarioid=" + usuarioid + " ]";
    }
    
}
