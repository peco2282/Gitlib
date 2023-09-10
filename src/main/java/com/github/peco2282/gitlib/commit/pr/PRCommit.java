package com.github.peco2282.gitlib.commit.pr;

import com.github.peco2282.gitlib.base.ParentComponent;
import com.github.peco2282.gitlib.commit.list.User;
import com.google.gson.annotations.SerializedName;
import lombok.Value;

@Value
public class PRCommit implements ParentComponent {
  String url;
  int id;
  @SerializedName("node_id")
  String nodeId;
  @SerializedName("html_url")
  String htmlUrl;
  @SerializedName("diff_url")
  String diffUrl;
  @SerializedName("patch_url")
  String patchUrl;
  @SerializedName("issue_url")
  String issueUrl;

  int number;
  String state;
  boolean locked;
  String title;

  User user;
  String body;
  @SerializedName("created_at")
  String createdAt;
  @SerializedName("updated_at")
  String updatedAt;
  @SerializedName("closed_at")
  String closedAt;
  @SerializedName("merged_at")
  String mergedAt;
  @SerializedName("merge_commit_sha")
  String mergeCommitSha;
  String assignee;
  String[] assignees;
  @SerializedName("requested_reviewers")
  Object[] requestedReviewers;
  @SerializedName("requested_teams")
  Object[] requestedTeams;
  Object[] labels;
  Object milestone;
  boolean draft;
  @SerializedName("commits_url")
  String commitsUrl;
  @SerializedName("review_comments_url")
  String reviewCommentsUrl;
  @SerializedName("review_comment_url")
  String reviewCommentUrl;
  @SerializedName("comments_url")
  String commentsUrl;
  @SerializedName("statuses_url")
  String statusesUrl;
  HeadNode head;
  BaseNode base;
  @SerializedName("_links")
  HrefLinkNode link;
  @SerializedName("author_association")
  String authorAssociation;
  @SerializedName("auto_merge")
  String autoMerge;
  @SerializedName("active_lock_reason")
  String activeLockReason;
}
