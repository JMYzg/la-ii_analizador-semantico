package mx.edu.analizador.semantico;

import mx.edu.analizador.lexico.Token;
import java.util.ArrayList;
import java.util.List;

import java.util.HashSet;
import java.util.Set;

public class ErrorConversionTipos 
{

    public List<ErrorSemantico> analizar(List<Token> tokens) 
    {

        List<ErrorSemantico> errores = new ArrayList<>();

        Set<String> variablesStruct = new HashSet<>();


        for (int i = 0; i + 2 < tokens.size(); i++)  

        {

     Token token = tokens.get(i);

     if (token.lexema().equals("struct")) 
         {

        Token siguiente = tokens.get(i + 1);
        Token variable = tokens.get(i + 2);

        if (siguiente.tipo().name().equals("IDENTIFICADOR")
                && variable.tipo().name().equals("IDENTIFICADOR")) 
            {

            variablesStruct.add(variable.lexema()); //guarda el nombre de esta variable en nuestra coleccion de estructuras

            System.out.println("Variable struct encontrada: "
                    + variable.lexema());
            }
            }   
        }   



for (int i = 0; i + 3 < tokens.size(); i++) {

    Token apertura = tokens.get(i);
    Token tipoDestino = tokens.get(i + 1);
    Token cierre = tokens.get(i + 2);
    Token origen = tokens.get(i + 3);

    if (apertura.lexema().equals("(")
            && cierre.lexema().equals(")")) {

        if (tipoDestino.lexema().equals("int")
                && variablesStruct.contains(origen.lexema())) {

            errores.add(new ErrorSemantico(
             "Conversión de tipos no permitida",
             "No se puede convertir una estructura a int",
              origen.linea(),
              origen.inicio(),
              origen.fin()
        ));
        }
    }
}

        
        
        return errores;
    }
}