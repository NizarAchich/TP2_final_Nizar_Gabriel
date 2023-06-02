package qc.ca.bdeb.Utilities;

    public class Temps {
        private Integer secondes;
        private Integer minutes;
        private Integer heures;

        public Temps() {
            secondes = 0;
            minutes = 0;
            heures = 0;
        }
        public Temps(Integer heures,Integer minutes,Integer secondes){
            this.heures=heures;
            this.minutes=minutes;
            this.secondes=secondes;
        }

        public Integer getSecondes() {
            return secondes;
        }

        public void setSecondes(Integer seconde) {
            this.secondes = seconde;
        }

        public Integer getMinutes() {
            return minutes;
        }

        public void setMinutes(Integer minutes) {
            this.minutes = minutes;
        }

        public Integer getHeures() {
            return heures;
        }

        public void setHeures(Integer heures) {
            this.heures = heures;
        }

        public String toString() {
            return heures + "h:" + minutes + "min:" + secondes + "sec";
        }

        public void Ajout_une_seconde() {
            secondes = secondes + 1;
            if (secondes == 60) {
                secondes = 0;
                minutes = minutes + 1;
                if (minutes == 60) {
                    minutes = 0;
                    heures = heures + 1;
                }
            }
        }

    }
