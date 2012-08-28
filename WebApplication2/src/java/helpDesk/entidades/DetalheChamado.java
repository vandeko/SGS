/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package helpDesk.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author EDLAINE
 */
@Entity
@Table(name = "detalheServico")
@NamedQueries({
    @NamedQuery(name = "DetalheChamado.findAll", query = "SELECT ds FROM DetalheChamado ds")})
public class DetalheChamado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idDetalhe")
    private Integer id;
    @Column(name = "descricaoServico")
    private String descriServico;
    @Column(name = "dataServico")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataServico;
    @JoinColumn(name = "servico", referencedColumnName = "idservico")
    @ManyToOne
    private Servico servico;
    @JoinColumn(name = "usuarioEx", referencedColumnName = "matricula")
    @ManyToOne
    private Usuario usuario;
    @JoinColumn(name = "numChamado", referencedColumnName = "numChamado")
    @ManyToOne
    private Chamado chamado;

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
     * @return the descriServico
     */
    public String getDescriServico() {
        return descriServico;
    }

    /**
     * @param descriServico the descriServico to set
     */
    public void setDescriServico(String descriServico) {
        this.descriServico = descriServico;
    }

    /**
     * @return the dataServico
     */
    public Date getDataServico() {
        return dataServico;
    }

    /**
     * @param dataServico the dataServico to set
     */
    public void setDataServico(Date dataServico) {
        this.dataServico = dataServico;
    }

    /**
     * @return the servico
     */
    public Servico getServico() {
        return servico;
    }

    /**
     * @param servico the servico to set
     */
    public void setServico(Servico servico) {
        this.servico = servico;
    }

    /**
     * @return the usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the chamado
     */
    public Chamado getChamado() {
        return chamado;
    }

    /**
     * @param chamado the chamado to set
     */
    public void setChamado(Chamado chamado) {
        this.chamado = chamado;
    }
}
