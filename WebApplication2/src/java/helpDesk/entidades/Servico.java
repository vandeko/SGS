
package helpDesk.entidades;

import java.io.Serializable;
import javax.persistence.*;


@Entity
@Table(name = "servico")
@NamedQueries({
    @NamedQuery(name = "Servico.findAll", query = "SELECT s FROM Servico s")})

public class Servico implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idservico")
    private Integer id;
    @Column(name="nome")
    private String nome;
    @Column(name="descricao")
    private String descricao;
    @Column(name="situacao")
    private Integer situacao;
    @Column(name="visibilidade")
    private Integer visibilidade;
    @JoinColumn(name = "tipoServico", referencedColumnName = "idTipoServico")
    @ManyToOne
    private TipoServico tipoServico;

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

    /**
     * @return the visibilidade
     */
    public Integer getVisibilidade() {
        return visibilidade;
    }

    /**
     * @param visibilidade the visibilidade to set
     */
    public void setVisibilidade(Integer visibilidade) {
        this.visibilidade = visibilidade;
    }

    /**
     * @return the tipoServico
     */
    public TipoServico getTipoServico() {
        return tipoServico;
    }

    /**
     * @param tipoServico the tipoServico to set
     */
    public void setTipoServico(TipoServico tipoServico) {
        this.tipoServico = tipoServico;
    }
    
    
}
