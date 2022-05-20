# ANTLR
LENGUAJE RU
a) Contaremos con una interfaz de usuario. La interfaz de usuario dará la
posibilidad de ingresar el código que será traducido y en otra parte de
la ventana me mostrará el resultado de la ejecución o salida. Es decir,
podré ingresar la entrada o input se desplegarán los resultados de la
ejecución o output de la traducción en otra parte de la ventana . Así
mismo, como alternativa para el input en la ventana, se contará con un
botón botón mediante el cual podré seleccionar un archivo de entrada
donde se encuentre el código que será traducido y ejecutado y, me dirá
cuál es el archivo de salida, donde se encuentran escritos los resultado de
la ejecución.

    Input test.ru

Gramática descrita en:

    Ru.g4
    
b) Partes y funciones del analizador.

-> Visitador clase abstracta: Script generado de manera automática a partir de la gramática (Ru.g4)

    RuBaseVisitor.java

-> Analizador Léxico:  

    RuLexer
    
-> Analizador Sintáctico:  

    RuParser

c) Uso del analizador

    Se debe dar click derecho en el editor IntelliJ sobre la clase Interfaz para ejecutarla. 
    
-> Click en 'Buscar archivo'
  -> Seleccionar cualquier_archivo.ru
    -> El contenido del archivo se muestra en el campo de texto de la izquierda.
      -> Click en Traducir
        -> Los resultados se muestran en el campo de texto de la derecha
