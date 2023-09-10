package com.github.peco2282.gitlib.commit.list;

import com.github.peco2282.gitlib.base.IGitLib;
import com.google.gson.annotations.SerializedName;
import lombok.Value;

@Value
public class User implements IGitLib {
  String login;
  int id;
  @SerializedName("node_id")
  String nodeId;
  @SerializedName("avatar_url")
  String avatarUrl;
  @SerializedName("gravatar_id")
  String gravatarId;
  String url;
  @SerializedName("html_url")
  String htmlUrl;
  @SerializedName("followers_url")
  String followersUrl;
  @SerializedName("following_url")
  String followingUrl;
  @SerializedName("gists_url")
  String gistUrl;
  @SerializedName("starred_url")
  String starredUrl;
  @SerializedName("subscriptions_url")
  String subscriptionsUrl;
  @SerializedName("organizations_url")
  String organaizationUrl;
  @SerializedName("repos_url")
  String reposUrl;
  @SerializedName("events_url")
  String eventsUrl;
  @SerializedName("received_events_url")
  String receiptEventsUrl;
  String type;
  @SerializedName("site_admin")
  boolean siteAdmin;

}
