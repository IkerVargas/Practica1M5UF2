package Practica1;

import java.util.Arrays;

/**
 * Practica1 M4 UF2
 * @author Iker
 */
public class Practica1 {

    public static void main(String[] args) {
        final int NALUMNES = 40;

        // Generar les notes per als controls i les practiques
        int[] control = new int[NALUMNES];
        int[] pract = new int[NALUMNES];
        generarNotesRandom(control);
        generarNotesRandom(pract);

        // Calcular la nota final
        float[] notaFinal = new float[NALUMNES];
        calcularNotaFinal(notaFinal, pract, control);

        // Buscar la nota minima i maxima
        int minNota = buscarNotaMin(control);
        int maxNota = buscarNotaMax(control);

        // Obtenir els indexs de les notes minima i maxima
        int indMinNota = buscarIndex(control, minNota);
        int indMaxNota = buscarIndex(control, maxNota);

        // Mostra l'estadistica
        mostrarEstadistica(notaFinal);

        // Creacio de les variables de aprovats i suspesos
        int[] aprovats = new int[NALUMNES];
        int[] suspesos = new int[NALUMNES];
        int cAprovats = generarAprovats(aprovats, notaFinal);
        int cSuspesos = generarSuspesos(suspesos, notaFinal);

        resumAprovats(aprovats, cAprovats);
        resumSuspesos(suspesos, cSuspesos);

        // Mostrar els resultats
        System.out.println("Nota minim: " + minNota);
        System.out.println("Nota maxima: " + maxNota);
        System.out.println("Index de la minima nota: " + indMinNota);
        System.out.println("Index la maxima nota: " + indMaxNota);
        System.out.println("Llista de la classe: " + Arrays.toString(generarLlista(NALUMNES)));
        System.out.println("Array de les notes: " + Arrays.toString(control));
        System.out.println("Array de les practiques: " + Arrays.toString(pract));
        System.out.println("Array de les notes finals: " + Arrays.toString(notaFinal));
    }

    // Genera notes aleatories entre 0 i 10, i es guarden a un array notes
    public static void generarNotesRandom(int[] notes) {
        for (int i = 0; i < notes.length; i++) {
            notes[i] = (int) (Math.random() * 11);
        }
    }

    // Busca la nota minima en un array de notes, retorna la nota minima
    public static int buscarNotaMin(int[] notes) {
        int minNota = notes[0];
        for (int note : notes) {
            if (note < minNota) {
                minNota = note;
            }
        }
        return minNota;
    }

    // Busca la nota maxima en un array de notes, retorna la nota maxima
    public static int buscarNotaMax(int[] notes) {
        int maxNota = notes[0];
        for (int note : notes) {
            if (note > maxNota) {
                maxNota = note;
            }
        }
        return maxNota;
    }

    // Busca l'índex d'una nota específica en un array de notes, retorna l'index de la nota
    public static int buscarIndex(int[] notes, int nota) {
        for (int i = 0; i < notes.length; i++) {
            if (notes[i] == nota) {
                return i + 1;
            }
        }
        return -1;
    }

    // Genera una llista de nombres consecutius a partir de 1 fins a size, retorna l'array generat
    public static int[] generarLlista(int size) {
        int[] llista = new int[size];
        for (int i = 0; i < size; i++) {
            llista[i] = i + 1;
        }
        return llista;
    }

    // Calcula la nota final com la mitjana de les notes de control i practiques
    public static void calcularNotaFinal(float[] promedi, int[] pract, int[] control) {
        for (int i = 0; i < promedi.length; i++) {
            promedi[i] = (float) (control[i] + pract[i]) / 2;
        }
    }

    // Mostra l'estadística les notes finals
    public static void mostrarEstadistica(float[] notaFinal) {
        int[] estadistica = new int[11];
        for (float nota : notaFinal) {
            estadistica[(int) Math.floor(nota)]++;
        }
        for (int i = 0; i < estadistica.length; i++) {
            double percentatge = (double) estadistica[i] / notaFinal.length * 100;
            System.out.printf("Estadistica de notes finals %2d = %5.2f%%\n", i, percentatge);
        }
    }

    // Genera una llista d'alumnes aprovats, retorna el numero d'aprovats
    public static int generarAprovats(int[] aprovats, float[] notaFinal) {
        int cAprovats = 0;
        for (int i = 0; i < notaFinal.length; i++) {
            if (notaFinal[i] >= 5) {
                aprovats[cAprovats] = i + 1;
                cAprovats++;
            }
        }
        return cAprovats;
    }

    // Genera una llista d'alumnes suspesos, retorna el numero de suspesos
    public static int generarSuspesos(int[] suspesos, float[] notaFinal) {
        int cSuspesos = 0;
        for (int i = 0; i < notaFinal.length; i++) {
            if (notaFinal[i] < 5) {
                suspesos[cSuspesos] = i + 1;
                cSuspesos++;
            }
        }
        return cSuspesos;
    }

    // Mostra el resum d'alumnes aprovats
    public static void resumAprovats(int[] aprovats, int cAprovats) {
        int[] alumnesAprovats = Arrays.copyOf(aprovats, cAprovats);
        System.out.println("Resum d'aprovats per numero de llista: " + Arrays.toString(alumnesAprovats));
    }

    // Mostra el resum d'alumnes suspesos
    public static void resumSuspesos(int[] suspesos, int cSuspesos) {
        int[] alumnesSuspesos = Arrays.copyOf(suspesos, cSuspesos);
        System.out.println("Resum de suspesos per numero de llista: " + Arrays.toString(alumnesSuspesos));
    }
}
