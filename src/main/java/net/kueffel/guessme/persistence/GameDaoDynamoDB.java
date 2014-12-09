package net.kueffel.guessme.persistence;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

import net.kueffel.guessme.model.Game;

public class GameDaoDynamoDB implements GameDao,InitializingBean {

	@Autowired
	private AmazonDynamoDB dynamoClient;
	private DynamoDBMapper mapper;
	
	
	@Override
	public Game findById(String id) {
		return mapper.load(Game.class,id);
	}

	@Override
	public void save(Game game) {
		mapper.save(game);

	}

	@Override
	public void afterPropertiesSet() throws Exception {
		mapper=new DynamoDBMapper(dynamoClient);
	}

}
