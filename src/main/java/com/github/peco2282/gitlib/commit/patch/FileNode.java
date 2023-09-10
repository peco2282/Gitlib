package com.github.peco2282.gitlib.commit.patch;

import com.github.peco2282.gitlib.base.IGitLib;
import com.google.gson.annotations.SerializedName;
import lombok.Value;

@Value
public class FileNode implements IGitLib {
  String sha, filename, status;
  int additions, deletions, changes;
  @SerializedName("blob_url")
  String blobUrl;
  @SerializedName("raw_url")
  String rawUrl;
  @SerializedName("contents_url")
  String contentsUrl;
  String patch;
}
