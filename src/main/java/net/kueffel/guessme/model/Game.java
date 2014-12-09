package net.kueffel.guessme.model;

import java.io.Serializable;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName="guessme")
public class Game implements Serializable {

	private static final long serialVersionUID = 1L;

	@DynamoDBHashKey
	private String id;
	@DynamoDBAttribute
	private int numberToGuess;
	@DynamoDBAttribute
	private int attempts=0;
	@DynamoDBAttribute
	private boolean finished=false;
	@DynamoDBAttribute
	private boolean won=false;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Game withId(String id) {
		setId(id);
		return this;
	}

	public int getNumberToGuess() {
		return numberToGuess;
	}

	public void setNumberToGuess(int numberToGuess) {
		this.numberToGuess = numberToGuess;
	}

	public int getAttempts() {
		return attempts;
	}

	public void setAttempts(int attempts) {
		this.attempts = attempts;
	}

	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}

	public boolean isWon() {
		return won;
	}

	public void setWon(boolean won) {
		this.won = won;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + attempts;
		result = prime * result + (finished ? 1231 : 1237);
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + numberToGuess;
		result = prime * result + (won ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Game other = (Game) obj;
		if (attempts != other.attempts)
			return false;
		if (finished != other.finished)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (numberToGuess != other.numberToGuess)
			return false;
		if (won != other.won)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Game [id=");
		builder.append(id);
		builder.append(", numberToGuess=");
		builder.append(numberToGuess);
		builder.append(", attempts=");
		builder.append(attempts);
		builder.append(", finished=");
		builder.append(finished);
		builder.append(", won=");
		builder.append(won);
		builder.append("]");
		return builder.toString();
	}



}
