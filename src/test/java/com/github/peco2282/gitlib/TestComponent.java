package com.github.peco2282.gitlib;

import com.github.peco2282.gitlib.commit.patch.FileNode;
import com.github.peco2282.gitlib.exceptions.BaseException;
import com.github.peco2282.gitlib.socket.commit.CommitCompareRequest;
import com.github.peco2282.gitlib.socket.commit.CommitPatchRequest;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class TestComponent {
  @Test
  public void patch() throws BaseException {
    System.out.println(Arrays.toString(new CommitPatchRequest("peco2282", "slack.py", "1e1927d1df9e00802bc4b949e1c6216f3b9b6e19").request()));
  }

  @Test
  @SneakyThrows
  public void compare() {
      for (FileNode c : new CommitCompareRequest("peco2282", "slack.py", "fix_typing", "main").request()[0].getFiles()) {
        System.out.println(c.getPatch());
      }
  }
}
