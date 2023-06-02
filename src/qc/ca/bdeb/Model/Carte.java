package qc.ca.bdeb.Model;

public class Carte {
    private String imgpath;
    private Boolean retournee;
    private Boolean trouvée;

    /**
     * Classe Carte
     * @param imgpath
     */
    public Carte(String imgpath) {
        this.imgpath = imgpath;
        this.retournee = false;
        this.trouvée = false;
    }

    public String getImgpath() {
        return imgpath;
    }

    public void setImgpath(String imgpath) {
        this.imgpath = imgpath;
    }

    public Boolean getRetournee() {
        return retournee;
    }

    public void setRetournee(Boolean retournee) {
        this.retournee = retournee;
    }

    public Boolean getTrouvée() {
        return trouvée;
    }

    public void setTrouvée(Boolean trouvée) {
        this.trouvée = trouvée;
    }
}
