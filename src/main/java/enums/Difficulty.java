package enums;

    /**
     * Representing the games difficulty levels, high score uses this to build itself.
     */
    public enum Difficulty {
        SLOW(0.7),
        NORMAL(1),
        FAST(2.25);

        private double speedScale;
        Difficulty(double d) { this.speedScale = d; }
        public double getSpeedScale() { return speedScale; }

    }


