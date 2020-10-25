/*
 * Clase encargada de contener la parte logica del codigo
 */
package com.mycompany.pruebaee;

import javax.inject.Named;
import javax.enterprise.context.ConversationScoped;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author Johan Andres Sanchez
 * @author Fernando Arévalo
 */
@Named(value = "conversationBean")
@ConversationScoped
public class ConversationBean implements Serializable {
  
  @Inject
  private Conversation conversation;
  
  private int counter;
  
  // El codigo solo se inicializa una vez
  @PostConstruct
  public void init(){
    counter = 0;
  }
  /*
   * Esta función inicia la conversación desde que el resultado que recibe la varable es falso
  */
  public void initConversation(){
    if (!FacesContext.getCurrentInstance().isPostback() 
      && conversation.isTransient()) {
      
      conversation.begin();
    }
  }
  /*
  * Se crea el metodo por el cual se ve el auto incremento de los números
  */
  public void increment(){
    counter++;
  }
  /*
   *Metodo con el cual se logra hacer el cambio de pagina 
  */
  public String handleFirstStepSubmit(){
    return "step2?faces-redirect=true";
  }
  /*
   *Metodo con el cual se logra terminar todo el programa
  */
  public String endConversation(){
    if(!conversation.isTransient()){
      conversation.end();
    }
    return "step1?faces-redirect=true";
  }

  public int getCounter() {
    return counter;
  }

  public void setCounter(int counter) {
    this.counter = counter;
  }

  public Conversation getConversation() {
    return conversation;
  }
    public ConversationBean() {
    }
    
}
