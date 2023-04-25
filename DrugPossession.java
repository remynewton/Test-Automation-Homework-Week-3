public class DrugPossession implements Crime {
    private String description;

    public DrugPossession() {
        this.description = DRUG_POSSESSION_DESC;
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