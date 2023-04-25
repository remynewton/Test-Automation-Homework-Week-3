public class Burglary implements Crime {
    private String description;

    public Burglary() {
        this.description = BURGLARY_DESC;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public int getSeverity() {
        return MEDIUM_SEVERITY;
    }

    @Override
    public void setSeverity(int severity) {
        // do nothing, as severity is already fixed for this type of crime
    }
}
