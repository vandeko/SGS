
package gestordeprocessos.util;

public class produto {

    private int cod;
    private String descricao;
    private double preco;

    public produto(String desc, double preco) {
        this.setDescricao(desc);
        this.setPreco(preco);
    }

    public int getcod() {
        return cod;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setcod(int cod) {
        this.cod = cod;
    }

    public void setDescricao(String desc) {
        this.descricao = desc;
    }

    public void setPreco(double pc) {
        this.preco = pc;
    }
}
