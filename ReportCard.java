package ReportCard;

public class ReportCard {

	private static final int YEAR = 2017;
	
    private String grade;
    private int geography;
    private int history;
    private int physics;
    private int chemistry;
    private int math;
  
    public ReportCard(String grade, int geography, int history, int physics, int chemistry, int math) {
		super();
		this.grade = grade;
		this.geography = geography;
		this.history = history;
		this.physics = physics;
		this.chemistry = chemistry;
		this.math = math;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public int getGeography() {
		return geography;
	}

	public void setGeography(int geography) {
		this.geography = geography;
	}

	public int getHistory() {
		return history;
	}

	public void setHistory(int history) {
		this.history = history;
	}

	public int getPhysics() {
		return physics;
	}

	public void setPhysics(int physics) {
		this.physics = physics;
	}

	public int getChemistry() {
		return chemistry;
	}

	public void setChemistry(int chemistry) {
		this.chemistry = chemistry;
	}

	public int getMath() {
		return math;
	}

	public void setMath(int math) {
		this.math = math;
	}
	
	public static int getYear() {
        return YEAR;
    }

	@Override
    public String toString() {
        return "Result { " +
                "Grade = " + grade +
                ", History = " + history +
                ", Geography = " + geography +
                ", Math = " + math +
                ", Chemistry = " + chemistry +
                ", Physics = " + physics +
                " }";
    }
}
