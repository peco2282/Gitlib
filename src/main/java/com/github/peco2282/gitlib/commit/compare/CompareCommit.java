package com.github.peco2282.gitlib.commit.compare;

import com.github.peco2282.gitlib.base.ParentComponent;
import com.github.peco2282.gitlib.commit.patch.FileNode;
import com.google.gson.annotations.SerializedName;
import lombok.Value;
import org.jetbrains.annotations.NotNull;

/**
 * Compares two commits against one another.
 * <p>
 * You can compare branches in the same repository, or you can compare branches that exist in different repositories within the same repository network, including fork branches.
 * @author peco2282
 */
@Value
public class CompareCommit implements ParentComponent, Comparable<CompareCommit> {
  String url;
  @SerializedName("html_url")
  String htmlUrl;
  @SerializedName("permalink_url")
  String permalink;
  @SerializedName("diff_url")
  String diffUrl;
  @SerializedName("patch_url")
  String patchUrl;
  @SerializedName("base_commit")
  CommitBase commitBase;
  @SerializedName("merge_base_commit")
  CommitBase mergeBaseCommit;
  String status;
  @SerializedName("ahead_by")
  int aheadBy;
  @SerializedName("behind_by")
  int behindBy;
  @SerializedName("total_commits")
  int totalCommits;
  CommitBase[] commits;
  FileNode[] files;

  /**
   * Compares this object with the specified object for order.  Returns a
   * negative integer, zero, or a positive integer as this object is less
   * than, equal to, or greater than the specified object.
   *
   * <p>The implementor must ensure {@link Integer#signum
   * signum}{@code (x.compareTo(y)) == -signum(y.compareTo(x))} for
   * all {@code x} and {@code y}.  (This implies that {@code
   * x.compareTo(y)} must throw an exception if and only if {@code
   * y.compareTo(x)} throws an exception.)
   *
   * <p>The implementor must also ensure that the relation is transitive:
   * {@code (x.compareTo(y) > 0 && y.compareTo(z) > 0)} implies
   * {@code x.compareTo(z) > 0}.
   *
   * <p>Finally, the implementor must ensure that {@code
   * x.compareTo(y)==0} implies that {@code signum(x.compareTo(z))
   * == signum(y.compareTo(z))}, for all {@code z}.
   *
   * @param o the object to be compared.
   * @return a negative integer, zero, or a positive integer as this object
   * is less than, equal to, or greater than the specified object.
   * @throws NullPointerException if the specified object is null
   * @throws ClassCastException   if the specified object's type prevents it
   *                              from being compared to this object.
   * @apiNote It is strongly recommended, but <i>not</i> strictly required that
   * {@code (x.compareTo(y)==0) == (x.equals(y))}.  Generally speaking, any
   * class that implements the {@code Comparable} interface and violates
   * this condition should clearly indicate this fact.  The recommended
   * language is "Note: this class has a natural ordering that is
   * inconsistent with equals."
   */
  @Override
  public int compareTo(@NotNull CompareCommit o) {
    return Integer.compare(this.getCommits().length, o.getCommits().length);
  }
}
