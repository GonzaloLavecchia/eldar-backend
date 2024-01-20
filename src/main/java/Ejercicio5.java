public class Ejercicio5 {

    public static void main(String[] args) {
        String[] my_array = {"FirstWord", "SecondWord", "THIRDWORD"};

        try {
            convertirArray(my_array);
        } catch (IllegalArgumentException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }



    private static void convertirArray(String[] strings) throws IllegalArgumentException {
        if (strings.length > 0 && strings.length <= 10) {
            try {

                System.out.println(String.join(" ", strings).toLowerCase());
            } catch (Exception e) {
                throw new IllegalArgumentException("Error al procesar el array: " + e.getMessage());
            }
        } else if (strings.length == 0) {
            throw new IllegalArgumentException("array vacio");
        } else {
            throw new IllegalArgumentException("El array excede el limite de 10 elementos");
        }
    }
}
