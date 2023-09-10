package com.github.peco2282.gitlib.commit.list;

import com.github.peco2282.gitlib.base.IGitLib;
import com.google.gson.annotations.SerializedName;
import lombok.Value;

@Value
public class CommitNode implements IGitLib {
  UserNode author, committer;
  String message;
  TreeNode tree;
  String url;
  @SerializedName("comment_count")
  int commentCount;
  VerificationNode verification;
}
