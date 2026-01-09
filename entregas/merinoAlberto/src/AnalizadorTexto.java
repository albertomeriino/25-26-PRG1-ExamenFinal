import java.util.Scanner;

class AnalizadorTexto {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Analizador de Texto - Herramienta Linguistica v2.0");
        System.out.println("Introduce un texto (mínimo 10 caracteres):");
        String texto = sc.nextLine();

        boolean textoValido = true;
        if (texto.length() < 10) {
            System.out.println("El texto es demasiado corto");
            textoValido = false;
        }

        if (textoValido) {
            int longitudTexto = texto.length();
            int vocales = 0;
            int consonantes = 0;
            int numeros = 0;
            int espacios = 0;
            int otros = 0;

            for (int i = 0; i < longitudTexto; i++) {
                char c = texto.charAt(i);

                boolean esLetra = (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
                boolean esNumero = (c >= '0' && c <= '9');

                if (esLetra) {
                    boolean esVocal = c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u'
                                   || c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U';
                    if (esVocal) {
                        vocales++;
                    } else {
                        consonantes++;
                    }
                } else if (esNumero) {
                    numeros++;
                } else if (c == ' ') {
                    espacios++;
                } else {
                    otros++;
                }
            }

            System.out.println("ESTADÍSTICAS BÁSICAS");
            System.out.println("Longitud: " + longitudTexto);
            System.out.println("Vocales: " + vocales);
            System.out.println("Consonantes: " + consonantes);
            System.out.println("Números: " + numeros);
            System.out.println("Espacios: " + espacios);
            System.out.println("Otros: " + otros);

            if (numeros > 5 && otros >= 2 && espacios < longitudTexto / 10) {
                System.out.println("Categoría: Código o expresión matemática");
            } else if (vocales > consonantes && espacios >= 2 && numeros == 0) {
                System.out.println("Categoría: Texto literario con alta densidad vocálica");
            } else if (consonantes > vocales && (otros >= 3 || numeros >= 2)) {
                System.out.println("Categoría: Texto técnico o científico");
            } else if (espacios > longitudTexto / 5) {
                System.out.println("Categoría: Texto informal o conversacional");
            } else {
                System.out.println("Categoría: Texto genérico");
            }

            int palabras = 0;
            for (int i = 0; i < longitudTexto; i++) {
                if (texto.charAt(i) != ' ' && (i == 0 || texto.charAt(i - 1) == ' ')) {
                    palabras++;
                }
            }
            System.out.println("Palabras estimadas: " + palabras);

            double densidadVocales = (vocales * 100.0) / longitudTexto;
            if (densidadVocales > 45 && vocales > 10 && consonantes > 5 && espacios > 2) {
                System.out.println("¡Texto muy fluido!");
            } else if (densidadVocales < 25 && consonantes > 10 && (otros > 5 || numeros > 3)) {
                System.out.println("Texto complejo de leer");
            }

            boolean tieneMayuscula = false;
            boolean tieneMinuscula = false;
            for (int i = 0; i < longitudTexto; i++) {
                char c = texto.charAt(i);
                if (c >= 'A' && c <= 'Z') {
                    tieneMayuscula = true;
                } else if (c >= 'a' && c <= 'z') {
                    tieneMinuscula = true;
                }
                if (tieneMayuscula && tieneMinuscula) {
                    break;
                }
            }

            char primera = texto.charAt(0);
            char ultima = texto.charAt(longitudTexto - 1);
            boolean empiezaMayuscula = primera >= 'A' && primera <= 'Z';
            boolean terminaBien = ultima == '.' || ultima == '!' || ultima == '?';

            if (tieneMayuscula && tieneMinuscula && empiezaMayuscula && terminaBien) {
                System.out.println("Formato: Oración bien formada");
            } else if (tieneMayuscula && !tieneMinuscula && numeros >= 2) {
                System.out.println("Formato: Código o identificador");
            } else {
                System.out.println("Formato: Variado");
            }
        }
    }
}