/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ud.example.com;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Samae
 */
@WebService(serviceName = "MiServicioWeb")
public class MiServicioWeb {
    private double Euro;
    public MiServicioWeb(){
          Euro =4300;  
          }
    /**
     * This is a sample web service operation
     */
   @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "EURO HOY" + txt + " !";
    }
       
    @WebMethod(operationName = "getEuro")
    public double getEuro(){
    return Euro;
    }
    
     @WebMethod(operationName = "peso2Euro")
    public double peso2Euro(@WebParam(name="valor")String valor1){
    return Double.parseDouble(valor1)/Euro;
    }
    
     @WebMethod(operationName = "Euro2Pero")
    public double Euro2Pero(@WebParam(name="valor")String valor1){
    return Double.parseDouble(valor1)*Euro;
    }
     
    
}
