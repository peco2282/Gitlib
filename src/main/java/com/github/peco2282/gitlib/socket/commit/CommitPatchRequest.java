package com.github.peco2282.gitlib.socket.commit;

import com.github.peco2282.gitlib.commit.patch.PatchCommit;
import com.github.peco2282.gitlib.exceptions.*;
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
public class CommitPatchRequest extends CommitRequest<PatchCommit> {
  private final String commitSha;

  public CommitPatchRequest(String owner, String repo, String commitSha) throws InvalidQueryException {
    this(owner, repo, commitSha, Map.of());
  }

  public CommitPatchRequest(String owner, String repo, String commitSha, Map<String, String> maps) throws InvalidQueryException {
    List<String> valid = List.of("page", "per_page");
    checkQuery(maps, valid);
    this.owner = owner;
    this.repo = repo;
    this.commitSha = commitSha;
    this.maps = maps;
  }

  @Override
  public PatchCommit[] request() throws BaseException {
    //    HttpURLConnection connection = null;
    InputStream stream;
    BufferedReader reader = null;
    Gson gson = new Gson();
    StringBuilder builder = new StringBuilder();
    HttpURLConnection connection = null;


    try {
      URL url = new URL(Request.BASE + String.format("repos/%s/%s/commits/%s", owner, repo, commitSha) + generateQuery(maps));
      connection = (HttpURLConnection) url.openConnection();
      connection.setRequestMethod(Request.GET);
      connection.connect();

      int status = connection.getResponseCode();
      if (useLog) log.info("HTTPステータス: " + status);

      switch (status) {
        case HttpURLConnection.HTTP_OK -> {
          stream = connection.getInputStream();
          reader = new BufferedReader(new InputStreamReader(stream));
          String line;

          while ((line = reader.readLine()) != null) builder.append(line);
        }
        case HttpURLConnection.HTTP_NOT_FOUND ->
          throw new NotFoundException("Repository `" + repo + "` at " + owner + " is Not Found.(404)");
        case 422 -> throw new ValidationException("Validation failed, or the endpoint has been spammed.");
        case HttpURLConnection.HTTP_INTERNAL_ERROR -> throw new InternalException();
        case HttpURLConnection.HTTP_UNAVAILABLE -> throw new UnavailableException("Service unavailable");
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
      PatchCommit c = gson.fromJson(builder.toString(), PatchCommit.class);
      return new PatchCommit[]{c};
    } catch (JsonSyntaxException e) {
      throw new FailedJsonParseException(e);
    }
  }
}
