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
@Table(name = "tiposervico")
@NamedQueries({
    @NamedQuery(name = "TipoServico.findAll", query = "SELECT ts FROM TipoServico ts")})
public class TipoServico implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idTipoServico")
    private Integer id;
    @Column(name="nome")
    private String nome;
    @Column(name="descricao")
    private String descricao;
    @Column(name="situacao")
    private Integer situacao;

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
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return the situacao
     */
    public Integer getSituacao() {
        return situacao;
    }

    /**
     * @param situacao the situacao to set
     */
    public void setSituacao(Integer situacao) {
        this.situacao = situacao;
    }
    
}
