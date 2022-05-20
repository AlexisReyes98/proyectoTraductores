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
 
Caso de uso:
 
Interfaz principal:

![cap1](https://user-images.githubusercontent.com/72325257/169454450-527a8b26-24c6-420c-9226-197d4f55c17e.png)

Buscador de archivos (pueden ser .ru o .txt):

![cap2](https://user-images.githubusercontent.com/72325257/169454597-7b62a021-c94c-446c-bf78-d0b4a6464d30.png)

Después de seleccionar el archivo de entrada, el contenido aparece en el recuadro de la izquierda:

![cap3](https://user-images.githubusercontent.com/72325257/169454625-171920da-4698-4328-9666-c867a35a4bd4.png)

Después de precionar el botón de "Traducir" obtenemos el resultado en el recuadro de la derecha:

![cap4](https://user-images.githubusercontent.com/72325257/169454644-43b8e2ac-be9b-4d47-b410-4a0c91f61cb0.png)
