package enums;

    public enum Difficulty {

        /**
         *
         */
        SLOW(4.0), // Difficulté lente avec une vitesse de 4.0
        NORMAL(5.0), // Difficulté normale avec une vitesse de 5.0
        FAST(6.0); // Difficulté rapide avec une vitesse de 6.0
        private double speed;

        // Constructeur prenant en paramètre la vitesse de la difficulté
        Difficulty(double d) { this.speed = d; }
        public double getSpeedScale() { return speed; } // pour modifier la valeur de la vitesse dans le controlleur du jeu

    }


