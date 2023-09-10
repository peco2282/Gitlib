package com.github.peco2282.gitlib.socket.commit;

import com.github.peco2282.gitlib.commit.list.ListCommit;
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
public class CommitListRequest extends CommitRequest<ListCommit> {
  public CommitListRequest(String owner, String repo) throws InvalidQueryException {
    this(owner, repo, Map.of());
  }

  public CommitListRequest(String owner, String repo, Map<String, String> maps) throws InvalidQueryException {
    List<String> valid = List.of("sha", "path", "author", "committer", "since", "until", "per_page", "page");
    checkQuery(maps, valid);
    this.owner = owner;
    this.repo = repo;
    this.maps = maps;
  }

  @Override
  public ListCommit[] request() throws BaseException {
    //    HttpURLConnection connection = null;
    InputStream stream;
    BufferedReader reader = null;
    Gson gson = new Gson();
    StringBuilder builder = new StringBuilder();
    HttpURLConnection connection = null;
    String query = generateQuery(maps);

    try {
      URL url = new URL(Request.BASE + String.format("repos/%s/%s/commits", owner, repo) + query);
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
        case HttpURLConnection.HTTP_BAD_REQUEST -> throw new BadRequestException("");
        case HttpURLConnection.HTTP_NOT_FOUND ->
          throw new NotFoundException("Repository `" + repo + "` at " + owner + " is Not Found.(404)");
        case HttpURLConnection.HTTP_CONFLICT -> throw new ConflictException("");
        case HttpURLConnection.HTTP_INTERNAL_ERROR -> throw new InternalException();
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
      return gson.fromJson(builder.toString(), ListCommit[].class);
    } catch (JsonSyntaxException e) {
      throw new FailedJsonParseException(e);
    }
  }
}
