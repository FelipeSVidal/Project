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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author felipe
 */
@Entity
@Table(name = "login", catalog = "marcai", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Login.findAll", query = "SELECT l FROM Login l"),
    @NamedQuery(name = "Login.findByLoginid", query = "SELECT l FROM Login l WHERE l.loginid = :loginid"),
    @NamedQuery(name = "Login.findByLoginuser", query = "SELECT l FROM Login l WHERE l.loginuser = :loginuser"),
    @NamedQuery(name = "Login.findByLoginpassword", query = "SELECT l FROM Login l WHERE l.loginpassword = :loginpassword")})
public class Login implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Login_id")
    private Integer loginid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Login_user")
    private String loginuser;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Login_password")
    private String loginpassword;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "loginLoginid")
    private Collection<Cliente> clienteCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "loginLoginid")
    private Collection<Usuario> usuarioCollection;

    public Login() {
    }

    public Login(Integer loginid) {
        this.loginid = loginid;
    }

    public Login(Integer loginid, String loginuser, String loginpassword) {
        this.loginid = loginid;
        this.loginuser = loginuser;
        this.loginpassword = loginpassword;
    }
    
    @Override
    public Login clone(){
        Login l = new Login();
        l.setLoginid(loginid);
        l.setLoginuser(loginuser);
        l.setLoginpassword(loginpassword);
        l.setUsuarioCollection(new ArrayList<Usuario>());
        l.setClienteCollection(new ArrayList<Cliente>());
        for(Cliente c : getClienteCollection()){
        l.getClienteCollection().add(c.clone());
        }
        for(Usuario u : getUsuarioCollection()){
        l.getUsuarioCollection().add(u.clone());
        }
        return l;
    }

    public Integer getLoginid() {
        return loginid;
    }

    public void setLoginid(Integer loginid) {
        this.loginid = loginid;
    }

    public String getLoginuser() {
        return loginuser;
    }

    public void setLoginuser(String loginuser) {
        this.loginuser = loginuser;
    }

    public String getLoginpassword() {
        return loginpassword;
    }

    public void setLoginpassword(String loginpassword) {
        this.loginpassword = loginpassword;
    }


    public Collection<Cliente> getClienteCollection() {
        return clienteCollection;
    }

    public void setClienteCollection(Collection<Cliente> clienteCollection) {
        this.clienteCollection = clienteCollection;
    }


    public Collection<Usuario> getUsuarioCollection() {
        return usuarioCollection;
    }

    public void setUsuarioCollection(Collection<Usuario> usuarioCollection) {
        this.usuarioCollection = usuarioCollection;
    }
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (loginid != null ? loginid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Login)) {
            return false;
        }
        Login other = (Login) object;
        if ((this.loginid == null && other.loginid != null) || (this.loginid != null && !this.loginid.equals(other.loginid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Login[ loginid=" + loginid + " ]";
    }
    
}
