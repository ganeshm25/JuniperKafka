
package com.juniper.kafka.pubsub.common;

import com.google.api.gax.core.CredentialsProvider;
import com.google.protobuf.ByteString;
import io.grpc.Channel;
import io.grpc.ClientInterceptors;
import io.grpc.ManagedChannel;
import java.io.IOException;
import java.util.concurrent.Executors;

/** Utility methods and constants that are repeated across one or more classes. */
public class ConnectorUtils {

  //private static final String ENDPOINT = "pubsub.googleapis.com";

  public static final String SCHEMA_NAME = ByteString.class.getName();
  public static final String CPS_SUBSCRIPTION_FORMAT = "projects/%s/subscriptions/%s";
  public static final String CPS_TOPIC_FORMAT = "projects/%s/topics/%s";
  public static final String CPS_PROJECT_CONFIG = "cps.project";
  public static final String CPS_TOPIC_CONFIG = "cps.topic";
  public static final String CPS_MESSAGE_KEY_ATTRIBUTE = "key";
  public static final String GCP_CREDENTIALS_FILE_PATH_CONFIG  = "gcp.credentials.file.path";
  public static final String GCP_CREDENTIALS_JSON_CONFIG  = "gcp.credentials.json";
  public static final String KAFKA_MESSAGE_CPS_BODY_FIELD = "message";
  public static final String KAFKA_TOPIC_ATTRIBUTE = "kafka.topic";
  public static final String KAFKA_PARTITION_ATTRIBUTE = "kafka.partition";
  public static final String KAFKA_OFFSET_ATTRIBUTE = "kafka.offset";
  public static final String KAFKA_TIMESTAMP_ATTRIBUTE = "kafka.timestamp";

  /** Return {@link io.grpc.Channel} which is used by Cloud Pub/Sub gRPC API's. */
  /*public static Channel getChannel(CredentialsProvider credentialsProvider) throws IOException {
    ManagedChannel channelImpl =
        NettyChannelBuilder.forAddress(ENDPOINT, 443)
            .negotiationType(NegotiationType.TLS)
            // Maximum Pub/Sub message size is 10MB.
            .maxInboundMessageSize(10 * 1024 * 1024)
            .build();
    final ClientAuthInterceptor interceptor =
        new ClientAuthInterceptor(
            credentialsProvider.getCredentials(),
            Executors.newCachedThreadPool());
    return ClientInterceptors.intercept(channelImpl, interceptor);
  }*/

}
