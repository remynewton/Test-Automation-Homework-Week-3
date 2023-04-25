public class JavaInstruction implements Crime {
    private String description;

    public JavaInstruction() {
        this.description = JAVA_INSTR_DESC;
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