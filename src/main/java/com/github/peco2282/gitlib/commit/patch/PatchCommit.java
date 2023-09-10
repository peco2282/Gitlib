package com.github.peco2282.gitlib.commit.patch;

import com.github.peco2282.gitlib.base.ParentComponent;
import com.github.peco2282.gitlib.commit.list.ParentNode;
import com.github.peco2282.gitlib.commit.list.User;
import com.github.peco2282.gitlib.commit.list.CommitNode;
import com.google.gson.annotations.SerializedName;
import lombok.Value;

@Value
public class PatchCommit implements ParentComponent {

  String sha;
  @SerializedName("node_id")
  String nodeId;
  CommitNode commit;

  String url;
  @SerializedName("html_url")
  String htmlUrl;
  @SerializedName("comments_url")
  String commentsUrl;
  User author, committer;
  ParentNode[] parents;
  Stats stats;
  FileNode[] files;

  @Value
  public static class Stats {
    int total, additions, deletions;
  }
}
