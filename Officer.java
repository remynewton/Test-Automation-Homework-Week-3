public class Officer extends Person {
    private int badgeNumber;
    private String rank;

    public Officer(String name, String DOB, String address, int badgeNumber, String rank) {
        super(name, DOB, address);
        this.badgeNumber = badgeNumber;
        this.rank = rank;
    }

    public int getBadgeNumber() {
        return badgeNumber;
    }

    public void setBadgeNumber(int badgeNumber) {
        this.badgeNumber = badgeNumber;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    @Override
    public String getProfile() {
        return "Officer " + getName() + " (" + getRank() + "), Badge #" + getBadgeNumber();
    }

    @Override
    public String toString() {
        return getProfile();
    }

    @Override
    public int hashCode() {
        return getProfile().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Officer) {
            Officer otherOfficer = (Officer) obj;
            return getProfile().equals(otherOfficer.getProfile());
        }
        return false;
    }
}