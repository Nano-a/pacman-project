/*
 * La classe SimultaneousClip stocke des données audio avec leur format. Cette classe est conçue
 * pour gérer des clips audio qui peuvent être joués simultanément, bien qu'elle ne contienne que des
 * méthodes pour accéder à ses propriétés et non pour la lecture audio elle-même.
 */
package audio;

import javax.sound.sampled.AudioFormat;

// La classe SimultaneousClip permet de stocker et manipuler des données audio pour être jouées simultanément.
public class SimultaneousClip {
    private AudioFormat format; // Format du clip audio
    private byte[] data; // Données du clip audio

    // Constructeur pour créer un SimultaneousClip avec un format et des données
    public SimultaneousClip(AudioFormat format, byte[] data) {
        this.format = format;
        this.data = data;
    }

    // Renvoie le format audio
    public AudioFormat getFormat() {
        return format;
    }

    // Renvoie les données de l'audio
    public byte[] getData() {
        return data;
    }
}
