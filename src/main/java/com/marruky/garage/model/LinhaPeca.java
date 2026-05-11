package com.marruky.garage.model;
import com.marruky.garage.interfaces.Faturavel;


public class LinhaPeca {
    private Peca peca; //referencia ao catálogo (isto nao e uma copia)
    private int quantidade;
    private double precoSnapshot;

    public LinhaPeca(Peca peca, int quantidade) {
        if(peca == null) {
            throw new IllegalArgumentException("Peça nao pode ser null");
        }

        this.peca = peca;
        setQuantidade(quantidade);
        this.precoSnapshot = peca.getPrecoUnitario();
    }

    //GETTERS
    public Peca getPeca() {
        return peca;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getPrecoSnapshot() {
        return precoSnapshot;
    }

    //SETTER

    public void setQuantidade(int quantidade) {
        if(quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade tem que ser maior que 0");
        }
        this.quantidade = quantidade;
    }

    //Logica negócio

    public double calcularSubtotal() {
        return quantidade * precoSnapshot;
    }

    public double calcularSubtotalComIVA() {
        return calcularSubtotal() * (1 + Faturavel.IVA_NORMAL);
    }

    @Override
    public String toString() {
        return "LinhaPeca{" +
                "peca=" + peca.getNome() +
                ", quantidade=" + quantidade +
                ", precoSnapshot=" + precoSnapshot +
                ", subtotal=" + calcularSubtotal() +
                "}";
    }

}
