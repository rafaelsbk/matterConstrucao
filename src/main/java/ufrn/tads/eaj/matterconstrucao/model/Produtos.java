package ufrn.tads.eaj.matterconstrucao.model;

public class Produtos {
        private int idProduto;
        private String nomeProduto;
        private String descricao;
        private String fabricante;
        private String categoria;
        private int qtdEstoque;
        private float precoProduto;
        private float precoCompraProduto;

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getQtdEstoque() {
        return qtdEstoque;
    }

    public void setQtdEstoque(int qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
    }

    public float getPrecoProduto() {
        return precoProduto;
    }

    public void setPrecoProduto(float precoProduto) {
        this.precoProduto = precoProduto;
    }

    public float getPrecoCompraProduto() {
        return precoCompraProduto;
    }

    public void setPrecoCompraProduto(float precoCompraProduto) {
        this.precoCompraProduto = precoCompraProduto;
    }

}
