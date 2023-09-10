package com.github.peco2282.gitlib.socket.commit;

import com.github.peco2282.gitlib.commit.header.HeaderCommit;
import com.github.peco2282.gitlib.exceptions.*;
import com.github.peco2282.gitlib.socket.Request;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class CommitHeaderRequest extends CommitRequest<HeaderCommit> {
  private final String commitSha;

  public CommitHeaderRequest(String owner, String repo, String commitSha) {
    this.owner = owner;
    this.repo = repo;
    this.commitSha = commitSha;
    this.maps = Map.of();
  }

  @Override
  public HeaderCommit[] request() throws BaseException {
    //    HttpURLConnection connection = null;
    InputStream stream;
    BufferedReader reader = null;
    Gson gson = new Gson();
    StringBuilder builder = new StringBuilder();
    HttpURLConnection connection = null;

    try {
      URL url = new URL(Request.BASE + String.format("repos/%s/%s/commits/%s/branches-where-head", owner, repo, commitSha));
      connection = (HttpURLConnection) url.openConnection();
      connection.setRequestMethod(Request.GET);
      connection.connect();

      int status = connection.getResponseCode();
      System.out.println("HTTPステータス: " + status);

      switch (status) {
        case HttpURLConnection.HTTP_OK -> {
          stream = connection.getInputStream();
          reader = new BufferedReader(new InputStreamReader(stream));
          String line;

          while ((line = reader.readLine()) != null) builder.append(line);
        }
        case 422 -> throw new ValidationException("Validation failed, or the endpoint has been spammed.");
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    } finally {
      if (reader != null) {
        try {
          reader.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      if (connection != null) connection.disconnect();
    }
    try {
      return gson.fromJson(builder.toString(), HeaderCommit[].class);
    } catch (JsonSyntaxException e) {
      throw new FailedJsonParseException(e);
    }
  }
}
