package com.github.peco2282.gitlib.commit.compare;

import com.github.peco2282.gitlib.commit.list.ParentNode;
import com.github.peco2282.gitlib.commit.list.User;
import com.github.peco2282.gitlib.commit.list.CommitNode;
import com.google.gson.annotations.SerializedName;
import lombok.Value;

@Value
public class CommitBase {
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
