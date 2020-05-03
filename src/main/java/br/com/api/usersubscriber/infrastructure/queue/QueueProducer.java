package br.com.api.usersubscriber.infrastructure.queue;

public interface QueueProducer {
    boolean send(String payload);
}
