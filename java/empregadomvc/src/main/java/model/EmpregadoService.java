package model;

import java.util.ArrayList;
import java.util.List;

public class EmpregadoService {
    private static final List<Empregado>  empregados = new ArrayList<>();
    private static int contador = 1;

    public List<Empregado> listar(){
        return empregados;
    }

    public void adicionar(Empregado e){
        e.setId(contador++);
        empregados.add(e);
    }
    
}
