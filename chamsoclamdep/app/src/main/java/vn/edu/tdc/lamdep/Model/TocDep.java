package vn.edu.tdc.lamdep.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TocDep {

    public TocDep(int postsId, String postTitle, String postImage) {
        this.postsId = postsId;
        this.postTitle = postTitle;
        this.postImage = postImage;
    }

    @Override
    public String toString() {
        return "TocDep{" +
                "postsId=" + postsId +
                ", postTitle='" + postTitle + '\'' +
                ", postImage='" + postImage + '\'' +
                '}';
    }

    @SerializedName("posts_id")
@Expose
private int postsId;
@SerializedName("post_title")
@Expose
private String postTitle;
@SerializedName("post_image")
@Expose
private String postImage;

public int getPostsId() {
return postsId;
}

public void setPostsId(int postsId) {
this.postsId = postsId;
}

public String getPostTitle() {
return postTitle;
}

public void setPostTitle(String postTitle) {
this.postTitle = postTitle;
}

public String getPostImage() {
return postImage;
}

public void setPostImage(String postImage) {
this.postImage = postImage;
}

}