/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package harker.gutierrez.andres.ejemplopropiospringbootmaven;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author aharker
 */
@Service
public class MyBeanSingleton {
    
    private List<String> mensajesRecibidos;
    
    public void addMessage(String message){
        if(mensajesRecibidos==null){
            mensajesRecibidos = new ArrayList();
        }
        mensajesRecibidos.add(message);
    }

    public List<String> getMensajesRecibidos() {
        return mensajesRecibidos;
    }

    public void setMensajesRecibidos(List<String> mensajesRecibidos) {
        this.mensajesRecibidos = mensajesRecibidos;
    }
    
    
    
}
