package com.github.peco2282.gitlib.socket.commit;

import com.github.peco2282.gitlib.commit.pr.PRCommit;
import com.github.peco2282.gitlib.exceptions.FailedJsonParseException;
import com.github.peco2282.gitlib.exceptions.InvalidQueryException;
import com.github.peco2282.gitlib.socket.Request;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import lombok.extern.java.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

@Log
public class CommitPRRequest extends CommitRequest<PRCommit> {
  private final String commitSha;

  public CommitPRRequest(String owner, String repo, String commitSha) throws InvalidQueryException {
    this(owner, repo, commitSha, Map.of());
  }

  public CommitPRRequest(String owner, String repo, String commitSha, Map<String, String> maps) throws InvalidQueryException {
    List<String> valid = List.of("per_page", "page");
    checkQuery(maps, valid);
    this.owner = owner;
    this.repo = repo;
    this.commitSha = commitSha;
    this.maps = maps;
  }

  @Override
  public PRCommit[] request() {
    //    HttpURLConnection connection = null;
    InputStream stream;
    BufferedReader reader = null;
    Gson gson = new Gson();
    StringBuilder builder = new StringBuilder();
    HttpURLConnection connection = null;

    try {
      URL url = new URL(Request.BASE + String.format("repos/%s/%s/commits/%s/pulls", owner, repo, commitSha) + generateQuery(maps));
      connection = (HttpURLConnection) url.openConnection();
      connection.setRequestMethod(Request.GET);
      connection.connect();

      int status = connection.getResponseCode();
      if (useLog) log.info("HTTPステータス: " + status);

      if (status == HttpURLConnection.HTTP_OK) {
        stream = connection.getInputStream();
        reader = new BufferedReader(new InputStreamReader(stream));
        String line;

        while ((line = reader.readLine()) != null) builder.append(line);
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
      return gson.fromJson(builder.toString(), PRCommit[].class);
    } catch (JsonSyntaxException e) {
      throw new FailedJsonParseException(e);
    }
  }
}
