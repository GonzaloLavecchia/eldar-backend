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


  Ejercicio 3:
  
La tabla de empleados tiene un total de 107 registros ¿Cuantos registros imprime la siguiente consulta?
  
DECLARE
CURSOR exp_cur IS
SELECT first_name FROM employees; TYPE 
nt_fName IS TABLE OF VARCHAR2(20); 
fname nt_fName;
BEGIN
OPEN exp_cur;
FETCH exp_cur BULK COLLECT INTO fname
LIMIT 10;
CLOSE exp_cur;
FOR idx IN 1 .. fname.COUNT
LOOP
DBMS_OUTPUT.PUT_LINE (idx||'
'||fname(idx) );
END LOOP;
END;

Esta consulta imprime solo 10 registros, el cursor recorre el primer nombre de los empleados pero en la clausula LIMIT 10 indica que se detenga al llegar a los 10 registros.




Ejercicio 4:

¿Que es cierto acerca de la siguiente funcion?

Create or Replace function Get_salary(P_Emp_id Number) Return Number As
L_salary Number;
Begin
Select Salary into L_salary from Employees where employee_id = P_Emp_Id;
End Get_salary;


Esta funcion presenta un error de sintaxis por lo cual no compilara, en dicha funcion falta un retorno.

Funcion corregida:

CREATE OR REPLACE FUNCTION Get_salary(P_Emp_id NUMBER) RETURN NUMBER AS
  L_salary NUMBER;
BEGIN
  SELECT Salary INTO L_salary FROM Employees WHERE employee_id = P_Emp_Id;
  RETURN L_salary;
END Get_salary;



  
