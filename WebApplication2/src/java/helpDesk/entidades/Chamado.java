//nome do pacote onde se encontra a classe
package helpDesk.entidades;

//as importaçoes necessarias para a entidade Chamado
import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

//anotaçoes utilizadas para ´realizar a persistencia no banco
@Entity
@Table(name = "chamado")//nessa linha a anotaçao @Table vai identificar 
        //com qual tabela essa classe vai realizar a persistencia
@NamedQueries({
    @NamedQuery(name = "Chamado.findAll", query = "SELECT c FROM Chamado c")})
//@NamedQueries sao utilizadas para definar algumas consultas
public class Chamado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    //@Column anotação que vincula nome da variavel com 
            //o nome da coluna no banco de dados
    @Column(name = "numChamado")
    private Integer id;
    @Column(name = "dataAbertura")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataAber;
    @Column(name = "dataEncerramento")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataEnc;
    @Column(name = "dataAgendamento")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataAgen;
    @Column(name = "descricaoProblema")
    private String descrProblema;
    @Column(name = "situacao")
    private Integer situacao;
    @JoinColumn(name = "setor", referencedColumnName = "idsetor")
    @OneToOne
    private Setor setor;
    @JoinColumn(name = "patrimonio", referencedColumnName = "codigoPatrimonio")
    @OneToOne
    private Patrimonio patrimonio;
    @JoinColumn(name = "usuario", referencedColumnName = "matricula")
    @OneToOne
    private Usuario usuario;
    @Column(name="horaAgendamento")
    private Time hora;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "chamado")
    private List<DetalheChamado> detalheChamado;
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDataAber() {
        return dataAber;
    }

    public void setDataAber(Date dataAber) {
        this.dataAber = dataAber;
    }

    public Date getDataEnc() {
        return dataEnc;
    }

    public void setDataEnc(Date dataEnc) {
        this.dataEnc = dataEnc;
    }

    public Date getDataAgen() {
        return dataAgen;
    }

    public void setDataAgen(Date dataAgen) {
        this.dataAgen = dataAgen;
    }

    public String getDescrProblema() {
        return descrProblema;
    }

    public void setDescrProblema(String descrProblema) {
        this.descrProblema = descrProblema;
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

    public Patrimonio getPatrimonio() {
        return patrimonio;
    }

    public void setPatrimonio(Patrimonio patrimonio) {
        this.patrimonio = patrimonio;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
 //metodos responsaveis para o auto increment da chave primaria
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }
  
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Chamado)) {
            return false;
        }
        Chamado other = (Chamado) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return id.toString();
    }

    /**
     * @return the detalheChamado
     */
    public List<DetalheChamado> getDetalheChamado() {
        return detalheChamado;
    }

    /**
     * @param detalheChamado the detalheChamado to set
     */
    public void setDetalheChamado(List<DetalheChamado> detalheChamado) {
        this.detalheChamado = detalheChamado;
    }

    /**
     * @return the hora
     */
    public Time getHora() {
        return hora;
    }

    /**
     * @param hora the hora to set
     */
    public void setHora(Time hora) {
        this.hora = hora;
    }
}
