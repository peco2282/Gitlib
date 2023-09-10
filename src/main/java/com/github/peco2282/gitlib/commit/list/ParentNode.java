package com.github.peco2282.gitlib.commit.list;

import com.github.peco2282.gitlib.base.IGitLib;
import com.google.gson.annotations.SerializedName;
import lombok.Value;

@Value
public class ParentNode implements IGitLib {
  String sha, url;
  @SerializedName("html_url")
  String htmlUrl;
}
