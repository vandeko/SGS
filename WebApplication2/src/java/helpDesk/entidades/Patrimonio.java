package helpDesk.entidades;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "patrimonio")
@NamedQueries({
    @NamedQuery(name = "Patrimonio.findAll", query = "SELECT p FROM Patrimonio p")})

public class Patrimonio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id      
    @Column(name = "codigoPatrimonio")
    private Integer id;
    @Column(name = "nome")
    private String nome;
    @Column(name = "descricao")
    private String descricao;
    @Column(name = "situacao")
    private Integer situacao;
    @JoinColumn(name ="setor", referencedColumnName = "idsetor")
    @ManyToOne
    private Setor setor;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getSituacao() {
        return situacao;
    }

    public void setSituacao(Integer situacao) {
        this.situacao = situacao;
    }

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }


}
