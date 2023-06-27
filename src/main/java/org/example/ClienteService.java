package org.example;

import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.ArrayList;
import java.util.List;

public class ClienteService {

    List<Cliente> clientes = new ArrayList<>();
    private String senhaDigitada;

    //Métodos
    public void adicionaCliente(Cliente cliente){
        criptografarSenha(cliente);
        clientes.add(cliente);
    }

    public void criptografarSenha(Cliente cliente){
        this.senhaDigitada = cliente.getSenha(); // Mudança para armazenar senha digitada, apenas para fins didáticos

        String hashDaSenha = BCrypt.hashpw(cliente.getSenha(), BCrypt.gensalt());
        cliente.setSenha(hashDaSenha);
    }

    public void mostrarSenhas(){
        System.out.println("Senha digitada: " + this.senhaDigitada);
        System.out.println("Senha criptografada: " + clientes.get(0).getSenha());
    }

    public boolean login(String senha){
        return BCrypt.checkpw(senha, clientes.get(0).getSenha());
    }
}
