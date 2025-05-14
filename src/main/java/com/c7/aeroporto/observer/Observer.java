package com.c7.aeroporto.observer;

//interface base, define o contrato para qualquer classe que queira ser notificada

public interface Observer {
    void update(String message); // é chamado quando o Subject notifica uma mudança
}
