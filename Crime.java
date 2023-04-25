public interface Crime {
    public static final String THEFT = "Theft";
    public static final String ASSAULT = "Assault";
    public static final String BURGLARY = "Burglary";
    public static final String FRAUD = "Fraud";
    public static final String DRUG_POSSESSION = "Drug Possession";
    public static final String MURDER = "Murder";
    public static final String JAVA_INSTR = "Java Instruction";

    public static final int LOW_SEVERITY = 1;
    public static final int MEDIUM_SEVERITY = 2;
    public static final int HIGH_SEVERITY = 3;

    public static final String THEFT_DESC = "Taking someone's property without their consent.";
    public static final String ASSAULT_DESC = "Physically attacking someone, causing them harm.";
    public static final String BURGLARY_DESC = "Entering a building illegally to commit a crime.";
    public static final String FRAUD_DESC = "Deceiving someone to obtain money or property.";
    public static final String DRUG_POSSESSION_DESC = "Illegally possessing controlled substances.";
    public static final String MURDER_DESC = "Intentionally killing another person.";
    public static final String JAVA_INSTR_DESC = "The vile act of teaching the Java programming language";
    
    public String getDescription();
    public void setSeverity(int severity);
    public int getSeverity();
}