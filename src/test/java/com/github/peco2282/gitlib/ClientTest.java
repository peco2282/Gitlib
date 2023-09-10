package com.github.peco2282.gitlib;

import com.github.peco2282.gitlib.commit.header.HeaderCommit;
import com.github.peco2282.gitlib.commit.list.*;
import com.github.peco2282.gitlib.exceptions.BaseException;
import com.github.peco2282.gitlib.socket.commit.CommitHeaderRequest;
import com.github.peco2282.gitlib.socket.commit.CommitListRequest;
import com.github.peco2282.gitlib.exceptions.FailedJsonParseException;
import com.github.peco2282.gitlib.socket.commit.CommitPRRequest;
import com.google.gson.Gson;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class ClientTest {
  @Test
  public void commit() {
    Gson gson = new Gson();

    System.out.println(gson.toJson(
      new UserNode("peco2282", "pecop2282@gmail.com", "a")
    ));
    System.out.println(gson.toJson(
      new CommitNode(
        new UserNode("peco2282", "pecop2282@gmail.com", "a"),
        new UserNode("peco2282", "pecop2282@gmail.com", "b"),
        "Message",
        new TreeNode("0123", "aa"),
        "url",
        0,
        new VerificationNode(
          true,
          "reason",
          "signature"
        )
      )
    ));
    String c = """
      {
            "author": {
              "name": "peco2282",
              "email": "87461070+peco2282@users.noreply.github.com",
              "date": "2023-06-25T07:54:00Z"
            },
            "committer": {
              "name": "peco2282",
              "email": "87461070+peco2282@users.noreply.github.com",
              "date": "2023-06-25T07:54:00Z"
            },
            "message": "fix accessibility.",
            "tree": {
              "sha": "748f1574fef5a81afefbcf96dcfecde38701ccf9",
              "url": "https://api.github.com/repos/peco2282/slack.py/git/trees/748f1574fef5a81afefbcf96dcfecde38701ccf9"
            },
            "url": "https://api.github.com/repos/peco2282/slack.py/git/commits/168d7a5236b9d60554f5378988b68cd9a1c472d3",
            "comment_count": 0,
            "verification": {
              "verified": true,
              "reason": "valid",
              "signature": "-----BEGIN PGP SIGNATURE-----\\n\\niQIzBAABCAAdFiEEKT0cTMaMD00kIMPHHZOW76FI7QoFAmSX8qAACgkQHZOW76FI\\n7QrQsg/7BxEArrAegiQEHqd7fvNhTbBYk1f1n6D/wTEdw9T0ty1EEd+VYffDv8eR\\n1grXcNBX+AO4fE+cHDtkTw3F3Ne6J/Ex6PfxihMids5YNT1G0/ewZgT+GJ9i3uas\\ndH335o4iZ8GSWF6xD/oaBhWB2nCCehjiEWApU/zJa3yLvg4FXseR/VBr7Z+RQkMp\\nzSgX55n7jff+QGCJefgNoZCH4eClakDcOLmLAsw/F2UG8HsVEziuZjMkmSXD9y/m\\nLmVknXBv1AnxdZoENAPkTujjroa4t+CeiHUwrHTkk314K5Vzcplzp7gv4C+IZ5O5\\njppz0Vkv7YPRhe5GMbq2BondYfHwbp9avK2Gd5IYTCBBfLxoTZ5sAC9DR8+S/O2V\\nEoSzdpJs/PsoNMNT+mN4PO3tMvv1ikQxx+4Inewe0kEwsQmOsibRNmhE7XZT1BG3\\n8YPapcgZfgFcfgwdacbZHn/z1NwSlG0BXggoZVdHoVMdaM5BzK6hIUjXj7khH/X6\\n9okpi61tY20HzSa8Xj/DoDpCv73uivOrsp0aWcpWytYWmvYT7lkD1z0hr5RyX+Qi\\ngvUm09qUhRTicA7tinbt9ISvo0FjfrYjjSxVI/zxqPJFjV+fElu9J7uW0s+zKIgN\\nARukrFW7pJeTsRXOMTbq6c6FQYtZsWVlY0Idlrg3iaGLatLeVPM=\\n=rC02\\n-----END PGP SIGNATURE-----",
              "payload": "tree 748f1574fef5a81afefbcf96dcfecde38701ccf9\\nparent bdb312506c6fdc0b11df12f0ab84e2476f52f14a\\nauthor peco2282 <87461070+peco2282@users.noreply.github.com> 1687679640 +0900\\ncommitter peco2282 <87461070+peco2282@users.noreply.github.com> 1687679640 +0900\\n\\nfix accessibility.\\n"
            }
          }
          """;
    System.out.println(gson.fromJson(c, CommitNode.class).getMessage());
  }

  @Test
  @SneakyThrows
  public void pr() {
    System.out.println(Arrays.toString(new CommitPRRequest("Oni-Men", "ExtendTheLow", "5f0d93fc37d2829bf4512e6746069309759a746c").request()));
  }

  @Test
  public void parents() {
    String c = """
      [
            {
              "sha": "bdb312506c6fdc0b11df12f0ab84e2476f52f14a",
              "url": "https://api.github.com/repos/peco2282/slack.py/commits/bdb312506c6fdc0b11df12f0ab84e2476f52f14a",
              "html_url": "https://github.com/peco2282/slack.py/commit/bdb312506c6fdc0b11df12f0ab84e2476f52f14a"
            }
          ]
          """;
    System.out.println(Arrays.toString(new Gson()
      .fromJson(
        c,
        ParentNode[].class
      ))
    );
  }

  @Test
  public void request() throws FailedJsonParseException, BaseException {
    String s = """
      2416fb67cc8cd3136a1c75b4a0799b864c47e0b6
      e1902cf0ffba68f02f6a5b151e22026530813476
      48026e16dd104e33cb4b328c9501c62706fae456
      3774a2a6c00f3263370c599194bc25554290d8fd
      a29fcabcc1efcd04dc3a86f0929413b8fc060d53
      5873e95f68b19343234624f2618e903178f6bd97
      09d94ef9329252c759a6151dd96dab65e2e3df0b
      809909faeb7e781a8ffb41f94acab59be705f38a
      b47d4c7917271c486719cf7b6b9ea361c6e7e683
      359c6165b3c52b586fcb4ae7334824fcb69c46c7
      f7c5833f2804a9cbcbab63192c1e549835d44515
      b165306678d4fa100785c541453cd3666eccaf52
      cbbedf7de971ea914c590f0bab3fd83d52d9a306
      1e1927d1df9e00802bc4b949e1c6216f3b9b6e19
      9aefd4d18d1c8c69b4f8a152f7ea60aa80d88a88
      bb3dc75eb99c12eda1f965512377d8eb93198cf3
      e54a723715bcb59dd21c3c727e981aa98b6b2b87
      6e396df10ba5c21b3ad8433b57a7922e30164a66
      1a7b2cf3a1702ae7e3f3425a2f311ba270b55aa0
      8cc1caae5e70ab336cc516a40a67885299167a76
      26dceab85b6b6a29900c32bd388448263310df83
      119de58cb76cac285502bb84fb7202c2de63b3cf
      a06378030e3b87fdc5f246821588ec016243afd9
      d43d1b01def64927cd2c9269ecec5ee36835f56b
      dede6e4d32f67e86d7e2450a846f73de46671fc2
      b9e7f15a5949a517e0c51c8702e63889bf891437
      2f82ad129c6cf97c7fff8393a3946015215f2351
      d5637c0b964d4ec799ca60c6ef0cda2dda4a8c02
      d60ba6654e3648702251b370e0f692538b606636
      caaf08326be6ce9d36a9939138245df5e039f9ee""";
//    System.out.println(Arrays.toString(new CommitRequest("peco2282", "slack.py").request()));
    ListCommit[] commits = new CommitListRequest("peco2282", "slack.py"
//      , Map.of("sha", "fix_typing")
      ).request();
    for (ListCommit c: commits) {
      System.out.println(c.getSha());
    }
  }

  @Test
  public void sha() throws BaseException {
    String s = """
      168d7a5236b9d60554f5378988b68cd9a1c472d3
      bdb312506c6fdc0b11df12f0ab84e2476f52f14a
      e584707c0ff4f600f15c43f304c00d71fd72a5fb
      1848676eb6d8a85d0ecb2e5b8960d1b75b5eeba2
      6b3918336c9feef8ce9496d24d4f6424ff557da5
      461668e7f7e7abb7cfb01ad6b6245625ff7deb6e
      6be46b40e67a55fd037f3bf2604d350acf9b6722
      0ba1891e7375e6b0c8e7735c6ea6f44afbd8ac9f
      57ca6e46858dd08d153d0edefcfe3b9852c831b1
      64e3b7cf5fa91e3b7b9ce63e97374535881f1f34
      abaa9e2f9a9ef1313c8dae5de43d394bbeee2cfd
      aaaf2ffb8b7465d4da76d30de625f1247d17dda9
      12ab0d0f654b42371210952e63378907e5210ac4
      74ad8cfdaae1c6dc64f3554373bc2ab2f322837f
      f80665accd206f41cc11dc97b4205b1b0a869596
      2416fb67cc8cd3136a1c75b4a0799b864c47e0b6
      92d675dfac7b400449c4aeb4add4d58c5be05b74
      22816f67511046ac0dd1103ad49bc4824ffcfd13
      e1902cf0ffba68f02f6a5b151e22026530813476
      48026e16dd104e33cb4b328c9501c62706fae456
      3774a2a6c00f3263370c599194bc25554290d8fd
      a29fcabcc1efcd04dc3a86f0929413b8fc060d53
      5873e95f68b19343234624f2618e903178f6bd97
      09d94ef9329252c759a6151dd96dab65e2e3df0b
      809909faeb7e781a8ffb41f94acab59be705f38a
      b47d4c7917271c486719cf7b6b9ea361c6e7e683
      359c6165b3c52b586fcb4ae7334824fcb69c46c7
      f7c5833f2804a9cbcbab63192c1e549835d44515
      b165306678d4fa100785c541453cd3666eccaf52
      cbbedf7de971ea914c590f0bab3fd83d52d9a306""";
    HeaderCommit[] commits = new CommitHeaderRequest(
      "peco2282",
      "slack.py",
      "c82d8bfaeaa17d419bc34a0bb72fce33756ffe99"
    ).request();
    for (HeaderCommit commit: commits) {
      System.out.println(commit);
    }
  }

  @Test
  public void toJson() {
    Gson gson = new Gson();

    System.out.println(gson.toJson(
      new UserNode("peco2282", "pecop2282@gmail.com", "a")
    ));
    System.out.println(gson.toJson(
      new CommitNode(
        new UserNode("peco2282", "pecop2282@gmail.com", "a"),
        new UserNode("peco2282", "pecop2282@gmail.com", "b"),
        "Message",
        new TreeNode("0123", "aa"),
        "url",
        0,
        new VerificationNode(
          true,
          "reason",
          "signature"
        )
      )
    ));
  }
  @Test
  public void all() {
    String s = """
      [{
          "sha": "168d7a5236b9d60554f5378988b68cd9a1c472d3",
          "node_id": "C_kwDOIJ4zLNoAKDE2OGQ3YTUyMzZiOWQ2MDU1NGY1Mzc4OTg4YjY4Y2Q5YTFjNDcyZDM",
          "commit": {
            "author": {
              "name": "peco2282",
              "email": "87461070+peco2282@users.noreply.github.com",
              "date": "2023-06-25T07:54:00Z"
            },
            "committer": {
              "name": "peco2282",
              "email": "87461070+peco2282@users.noreply.github.com",
              "date": "2023-06-25T07:54:00Z"
            },
            "message": "fix accessibility.",
            "tree": {
              "sha": "748f1574fef5a81afefbcf96dcfecde38701ccf9",
              "url": "https://api.github.com/repos/peco2282/slack.py/git/trees/748f1574fef5a81afefbcf96dcfecde38701ccf9"
            },
            "url": "https://api.github.com/repos/peco2282/slack.py/git/commits/168d7a5236b9d60554f5378988b68cd9a1c472d3",
            "comment_count": 0,
            "verification": {
              "verified": true,
              "reason": "valid",
              "signature": "-----BEGIN PGP SIGNATURE-----\\n\\niQIzBAABCAAdFiEEKT0cTMaMD00kIMPHHZOW76FI7QoFAmSX8qAACgkQHZOW76FI\\n7QrQsg/7BxEArrAegiQEHqd7fvNhTbBYk1f1n6D/wTEdw9T0ty1EEd+VYffDv8eR\\n1grXcNBX+AO4fE+cHDtkTw3F3Ne6J/Ex6PfxihMids5YNT1G0/ewZgT+GJ9i3uas\\ndH335o4iZ8GSWF6xD/oaBhWB2nCCehjiEWApU/zJa3yLvg4FXseR/VBr7Z+RQkMp\\nzSgX55n7jff+QGCJefgNoZCH4eClakDcOLmLAsw/F2UG8HsVEziuZjMkmSXD9y/m\\nLmVknXBv1AnxdZoENAPkTujjroa4t+CeiHUwrHTkk314K5Vzcplzp7gv4C+IZ5O5\\njppz0Vkv7YPRhe5GMbq2BondYfHwbp9avK2Gd5IYTCBBfLxoTZ5sAC9DR8+S/O2V\\nEoSzdpJs/PsoNMNT+mN4PO3tMvv1ikQxx+4Inewe0kEwsQmOsibRNmhE7XZT1BG3\\n8YPapcgZfgFcfgwdacbZHn/z1NwSlG0BXggoZVdHoVMdaM5BzK6hIUjXj7khH/X6\\n9okpi61tY20HzSa8Xj/DoDpCv73uivOrsp0aWcpWytYWmvYT7lkD1z0hr5RyX+Qi\\ngvUm09qUhRTicA7tinbt9ISvo0FjfrYjjSxVI/zxqPJFjV+fElu9J7uW0s+zKIgN\\nARukrFW7pJeTsRXOMTbq6c6FQYtZsWVlY0Idlrg3iaGLatLeVPM=\\n=rC02\\n-----END PGP SIGNATURE-----",
              "payload": "tree 748f1574fef5a81afefbcf96dcfecde38701ccf9\\nparent bdb312506c6fdc0b11df12f0ab84e2476f52f14a\\nauthor peco2282 <87461070+peco2282@users.noreply.github.com> 1687679640 +0900\\ncommitter peco2282 <87461070+peco2282@users.noreply.github.com> 1687679640 +0900\\n\\nfix accessibility.\\n"
            }
          },
          "url": "https://api.github.com/repos/peco2282/slack.py/commits/168d7a5236b9d60554f5378988b68cd9a1c472d3",
          "html_url": "https://github.com/peco2282/slack.py/commit/168d7a5236b9d60554f5378988b68cd9a1c472d3",
          "comments_url": "https://api.github.com/repos/peco2282/slack.py/commits/168d7a5236b9d60554f5378988b68cd9a1c472d3/comments",
          "author": {
            "login": "peco2282",
            "id": 87461070,
            "node_id": "MDQ6VXNlcjg3NDYxMDcw",
            "avatar_url": "https://avatars.githubusercontent.com/u/87461070?v=4",
            "gravatar_id": "",
            "url": "https://api.github.com/users/peco2282",
            "html_url": "https://github.com/peco2282",
            "followers_url": "https://api.github.com/users/peco2282/followers",
            "following_url": "https://api.github.com/users/peco2282/following{/other_user}",
            "gists_url": "https://api.github.com/users/peco2282/gists{/gist_id}",
            "starred_url": "https://api.github.com/users/peco2282/starred{/owner}{/repo}",
            "subscriptions_url": "https://api.github.com/users/peco2282/subscriptions",
            "organizations_url": "https://api.github.com/users/peco2282/orgs",
            "repos_url": "https://api.github.com/users/peco2282/repos",
            "events_url": "https://api.github.com/users/peco2282/events{/privacy}",
            "received_events_url": "https://api.github.com/users/peco2282/received_events",
            "type": "User",
            "site_admin": false
          },
          "committer": {
            "login": "peco2282",
            "id": 87461070,
            "node_id": "MDQ6VXNlcjg3NDYxMDcw",
            "avatar_url": "https://avatars.githubusercontent.com/u/87461070?v=4",
            "gravatar_id": "",
            "url": "https://api.github.com/users/peco2282",
            "html_url": "https://github.com/peco2282",
            "followers_url": "https://api.github.com/users/peco2282/followers",
            "following_url": "https://api.github.com/users/peco2282/following{/other_user}",
            "gists_url": "https://api.github.com/users/peco2282/gists{/gist_id}",
            "starred_url": "https://api.github.com/users/peco2282/starred{/owner}{/repo}",
            "subscriptions_url": "https://api.github.com/users/peco2282/subscriptions",
            "organizations_url": "https://api.github.com/users/peco2282/orgs",
            "repos_url": "https://api.github.com/users/peco2282/repos",
            "events_url": "https://api.github.com/users/peco2282/events{/privacy}",
            "received_events_url": "https://api.github.com/users/peco2282/received_events",
            "type": "User",
            "site_admin": false
          },
          "parents": [
            {
              "sha": "bdb312506c6fdc0b11df12f0ab84e2476f52f14a",
              "url": "https://api.github.com/repos/peco2282/slack.py/commits/bdb312506c6fdc0b11df12f0ab84e2476f52f14a",
              "html_url": "https://github.com/peco2282/slack.py/commit/bdb312506c6fdc0b11df12f0ab84e2476f52f14a"
            }
          ]
        },
        {
          "sha": "bdb312506c6fdc0b11df12f0ab84e2476f52f14a",
          "node_id": "C_kwDOIJ4zLNoAKGJkYjMxMjUwNmM2ZmRjMGIxMWRmMTJmMGFiODRlMjQ3NmY1MmYxNGE",
          "commit": {
            "author": {
              "name": "peco2282",
              "email": "87461070+peco2282@users.noreply.github.com",
              "date": "2023-06-11T07:48:42Z"
            },
            "committer": {
              "name": "peco2282",
              "email": "87461070+peco2282@users.noreply.github.com",
              "date": "2023-06-11T07:48:42Z"
            },
            "message": "add `get` method",
            "tree": {
              "sha": "ed93d0edb523bc76ac524084e9d8c4c9e03da28b",
              "url": "https://api.github.com/repos/peco2282/slack.py/git/trees/ed93d0edb523bc76ac524084e9d8c4c9e03da28b"
            },
            "url": "https://api.github.com/repos/peco2282/slack.py/git/commits/bdb312506c6fdc0b11df12f0ab84e2476f52f14a",
            "comment_count": 0,
            "verification": {
              "verified": true,
              "reason": "valid",
              "signature": "-----BEGIN PGP SIGNATURE-----\\n\\niQIzBAABCAAdFiEEKT0cTMaMD00kIMPHHZOW76FI7QoFAmSFfGMACgkQHZOW76FI\\n7QqaeBAAhbCaSpsk0LbEdyfPDYSZUuFeWUDxWT13LWZctWj1/qSRKokPc+wemdXC\\nyQS33tjYW79RlXL4hZ5cUGtwzWHzYBDo9/pyLja4qZ3JlOQx1mNunGi7fbvYyRyU\\n7T95zDP0pV7iyo4yhYjLjjpWcj1rBKfyjzAaa677QiJaLu7ZodNQzJWo7gYL8cxr\\nzDpqqc5IfYNMFKKPWGadjBIFxIXNl2bQgQ13jrAfZyUQXFwWRKl9G0LDNqVaw/oa\\nZcH7+Bh9KIRcCDvqdUVep1Y0CcpOihUQsdRExwdR7tg/Ynn4ICSpu9Ra0ZSjtFiV\\nrjt3PKVL6LqrB3SFRfqyuyuVAD4jx/u+ECK/1aQnWXMP2xgCJ+yMbVRXAopfEwf7\\niytpfzGWqEwgjMDCUnw0id8mTnfH/mnbcmT8deFEJ0kAlZ9JP7MiWJzhv3A4tgwe\\nosRM0KEQoB2ETxpV0rL1YfJEQbJygn/UDBw++dG/tjPYCUKbrMnV1l/PEEz7Y/C8\\nfouqlCvcd445l16ORcnhXaMNx9lFwZ+FxLi5QYGKH4wwH6yxe6AlY0/d3422EDxK\\n5+ch+bS7P45caGsng9dKmrcJhZw8k5zcB9Y4uFE9dXeKNqJRiLFSzKbt+9HLyjxX\\n55dOyp+D+Wtn14xq7pztmkfEztaX56o+Rg4emE4RTAdCs87bLvo=\\n=P01V\\n-----END PGP SIGNATURE-----",
              "payload": "tree ed93d0edb523bc76ac524084e9d8c4c9e03da28b\\nparent e584707c0ff4f600f15c43f304c00d71fd72a5fb\\nauthor peco2282 <87461070+peco2282@users.noreply.github.com> 1686469722 +0900\\ncommitter peco2282 <87461070+peco2282@users.noreply.github.com> 1686469722 +0900\\n\\nadd `get` method\\n"
            }
          },
          "url": "https://api.github.com/repos/peco2282/slack.py/commits/bdb312506c6fdc0b11df12f0ab84e2476f52f14a",
          "html_url": "https://github.com/peco2282/slack.py/commit/bdb312506c6fdc0b11df12f0ab84e2476f52f14a",
          "comments_url": "https://api.github.com/repos/peco2282/slack.py/commits/bdb312506c6fdc0b11df12f0ab84e2476f52f14a/comments",
          "author": {
            "login": "peco2282",
            "id": 87461070,
            "node_id": "MDQ6VXNlcjg3NDYxMDcw",
            "avatar_url": "https://avatars.githubusercontent.com/u/87461070?v=4",
            "gravatar_id": "",
            "url": "https://api.github.com/users/peco2282",
            "html_url": "https://github.com/peco2282",
            "followers_url": "https://api.github.com/users/peco2282/followers",
            "following_url": "https://api.github.com/users/peco2282/following{/other_user}",
            "gists_url": "https://api.github.com/users/peco2282/gists{/gist_id}",
            "starred_url": "https://api.github.com/users/peco2282/starred{/owner}{/repo}",
            "subscriptions_url": "https://api.github.com/users/peco2282/subscriptions",
            "organizations_url": "https://api.github.com/users/peco2282/orgs",
            "repos_url": "https://api.github.com/users/peco2282/repos",
            "events_url": "https://api.github.com/users/peco2282/events{/privacy}",
            "received_events_url": "https://api.github.com/users/peco2282/received_events",
            "type": "User",
            "site_admin": false
          },
          "committer": {
            "login": "peco2282",
            "id": 87461070,
            "node_id": "MDQ6VXNlcjg3NDYxMDcw",
            "avatar_url": "https://avatars.githubusercontent.com/u/87461070?v=4",
            "gravatar_id": "",
            "url": "https://api.github.com/users/peco2282",
            "html_url": "https://github.com/peco2282",
            "followers_url": "https://api.github.com/users/peco2282/followers",
            "following_url": "https://api.github.com/users/peco2282/following{/other_user}",
            "gists_url": "https://api.github.com/users/peco2282/gists{/gist_id}",
            "starred_url": "https://api.github.com/users/peco2282/starred{/owner}{/repo}",
            "subscriptions_url": "https://api.github.com/users/peco2282/subscriptions",
            "organizations_url": "https://api.github.com/users/peco2282/orgs",
            "repos_url": "https://api.github.com/users/peco2282/repos",
            "events_url": "https://api.github.com/users/peco2282/events{/privacy}",
            "received_events_url": "https://api.github.com/users/peco2282/received_events",
            "type": "User",
            "site_admin": false
          },
          "parents": [
            {
              "sha": "e584707c0ff4f600f15c43f304c00d71fd72a5fb",
              "url": "https://api.github.com/repos/peco2282/slack.py/commits/e584707c0ff4f600f15c43f304c00d71fd72a5fb",
              "html_url": "https://github.com/peco2282/slack.py/commit/e584707c0ff4f600f15c43f304c00d71fd72a5fb"
            }
          ]
        }]""";
    Gson gson = new Gson();
    System.out.println(Arrays.toString(gson.fromJson(s, ListCommit[].class)));
  }
}
