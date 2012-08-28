/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package helpDesk.entidades;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author EDLAINE
 */
@Entity
@Table(name = "setor")
@NamedQueries({
    @NamedQuery(name = "Setor.findAll", query = "SELECT s FROM Setor s")})
public class Setor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "idsetor")
    private Integer id;
    @Column(name = "nome")
    private String nome;
    @Column(name = "situacao")
    private Integer status;

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return id.toString();
    }
}
