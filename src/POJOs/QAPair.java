package POJOs;


public class QAPair {
	public String Question;
	public String Answer;
	public String QAuthor;
	public String AAuthor;
	public String UUID;
	
	public QAPair(String Question, String Answer, String QAuthor, String AAuthor, String UUID) {
		this.Question = Question;
		this.Answer = Answer;
		this.QAuthor = QAuthor;
		this.AAuthor = AAuthor;
		this.UUID = UUID;
	}
		
	public String getQuestion() {
		return Question;
	}
	public void setQuestion(String question) {
		Question = question;
	}
	public String getAnswer() {
		return Answer;
	}
	public void setAnswer(String answer) {
		Answer = answer;
	}
	public String getQauthor() {
		return QAuthor;
	}
	public void setQAuthor(String qAuthor) {
		QAuthor = qAuthor;
	}
	public String getAauthor() {
		return AAuthor;
	}
	public void setAAuthor(String aAuthor) {
		AAuthor = aAuthor;
	}
	public String getUuid() {
		return UUID;
	}
	public void setUUID(String uUID) {
		UUID = uUID;
	}

}
