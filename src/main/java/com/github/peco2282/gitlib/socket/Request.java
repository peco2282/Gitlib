package com.github.peco2282.gitlib.socket;

import com.github.peco2282.gitlib.base.ParentComponent;
import com.github.peco2282.gitlib.exceptions.BaseException;
import com.github.peco2282.gitlib.exceptions.InvalidQueryException;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public interface Request<T extends ParentComponent> {
  @SuppressWarnings("unused")
  String GET = "GET",
    POST = "POST",
    HEAD = "HEAD",
    OPTIONS = "OPTIONS",
    PUT = "PUT",
    DELETE = "DELETE",
    TRACE = "TRACE";
  String BASE = "https://api.github.com/";


  @SuppressWarnings("UnusedReturnValue")
  T[] request() throws BaseException;

  default void checkQuery(@NotNull Map<String, ?> map, @NotNull List<String> allow) throws InvalidQueryException {
    List<String> invalid = new ArrayList<>();
    map.forEach(
      (k, v) -> {
        if (!(allow.contains(k))) invalid.add(k);
      }
    );
    if (invalid.size() != 0) throw new InvalidQueryException("Query `" + invalid + "` is invalid. Allows " + Arrays.toString(allow.toArray()) + ".");
  }
  default String generateQuery(@NotNull Map<String, String> map) {
    if (map.isEmpty()) return "";
    List<String> q = new ArrayList<>();
    map.forEach((k, v) -> q.add(k + "=" + v));
    return "?" + String.join("&", q);
  }
}
