import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

public class Ejercicio1 {


    public static void main(String[] args) {

        TarjetaCredito tarjeta1 = new TarjetaCredito("VISA", "1234-5678-9101-1121", "Carlos", "Perez", generateRandomDate());
        TarjetaCredito tarjeta2 = new TarjetaCredito("NARA", "5678-1234-2111-0199", "Gonzalo", "Lavecchia", generateRandomDate());
        TarjetaCredito tarjeta3 = new TarjetaCredito("AMEX", "9876-5432-3210-1011", "Jose", "Gonzalez", generateRandomDate());


        imprimirInformacionTarjeta("9876-5432-3210-1011", tarjeta1, tarjeta2, tarjeta3);
        validarOperacion(tarjeta1,5000);
        validarOperacionTarjeta(tarjeta2);
        diferenciarTarjetas(tarjeta1,tarjeta3);
        informarTasaOperacion(tarjeta3,600);
    }


    private static void imprimirInformacionTarjeta(String targetCardNumber, TarjetaCredito... tarjetas) {
        try {
            for (TarjetaCredito tarjeta : tarjetas) {
                if (tarjeta.getNumero_tarjeta().equals(targetCardNumber)) {
                    System.out.println("Información de la tarjeta " + tarjeta.getNumero_tarjeta() + ":");
                    System.out.println("Marca: " + tarjeta.getMarca());
                    System.out.println("Numero de tarjeta: " + tarjeta.getNumero_tarjeta());
                    System.out.println("Nombre: " + tarjeta.getNombre());
                    System.out.println("Apellido: " + tarjeta.getApellido());
                    System.out.println("Fecha vencimiento: " + formatDate(tarjeta.getFecha_vencimiento()));
                    return;
                }
            }
            System.out.println("Tarjeta con número " + targetCardNumber + " no encontrada.");
        }catch (Exception e){
            System.out.println("Error al buscar la tarjeta: " + e.getMessage());
        }
    }


    private static void validarOperacion(TarjetaCredito tarjeta, double importe) {
        System.out.println("Validando operacion para la tarjeta: " + tarjeta.getNumero_tarjeta());

        if (tarjeta.operacionValida(importe)) {
            System.out.println("Transaccion valida. Importe: $" + importe);
        } else {
            System.out.println("Transaccion incorrecta. El importe excede el limite. Importe: $" + importe);
        }
        System.out.println();
    }

    private static void validarOperacionTarjeta(TarjetaCredito tarjeta) {
        System.out.println("Validando operacion para la tarjeta: " + tarjeta.getNumero_tarjeta());

        System.out.println("Fecha Vencimiento: " + formatDate((tarjeta.getFecha_vencimiento())));

        if (tarjeta.validarParaOperar()) {
            System.out.println("La tarjeta es valida." );
        } else {
            System.out.println("La tarjeta no puede operar esta vencida." );
        }
        System.out.println();
    }


    private static void diferenciarTarjetas(TarjetaCredito tarjeta1, TarjetaCredito tarjeta2) {
        if (!tarjeta1.equals(tarjeta2)) {
            System.out.println("La tarjeta " + tarjeta1.getNumero_tarjeta() + " es diferente a la tarjeta " + tarjeta2.getNumero_tarjeta());
        } else {
            System.out.println("La tarjeta " + tarjeta1.getNumero_tarjeta() + " es la misma que la tarjeta " + tarjeta2.getNumero_tarjeta());
        }
    }


    private static void informarTasaOperacion(TarjetaCredito tarjeta, double importe){
        if (tarjeta != null) {
            double tasa = tarjeta.obtenerTasa(tarjeta, importe);
            System.out.println("La tasa para la operacion de la tarjeta " + tarjeta.getNumero_tarjeta() +
                    ", marca " + tarjeta.getMarca() + ", y importe $" + importe + " es : " + tasa);
        }else {
            throw new NullPointerException("No hay ninguna tarjeta ingresada.");
        }
    }

    private static Date generateRandomDate() {
        int minYear = 2000;
        int maxYear = 2050;
        int randomYear = ThreadLocalRandom.current().nextInt(minYear, maxYear + 1);

        int randomMonth = ThreadLocalRandom.current().nextInt(1, 13);

        String randomDateStr = String.format("%02d/%04d", randomMonth, randomYear);

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/yyyy");
            return dateFormat.parse(randomDateStr);
        } catch (Exception e) {
            throw new RuntimeException("Error generating random date: " + e.getMessage());
        }
    }

    private static String formatDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/yyyy");
        return dateFormat.format(date);
    }

}



class TarjetaCredito {
    private String marca;
    private String numero_tarjeta;
    private String nombre;
    private String apellido;
    private Date fecha_vencimiento;


    public TarjetaCredito(String marca, String numero_tarjeta, String nombre, String apellido, Date fecha_vencimiento) {
        this.marca = marca;
        this.numero_tarjeta = numero_tarjeta;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fecha_vencimiento = fecha_vencimiento;
    }

    public boolean operacionValida(double importe) {
        return importe < 1000.0;
    }

    public boolean validarParaOperar() {
        Date currentDate = new Date();
        return getFecha_vencimiento().after(currentDate);
    }

    public double obtenerTasa(TarjetaCredito tarjeta,double importe){
        Calendar fechaActual = Calendar.getInstance();
        if (importe < 0)
            throw new IllegalArgumentException("El importe no puede ser negativo.");
        if (tarjeta.marca.equals("VISA")){
            return importe * fechaActual.get(Calendar.YEAR) / (fechaActual.get(Calendar.MONTH)+1);
        }else
            if (tarjeta.marca.equals("NARA")){
                return importe * fechaActual.get(Calendar.DAY_OF_MONTH) * 0.5;
        }else
            if (tarjeta.marca.equals("AMEX")){
                return importe * (fechaActual.get(Calendar.MONTH)+1) * 0.1;
            }

            return 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        TarjetaCredito that = (TarjetaCredito) obj;
        return marca.equals(that.marca) &&
                numero_tarjeta.equals(that.numero_tarjeta) &&
                nombre.equals(that.nombre) &&
                apellido.equals(that.apellido) &&
                fecha_vencimiento.equals(that.fecha_vencimiento);
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getNumero_tarjeta() {
        return numero_tarjeta;
    }

    public void setNumero_tarjeta(String numero_tarjeta) {
        this.numero_tarjeta = numero_tarjeta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Date getFecha_vencimiento() {
        return fecha_vencimiento;
    }

    public void setFecha_vencimiento(Date fecha_vencimiento) {
        this.fecha_vencimiento = fecha_vencimiento;
    }
}



