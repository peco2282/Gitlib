package com.github.peco2282.gitlib.commit.pr;

import com.github.peco2282.gitlib.base.IGitLib;
import com.github.peco2282.gitlib.commit.list.User;
import lombok.Value;

@Value
public class BaseNode implements IGitLib {
  String label;
  String ref;
  String sha;
  User user;
  HeadNode.RepositoryNode repo;
}
