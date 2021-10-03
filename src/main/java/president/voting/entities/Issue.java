package president.voting.entities;

public enum Issue {
    PRESIDENT("President");

    private String label;

    Issue(String label) {
        this.label = label;
    }
}
