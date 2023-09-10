package com.github.peco2282.gitlib.socket.commit;

import com.github.peco2282.gitlib.commit.compare.CompareCommit;
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
public class CommitCompareRequest extends CommitRequest<CompareCommit> {
  private final String before, after;

  /**
   * Compares two commits against one another. You can compare branches in the same repository, or you can compare branches that exist in different repositories within the same repository network, including fork branches. For more information about how to view a repository's network, {@see <a href="https://docs.github.com/rest/guides/using-pagination-in-the-rest-api">Understanding connections between repositories.</a>}
   * <p>
   * This endpoint is equivalent to running the git log BASE..HEAD command, but it returns commits in a different order. The git log BASE..HEAD command returns commits in reverse chronological order, whereas the API returns commits in chronological order. You can pass the appropriate media type to fetch diff and patch formats.
   * <p>
   * The API response includes details about the files that were changed between the two commits. This includes the status of the change (if a file was added, removed, modified, or renamed), and details of the change itself. For example, files with a renamed status have a previous_filename field showing the previous filename of the file, and files with a modified status have a patch field showing the changes made to the file.
   *
   * @param owner  The account owner of the repository. The name is not case-sensitive.
   * @param repo   The name of the repository without the .git extension. The name is not case-sensitive.
   * @param before The base branch. (Both branch name and commit hash are allowed.)
   * @param after  head branch. (Both branch name and commit hash are allowed.)
   * @throws InvalidQueryException Contains not allowed query key.
   */
  public CommitCompareRequest(String owner, String repo, String before, String after) throws InvalidQueryException {
    this(owner, repo, before, after, Map.of());
  }

  /**
   * @param owner  The account owner of the repository. The name is not case-sensitive.
   * @param repo   The name of the repository without the .git extension. The name is not case-sensitive.
   * @param before The base branch. (Both branch name and commit hash are allowed.)
   * @param after  Head branch. (Both branch name and commit hash are allowed.)
   * @param maps   When create this instancewithout any paging parameter (per_page or page),
   *               the returned list is limited to 250 commits, and the last commit in the list is the most recent of the entire comparison.
   * @throws InvalidQueryException Contains not allowed query key.
   */
  public CommitCompareRequest(String owner, String repo, String before, String after, Map<String, String> maps) throws InvalidQueryException {
    List<String> valid = List.of("page", "per_page");
    checkQuery(maps, valid);
    this.owner = owner;
    this.repo = repo;
    this.before = before;
    this.after = after;
    this.maps = maps;
  }

  /**
   *
   * @return
   * @throws NotFoundException Invalid url. May hash or branch name not exists.
   * @throws ValidationException
   * @throws InternalException
   * @throws UnavailableException
   */
  @Override
  public CompareCommit[] request() throws NotFoundException, ValidationException, InternalException, UnavailableException {
    //    HttpURLConnection connection = null;
    InputStream stream;
    BufferedReader reader = null;
    Gson gson = new Gson();
    StringBuilder builder = new StringBuilder();
    HttpURLConnection connection = null;


    try {
      URL url = new URL(BASE + String.format("repos/%s/%s/compare/%s...%s", owner, repo, before, after) + generateQuery(maps));
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
      CompareCommit c = gson.fromJson(builder.toString(), CompareCommit.class);
      return new CompareCommit[]{c};
    } catch (JsonSyntaxException e) {
      throw new FailedJsonParseException(e);
    }
  }
}
