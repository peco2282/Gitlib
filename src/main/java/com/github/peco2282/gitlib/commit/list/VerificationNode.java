package com.github.peco2282.gitlib.commit.list;

import com.github.peco2282.gitlib.base.IGitLib;
import lombok.Value;

@Value
public class VerificationNode implements IGitLib {
  boolean velified;
  String reason, signature;

}
