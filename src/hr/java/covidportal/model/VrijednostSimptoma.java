package hr.java.covidportal.model;

public enum VrijednostSimptoma {
    RIJETKO("RIJETKO"), SREDNJE("SREDNJE"), CESTO("ČESTO");

    public final String vrijednost;

    private VrijednostSimptoma(String vrijednost) {
        this.vrijednost = vrijednost;
    }

    public String getVrijednost() {
        return vrijednost;
    }

    @Override
    public String toString() {
        return "VrijednostSimptoma{" +
                "vrijednost='" + vrijednost + '\'' +
                '}';
    }
}
