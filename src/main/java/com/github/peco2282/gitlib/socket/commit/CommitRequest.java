package com.github.peco2282.gitlib.socket.commit;

import com.github.peco2282.gitlib.base.ParentComponent;
import com.github.peco2282.gitlib.socket.Request;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

public abstract class CommitRequest<T extends ParentComponent> implements Request<T> {
  protected String owner;
  protected String repo;
  protected Map<String, String> maps = Map.of();

  @Setter
  @Getter
  boolean useLog;

  public Map<String, String> addQuery(Map<String, String> maps) {
    this.maps.putAll(maps);
    return this.maps;
  }
}
