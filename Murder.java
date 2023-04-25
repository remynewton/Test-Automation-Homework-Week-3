public class Murder implements Crime {
    private String description;

    public Murder() {
        this.description = MURDER_DESC;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public int getSeverity() {
        return HIGH_SEVERITY;
    }

    @Override
    public void setSeverity(int severity) {
        // do nothing, as severity is already fixed for this type of crime
    }
}