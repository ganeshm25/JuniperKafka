
package com.juniper.kafka.pubsub.common;

import com.google.api.gax.core.CredentialsProvider;
import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ConnectorCredentialsProvider implements CredentialsProvider {

  private static final List<String> CPS_SCOPE =
    Arrays.asList("https://www.googleapis.com/auth/pubsub");

  GoogleCredentials credentials;

  public void loadFromFile(String credentialPath) throws IOException {
    this.credentials = GoogleCredentials.fromStream(new FileInputStream(credentialPath));
  }

  public void loadJson(String credentialsJson) throws IOException {
    ByteArrayInputStream bs = new ByteArrayInputStream(credentialsJson.getBytes());
    this.credentials = credentials = GoogleCredentials.fromStream(bs);
  }

  @Override
  public Credentials getCredentials() throws IOException {
    if (this.credentials == null) {
      return GoogleCredentials.getApplicationDefault().createScoped(this.CPS_SCOPE);
    } else {
      return this.credentials.createScoped(this.CPS_SCOPE);
    }
  }

}
