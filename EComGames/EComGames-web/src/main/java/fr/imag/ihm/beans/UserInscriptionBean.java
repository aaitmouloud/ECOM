package fr.imag.ihm.beans;

import fr.imag.dao.remote.IntRemoteUtilisateurDAO;
import fr.imag.entities.Utilisateur;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

@ManagedBean(name = "userInscriptionBean")
@ApplicationScoped
public class UserInscriptionBean {
    
    private Utilisateur usr;
    private String nom="";
    private String email="";
    private String dateNaissance="";
    private String password1="";
    private String password2="";
    
    public String onclick="";
    private String error="";
    
    @EJB
    IntRemoteUtilisateurDAO usrDAO;
    private Object context;
    

    
    public String creatUser() {
         Date date = new Date();
         onclick="";
         String url= "location.reload()"; 
                 //"document.location.href='signUp.jsf'";
                 //"signUp?faces-redirect=true";;
        if (nom.equals("") || email.equals("") || dateNaissance.equals("") || password1.equals("") || password2.equals("") )
        {
            //des champs ne sont pas complété
            error= "une ou des informations demandés ne sont pas remplient";
            
        }else
        {
            
            if (email.contains("@") && (email.contains(".fr") || email.contains(".com") ))
            {
                
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                try {
                    date = formatter.parse(dateNaissance);
                } catch (ParseException e) {
                    //date n'est pas dans le bon format
                    error= "le format de la date n'est pas valide";
                    onclick=url;
                    return url;
                }
            
                if (password1.equals(password2))
                {
                    usr = new Utilisateur();
                    usr.setNom(nom);
                    usr.setEmail(email);
                    usr.setDateNaissance(date);
                    usr.setHashMdp(password1);
                    
                    
                    usr.setHashMdp(password1);
                    if(usrDAO.create(usr)) {
                        //utilisateur a été créer !!!!!
                        error= ""; // "Votre compte a été créé. veuillez-vous connecter";
                        url= "PF('dlg2').show()";
                        onclick="PF('dlg2').show()";
                    }else
                    {
                        //ce loggin existe deja
                        error="ce loggin est deja pris";
                    }
                }else
                {   //mot de pass entre 1 et 2 sont different
                    error= "votre confirmation de mot de passe n'est pas correcte";
                }
                
            }else
            {
                //adresse mail non valide
                error="votre addresse email n'est pas valide";

            }
        }
        RequestContext context = RequestContext.getCurrentInstance();
        context.addCallbackParam("register", url.equals("PF('dlg2').show()"));
        onclick=url;
        return url;
    }
    //url= "index?faces-redirect=true";
    
   public String getError() {
       return error;
   }
   public String getPassword1() {
       return password1;
   }
   public String getPassword2() {
       return password2;
   }
   public String getNom() {
       return nom;
   }
   public String getEmail() {
       return email;
   }
   public String getDateNaissance() {
       return dateNaissance;
   }
   public String getOnClick() {
       return onclick;
   }
   
   
   public void setPassword1(String pass1) {
        password1=pass1;
   }
   public void setPassword2(String pass2) {
        password2=pass2;
   }
   public void setNom( String loggin) {
        nom=loggin;
   }
   public void setEmail(String mail) {
        email=mail;
   }
   public void setDateNaissance(String date) {
        dateNaissance=date;
   }

}
