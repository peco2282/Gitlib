package com.github.peco2282.gitlib.commit.header;

import com.github.peco2282.gitlib.base.IGitLib;
import com.github.peco2282.gitlib.base.ParentComponent;
import com.google.gson.annotations.SerializedName;
import lombok.Value;

@Value
public class HeaderCommit implements ParentComponent {
  String name;
  HeaderCommitNode commit;
  @SerializedName("protected")
  boolean protect;

  @Value
  public static class HeaderCommitNode implements IGitLib {
    String sha;
    String url;
  }
}
