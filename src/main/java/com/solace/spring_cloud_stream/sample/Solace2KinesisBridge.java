package com.solace.spring_cloud_stream.sample;

/**
 * Class to which the SolaceBinder will pass Messages received from VMR
 */
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.integration.aws.support.AwsHeaders;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.SendTo;

import com.solace.spring_cloud_stream.binder.SolaceBinderConstants;

@EnableBinding(Processor.class)
public class Solace2KinesisBridge {  

	/**
	 * 
	 * @param message
	 * @return
	 */
	@StreamListener(Processor.INPUT)
	@SendTo(Processor.OUTPUT)
	public Message<?> receiveAndForward(Message<?> message) {
		MessageBuilder<?> mb = MessageBuilder.fromMessage(message);
		String destName = (String) message.getHeaders().get(SolaceBinderConstants.FIELD_DESTINATION_NAME);
		
		// Partition key is mandatory when sending to Kinesis
		mb.setHeader(AwsHeaders.PARTITION_KEY, destName);
		
		message = mb.build();
		return message;
	}
	
}
