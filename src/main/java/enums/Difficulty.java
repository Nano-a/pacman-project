package enums;

    /**
     * Representing the games difficulty levels.
     */
    public enum Difficulty {
        SLOW(0.7),
        NORMAL(1),
        FAST(2.25);

        private double speedScale;
        Difficulty(double d) { this.speedScale = d; }

        /**
         * @return speed scale for given difficulty.
         */
        public double getSpeedScale() { return speedScale; }

    }


