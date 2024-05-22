package com.example.pruebatecnica.servicio;

public class Utils {
    public String Mensaje(int numException,String Clase,String Accion){
        String mjs="";
        switch (numException){
            case 200:{
                mjs="Se '"+Accion+"' Correctamente en "+Clase;
                break;
            }
            case 201:{
                mjs="Se Registro Correctamente "+Clase;
                break;
            }
            case 400:{
                mjs="ERROR EN '"+Accion+"': no se encontro el valor de "+Clase+" revise si los valores son correctos";
                break;
            }
            case 404:{
                mjs="ERROR EN '"+Accion+"': no se pudo encontrar el valor de "+Clase+" el dato enviado es erroneo o no existe";
                break;
            }
            case 500:{
                mjs="ERROR EN '"+Accion+"': en la aplicacion en la "+Clase;
                break;
            }
            default:{
                mjs="No existe ese codigo de excepcion";
            }
        }
        return mjs;
    }
    public String GenerarID(){
        String Clave="";
        return Clave;
    }


}
