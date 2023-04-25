public class Assault implements Crime {
    private String description;

    public Assault() {
        this.description = ASSAULT_DESC;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public int getSeverity() {
        return LOW_SEVERITY;
    }

    @Override
    public void setSeverity(int severity) {
        // do nothing, as severity is already fixed for this type of crime
    }
}
