package com.github.peco2282.gitlib.commit.list;

import com.github.peco2282.gitlib.base.ParentComponent;
import com.google.gson.annotations.SerializedName;
import lombok.Value;

@Value
public class ListCommit implements ParentComponent {
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
}
