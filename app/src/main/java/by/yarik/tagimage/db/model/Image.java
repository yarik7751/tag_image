package by.yarik.tagimage.db.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

@DatabaseTable(tableName = "images")
public class Image {

    @DatabaseField(columnName = "id", generatedId = true)
    private long id;

    @DatabaseField(columnName = "path")
    private String path;

    @DatabaseField(columnName = "tags")
    private String tags;

    public Image() {
    }

    public Image(long id, String path, String tags) {
        this.id = id;
        this.path = path;
        this.tags = tags;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public static String listToString(List<String> list) {
        String str = "";
        for(int i = 0; i < list.size(); i++) {
            str += list.get(i) + (i == list.size() - 1 ? "" : ",");
        }
        return str;
    }
    public static List<String> StringToList(String str) {
        return Arrays.asList(str.split(","));
    }
}
