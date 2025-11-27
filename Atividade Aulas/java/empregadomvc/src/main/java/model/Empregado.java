package model;

public class Empregado {
    private int id;
    private String nome;
    private String cargo;
    private double salario;

    public Empregado(int id, String nome, String cargo, double salario){
        this.id = id;
        this.nome = nome;
        this.cargo = cargo;
        this.salario = salario;
    }
    
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome;}

    public String getCargo() { return cargo; } 
    public void setCargo(String cargo) { this.cargo = cargo; }

    public double getSalario() { return salario; }
    public void setSalario(double salario) { this.salario = salario; }
}
