# eldar-backend


Ejercicio 1: 

Se crean 5 metodos para la resolución de los distintos enunciados:

- Invocar un metodo que devuelva toda la informacion de la tarjeta                                                         imprimirInformacionTarjeta
  Este metodo dado un numero de tarjeta, busca la informacion en las tarjetas existentes.
- Informar si una operacion es valida                                                                                      validarOperacion
  Este metodo recibe una tarjeta y el importe el cual debe ser menor a $1000 para que sea valida la operacion
- Informar si una tarjeta es valida para operar                                                                            validarOperacionTarjeta
  Este metodo recibe una tarjeta y si su fecha de vencimiento es mayor a la fecha actual se encuentra valida para operar
- Identificar si una tarjeta es distinta a otra                                                                            diferenciarTarjetas
  Este metodo recibe dos tarjetas, las cuales son comparadas para determinar si son iguales
- Obtener por medio de un metodo la tasa de una operacion informando marca e importe                                       informarTasaOperacion
  Este metodo recibe una tarjeta y un importe y se calcula la tasa segun la marca de la tarjeta

  En este ultimo metodo me surgieron varias dudas al no entender bien la logica de las tasas, por lo cual procedi a resolverlo utilizando la siguiente logica:

  TASA VISA: importe  * AÑO / MES
  TASA NARA: importe * DIA * 0.5
  TASA AMEX: importe * MES * 0.1


  

  
