package com.github.peco2282.gitlib.commit.list;

import com.github.peco2282.gitlib.base.IGitLib;
import lombok.Value;

@Value
public class UserNode implements IGitLib {
  String name, email, date;

  public UserNode(String name, String email, String date) {
    this.name = name;
    this.email = email;
    this.date = date;
  }
}
