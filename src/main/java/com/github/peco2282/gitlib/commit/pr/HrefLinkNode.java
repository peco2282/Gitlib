package com.github.peco2282.gitlib.commit.pr;

import com.google.gson.annotations.SerializedName;
import lombok.Value;

@Value
public class HrefLinkNode {
  HrefNode self, html, issue, comments;
  @SerializedName("review_comments")
  HrefNode reviewComments;
  @SerializedName("review_comment")
  HrefNode reviewComment;
  HrefNode commitsm, statuses;

  @Value
  public static class HrefNode {
    String href;
  }
}
